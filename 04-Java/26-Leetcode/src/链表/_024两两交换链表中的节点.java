package 链表;

import tools.Asserts;
import tools.Times;

/**
 * 给你一个链表，两两交换其中相邻的节点，并返回交换后链表的头节点。
 * 你必须在不修改节点内部的值的情况下完成本题（即，只能进行节点交换）。
 */
public class _024两两交换链表中的节点 {
    /**
     * 递归地交换链表中的相邻节点
     * 由于这是一个递归函数，对于非常长的链表（例如数万个节点），可能会导致栈溢出（Stack Overflow）。可以考虑使用迭代方法来避免这个问题
     * <p>
     * 时间复杂度：O(n)，因为需要遍历整个链表来交换节点
     * 空间复杂度：O(n)，因为递归调用会使用到栈空间，最坏情况下（链表长度为奇数时）需要n/2层递归调用
     *
     * @param head 链表的头节点
     * @return 交换后的链表的头节点
     */
    public ListNode swapPairs(ListNode head) {
        // 如果链表为空或只有一个节点，则直接返回该节点，因为没有节点需要交换
        if (head == null || head.next == null) {
            return head;
        }

        // 获取当前节点的下一个节点
        ListNode next = head.next;
        // 递归地交换剩余节点，并将交换后的头节点连接到当前节点后面
        head.next = swapPairs(next.next);
        // 将下一个节点连接到当前节点前面，完成两个节点的交换
        next.next = head;
        // 返回交换后的头节点
        return next;
    }

    /**
     * 快慢指针
     * 时间复杂度：O(n)
     * 空间复杂度：O(1)
     *
     * @param head
     * @return
     */
    public ListNode swapPairs2(ListNode head) {
        // 异常处理：检查输入是否合法
        if (head == null || head.next == null) {
            return head;
        }

        // 创建虚拟头节点
        ListNode dummyHead = new ListNode(-1, head);
        ListNode cur = dummyHead;

        while (cur.next != null && cur.next.next != null) {
            // 获取需要交换的两个节点
            ListNode node1 = cur.next;
            ListNode node2 = cur.next.next;

            // 交换节点
            cur.next = node2;
            node1.next = node2.next;
            node2.next = node1;

            // 移动当前指针到下一对节点的前一个位置
            cur = node1;
        }

        return dummyHead.next;
    }


    public static void main(String[] args) {
        _024两两交换链表中的节点 obj = new _024两两交换链表中的节点();

        int[] nums = {1, 2, 3, 4};
        ListNode head = LinkedListUtil.buildLinkedList(nums);
        Times.test("递归", () -> {
            Asserts.test(LinkedListUtil.toString(obj.swapPairs(head)).equals("[2,1,4,3]"));
        });

        int[] nums2 = {1, 2, 3, 4};
        ListNode head2 = LinkedListUtil.buildLinkedList(nums2);
        Times.test("快慢指针", () -> {
            Asserts.test(LinkedListUtil.toString(obj.swapPairs2(head2)).equals("[2,1,4,3]"));
        });
    }
}
