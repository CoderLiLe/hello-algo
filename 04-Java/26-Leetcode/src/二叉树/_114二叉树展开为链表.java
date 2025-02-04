package 二叉树;

import common.TreeNode;

public class _114二叉树展开为链表 {
    /**
     * 定义：将以 root 为根的树拉平为链表
     */
    public void flatten(TreeNode root) {
        // base case
        if (root == null) return;

        // 利用定义，把左右子树拉平
        flatten(root.left);
        flatten(root.right);

        // *** 后序遍历位置 ***
        // 1、左右子树已经被拉平成一条链表
        TreeNode left = root.left;
        TreeNode right = root.right;

        // 2、将左子树作为右子树
        root.left = null;
        root.right = left;

        // 3、将原先的右子树接到当前右子树的末端
        TreeNode p = root;
        while (p.right != null) {
            p = p.right;
        }
        p.right = right;
    }

    public static void main(String[] args) {
        _114二叉树展开为链表 obj = new _114二叉树展开为链表();

        TreeNode root = new TreeNode(1);
        root.right = new TreeNode(2);
        root.right.left = new TreeNode(3);
        obj.flatten(root);
    }
}
