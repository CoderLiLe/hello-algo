package 链表;

import tools.Asserts;
import tools.Times;

public class _082_删除排序链表中的重复元素II {
	public static void main(String[] args) {
		_082_删除排序链表中的重复元素II obj = new _082_删除排序链表中的重复元素II();

		int[] nums = {1,2,3,3,4,4,5};
		String res = "[1,2,5]";

		Times.test("前后指针", () -> {
			ListNode head = LinkedListUtil.buildLinkedList(nums);
			Asserts.test(LinkedListUtil.toString(obj.deleteDuplicates(head)).equals(res));
		});
	}

	public ListNode deleteDuplicates(ListNode head) {
		if (head == null || head.next == null) {
			return head;
		}

		ListNode dummy = new ListNode(-1, head);
		ListNode cur = dummy;
		while (cur.next != null && cur.next.next != null) {
			int val = cur.next.val;
			if (cur.next.next.val == val) {
				while (cur.next != null && cur.next.val == val) {
					cur.next = cur.next.next;
				}
			} else {
				cur = cur.next;
			}
		}
		return dummy.next;
	}
}
