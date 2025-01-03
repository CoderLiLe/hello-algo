package 链表;

import tools.Asserts;
import tools.Times;

import java.util.HashSet;
import java.util.Set;

public class _141_环形链表 {
	public static void main(String[] args) {
		ListNode node1 = new ListNode(1);
		ListNode node2 = new ListNode(2, node1);
		node1.next = node2;
		Times.test("快慢指针法", () -> {
			Asserts.test(hasCycle1(node1));
		});
		Times.test("哈希判断", () -> {
			Asserts.test(hasCycle2(node1));
		});
	}

	private static boolean hasCycle1(ListNode head) {
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

	/**
	 * 哈希表判断
	 * 时间复杂度：O(N)，其中 N 是链表中的节点数。最坏情况下我们需要遍历每个节点一次。
	 *
	 * 空间复杂度：O(N)，其中 N 是链表中的节点数。主要为哈希表的开销，最坏情况下我们需要将每个节点插入到哈希表中一次。
	 */
	private static boolean hasCycle2(ListNode head) {
		Set<ListNode> set = new HashSet<>();
		while (head != null) {
			if (!set.add(head)) {
				return true;
			}
			head = head.next;
		}
		return false;
	}
}
