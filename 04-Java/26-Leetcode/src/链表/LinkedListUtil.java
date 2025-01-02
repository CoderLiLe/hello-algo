package 链表;

public class LinkedListUtil {
    public static void print(ListNode head) {
        System.out.print("[");
        while (head != null) {
            System.out.print(head.val);
            if (head.next != null) {
                System.out.print(", ");
            }

            head = head.next;
        }
        System.out.print("]");
    }

    public static String toString(ListNode head) {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        while (head != null) {
            sb.append(head.val);
            if (head.next != null) {
                sb.append(",");
            }

            head = head.next;
        }
        sb.append("]");
        return sb.toString();
    }

    public static ListNode buildLinkedList(int[] nums) {
        ListNode nextNode = null;
        for (int i = nums.length - 1; i >= 0; i--) {
            ListNode node = new ListNode(nums[i], nextNode);
            nextNode = node;
        }
        return nextNode;
    }
}
