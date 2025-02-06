package 二叉树.层序遍历;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

public class _117填充每个节点的下一个右侧节点指针II {
    public Node connect(Node root) {
        Node ans = root;
        if (root == null) return ans;
        Deque<Node> queue = new ArrayDeque<>();
        queue.addLast(root);
        while (!queue.isEmpty()) {
            int sz = queue.size();
            while (sz-- > 0) {
                Node curr = queue.pollFirst();
                if (sz != 0) {
                    curr.next = queue.peekFirst();
                }
                if (curr.left != null) {
                    queue.addLast(curr.left);
                }
                if (curr.right != null) {
                    queue.addLast(curr.right);
                }
            }
        }
        return ans;
    }

    /**
     * 时间复杂度：O(n)
     * 空间复杂度：O(1)
     * @param root
     * @return
     */
    public Node connect2(Node root) {
        Node ans = root;
        if (root == null) {
            return ans;
        }
        Node cur = root;
        while (cur != null) {
            Node head = new Node(-1), tail = head;
            for (Node i = cur; i != null; i = i.next) {
                if (i.left != null) {
                    tail = tail.next = i.left;
                }
                if (i.right != null) {
                    tail = tail.next = i.right;
                }
            }
            cur = head.next;
        }
        return ans;
    }

    public Node connect3(Node root) {
        Queue<Node> queue = new LinkedList<>();
        if (root != null) {
            queue.add(root);
        }
        while (!queue.isEmpty()) {
            int size = queue.size();
            Node node = null;
            Node nodePre = null;

            for (int i = 0; i < size; i++) {
                if (i == 0) {
                    nodePre = queue.poll(); // 取出本层头一个节点
                    node = nodePre;
                } else {
                    node = queue.poll();
                    nodePre.next = node; // 本层前一个节点 next 指向当前节点
                    nodePre = nodePre.next;
                }
                if (node.left != null) {
                    queue.add(node.left);
                }
                if (node.right != null) {
                    queue.add(node.right);
                }
            }
            nodePre.next = null; // 本层最后一个节点 next 指向 null
        }
        return root;
    }

    public static void main(String[] args) {
        _117填充每个节点的下一个右侧节点指针II obj = new _117填充每个节点的下一个右侧节点指针II();
        Node root = new Node(1);
        Node node2 = new Node(2);
        Node node3 = new Node(3);
        Node node4 = new Node(4);
        Node node5 = new Node(5);
        Node node7 = new Node(7);
        root.left = node2;
        root.right = node3;
        node2.left = node4;
        node2.right = node5;
        node3.right = node7;
        obj.connect(root);
    }

    static class Node {
        public int val;
        public Node left;
        public Node right;
        public Node next;

        public Node() {
        }

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, Node _left, Node _right, Node _next) {
            val = _val;
            left = _left;
            right = _right;
            next = _next;
        }
    }
}
