package 二叉树.查找;

import common.TreeNode;
import tools.Asserts;

/**
 * 【1214_查找两棵二叉搜索树之和】
 *
 * 给出两棵二叉搜索树，请你从两棵树中各找出一个节点，使得这两个节点的值之和等于目标值 Target。
 *
 * 如果可以找到返回 True，否则返回 False。
 */

public class _1214_查找两棵二叉搜索树之和 {
    public boolean twoSumBSTs(TreeNode root1, TreeNode root2, int target) {
        if (root1 == null) return false;
        return find(root2, target - root1.val) || twoSumBSTs(root1.left, root2, target) || twoSumBSTs(root1.right, root2, target);
    }

    private boolean find(TreeNode root, int value) {
        if (root == null) {
            return false;
        }
        if (root.val == value) {
            return true;
        } else if (root.val < value) {
            return find(root.right, value);
        } else {
            return find(root.left, value);
        }
    }

    public static void main(String[] args) {
        TreeNode root1 = new TreeNode(2);
        TreeNode node1 = new TreeNode(1);
        TreeNode node2 = new TreeNode(4);
        root1.left = node1;
        root1.right = node2;

        TreeNode root2 = new TreeNode(1);
        TreeNode node3 = new TreeNode(0);
        TreeNode node4 = new TreeNode(3);
        root2.left = node3;
        root2.right = node4;

        _1214_查找两棵二叉搜索树之和 bstSum = new _1214_查找两棵二叉搜索树之和();
        Asserts.test(bstSum.twoSumBSTs(root1, root2, 5));
    }
}
