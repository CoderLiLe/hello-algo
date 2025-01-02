package 链表;

import tools.Asserts;

/**
 * 给你两个 非空 的链表，表示两个非负的整数。它们每位数字都是按照 逆序 的方式存储的，并且每个节点只能存储 一位 数字。
 *
 * 请你将两个数相加，并以相同形式返回一个表示和的链表。
 *
 * 你可以假设除了数字 0 之外，这两个数都不会以 0 开头。
 */
public class _002_两数相加 {

	public static void main(String[] args) {
		// 输入：l1 = [2,4,3], l2 = [5,6,4]
		// 输出：[7,0,8]
		// 解释：342 + 465 = 807.
		int[] l1Nums = {2, 4, 3};
		int[] l2Nums = {5, 6, 4};
		ListNode l1 = LinkedListUtil.buildLinkedList(l1Nums);
		ListNode l2 = LinkedListUtil.buildLinkedList(l2Nums);
		Asserts.test(LinkedListUtil.toString(addTwoNumbers(l1, l2)).equals("[7,0,8]"));

		int[] l1Nums2 = {9,9,9,9,9,9,9};
		int[] l2Nums2 = {9,9,9,9};
		ListNode l3 = LinkedListUtil.buildLinkedList(l1Nums2);
		ListNode l4 = LinkedListUtil.buildLinkedList(l2Nums2);
		Asserts.test(LinkedListUtil.toString(addTwoNumbers(l3, l4)).equals("[8,9,9,9,0,0,0,1]"));
	}
	
	public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
		if (l1 == null) return l2;
		if (l2 == null) return l1;
		
		// 虚拟头节点
		ListNode dummyHead = new ListNode(0);
		ListNode last = dummyHead;
		
		int sum = 0;
		// 进位值
		int carry = 0;
		while (l1 != null || l2 != null) {
			int v1 = 0;
			if (l1 != null) {
				v1 = l1.val;
				l1 = l1.next;
			}
			int v2 = 0;
			if (l2 != null) {
				v2 = l2.val;
				l2 = l2.next;
			}
			sum = v1 + v2 + carry;
			// 设置进位值
			carry = sum / 10;
			// sum 的个位数作为新节点的值
			last.next = new ListNode(sum % 10);
			last = last.next;
		}
		
		// 检查最后的进位
		if (carry > 0) {
			// carry == 1
			last.next = new ListNode(carry);
		}
		
		return dummyHead.next;
    }

}
