package 二叉树;

import common.TreeNode;

public class _236_二叉树的最近公共祖先 {
    /**
     * 去以 root 为根节点的二叉树中查找 p, q 的最近公共祖先
     * ① 如果p、q同时存在于这棵二叉树中，就能成功返回他们的最近公共祖先
     * ② 如果p、q不存在于这棵二叉树中，就返回null
     * ③ 如果只有 p 存在于这棵二叉树中，就返回 p
     * ④ 如果只有 q 存在于这棵二叉树中，就返回 q
     */
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || root == p || root == q) return root;
        // 去以 root.left 为根节点的二叉树中查找 p, q 的最近公共祖先
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        // 去以 root.right 为根节点的二叉树中查找 p, q 的最近公共祖先
        TreeNode right = lowestCommonAncestor(root.right, p, q);
        if (left != null && right != null) return root;
        return (left != null) ? left : right;
    }
}
