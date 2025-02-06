package 二叉树;

import common.TreeNode;

public class _236_二叉树的最近公共祖先 {

    /**
     * 时间复杂度 O(N) ： 其中 N 为二叉树节点数；最差情况下，需要递归遍历树的所有节点。
     * 空间复杂度 O(N) ： 最差情况下，递归深度达到 N ，系统使用 O(N) 大小的额外空间。
     *
     * @param root
     * @param p
     * @param q
     * @return
     */
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) {
            return null;
        }

        // 只要当前根节点是p和q中的任意一个，就返回（因为不能比这个更深了，再深p和q中的一个就没了）
        if (root == p || root == q) {
            return root;
        }

        // 根节点不是p和q中的任意一个，那么就继续分别往左子树和右子树找p和q
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);

        // p和q都没找到，那就没有
        if(left == null && right == null) {
            return null;
        }

        // 左子树没有p也没有q，就返回右子树的结果
        if (left == null) {
            return right;
        }

        // 右子树没有p也没有q就返回左子树的结果
        if (right == null) {
            return left;
        }

        // 左右子树都找到p和q了，那就说明p和q分别在左右两个子树上，所以此时的最近公共祖先就是root
        return root;
    }

    /**
     * 去以 root 为根节点的二叉树中查找 p, q 的最近公共祖先
     * ① 如果p、q同时存在于这棵二叉树中，就能成功返回他们的最近公共祖先
     * ② 如果p、q不存在于这棵二叉树中，就返回null
     * ③ 如果只有 p 存在于这棵二叉树中，就返回 p
     * ④ 如果只有 q 存在于这棵二叉树中，就返回 q
     */
    public TreeNode lowestCommonAncestor2(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || root == p || root == q) return root;
        // 去以 root.left 为根节点的二叉树中查找 p, q 的最近公共祖先
        TreeNode left = lowestCommonAncestor2(root.left, p, q);
        // 去以 root.right 为根节点的二叉树中查找 p, q 的最近公共祖先
        TreeNode right = lowestCommonAncestor2(root.right, p, q);
        if (left != null && right != null) return root;
        return (left != null) ? left : right;
    }
}
