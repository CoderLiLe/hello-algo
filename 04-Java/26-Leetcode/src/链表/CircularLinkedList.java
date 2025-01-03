package 链表;

public class CircularLinkedList {
    private ListNode head;
    private ListNode tail;

    public CircularLinkedList() {
        this.head = null;
        this.tail = null;
    }

    // 添加节点到链表末尾
    public void add(int data) {
        ListNode newNode = new ListNode(data);
        if (head == null) {
            head = newNode;
            tail = newNode;
            newNode.next = head; // 形成循环
        } else {
            tail.next = newNode;
            tail = newNode;
            tail.next = head; // 形成循环
        }
    }

    // 删除指定值的节点
    public void delete(int data) {
        if (head == null) {
            System.out.println("链表为空");
            return;
        }

        ListNode current = head;
        ListNode previous = null;

        // 如果头节点是要删除的节点
        if (current.val == data) {
            if (current.next == head) { // 只有一个节点
                head = null;
                tail = null;
            } else {
                while (current.next != head) {
                    current = current.next;
                }
                current.next = head.next;
                head = head.next;
                tail.next = head; // 形成循环
            }
            return;
        }

        // 查找要删除的节点
        do {
            previous = current;
            current = current.next;
            if (current.val == data) {
                previous.next = current.next;
                if (current == tail) { // 如果删除的是尾节点
                    tail = previous;
                }
                return;
            }
        } while (current != head);

        System.out.println("未找到节点");
    }

    public ListNode getHead() {
        return head;
    }

    // 显示链表中的所有节点
    public void display() {
        if (head == null) {
            System.out.println("链表为空");
            return;
        }

        ListNode current = head;
        do {
            System.out.print(current.val + " ");
            current = current.next;
        } while (current != head);
        System.out.println();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");

        if (head != null) {
            ListNode current = head;
            do {
                sb.append(current.val);
                current = current.next;
                if (current != head) {
                    sb.append(",");
                }
            } while (current != head);
        }

        sb.append("]");
        return sb.toString();
    }

    public static void main(String[] args) {
        CircularLinkedList list = new CircularLinkedList();
        list.add(10);
        list.add(20);
        list.add(30);
        list.add(40);

        System.out.println("原始链表:");
        list.display();

        list.delete(20);
        System.out.println("删除节点 20 后的链表:");
        list.display();

        list.delete(10);
        System.out.println("删除节点 10 后的链表:");
        list.display();

        list.delete(40);
        System.out.println("删除节点 40 后的链表:");
        list.display();

        list.delete(30);
        System.out.println("删除节点 30 后的链表:");
        list.display();
    }
}

