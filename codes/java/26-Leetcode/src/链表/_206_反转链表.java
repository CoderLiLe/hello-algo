package 链表;

import tools.Asserts;
import tools.Times;

public class _206_反转链表 {
	public static void main(String[] args) {
		_206_反转链表 obj = new _206_反转链表();
		int[] nums = {1,2,3,4,5};
		ListNode l1 = LinkedListUtil.buildLinkedList(nums);
		Times.test("递归法1", () -> {
			Asserts.test(LinkedListUtil.toString(obj.reverseList1(l1)).equals("[5,4,3,2,1]"));
		});

		ListNode l2 = LinkedListUtil.buildLinkedList(nums);
		Times.test("递归法2", () -> {
			Asserts.test(LinkedListUtil.toString(obj.reverseList2(l2)).equals("[5,4,3,2,1]"));
		});


		ListNode l3 = LinkedListUtil.buildLinkedList(nums);
		Times.test("双指针法（迭代）", () -> {
			Asserts.test(LinkedListUtil.toString(obj.reverseList3(l3)).equals("[5,4,3,2,1]"));
		});


	}

	/**
	 * 递归法1
	 * 从后向前递归
	 * 时间复杂度 O(N) ： 遍历链表使用线性大小时间。
	 * 空间复杂度 O(N) ： 递归调用栈使用 O(N) 大小额外空间。
	 */
	public ListNode reverseList1(ListNode head) {
		if (head == null || head.next == null) {
			return head;
		}

		// 递归调用，翻转第二个节点开始往后的链表
		ListNode newHead = reverseList1(head.next);
		// 翻转头节点与第二个节点的指向
		head.next.next = head;
		// 此时的 head 节点为尾节点，next 需要指向 NULL
		head.next = null;
		return newHead;
	}

	/**
	 * 递归法2
	 * 时间复杂度 O(N) ： 遍历链表使用线性大小时间。
	 * 空间复杂度 O(N) ： 遍历链表的递归深度达到 N ，系统使用 O(N) 大小额外空间。
	 */
	public ListNode reverseList2(ListNode head) {
		// 调用递归并返回
		return recur(head, null);
	}

	public ListNode recur(ListNode cur, ListNode pre) {
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
	public ListNode reverseList3(ListNode head) {
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
