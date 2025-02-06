package 链表;

import tools.Asserts;

import java.util.*;

/**
 * 标签：链表、分治、堆（优先队列）、归并排序
 * 难度：困难
 * 链接：https://leetcode-cn.com/problems/merge-k-sorted-lists/
 * 描述：给定一个链表数组，每个链表都已经按照升序排列。
 * 要求：将所有链表合并到一个升序链表中，返回合并后的链表。
 * 思路：分治、堆、归并排序
 */
public class _023合并K个升序链表 {
    /**
     * 合并K个升序链表
     * 使用优先队列（小顶堆）来快速找到当前最小的节点，然后构建合并后的链表。
     * 时间复杂度：O(NlogK)，其中N是所有链表中节点的总数，K是链表个数。
     * 空间复杂度：O(K)，其中K是链表个数。
     *
     * @param lists 一个包含K个升序链表的数组
     * @return 合并后的单个升序链表的头节点
     */
    public ListNode mergeKLists(ListNode[] lists) {
        // 检查输入的有效性
        if (lists == null || lists.length == 0) {
            return null;
        }

        // 使用优先队列来快速找到当前最小的节点
        PriorityQueue<ListNode> minHeap = new PriorityQueue<>(Comparator.comparingInt(node -> node.val));

        // 将每个链表的头节点加入优先队列
        for (ListNode list : lists) {
            if (list != null) {
                minHeap.offer(list);
            }
        }

        // 创建一个虚拟头节点，简化链表的构建过程
        ListNode dummyHead = new ListNode(-1);
        ListNode preNode = dummyHead;

        // 当优先队列不为空时，循环构建合并后的链表
        while (!minHeap.isEmpty()) {
            // 从优先队列中取出当前最小的节点
            ListNode minNode = minHeap.poll();
            // 将当前最小节点连接到合并后的链表中
            preNode.next = minNode;
            preNode = preNode.next;

            // 如果当前最小节点有下一个节点，则将其下一个节点加入优先队列
            if (minNode.next != null) {
                minHeap.offer(minNode.next);
            }
        }

        // 返回合并后的链表的头节点
        return dummyHead.next;
    }

    /**
     * 合并K个升序链表
     * 使用分治法来合并链表。
     * 时间复杂度：O(NlogK)，其中N是所有链表中节点的总数，K是链表个数。
     * 空间复杂度：O(logK)，递归调用栈的空间。
     *
     * @param lists 一个包含K个升序链表的数组
     * @return 合并后的单个升序链表的头节点
     */
    public ListNode mergeKLists2(ListNode[] lists) {
        // 如果输入的链表数组为空或长度为0，则直接返回null
        if (lists == null || lists.length == 0) {
            return null;
        }
        // 调用递归函数mergeSort来执行链表的合并
        return mergeSort(lists, 0, lists.length - 1);
    }

    /**
     * 使用合并排序算法对链表数组进行排序
     * 该方法递归地将链表数组分成两半，直到每个子数组仅包含一个节点，然后将这些子数组合并成一个有序的链表
     *
     * @param lists 链表数组，其中每个链表都是升序排列的
     * @param left 链表数组的起始索引
     * @param right 链表数组的结束索引
     * @return 返回排序后的链表的头节点
     */
    private ListNode mergeSort(ListNode[] lists, int left, int right) {
        // 当起始索引等于结束索引时，说明已经分割到单个节点，直接返回该节点
        if (left == right) {
            return lists[left];
        }
        // 计算中间索引，将链表数组分成两半
        int mid = left + (right - left) / 2;
        // 递归地对左半部分链表数组进行合并排序
        ListNode nodeLeft = mergeSort(lists, left, mid);
        // 递归地对右半部分链表数组进行合并排序
        ListNode nodeRight = mergeSort(lists, mid + 1, right);
        // 合并左右两部分排序后的链表，并返回合并后的链表头节点
        return mergeTwoLists(nodeLeft, nodeRight);
    }

    /**
     * 21.合并两个有序链表
     * 将两个有序链表合并为一个新的有序链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的。
     *
     * @param l1 第一个有序链表的头节点
     * @param l2 第二个有序链表的头节点
     * @return 合并后的有序链表的头节点
     *
     * 时间复杂度 O(M+N) ： M,N 分别为链表 l1, l2的长度，合并操作需遍历两链表。
     * 空间复杂度 O(1) ： 节点引用 dum , cur 使用常数大小的额外空间。
     */
    private static ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        // 创建一个虚拟头节点，简化链表操作
        ListNode dummyHead = new ListNode(0);
        // 当前操作节点指针
        ListNode curr = dummyHead;

        // 遍历两个链表，直到其中一个链表为空
        while (l1 != null && l2 != null) {
            // 比较两个链表当前节点的值，将较小值的节点添加到新链表中
            if (l1.val < l2.val) {
                curr.next = l1;
                l1 = l1.next;
            } else {
                curr.next = l2;
                l2 = l2.next;
            }
            curr = curr.next;
        }

        // 如果l1链表还有剩余节点，直接添加到新链表末尾
        if (l1 != null) {
            curr.next = l1;
        }
        // 如果l2链表还有剩余节点，直接添加到新链表末尾
        else if (l2 != null) {
            curr.next = l2;
        }

        // 返回合并后的链表，跳过虚拟头节点
        return dummyHead.next;
    }


    public static void main(String[] args) {
        _023合并K个升序链表 obj = new _023合并K个升序链表();

        List<int[]> list = new ArrayList<>();
        list.add(new int[]{1, 4, 5});
        list.add(new int[]{1, 3, 4});
        list.add(new int[]{2, 6});
        String res = "[1,1,2,3,4,4,5,6]";

        ListNode[] nodes = buildLinkedLists(list);
        ListNode newHead = obj.mergeKLists(nodes);
        Asserts.test(res.equals(LinkedListUtil.toString(newHead)));

        ListNode[] nodes2 = buildLinkedLists(list);
        ListNode newHead2 = obj.mergeKLists2(nodes2);
        Asserts.test(res.equals(LinkedListUtil.toString(newHead2)));
    }

    private static ListNode[] buildLinkedLists(List<int[]> list) {
        ListNode[] nodes = new ListNode[list.size()];
        for (int i = 0; i < list.size(); i++) {
            int[] nums = list.get(i);
            nodes[i] = LinkedListUtil.buildLinkedList(nums);
        }
        return nodes;
    }
}
