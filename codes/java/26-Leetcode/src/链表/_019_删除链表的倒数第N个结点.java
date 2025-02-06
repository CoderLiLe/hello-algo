package 链表;

import tools.Asserts;
import tools.Times;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 给你一个链表，删除链表的倒数第 n 个结点，并且返回链表的头结点。
 */
public class _019_删除链表的倒数第N个结点 {

    public static void main(String[] args) {
        _019_删除链表的倒数第N个结点 obj = new _019_删除链表的倒数第N个结点();

        int[] nums = {1,2,3,4,5};
        int[] nums2 = {1};

        ListNode l1 = LinkedListUtil.buildLinkedList(nums);
        Times.test("快慢指针", () -> {
            Asserts.test(LinkedListUtil.toString(obj.removeNthFromEnd1(l1, 2)).equals("[1,2,3,5]"));
        });

        ListNode l2 = LinkedListUtil.buildLinkedList(nums2);
        Times.test("快慢指针", () -> {
            Asserts.test(LinkedListUtil.toString(obj.removeNthFromEnd1(l2, 1)).equals("[]"));
        });

        ListNode l3 = LinkedListUtil.buildLinkedList(nums);
        Times.test("栈", () -> {
            Asserts.test(LinkedListUtil.toString(obj.removeNthFromEnd2(l3, 2)).equals("[1,2,3,5]"));
        });

        ListNode l4 = LinkedListUtil.buildLinkedList(nums2);
        Times.test("栈", () -> {
            Asserts.test(LinkedListUtil.toString(obj.removeNthFromEnd2(l4, 1)).equals("[]"));
        });

        ListNode l5 = LinkedListUtil.buildLinkedList(nums);
        Times.test("计算链表长度", () -> {
            Asserts.test(LinkedListUtil.toString(obj.removeNthFromEnd3(l5, 2)).equals("[1,2,3,5]"));
        });

        ListNode l6 = LinkedListUtil.buildLinkedList(nums2);
        Times.test("计算链表长度", () -> {
            Asserts.test(LinkedListUtil.toString(obj.removeNthFromEnd3(l6, 1)).equals("[]"));
        });
    }

    /**
     * 快慢指针法
     * 时间复杂度：O(L)，其中 L 是链表的长度。
     *
     * 空间复杂度：O(1)。
     */
    public ListNode removeNthFromEnd1(ListNode head, int n) {
        ListNode dummy = new ListNode(0, head);
        ListNode slow = dummy, fast = head;
        while (n-- > 0) {
            fast = fast.next;
        }
        while (fast != null) {
            slow = slow.next;
            fast = fast.next;
        }
        slow.next = slow.next.next;
        return dummy.next;
    }

    /**
     * 栈
     * 时间复杂度：O(L)，其中 L 是链表的长度。
     *
     * 空间复杂度：O(L)，其中 L 是链表的长度。主要为栈的开销。
     */
    public ListNode removeNthFromEnd2(ListNode head, int n) {
        ListNode dummy = new ListNode(0, head);
        Deque<ListNode> stack = new LinkedList<>();
        ListNode curr = dummy;
        while (curr != null) {
            stack.push(curr);
            curr = curr.next;
        }

        for (int i = 0; i < n; i++) {
            stack.pop();
        }
        ListNode prev = stack.peek();
        prev.next = prev.next.next;
        return dummy.next;
    }

    /**
     * 计算链表长度
     * 时间复杂度：O(L)，其中 L 是链表的长度。
     *
     * 空间复杂度：O(1)。
     */
    public ListNode removeNthFromEnd3(ListNode head, int n) {
        ListNode dummy = new ListNode(0, head);
        int length = getLength(head);
        ListNode curr = dummy;
        for (int i = 1; i < length - n + 1; i++) {
            curr = curr.next;
        }
        curr.next = curr.next.next;
        return dummy.next;
    }

    private static int getLength(ListNode head) {
        int length = 0;
        while (head != null) {
            length++;
            head = head.next;
        }
        return length;
    }
}
