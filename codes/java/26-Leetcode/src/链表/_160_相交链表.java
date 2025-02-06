package 链表;

import tools.Asserts;

public class _160_相交链表 {

	public static void main(String[] args) {
		ListNode node5 = new ListNode(5, null);
		ListNode node4 = new ListNode(4, node5);
		ListNode node8 = new ListNode(8, node4);

		ListNode node1 = new ListNode(1, node8);
		ListNode l1 = new ListNode(4, node1);

		ListNode node11 = new ListNode(1, node8);
		ListNode node6 = new ListNode(6, node11);
		ListNode l2 = new ListNode(5, node6);
		ListNode node = getIntersectionNode(l1, l2);
		Asserts.test((node != null ? node.val : 0) == 8);
	}
	
	private static ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) return null;
		
		ListNode curA = headA, curB = headB;
		while (curA != curB) {
			curA = (curA == null) ? headB : curA.next;
			curB = (curB == null) ? headA : curB.next;
			
			// 这段代码在两个链表不相交的情况下会死循环
			// curA = (curA.next == null) ? headB : curA.next;
			// curB = (curB.next == null) ? headA : curB.next;
		}
		return curA;
    }

}
