package 链表;

import tools.Asserts;

/**
 * 此题和剑指 Offer II 029. 排序的循环链表相同
 * https://leetcode.cn/problems/4ueAj6/solutions/1352084/jian-zhi-offer-ii-029-pai-xu-de-xun-huan-a62w/
 *
 * 给定循环单调非递减列表中的一个点，写一个函数向这个列表中插入一个新元素 insertVal ，使这个列表仍然是循环升序的。
 *
 * 给定的可以是这个列表中任意一个顶点的指针，并不一定是这个列表中最小元素的指针。
 *
 * 如果有多个满足条件的插入位置，可以选择任意一个位置插入新的值，插入后整个列表仍然保持有序。
 *
 * 如果列表为空（给定的节点是 null），需要创建一个循环有序列表并返回这个节点。否则。请返回原先给定的节点。
 */
public class _708_循环有序列表的插入 {
    public static void main(String[] args) {
        CircularLinkedList list = new CircularLinkedList();
        list.add(3);
        list.add(4);
        list.add(1);
        list.display();
        System.out.println(list.getHead().val);
        insert(list.getHead(), 2);
        list.display();
        Asserts.test(list.toString().equals("[3,4,1,2]"));
    }

    private static ListNode insert(ListNode head, int insertVal) {
        ListNode node = new ListNode(insertVal);

        // 情况一：如果循环链表为空，则插入一个新节点并将新节点的 next 指针指向自身，插入新节点之后得到只有一个节点的循环链表，
        // 该循环链表一定是有序的，将插入的新节点作为新的头节点返回
        if (head == null) {
            node.next = node;
            return node;
        }

        // 情况二：如果循环链表的头节点的 next 指针指向自身，则循环链表中只有一个节点，在头节点之后插入新节点，将头节点的 next 指针指向新节点，
        // 将新节点的 next 指针指向头节点，此时循环链表中有两个节点且一定是有序的，返回头节点。
        if (head.next == head) {
            head.next = node;
            node.next = head;
            return head;
        }

        // 情况三：如果循环链表中的节点数大于 1，则需要从头节点开始遍历循环链表，寻找插入新节点的位置，使得插入新节点之后的循环链表仍然保持有序。
        //
        // 用 curr 和 next 分别表示当前节点和下一个节点，初始时 curr 位于 head，next 位于 head 的下一个节点，由于链表中的节点数大于 1，因此 curr
        // !=next。遍历过程中，判断值为 insertVal 的新节点是否可以在 curr 和 next 之间插入，如果符合插入要求则在 curr 和 next 之间插入新节点，
        // 否则将 curr 和 next 同时向后移动，直到找到插入新节点的位置或者遍历完循环链表中的所有节点。
        ListNode curr = head, next = head.next;
        while (next != head) {
            // 遍历过程中，如果找到插入新节点的位置，则有以下三种情况：

            // 此时新节点的值介于循环链表中的两个节点值之间，在 curr 和 next 之间插入新节点
            if (insertVal >= curr.val && insertVal <= next.val) {
                break;
            }

            // 此时 curr 和 next 分别是循环链表中的值最大的节点和值最小的节点
            if (curr.val > next.val) {

                // 新节点应该在 curr 的后面插入，即在 curr 和 next 之间插入新节点。
                if (insertVal > curr.val) {
                    break;
                }

                // 因此新节点应该在 next 的前面插入，即在 curr 和 next 之间插入新节点。
                if (insertVal < next.val) {
                    break;
                }
            }

            curr = curr.next;
            next = next.next;
        }

        curr.next = node;
        node.next = next;
        return head;
    }
}
