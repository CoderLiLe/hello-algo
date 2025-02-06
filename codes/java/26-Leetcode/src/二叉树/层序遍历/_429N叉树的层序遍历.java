package 二叉树.层序遍历;

import tools.Asserts;

import java.util.*;

public class _429N叉树的层序遍历 {
    public List<List<Integer>> levelOrder(Node root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        Queue<Node> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            List<Integer> level = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                Node node = queue.poll();
                level.add(node.val);

                if (node.children == null || node.children.isEmpty()) {
                    continue;
                }

                for (Node child : node.children) {
                    queue.offer(child);
                }
            }
            res.add(level);
        }
        return res;
    }

    public static void main(String[] args) {
        _429N叉树的层序遍历 obj = new _429N叉树的层序遍历();
        Node root = new Node(1);
        root.children = Arrays.asList(new Node(3), new Node(2), new Node(4));
        root.children.get(0).children = Arrays.asList(new Node(5), new Node(6));
        Asserts.test(obj.levelOrder(root).equals(Arrays.asList(Arrays.asList(1), Arrays.asList(3, 2, 4), Arrays.asList(5, 6))));
    }

    static class Node {
        public int val;
        public List<Node> children;

        public Node() {}

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, List<Node> _children) {
            val = _val;
            children = _children;
        }
    }
}