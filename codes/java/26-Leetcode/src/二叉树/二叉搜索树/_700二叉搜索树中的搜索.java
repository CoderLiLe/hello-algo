package 二叉树.二叉搜索树;

import common.TreeNode;
import tools.Asserts;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class _700二叉搜索树中的搜索 {

    /**
     * 递归法
     */
    public TreeNode searchBST(TreeNode root, int val) {
        if (root == null || root.val == val) {
            return root;
        }
        if (root.val > val) {
            return searchBST(root.left, val);
        } else if (root.val < val) {
            return searchBST(root.right, val);
        } else {
            return root;
        }
    }


    public TreeNode searchBST2(TreeNode root, int val) {
        while (root != null) {
            if (root.val < val) {
                root = root.right;
            } else if (root.val > val) {
                root = root.left;
            } else {
                return root;
            }
        }
        return null;
    }

    /**
     * 对于一般的二叉树查找，使用栈来模拟递归的过程
     * @param root
     * @param val
     * @return
     */
    public TreeNode searchBST3(TreeNode root, int val) {
        Stack<TreeNode> stack = new Stack<>();
        if (root != null) {
            stack.push(root);
        }
        while (!stack.isEmpty()) {
            TreeNode curr = stack.pop();
            if (curr.val == val) {
                return curr;
            }
            if (curr.left != null) {
                stack.push(curr.left);
            }
            if (curr.right != null) {
                stack.push(curr.right);
            }
        }
        return null;
    }

    public TreeNode searchBST4(TreeNode root, int val) {
        if (root == null || root.val == val) {
            return root;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            if (node.val == val) {
                return node;
            }
            if (node.left != null) {
                queue.offer(node.left);
            }
            if (node.right != null) {
                queue.offer(node.right);
            }
        }
        return null;
    }

    public static void main(String[] args) {
        _700二叉搜索树中的搜索 obj = new _700二叉搜索树中的搜索();
        TreeNode root = new TreeNode(4);
        root.left = new TreeNode(2);
        root.right = new TreeNode(7);
        root.left.left = new TreeNode(1);
        root.left.right = new TreeNode(3);
        Asserts.test(obj.searchBST(root, 2) == root.left);
        Asserts.test(obj.searchBST2(root, 2) == root.left);
        Asserts.test(obj.searchBST3(root, 2) == root.left);
    }
}
