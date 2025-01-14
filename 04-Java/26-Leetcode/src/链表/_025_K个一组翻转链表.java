package 链表;

import tools.Asserts;

/**
 * 给你链表的头节点 head ，每 k 个节点一组进行翻转，请你返回修改后的链表。
 * <p>
 * k 是一个正整数，它的值小于或等于链表的长度。如果节点总数不是 k 的整数倍，那么请将最后剩余的节点保持原有顺序。
 * <p>
 * 你不能只是单纯的改变节点内部的值，而是需要实际进行节点交换。
 */
public class _025_K个一组翻转链表 {
    public static void main(String[] args) {
        _025_K个一组翻转链表 obj = new _025_K个一组翻转链表();

        int[] nums = {1,2,3,4,5};
        ListNode l1 = LinkedListUtil.buildLinkedList(nums);
        Asserts.test(LinkedListUtil.toString(obj.reverseKGroup(l1, 2)).equals("[2,1,4,3,5]"));
    }

    /**
     * https://leetcode.cn/problems/reverse-nodes-in-k-group/solutions/10416/tu-jie-kge-yi-zu-fan-zhuan-lian-biao-by-user7208t/
     */
    public ListNode reverseKGroup(ListNode head, int k) {
        // 定义一个伪节点，用来返回结果
        ListNode dummyHead = new ListNode(0);
        // 将伪节点与链表的头节点相连。
        dummyHead.next = head;

        // 要反转链表头节点的上一个节点，将prev和end，先指向同一个位置
        // 都指向要反转链表的头节点的上一个节点
        ListNode prev = dummyHead;
        // 要反转链表尾节点，
        ListNode end = dummyHead;

        // end.next ！=null 表示链表到达链表的尾节点
        while (end.next != null) {
            // 循环k 次，将end节点移动 k 步。end！=null，是为了防止 end=end.next报空指针。
            for (int i = 0; i < k && end != null; i++) {
                end = end.next;
            }
            // 当不足k倍时，不反转剩余链表的节点
            if (end == null) {
                break;
            }
            // 反转链表的头部
            ListNode start = prev.next;
            // 反转链表尾部指向的节点，先存储起来，为了内在反转后，能重新连接上未反转的链表
            ListNode next = end.next;
            // 断开，要反转的链表和其余链表的连接
            end.next = null;
            // 反转后，先连接上头节点。
            prev.next = reverse(start);
            // 在连接尾节点，与不反转链表节点的连接
            start.next = next;
            // 将prev，换成下次要反转链表头节点的上一个节点
            prev = start;
            // 将end，也换成要反转链表头节点的上一个节点
            end = prev;
        }
        //返回伪节点的上一个节点
        return dummyHead.next;
    }

    public ListNode reverse(ListNode head) {
        ListNode prev = null;
        // 定义一个要反转的节点
        ListNode cur = head;
        while (cur != null) {
            // 用来连接反转后的链表节点，定义一个反转节点的下一个节点
            ListNode next = cur.next;
            // 将第一个节点的指向，反转
            cur.next = prev;
            // 将prev移动到，要反转节点的上一个节点
            prev = cur;
            // 将cur，移动到要下一个要反转的节点
            cur = next;
        }
        return prev;
    }
}
