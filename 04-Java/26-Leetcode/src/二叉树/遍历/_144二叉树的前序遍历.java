package 二叉树.遍历;

import common.TreeNode;
import tools.Asserts;

import java.util.*;

public class _144二叉树的前序遍历 {
    // 回溯算法思路
    LinkedList<Integer> res = new LinkedList<>();

    public List<Integer> preorderTraversal(TreeNode root) {
        dfs(root);
        return res;
    }

    // 二叉树遍历函数
    private void dfs(TreeNode root) {
        if (root == null) {
            return;
        }
        // 前序遍历位置
        res.add(root.val);
        dfs(root.left);
        dfs(root.right);
    }

    public List<Integer> preorderTraversal2(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) {
            return res;
        }

        Deque<TreeNode> stack = new LinkedList<>();
        TreeNode node = root;
        while (!stack.isEmpty() || node != null) {
            while (node != null) {
                res.add(node.val);
                stack.push(node);
                node = node.left;
            }
            node = stack.pop();
            node = node.right;
        }
        return res;
    }

    public static void main(String[] args) {
        _144二叉树的前序遍历 obj = new _144二叉树的前序遍历();
        TreeNode root = new TreeNode(1);
        root.right = new TreeNode(2);
        root.right.left = new TreeNode(3);
        Asserts.test(obj.preorderTraversal(root).equals(Arrays.asList(1, 2, 3)));
        Asserts.test(obj.preorderTraversal2(root).equals(Arrays.asList(1, 2, 3)));
    }
}
