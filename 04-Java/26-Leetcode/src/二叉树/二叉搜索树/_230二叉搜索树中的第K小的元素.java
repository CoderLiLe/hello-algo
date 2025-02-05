package 二叉树.二叉搜索树;

import common.TreeNode;
import tools.Asserts;

import java.util.Deque;
import java.util.LinkedList;

public class _230二叉搜索树中的第K小的元素 {
    public int kthSmallest(TreeNode root, int k) {
        Deque<TreeNode> stack = new LinkedList<>();
        TreeNode node = root;
        while (!stack.isEmpty() || node != null) {
            while (node != null) {
                stack.push(node);
                node = node.left;
            }
            node = stack.pop();
            if (--k == 0) {
                return node.val;
            }
            node = node.right;
        }
        return -1;
    }

    // 记录结果
    int res = 0;
    // 记录当前元素的排名
    int rank = 0;

    public int kthSmallest2(TreeNode root, int k) {
        dfs(root, k);
        return res;
    }

    private void dfs(TreeNode node, int k) {
        if (node == null) {
            return;
        }

        dfs(node.left, k);

        rank++;
        if (k == rank) {
            res = node.val;
            return;
        }

        dfs(node.right, k);
    }

    private int k;

    public int kthSmallest3(TreeNode root, int k) {
        this.k = k;
        return dfs3(root);
    }

    private int dfs3(TreeNode node) {
        if (node == null) {
            // 题目保证节点值非负，用 -1 表示没有找到
            return -1;
        }

        int leftRes = dfs3(node.left);
        // 答案在左子树中
        if (leftRes != -1) {
            return leftRes;
        }

        // 答案就是当前节点
        if (--this.k == 0) {
            return node.val;
        }

        // 右子树会返回答案或者 -1
        return dfs3(node.right);
    }

    public static void main(String[] args) {
        _230二叉搜索树中的第K小的元素 obj = new _230二叉搜索树中的第K小的元素();
        test1(obj);
        test2(obj);
    }

    private static void test1(_230二叉搜索树中的第K小的元素 obj) {
        TreeNode root = new TreeNode(1);
        root.right = new TreeNode(2);
        root.right.left = new TreeNode(3);

        int k = 1;
        int res = 1;

        Asserts.test(obj.kthSmallest(root, k) == res);
        Asserts.test(obj.kthSmallest2(root, k) == res);
        Asserts.test(obj.kthSmallest3(root, k) == res);
    }

    private static void test2(_230二叉搜索树中的第K小的元素 obj) {
        TreeNode root = new TreeNode(5);
        TreeNode node3 = new TreeNode(3);
        TreeNode node2 = new TreeNode(2);
        TreeNode node1 = new TreeNode(1);
        TreeNode node6 = new TreeNode(6);
        TreeNode node4 = new TreeNode(4);
        root.left = node3;
        root.right = node6;
        node3.left = node2;
        node3.right = node4;
        node2.left = node1;

        int k = 3;
        int res = 3;

        Asserts.test(obj.kthSmallest(root, k) == res);
        Asserts.test(obj.kthSmallest2(root, k) == res);
        Asserts.test(obj.kthSmallest3(root, k) == res);
    }

}
