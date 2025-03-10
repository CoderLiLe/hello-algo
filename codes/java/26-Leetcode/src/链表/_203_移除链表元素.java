package 链表;

import tools.Asserts;

public class _203_移除链表元素 {
    public static void main(String[] args) {
        // 1,2,6,3,4,5,6
        ListNode node7 = new ListNode(6, null);
        ListNode node6 = new ListNode(5, node7);
        ListNode node5 = new ListNode(4, node6);
        ListNode node4 = new ListNode(3, node5);
        ListNode node3 = new ListNode(6, node4);
        ListNode node2 = new ListNode(2, node3);
        ListNode node1 = new ListNode(1, node2);
        ListNode newHead = removeElements4(node1, 6);
        Asserts.test(LinkedListUtil.toString(newHead).equals("[1,2,3,4,5]"));
    }

    static public ListNode removeElements(ListNode head, int val) {
        if (head == null) return null;

        // 新链表的头节点
        ListNode newHead = null;
        // 新链表的尾节点
        ListNode newTail = null;

        while (head != null) {
            if (head.val != val) {
                if (newTail == null) {
                    newHead = head;
                    newTail = head;
                } else {
                    newTail.next = head;
                    newTail = head;
                }
            }
            head = head.next;
        }
        if (newTail != null) {
            // 节点尾部的 next 要清空
            newTail.next = null;
        }

        return newHead;
    }

    static public ListNode removeElements2(ListNode head, int val) {
        // 删除头节点
        while (head != null && head.val == val) {
            head = head.next;
        }

        // 删除非头节点
        ListNode curr = head;
        while (curr != null && curr.next != null) {
            if (curr.next.val == val) {
                curr.next = curr.next.next;
            } else {
                curr = curr.next;
            }
        }

        return head;
    }

    /**
     * 使用虚拟头节点简化
     */
    static public ListNode removeElements3(ListNode head, int val) {
        if (head == null) return null;

        // 新链表的头节点
        ListNode newHead = new ListNode(0);
        // 新链表的尾节点
        ListNode newTail = newHead;

        while (head != null) {
            if (head.val != val) {
                newTail.next = head;
                newTail = head;
            }
            head = head.next;
        }

        // 节点尾部的 next 要清空
        newTail.next = null;

        return newHead.next;
    }

    /**
     * 使用虚拟头节点简化
     */
    static public ListNode removeElements4(ListNode head, int val) {
        if (head == null) return null;

        // 虚拟头节点，统一删除写法
        ListNode dummyNode = new ListNode(0);
        dummyNode.next = head;
        ListNode curr = dummyNode;

        while (curr.next != null) {
            if (curr.next.val == val) {
                curr.next = curr.next.next;
            } else {
                curr = curr.next;
            }
        }

        return dummyNode.next;
    }

    public ListNode removeElements5(ListNode head, int val) {
        if (head == null) {
            return head;
        }

        // 假设 removeElements() 返回后面完整的已经去掉val节点的子链表
        // 在当前递归层用当前节点接住后面的子链表
        // 随后判断当前层的node是否需要被删除，如果是，就返回
        // 也可以先判断是否需要删除当前node，但是这样条件语句会比较不好想
        head.next = removeElements5(head.next, val);
        if (head.val == val) {
            return head.next;
        }
        return head;

        // 实际上就是还原一个从尾部开始重新构建链表的过程
    }
}



