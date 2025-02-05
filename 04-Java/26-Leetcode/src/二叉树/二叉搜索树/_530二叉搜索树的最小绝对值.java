package 二叉树.二叉搜索树;

import com.lile.tools.TreeNode;
import tools.Asserts;

import java.util.Stack;

public class _530二叉搜索树的最小绝对值 {
    public int getMinimumDifference(TreeNode root) {
        if (root == null) {
            return 0;
        }

        Stack<TreeNode> stack = new Stack<>();
        TreeNode pre = null;
        TreeNode curr = root;
        int min = Integer.MAX_VALUE;
        while (curr != null || !stack.isEmpty()) {
            while (curr != null) {
                stack.push(curr);
                // 遍历左子树
                curr = curr.left;
            }

            // 出栈，处理中间节点
            curr = stack.pop();
            if (pre != null) {
                min = Math.min(min, curr.val - pre.val);
            }
            pre = curr;

            // 遍历右子树
            curr = curr.right;
        }

        return min;
    }

    public int getMinimumDifference2(TreeNode root) {
        if (root == null) {
            return 0;
        }

        Stack<TreeNode> stack = new Stack<>();
        TreeNode pre = null;
        TreeNode curr = root;
        int min = Integer.MAX_VALUE;
        while (curr != null || !stack.isEmpty()) {
            if (curr != null) {
                stack.push(curr);
                // 遍历左子树
                curr = curr.left;
            } else {
                // 出栈，处理中间节点
                curr = stack.pop();
                if (pre != null) {
                    min = Math.min(min, curr.val - pre.val);
                }
                pre = curr;

                // 遍历右子树
                curr = curr.right;
            }
        }

        return min;
    }

    private TreeNode pre = null;
    private int result = Integer.MAX_VALUE;

    public int getMinimumDifference3(TreeNode root) {
        dfs(root);
        return result;
    }

    private void dfs(TreeNode node) {
        if (node == null) {
            return;
        }
        dfs(node.left);
        if (pre != null) {
            result = Math.min(result, node.val - pre.val);
        }
        pre = node;
        dfs(node.right);
    }

    public static void main(String[] args) {
        _530二叉搜索树的最小绝对值 obj = new _530二叉搜索树的最小绝对值();
        TreeNode root = new TreeNode(4);
        TreeNode node2 = new TreeNode(2);
        TreeNode node1 = new TreeNode(1);
        TreeNode node3 = new TreeNode(3);
        TreeNode node6 = new TreeNode(6);
        root.left = node2;
        root.right = node6;
        node2.left = node1;
        node2.right = node3;

        Asserts.test(obj.getMinimumDifference(root) == 1);
        Asserts.test(obj.getMinimumDifference2(root) == 1);
        Asserts.test(obj.getMinimumDifference3(root) == 1);
    }

}
