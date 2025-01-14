package 二叉树;

import common.TreeNode;
import tools.Asserts;

/**
 *
 * 给你一棵二叉树的根节点，返回该树的 直径 。
 *
 * 二叉树的 直径 是指树中任意两个节点之间最长路径的 长度 。这条路径可能经过也可能不经过根节点 root 。
 *
 * 两节点之间路径的 长度 由它们之间边数表示。
 */
public class _543二叉树的直径 {
    private int res;
    public int diameterOfBinaryTree(TreeNode root) {
        dfs(root);
        return res;
    }

    /**
     * 时间复杂度：O(n)，其中 n 为二叉树的节点个数。
     * 空间复杂度：O(n)。最坏情况下，二叉树退化成一条链，递归需要 O(n) 的栈空间。
     * @param root
     * @return
     */
    private int dfs(TreeNode root) {
        if (root == null) {
            return -1;
        }
        int lLen = dfs(root.left) + 1;
        int rLen = dfs(root.right) + 1;
        res = Math.max(res, lLen + rLen);
        return Math.max(lLen, rLen);
    }

    public static void main(String[] args) {
        _543二叉树的直径 obj = new _543二叉树的直径();
        TreeNode root = new TreeNode(1);
        TreeNode node1 = new TreeNode(2);
        TreeNode node2 = new TreeNode(3);
        TreeNode node3 = new TreeNode(4);
        TreeNode node4 = new TreeNode(5);
        root.left = node1;
        root.right = node2;
        node1.left = node3;
        node1.right = node4;

        Asserts.test(obj.diameterOfBinaryTree(root) == 3);

    }
}
