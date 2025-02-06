package 链表;

import java.util.Scanner;

public class HJ51输出单向链表中倒数第k个结点 {

    public static void main(String[] args) {
        // 1.输入内容
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            int n = sc.nextInt(); // 链表长度
            ListNode headNode = new ListNode(-1); // 定义表头
            ListNode temp = headNode;
            for (int i = 0; i < n; i++) {
                int value = sc.nextInt();
                temp.next = new ListNode(value);
                temp = temp.next;
            }

            // 2.遍历倒数第k节点的指针
            int k = sc.nextInt();
            for (int i = 0; i < n - k + 1; i++) { // 链表总长 - 倒数位置k + 本身算起1 = n-k+1
                headNode = headNode.next;
            }
            System.out.println(headNode.val);
        }
    }

    public static void main2(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            int n = sc.nextInt();
            ListNode head = new ListNode(-1);
            ListNode temp = head;
            //生成链表
            for (int i = 0; i < n; i++) {
                ListNode node = new ListNode(sc.nextInt());
                temp.next = node;
                temp = temp.next;
            }
            int k = sc.nextInt();
            if (getKthFromEnd(head.next, k) != null) {
                System.out.println(getKthFromEnd(head.next, k).val);
            } else {
                System.out.println(0);
            }

        }
    }

    public static ListNode getKthFromEnd(ListNode head, int k) {
        int n = 0;
        ListNode node = null;
        //记录有多少节点
        for (node = head; node != null; node = node.next) {
            n++;
        }
        //找倒数第k个
        for (node = head; n > k; n--) {
            node = node.next;
        }

        return node;
    }

    //通过快慢指针搜索
    public static ListNode getKthFromEnd2(ListNode head, int k) {
        if (head == null) return null;

        ListNode fast = head, slow = head;

        //快指针先走k步
        for (int i = 0; i < k; i++) {
            if (fast == null) return fast;
            fast = fast.next;
        }
        while (fast != null) {
            fast = fast.next;
            slow = slow.next;
        }
        return slow;
    }
}
