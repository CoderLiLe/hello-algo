package 链表;

public class _234_回文链表 {

	public static void main(String[] args) {
		ListNode head = new ListNode(1);
		head.next = new ListNode(2);
		head.next.next = new ListNode(3);
		head.next.next.next = new ListNode(2);
		head.next.next.next.next = new ListNode(1);
		
		System.out.println(head);
		
		_234_回文链表 obj = new _234_回文链表();
		obj.isPalindrome(head);
		
		System.out.println(head);
	}
	
	public boolean isPalindrome(ListNode head) {
		if (head == null || head.next == null) return true;
		if (head.next.next == null) return head.val == head.next.val;
		
		// 找到中间节点
		ListNode middle = middleNode(head);
		// 翻转右半部分（中间节点的右半部分）
		ListNode rHead = reverseList(middle.next);
		ListNode lHead = head;
		ListNode rOldHead = rHead;
		
		boolean result = true;
		while (rHead != null) {
			if (lHead.val != rHead.val) {
				result = false;
				break;
			}
			lHead = lHead.next;
			rHead = rHead.next;
		}
		
		// 恢复右半段（对右半部分再次翻转）
		reverseList(rOldHead);
		
		return result;
    }
	
	/**
	 * 找到中间节点（右半部分链表头节点的前一个节点）
	 * 1 - 2 - 3 - 2 - 1 的中间节点为 3
	 * 1 - 2 - 2 - 1 的中间节点为左边第一个 2
	 * @param head
	 * @return
	 */
	private ListNode middleNode(ListNode head) {
		ListNode fast = head;
		ListNode slow = head;
		while (fast.next != null && fast.next.next != null) {
			fast = fast.next.next;
			slow = slow.next;
		}
		return slow;
	}

	/**
	 * 反转链表
	 * @param head
	 * @return
	 */
	private ListNode reverseList(ListNode head) {
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
