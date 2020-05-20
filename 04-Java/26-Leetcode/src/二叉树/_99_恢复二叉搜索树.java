package 二叉树;

import common.TreeNode;

public class _99_恢复二叉搜索树 {
    /**
     *  上一次中序遍历过的节点
     */
    private TreeNode prev;
    /**
     *  第一个错误节点
     */
    private TreeNode first;
    /**
     *  第二个错误节点
     */
    private TreeNode second;

    /**
     * 是一棵错误的二叉搜索树（有两个节点被错误交换）
     */
    public void recoverTree(TreeNode root) {
        findWrongNodes(root);
        int tmp = first.val;
        first.val = second.val;
        second.val = tmp;
    }

    private void findWrongNodes(TreeNode root) {
        if (root == null) return;

        findWrongNodes(root.left);
        find(root);
        findWrongNodes(root.right);
    }

    public void recoverTree2(TreeNode root) {
        TreeNode node = root;
        while (node != null) {
            if (node.left != null) {
                // 找到前驱节点(predecessor)、后继节点(successor)
                TreeNode pred = node.left;
                while (pred.right != null && pred.right != node) {
                    pred = pred.right;
                }

                if (pred.right == null) {
                    pred.right = node;
                    node = node.left;
                } else { // pred.right == node
                    find(node);
                    pred.right = null;
                    node = node.right;
                }
            } else {
                find(node);
                node = node.right;
            }
        }

        int tmp = first.val;
        first.val = second.val;
        second.val = tmp;
    }

    private void find(TreeNode node) {
        // 出现了逆序对
        if (prev != null && prev.val > node.val) {
            // 第二个错误节点：最后一个逆序对中较小的那个节点
            second = node;

            // 第一个错误节点：第一个逆序对中较大的那个节点
            if (first != null) return;
            first = prev;
        }
        prev = node;
    }
}
