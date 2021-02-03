package 链表;

public class _203_移除链表元素 {
    public static void main(String[] args) {
        ListNode node1 = new ListNode(1);
        removeElements2(node1, 1);
    }

    static public ListNode removeElements(ListNode head, int val) {
        if (head == null) return null;

        // 新链表的头节点
        ListNode newHead = null;
        // 新链表的尾节点
        ListNode newTail = null;

        while (head != null) {
            if (head.val != val) {
                if (newTail == null) {
                    newHead = head;
                    newTail = head;
                } else {
                    newTail.next = head;
                    newTail = head;
                }
            }
            head = head.next;
        }
        if (newTail != null) {
            // 节点尾部的 next 要清空
            newTail.next = null;
        }

        return newHead;
    }

    static public ListNode removeElements2(ListNode head, int val) {
        if (head == null) return null;

        // 新链表的头节点
        ListNode newHead = new ListNode(0);
        // 新链表的尾节点
        ListNode newTail = newHead;

        while (head != null) {
            if (head.val != val) {
                newTail.next = head;
                newTail = head;
            }
            head = head.next;
        }

        // 节点尾部的 next 要清空
        newTail.next = null;

        return newHead.next;
    }
}



