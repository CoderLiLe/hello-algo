package 链表;

import tools.Asserts;
import tools.Times;

public class _206_反转链表 {
	public static void main(String[] args) {
		int[] nums = {1,2,3,4,5};
		ListNode l1 = LinkedListUtil.buildLinkedList(nums);
		Times.test("递归法1", () -> {
			Asserts.test(LinkedListUtil.toString(reverseList1(l1)).equals("[5,4,3,2,1]"));
		});

		ListNode l2 = LinkedListUtil.buildLinkedList(nums);
		Times.test("递归法2", () -> {
			Asserts.test(LinkedListUtil.toString(reverseList2(l2)).equals("[5,4,3,2,1]"));
		});


		ListNode l3 = LinkedListUtil.buildLinkedList(nums);
		Times.test("双指针法（迭代）", () -> {
			Asserts.test(LinkedListUtil.toString(reverseList3(l3)).equals("[5,4,3,2,1]"));
		});


	}

	private static ListNode reverseList1(ListNode head) {
		if (head == null || head.next == null) {
			return head;
		}
		
		ListNode newHead = reverseList1(head.next);
		head.next.next = head;
		head.next = null;
		return newHead;
	}

	/**
	 * 时间复杂度 O(N) ： 遍历链表使用线性大小时间。
	 * 空间复杂度 O(N) ： 遍历链表的递归深度达到 N ，系统使用 O(N) 大小额外空间。
	 */
	private static ListNode reverseList2(ListNode head) {
		// 调用递归并返回
		return recur(head, null);
	}
	private static ListNode recur(ListNode cur, ListNode pre) {
		// 终止条件
		if (cur == null) {
			return pre;
		}
		// 递归后继节点
		ListNode res = recur(cur.next, cur);
		// 修改节点引用指向
		cur.next = pre;
		// 返回反转链表的头节点
		return res;
	}

	/**
	 * 时间复杂度 O(N) ： 遍历链表使用线性大小时间。
	 * 空间复杂度 O(1) ： 变量 pre 和 cur 使用常数大小额外空间。
	 */
	private static ListNode reverseList3(ListNode head) {
		ListNode prev = null;
		ListNode curr = head;
		while (curr != null) {
			// 暂存后继节点 curr.next
			ListNode nextTemp = curr.next;
			// 修改 next 引用指向
			curr.next = prev;
			// pre 暂存 curr
			prev = curr;
			// curr 访问下一节点
			curr = nextTemp;
		}
		
		return prev;
    }

}
