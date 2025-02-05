package 二叉树.二叉搜索树;

import common.TreeNode;
import tools.Asserts;

import java.util.Stack;

public class _098验证二叉搜索树 {
    /**
     * 迭代法
     */
    public boolean isValidBST(TreeNode root) {
       if (root == null) {
           return true;
       }

       Stack<TreeNode> stack = new Stack<>();
       TreeNode pre = null;
       TreeNode curr = root;
       while (curr != null || !stack.isEmpty()) {
           while (curr != null) {
               stack.push(curr);
               // 遍历左子树
               curr = curr.left;
           }

           // 出栈，处理中间节点
           curr = stack.pop();
           if (pre != null && curr.val <= pre.val) {
               return false;
           }
           pre = curr;

           // 遍历右子树
           curr = curr.right;
       }

       return true;
    }
    /**
     * 统一迭代法
     */
    public boolean isValidBST2(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        TreeNode pre = null;
        if (root != null) {
            stack.push(root);
        }
        while (!stack.isEmpty()) {
            TreeNode curr = stack.peek();
            if (curr != null) {
                stack.pop();
                if (curr.right != null) {
                    stack.push(curr.right);
                }
                stack.push(curr);
                stack.push(null);
                if (curr.left != null) {
                    stack.push(curr.left);
                }
            } else {
                stack.pop();
                TreeNode temp = stack.pop();
                if (pre != null && pre.val >= temp.val) {
                    return false;
                }
                pre = temp;
            }
        }
        return true;
    }

    // 递归
    TreeNode max;
    public boolean isValidBST3(TreeNode root) {
        if (root == null) {
            return true;
        }
        // 左
        boolean left = isValidBST(root.left);
        if (!left) {
            return false;
        }
        // 中
        if (max != null && root.val <= max.val) {
            return false;
        }
        max = root;
        // 右
        boolean right = isValidBST(root.right);
        return right;
    }

    public static void main(String[] args) {
        _098验证二叉搜索树 obj = new _098验证二叉搜索树();
        TreeNode root = new TreeNode(1);
        root.right = new TreeNode(2);
        root.right.left = new TreeNode(3);
        Asserts.test(!obj.isValidBST(root));
        Asserts.test(!obj.isValidBST2(root));
        Asserts.test(!obj.isValidBST3(root));

    }

}
