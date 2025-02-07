package 链表;

import tools.Asserts;

public class _876_链表的中间结点 {

	/**
	 * 参考题解
	 * https://leetcode.cn/problems/middle-of-the-linked-list/solutions/165152/kuai-man-zhi-zhen-zhu-yao-zai-yu-diao-shi-by-liwei/
	 */
    public ListNode middleNode(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        return slow;
    }

    public static void main(String[] args) {
        _876_链表的中间结点 obj = new _876_链表的中间结点();

        test1(obj);
    }

    private static void test1(_876_链表的中间结点 obj) {
        ListNode head = LinkedListUtil.buildLinkedList(new int[]{1, 2, 3, 4, 5});
        ListNode head2 = LinkedListUtil.buildLinkedList(new int[]{1, 2, 3, 4, 5, 6});
        String res = "[3,4,5]";
        String res2 = "[4,5,6]";
        Asserts.test(LinkedListUtil.toString(obj.middleNode(head)).equals(res));
        Asserts.test(LinkedListUtil.toString(obj.middleNode(head2)).equals(res2));
    }
}
