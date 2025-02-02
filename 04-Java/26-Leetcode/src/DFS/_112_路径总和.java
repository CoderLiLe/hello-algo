package DFS;

import common.TreeNode;
import tools.Asserts;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class _112_路径总和 {
    public boolean hasPathSum(TreeNode root, int sum) {
        return dfs(root, sum);
    }

    private boolean dfs(TreeNode node, int sum) {
        if (node == null) return false;

        sum = sum - node.val;
        if (node.left == null && node.right == null) {
            return sum == 0;
        }

        return dfs(node.left, sum) || dfs(node.right, sum);
    }

    public boolean hasPathSum2(TreeNode root, int targetSum) {
        if (root == null) return false;
        Queue<TreeNode> nodeQueue = new LinkedList<>();
        Queue<Integer> sumQueue = new LinkedList<>();
        nodeQueue.offer(root);
        sumQueue.offer(root.val);
        while (!nodeQueue.isEmpty()) {
            TreeNode node = nodeQueue.poll();
            Integer sum = sumQueue.poll();

            if (node.left == null && node.right == null && sum == targetSum) {
                return true;
            }

            if (node.left != null) {
                nodeQueue.offer(node.left);
                sumQueue.offer(sum + node.left.val);
            }

            if (node.right != null) {
                nodeQueue.offer(node.right);
                sumQueue.offer(sum + node.right.val);
            }

        }
        return false;
    }

    public boolean hasPathSum3(TreeNode root, int targetSum) {
        if (root == null) return false;
        Stack<TreeNode> nodeStack = new Stack<>();
        Stack<Integer> sumStack = new Stack<>();
        nodeStack.push(root);
        sumStack.push(root.val);
        while (!nodeStack.isEmpty()) {
            TreeNode node = nodeStack.pop();
            Integer sum = sumStack.pop();

            if (node.left == null && node.right == null && sum == targetSum) {
                return true;
            }

            if (node.right != null) {
                nodeStack.push(node.right);
                sumStack.push(sum + node.right.val);
            }

            if (node.left != null) {
                nodeStack.push(node.left);
                sumStack.push(sum + node.left.val);
            }

        }
        return false;
    }

    public static void main(String[] args) {
        _112_路径总和 obj = new _112_路径总和();
        TreeNode root = new TreeNode(5);
        TreeNode node1 = new TreeNode(4);
        TreeNode node2 = new TreeNode(8);
        TreeNode node3 = new TreeNode(11);
        TreeNode node4 = new TreeNode(13);
        TreeNode node5 = new TreeNode(4);
        TreeNode node6 = new TreeNode(7);
        TreeNode node7 = new TreeNode(2);
        TreeNode node8 = new TreeNode(1);
        root.left = node1;
        root.right = node2;
        node1.left = node3;
        node2.left = node4;
        node2.right = node5;
        node3.left = node6;
        node3.right = node7;
        node5.right = node8;
        Asserts.test(obj.hasPathSum(root, 22));
        Asserts.test(obj.hasPathSum2(root, 22));
        Asserts.test(obj.hasPathSum3(root, 22));
    }
}
