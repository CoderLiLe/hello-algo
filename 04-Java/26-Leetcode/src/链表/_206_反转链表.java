package 链表;

public class _206_反转链表 {
	
	public ListNode reverseList1(ListNode head) {	
		if (head == null || head.next == null) return head;
		
		ListNode newHead = reverseList1(head.next);
		head.next.next = head;
		head.next = null;
		return newHead;
	}
	
	public ListNode reverseList2(ListNode head) {
		ListNode prev = null;
		ListNode curr = head;
		while (curr != null) {
			ListNode nextTemp = curr.next;
			curr.next = prev;
			prev = curr;
			curr = nextTemp;
		}
		
		return prev;
    }

}
