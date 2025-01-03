package 链表;

import tools.Asserts;
import tools.Times;

public class _083_删除排序链表中的重复元素 {
	public static void main(String[] args) {
		int[] nums = {1,1,2,3,3};
		ListNode l1 = LinkedListUtil.buildLinkedList(nums);
		Times.test("前后指针", () -> {
			Asserts.test(LinkedListUtil.toString(deleteDuplicates1(l1)).equals("[1,2,3]"));
		});

		ListNode l2 = LinkedListUtil.buildLinkedList(nums);
		Times.test("快慢指针", () -> {
			Asserts.test(LinkedListUtil.toString(deleteDuplicates2(l2)).equals("[1,2,3]"));
		});
	}

	private static ListNode deleteDuplicates1(ListNode head) {
		if (head == null || head.next == null) {
			return head;
		}

		ListNode pre = head;
		ListNode next = head;
		while (true) {
			if (next == null || pre.val != next.val) {
				pre.next = next;
				pre = next;

				if (next == null) {
					break;
				}
			}

			next = next.next;
		}
		return head;
    }

	private static ListNode deleteDuplicates2(ListNode head) {
		if (head == null || head.next == null) {
			return head;
		}

		ListNode slow = head, fast = head;
		while (fast != null) {
			if (slow.val != fast.val) {
				slow.next = fast;
				slow = slow.next;
			}
			fast = fast.next;
		}

		slow.next = null;
		return head;
	}
}
