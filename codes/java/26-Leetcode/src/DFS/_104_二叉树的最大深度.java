package DFS;

import common.TreeNode;

public class _104_二叉树的最大深度 {
    public int maxDepth(TreeNode root) {
        if (root == null) return 0;

        return dfs(root);
    }

    private int dfs(TreeNode node) {
        if (node == null) {
            return 0;
        }
        if (node.left == null && node.right == null) { // 到达叶子节点
            return 1;
        }

        return Math.max(dfs(node.left), dfs(node.right)) + 1;
    }
}
