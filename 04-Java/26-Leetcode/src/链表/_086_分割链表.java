package 链表;

import tools.Asserts;

public class _086_分割链表 {

	public static void main(String[] args) {
		int[] nums = {1,4,3,2,5,2};
		ListNode head = LinkedListUtil.buildLinkedList(nums);
		Asserts.test(LinkedListUtil.toString(partition(head, 3)).equals("[1,2,2,4,3,5]"));
	}
	
	private static ListNode partition(ListNode head, int x) {
		if (head == null) return null;
		
		ListNode smlDummy = new ListNode(0);
		ListNode sml = smlDummy;
		ListNode bigDummy = new ListNode(0);
		ListNode big = bigDummy;
		while (head != null) {
			if (head.val < x) { // 放在 lTail 后面
				sml.next = head;
				sml = head;
			} else { // 放在 rTail 后面
				big.next = head;
				big = head;
			}
			head = head.next;
		}
		/**
		 * 此句不能少，因为可能会出现这样的情况：
		 * 原链表倒数第 N 个节点 A 的值 >= x 的，A 后所有的节点都是 < x 的
		 * 然后 rTail.next 其实就是 A.next 
		 */
		big.next = null;
		// 将 rHead.next 拼接到 lTail后面
		sml.next = bigDummy.next;
		
		return smlDummy.next;
    }

}
