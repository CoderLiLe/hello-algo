package 二叉树.遍历;

import common.TreeNode;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class _404左叶子之和 {
    public int sumOfLeftLeaves(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int leftValue = sumOfLeftLeaves(root.left);    // 左
        int rightValue = sumOfLeftLeaves(root.right);  // 右

        int midValue = 0;
        if (root.left != null && root.left.left == null && root.left.right == null) {
            midValue = root.left.val;
        }
        int sum = midValue + leftValue + rightValue;  // 中
        return sum;
    }

    public int sumOfLeftLeaves2(TreeNode root) {
        if (root == null) {
            return 0;
        }
        Stack<TreeNode> stack = new Stack<>();
        stack.add(root);
        int result = 0;
        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();

            if (node.left != null) {
                if (node.left.left == null && node.left.right == null) {
                    result += node.left.val;
                } else {
                    stack.add(node.left);
                }

            }

            if (node.right != null) {
                stack.add(node.right);
            }
        }
        return result;
    }

    public int sumOfLeftLeaves3(TreeNode root) {
        int sum = 0;
        if (root == null) {
            return sum;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            if (node.left != null) {
                if (node.left.left == null && node.left.right == null) {
                    sum += node.left.val;
                } else {
                    queue.add(node.left);
                }
            }
            if (node.right != null) {
                queue.add(node.right);
            }
        }
        return sum;
    }


}
