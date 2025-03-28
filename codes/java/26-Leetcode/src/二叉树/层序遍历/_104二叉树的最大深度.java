package 二叉树.层序遍历;

import common.TreeNode;
import tools.Asserts;

import java.util.LinkedList;
import java.util.Queue;

public class _104二叉树的最大深度 {

    public int maxDepth(TreeNode root) {
        int depth = 0;

        Queue<TreeNode> queue = new LinkedList<>();
        if (root != null) {
            queue.offer(root);
        }

        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                if (0 == i) {
                    depth++;
                }
                TreeNode node = queue.poll();
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }
        }
        return depth;
    }

    public static void main(String[] args) {
        _104二叉树的最大深度 obj = new _104二叉树的最大深度();
        TreeNode root = new TreeNode(1);
        TreeNode node2 = new TreeNode(2);
        TreeNode node3 = new TreeNode(3);
        TreeNode node4 = new TreeNode(4);
        TreeNode node5 = new TreeNode(5);
        TreeNode node6 = new TreeNode(6);
        TreeNode node7 = new TreeNode(7);
        TreeNode node8 = new TreeNode(8);
        root.left = node2;
        root.right = node3;
        node2.left = node4;
        node2.right = node5;
        node3.left = node6;
        node3.right = node7;
        node4.left = node8;
        Asserts.test(obj.maxDepth(root) == 4);
    }
}
