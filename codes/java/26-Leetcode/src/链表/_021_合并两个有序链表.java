package 链表;

import tools.Asserts;

public class _021_合并两个有序链表 {

    public static void main(String[] args) {
        int[] l1Nums = {1,2,4};
        int[] l2Nums = {1,3,4};
        ListNode l1 = LinkedListUtil.buildLinkedList(l1Nums);
        ListNode l2 = LinkedListUtil.buildLinkedList(l2Nums);
        Asserts.test(LinkedListUtil.toString(mergeTwoLists(l1, l2)).equals("[1,1,2,3,4,4]"));

        int[] l3Nums = {};
        int[] l4Nums = {};
        ListNode l3 = LinkedListUtil.buildLinkedList(l3Nums);
        ListNode l4 = LinkedListUtil.buildLinkedList(l4Nums);
        Asserts.test(LinkedListUtil.toString(mergeTwoLists(l3, l4)).equals("[]"));

        int[] l5Nums = {};
        int[] l6Nums = {1};
        ListNode l5 = LinkedListUtil.buildLinkedList(l5Nums);
        ListNode l6 = LinkedListUtil.buildLinkedList(l6Nums);
        Asserts.test(LinkedListUtil.toString(mergeTwoLists(l5, l6)).equals("[1]"));
    }

    /**
     * 合并两个有序链表
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
}
