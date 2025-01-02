package 链表;

import java.util.HashMap;
import java.util.Map;

public class _138_随机链表的复制 {
    public static void main(String[] args) {
        // 输入：head = [[1,1],[2,1]]
        // 输出：[[1,1],[2,1]]
        Node node2 = new Node(2);
        Node node1 = new Node(1);
        node1.next = node2;
        node1.random = node2;
        node2.random = node2;
        Node newHead = copyRandomList(node1);
        System.out.println(toString(newHead));
    }

    /**
     * 哈希表
     * 利用哈希表的查询特点，考虑构建 原链表节点 和 新链表对应节点 的键值对映射关系，
     * 再遍历构建新链表各节点的 next 和 random 引用指向即可
     *
     * 时间复杂度 O(N) ： 遍历链表两次，使用 O(N) 时间。
     * 空间复杂度 O(N) ： 节点引用变量使用 O(N) 大小的额外空间
     */
    private static Node copyRandomList(Node head) {
        // 1. 若头节点 head 为空节点，直接返回 null 。
        if(head == null) return null;

        // 2. 初始化： 哈希表 dic ， 节点 cur 指向头节点。
        Node cur = head;
        Map<Node, Node> map = new HashMap<>();

        // 3. 复制各节点，并建立 “原节点 -> 新节点” 的 Map 映射
        while(cur != null) {
            map.put(cur, new Node(cur.val));
            cur = cur.next;
        }

        cur = head;
        // 4. 构建新链表的 next 和 random 指向
        while(cur != null) {
            map.get(cur).next = map.get(cur.next);
            map.get(cur).random = map.get(cur.random);
            cur = cur.next;
        }

        // 5. 返回新链表的头节点
        return map.get(head);
    }

    /**
     * 拼接 + 拆分
     * 构建 原节点 1 -> 新节点 1 -> 原节点 2 -> 新节点 2 -> …… 的拼接链表，
     * 如此便可在访问原节点的 random 指向节点的同时找到新对应新节点的 random 指向节点
     *
     * 时间复杂度 O(N) ： 三轮遍历链表，使用 O(N) 时间。
     * 空间复杂度 O(1) ： 节点引用变量使用常数大小的额外空间
     */
    private static Node copyRandomList2(Node head) {
        if(head == null) return null;
        Node cur = head;

        // 1. 复制各节点，并构建拼接链表
        while(cur != null) {
            Node tmp = new Node(cur.val);
            tmp.next = cur.next;
            cur.next = tmp;
            cur = tmp.next;
        }

        // 2. 构建各新节点的 random 指向
        cur = head;
        while(cur != null) {
            if(cur.random != null)
                cur.next.random = cur.random.next;
            cur = cur.next.next;
        }

        // 3. 拆分两链表
        cur = head.next;
        Node pre = head, res = head.next;
        while(cur.next != null) {
            pre.next = pre.next.next;
            cur.next = cur.next.next;
            pre = pre.next;
            cur = cur.next;
        }

        pre.next = null; // 单独处理原链表尾节点
        return res;      // 返回新链表头节点
    }

    public static String toString(Node head) {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        while (head != null) {
            sb.append(head.val);
            if (head.next != null) {
                sb.append(",");
            }

            head = head.next;
        }
        sb.append("]");
        return sb.toString();
    }


    static class Node {
        int val;
        Node next, random;
        public Node(int val) {
            this.val = val;
            this.next = null;
            this.random = null;
        }
    }
}
