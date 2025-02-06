package 链表;

import tools.Asserts;
import tools.Times;

public class _092反转链表II {

    /**
     * 穿针引线
     *
     * @param head
     * @param left
     * @param right
     * @return
     */
    public ListNode reverseBetween(ListNode head, int left, int right) {
        // 因为头节点有可能发生变化，使用虚拟头节点可以避免复杂的分类讨论
        ListNode dummyNode = new ListNode(-1);
        dummyNode.next = head;

        ListNode pre = dummyNode;
        // 第 1 步：从虚拟头节点走 left - 1 步，来到 left 节点的前一个节点
        // 建议写在 for 循环里，语义清晰
        for (int i = 0; i < left - 1; i++) {
            pre = pre.next;
        }

        // 第 2 步：从 pre 再走 right - left + 1 步，来到 right 节点
        ListNode rightNode = pre;
        for (int i = 0; i < right - left + 1; i++) {
            rightNode = rightNode.next;
        }

        // 第 3 步：切断出一个子链表（截取链表）
        ListNode leftNode = pre.next;
        ListNode curr = rightNode.next;

        // 注意：切断链接
        pre.next = null;
        rightNode.next = null;

        // 第 4 步：同第 206 题，反转链表的子区间
        reverseLinkedList(leftNode);

        // 第 5 步：接回到原来的链表中
        pre.next = rightNode;
        leftNode.next = curr;
        return dummyNode.next;
    }

    private void reverseLinkedList(ListNode head) {
        // 也可以使用递归反转一个链表
        ListNode pre = null;
        ListNode cur = head;

        while (cur != null) {
            ListNode next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
    }

    /**
     * 一次遍历「穿针引线」反转链表（头插法）
     * 时间复杂度：O(n)，其中 n 为链表节点个数。
     * 空间复杂度：O(1)，仅用到若干额外变量。
     *
     * @param head 链表的头节点
     * @param left 反转部分的起始位置
     * @param right 反转部分的结束位置
     * @return 反转指定部分后，链表的头节点
     */
    public ListNode reverseBetween2(ListNode head, int left, int right) {
        // 设置 dummyNode 是这一类问题的一般做法
        ListNode dummyNode = new ListNode(-1);
        dummyNode.next = head;
        ListNode pre = dummyNode;
        for (int i = 0; i < left - 1; i++) {
            pre = pre.next;
        }
        ListNode cur = pre.next;
        ListNode next;
        for (int i = 0; i < right - left; i++) {
            next = cur.next;
            cur.next = next.next;
            next.next = pre.next;
            pre.next = next;
        }
        return dummyNode.next;
    }

    /**
     * 维护两个指针反转链表
     * 时间复杂度：O(n)，其中 n 为链表节点个数。
     * 空间复杂度：O(1)，仅用到若干额外变量。
     *
     * @param head 链表的头节点
     * @param left 反转部分的起始位置
     * @param right 反转部分的结束位置
     * @return 反转指定部分后，链表的头节点
     */
    public ListNode reverseBetween3(ListNode head, int left, int right) {
        // 创建一个虚拟头节点，便于处理可能变化的头节点
        ListNode dummy = new ListNode(0, head);

        // p0 指针用于找到left节点的前一个位置
        ListNode p0 = dummy;
        for (int i = 0; i < left - 1; i++) {
            p0 = p0.next;
        }

        // 初始化 pre 指针为 null，cur 指针为left位置的节点
        ListNode pre = null;
        ListNode cur = p0.next;

        // 反转left到right之间的链表
        for (int i = 0; i < right - left + 1; i++) {
            ListNode nxt = cur.next;
            // 每次循环只修改一个 next，方便大家理解
            cur.next = pre;
            pre = cur;
            cur = nxt;
        }

        // 连接反转后的链表与原始链表的剩余部分
        p0.next.next = cur;
        p0.next = pre;

        // 返回反转指定部分后，链表的头节点
        return dummy.next;
    }

    public ListNode reverseBetween4(ListNode head, int m, int n) {
        // base case
        if (m == 1) {
            return reverseN(head, n);
        }
        // 前进到反转的起点触发 base case
        head.next = reverseBetween4(head.next, m - 1, n - 1);
        return head;
    }

    // 后驱节点
    ListNode successor = null;
    // 反转以 head 为起点的 n 个节点，返回新的头结点
    ListNode reverseN(ListNode head, int n) {
        if (n == 1) {
            // 记录第 n + 1 个节点
            successor = head.next;
            return head;
        }
        // 以 head.next 为起点，需要反转前 n - 1 个节点
        ListNode last = reverseN(head.next, n - 1);

        head.next.next = head;
        // 让反转之后的 head 节点和后面的节点连起来
        head.next = successor;
        return last;
    }

    public static void main(String[] args) {
        _092反转链表II obj = new _092反转链表II();

        Times.test("迭代法", () -> {
            ListNode head = obj.reverseBetween(getListNode(), 2, 4);
            Asserts.test(LinkedListUtil.toString(head).equals("[1,4,3,2,5]"));
        });

        Times.test("迭代法2", () -> {
            ListNode head = obj.reverseBetween2(getListNode(), 2, 4);
            Asserts.test(LinkedListUtil.toString(head).equals("[1,4,3,2,5]"));
        });

        Times.test("迭代法3", () -> {
            ListNode head = obj.reverseBetween3(getListNode(), 2, 4);
            Asserts.test(LinkedListUtil.toString(head).equals("[1,4,3,2,5]"));
        });

        Times.test("递归法", () -> {
            ListNode head = obj.reverseBetween4(getListNode(), 2, 4);
            Asserts.test(LinkedListUtil.toString(head).equals("[1,4,3,2,5]"));
        });
    }

    private static ListNode getListNode() {
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(3);
        ListNode node4 = new ListNode(4);
        ListNode node5 = new ListNode(5);
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;
        return node1;
    }
}
