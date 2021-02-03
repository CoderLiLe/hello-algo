package 链表;

public class _141_环形链表 {

	public boolean hasCycle(ListNode head) {
		if (head == null || head.next == null) {
			return false;
		}
		
		// 快慢指针
		ListNode slow = head;
		ListNode fast = head.next;
		while (fast != slow) {
			if (fast == null || fast.next == null) {
				return false;
			}
			
			slow  = slow.next;
			fast = fast.next.next;
		}
		return true;
    }
}
