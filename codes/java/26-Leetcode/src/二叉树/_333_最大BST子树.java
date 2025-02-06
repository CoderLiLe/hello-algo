package 二叉树;

import common.TreeNode;

public class _333_最大BST子树 {
    private boolean isBST(TreeNode root) {
        return isBST(root, Integer.MAX_VALUE, Integer.MIN_VALUE);
    }

    private boolean isBST(TreeNode root, int max, int min) {
        if (root == null) return true;
        return min < root.val && root.val < max && isBST(root.left, root.val, min) && isBST(root.right, max, root.val);
    }

    private int nodesCount(TreeNode root) {
        if (root == null) return 0;
        return 1 + nodesCount(root.left) + nodesCount(root.right);
    }

    /**
     * 自顶向下
     */
    public int largetstBSTSubtree(TreeNode root) {
        if (root == null) return 0;
        if (isBST(root)) return nodesCount(root);
        return Math.max(largetstBSTSubtree(root.left), largetstBSTSubtree(root.right));
    }

    /**
     * 自低向上
     */
    public int largetstBSTSubtree2(TreeNode root) {
        return (root == null) ? 0 :  getInfo(root).size;
    }

    /**
     * 返回以 root 为根节点的二叉树的最大 BST 子树信息
     */
    private Info getInfo(TreeNode root) {
        if (root == null) return null;

        // li(left info)：左子树的最大BST子树信息
        Info li = getInfo(root.left);

        // ri(right info)：右子树的最大BST子树信息
        Info ri = getInfo(root.right);

        /**
         * 有4种情况，以 root 为根节点的二叉树就是一棵BST，最大BST就是其本身
         * ① li != null && ri != null
         * && li.root == root.left && root.val > li.max
         * && ri.root == root.right && root.val < ri.min
         *
         * ② li != null && ri == null
         * && li.root == root.left && root.val > li.max
         *
         * ③ li == null && ri != null
         * && ri.root == root.right && root.val < ri.min
         *
         * ④ li == null && ri == null
         */

        int leftBstSize = -1, rightBstSize = -1, max = root.val, min = root.val;
        if (li == null) {
            leftBstSize = 0;
        } else if (li.root == root.left && root.val > li.max) {
            leftBstSize = li.size;
            min = li.min;
        }

        if (ri == null) {
            rightBstSize = 0;
        } else if (ri.root == root.right && root.val < ri.min) {
            rightBstSize = ri.size;
            max = ri.max;
        }

        if (leftBstSize >= 0 && rightBstSize >= 0) {
            return new Info(root, 1 + leftBstSize + rightBstSize, max, min);
        }

        // 以 root 为根节点的二叉树不是BST
        // 返回最大BST子树的节点数量较多的Info
        if (li != null && ri != null) return (li.size > ri.size) ? li : ri;

        // 返回 li、ri中不为null的那个 Info
        return (li != null) ? li : ri;
    }

    /**
     * 最大BST子树信息
     */
    private static class Info {
        /** 根节点 */
        public TreeNode root;
        /** 节点总数 */
        public int size;
        /** 最小值 */
        public int min;
        /** 最大值 */
        public int max;

        public Info(TreeNode root, int size, int min, int max) {
            this.root = root;
            this.size = size;
            this.min = min;
            this.max = max;
        }
    }
}
