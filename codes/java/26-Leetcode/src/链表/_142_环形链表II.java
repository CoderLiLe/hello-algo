package 链表;

import tools.Asserts;
import tools.Times;

import java.util.HashSet;
import java.util.Set;

public class _142_环形链表II {
    /**
     * 快慢指针法
     * 当快慢指针相遇时，让其中任一个指针指向头节点，然后让它俩以相同速度前进，再次相遇时所在的节点位置就是环开始的位置
     * 时间复杂度：O(n)
     * 空间复杂度：O(1)
     *
     * @param head
     * @return
     */
    public ListNode detectCycle(ListNode head) {
        if (head == null || head.next == null) {
            return null;
        }

        ListNode slow, fast;
        slow = fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            // 快慢指针相遇
            if (slow == fast) {
                break;
            }
        }

        // fast 为空，说明没有环
        if (fast == null || fast.next == null) {
            return null;
        }

        // 慢指针重新指向头节点
        slow = head;
        // 快慢指针同步前进，相交点就是环起点
        while (slow != fast) {
            slow = slow.next;
            fast = fast.next;
        }
        return slow;
    }

    /**
     * 快慢指针法2
     * 当快慢指针相遇时，让其中任一个指针指向头节点，然后让它俩以相同速度前进，再次相遇时所在的节点位置就是环开始的位置
     * 时间复杂度：O(n)
     * 空间复杂度：O(1)
     *
     * @param head
     * @return
     */
    public ListNode detectCycle2(ListNode head) {
        if (head == null || head.next == null) {
            return null;
        }

        ListNode slow = head;
        ListNode fast = head;

        // 跳出循环时则，快慢指针相遇
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;

            if (slow == fast) {
                // 慢指针重新指向头节点
                slow = head;
                // 快慢指针同步前进，相交点就是环起点
                while (slow != fast) {
                    slow = slow.next;
                    fast = fast.next;
                }
                return slow;
            }
        }

        // 如果没有环，返回 null
        return null;
    }


    /**
     * 哈希表判断
     * 时间复杂度：O(N)，其中 N 是链表中的节点数。最坏情况下我们需要遍历每个节点一次。
     * <p>
     * 空间复杂度：O(N)，其中 N 是链表中的节点数。主要为哈希表的开销，最坏情况下我们需要将每个节点插入到哈希表中一次。
     */
    public ListNode detectCycle3(ListNode head) {
        if (head == null || head.next == null) {
            return null;
        }

        ListNode pos = head;
        Set<ListNode> visited = new HashSet<>();
        while (pos != null) {
            if (visited.contains(pos)) {
                return pos;
            }
            visited.add(pos);
            pos = pos.next;
        }

        return null;
    }

    public static void main(String[] args) {
        _142_环形链表II obj = new _142_环形链表II();

        ListNode node4 = new ListNode(-4);
        ListNode node3 = new ListNode(0, node4);
        ListNode node2 = new ListNode(2, node3);
        ListNode node1 = new ListNode(3, node2);
        node4.next = node2;

        Times.test("快慢指针法", () -> {
            Asserts.test(obj.detectCycle(node1).equals(node2));
        });
        Times.test("快慢指针法2", () -> {
            Asserts.test(obj.detectCycle2(node1).equals(node2));
        });
        Times.test("哈希判断", () -> {
            Asserts.test(obj.detectCycle3(node1).equals(node2));
        });
    }
}
