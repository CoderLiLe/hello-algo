package 链表;

import tools.Asserts;
import tools.Times;

public class _061旋转链表 {
    /**
     * 旋转链表
     * 将链表的每个节点向右移动k个位置
     * 如果k大于链表长度，则k模除链表长度
     * 如果链表为空或k为0，则直接返回原链表
     *
     * @param head 链表头节点
     * @param k    旋转步数
     * @return 旋转后的链表头节点
     */
    public ListNode rotateRight(ListNode head, int k) {
        // 检查链表是否为空或不需要旋转
        if (head == null || k == 0)
            return head;

        // l1指向链表的末尾节点
        ListNode l1 = head;
        int len = 1;

        // 计算链表长度
        while (l1.next != null) {
            l1 = l1.next;
            len++;
        }

        // 将链表的末尾节点指向头节点，形成环形链表
        l1.next = head;

        // l2用于找到新的头节点的前一个节点
        ListNode l2 = head;

        // 根据旋转步数找到新的头节点的位置
        for (int i = 1; i < len - k % len; i++)
            l2 = l2.next;

        // newHead为新的头节点
        ListNode newHead = l2.next;

        // 断开环形链表，形成新的链表
        l2.next = null;

        // 返回新的头节点
        return newHead;
    }

    public static void main(String[] args) {
        _061旋转链表 obj = new _061旋转链表();

        int[] nums = {1, 2, 3, 4, 5};
        String res = "[4,5,1,2,3]";
        Times.test("迭代法", () -> {
            ListNode head = LinkedListUtil.buildLinkedList(nums);
            Asserts.test(LinkedListUtil.toString(obj.rotateRight(head, 2)).equals(res));
        });
    }
}
