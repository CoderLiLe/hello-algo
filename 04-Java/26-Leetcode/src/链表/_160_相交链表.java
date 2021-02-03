package 链表;

public class _160_相交链表 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
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
