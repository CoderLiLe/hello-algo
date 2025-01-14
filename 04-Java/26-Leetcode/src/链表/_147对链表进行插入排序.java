package 链表;

import tools.Asserts;
import tools.Times;

/**
 * https://leetcode.cn/problems/insertion-sort-list/description/
 * <p>
 * 给定单个链表的头 head ，使用 插入排序 对链表进行排序，并返回 排序后链表的头 。
 * <p>
 * 插入排序 算法的步骤:
 * <p>
 * 插入排序是迭代的，每次只移动一个元素，直到所有元素可以形成一个有序的输出列表。
 * 每次迭代中，插入排序只从输入数据中移除一个待排序的元素，找到它在序列中适当的位置，并将其插入。
 * 重复直到所有输入数据插入完为止。
 * 下面是插入排序算法的一个图形示例。部分排序的列表(黑色)最初只包含列表中的第一个元素。每次迭代时，从输入数据中删除一个元素(红色)，并就地插入已排序的列表中。
 * <p>
 * 对链表进行插入排序。
 */
public class _147对链表进行插入排序 {
    /**
     * 对链表进行插入排序
     *
     * @param head 链表的头节点
     * @return 排序后的链表的头节点
     */
    public ListNode insertionSortList(ListNode head) {
        // 如果链表为空或只有一个节点，则直接返回
        if (head == null || head.next == null) {
            return head;
        }

        // 创建一个虚拟头节点，方便后续操作
        ListNode dummyHead = new ListNode(0, head);
        // 初始化已排序部分的最后一个节点和当前节点
        ListNode lastSorted = head;
        ListNode cur = head.next;

        // 遍历链表，进行插入排序
        while (cur != null) {
            // 如果当前节点值大于等于已排序部分的最后一个节点值，则无需调整
            if (lastSorted.val <= cur.val) {
                lastSorted = lastSorted.next;
            } else {
                // 寻找当前节点的正确插入位置
                ListNode pre = dummyHead;
                while (pre.next.val < cur.val) {
                    pre = pre.next;
                }
                // 将当前节点插入到预设节点之后
                lastSorted.next = cur.next;
                cur.next = pre.next;
                pre.next = cur;
            }
            // 将下一个节点设为当前节点，继续排序
            cur = lastSorted.next;
        }

        // 返回排序后的链表的头节点
        return dummyHead.next;
    }


    public static void main(String[] args) {
        _147对链表进行插入排序 obj = new _147对链表进行插入排序();
        int[] nums = {4, 2, 1, 3};
        ListNode head = LinkedListUtil.buildLinkedList(nums);
        Times.test("插入排序", () -> {
            Asserts.test(LinkedListUtil.toString(obj.insertionSortList(head)).equals("[1,2,3,4]"));
        });
    }
}
