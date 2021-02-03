package 二叉树;

/**
 * 【156.上下翻转二叉树】
 * 给定一个二叉树，其中所有的右节点要么是具有兄弟节点（拥有相同父节点的左节点）的叶节点，要么为空，
 * 将此二叉树上下翻转并将它变成一棵树， 原来的右节点将转换成左叶节点。返回新的根。
 *
 * 例子:
 * 输入: [1,2,3,4,5]
 *     1
 *    / \
 *   2   3
 *  / \
 * 4   5
 * 输出: 返回二叉树的根 [4,5,2,#,#,3,1]
 *    4
 *   / \
 *  5   2
 *     / \
 *    3   1
 *
 * 说明：二叉树的序列化遵循层次遍历规则，当没有节点存在时，'#' 表示路径终止符。
 */

import common.TreeNode;

/**
 * 【156. Binary Tree Upside Down】
 * Given the root of a binary tree, turn the tree upside down and return the new root.
 *
 * You can turn a binary tree upside down with the following steps:
 *
 * 1、The original left child becomes the new root.
 * 2、The original root becomes the new right child.
 * 3、The original right child becomes the new left child.
 *
 * The mentioned steps are done level by level, it is guaranteed that every node in the
 * given tree has either 0 or 2 children.
 */
public class _156_上下翻转二叉树 {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        TreeNode node1 = new TreeNode(2);
        TreeNode node2 = new TreeNode(3);
        TreeNode node3 = new TreeNode(4);
        TreeNode node4 = new TreeNode(5);
        root.left = node1;
        root.right = node2;
        node1.left = node3;
        node1.right = node4;
        TreeNode newRoot = upsideDownBinaryTree(root);
    }

    public static TreeNode upsideDownBinaryTree(TreeNode root) {
        // rterminator
        if (root == null || root.left == null) return root;

        // save
        TreeNode left = root.left;
        TreeNode right = root.right;

        // cut link
        root.left = null;
        root.right = null;

        // get new root
        TreeNode res = upsideDownBinaryTree(left);

        // reset link
        left.left = right;
        left.right = root;

        // return newRoot
        return res;
    }
}
