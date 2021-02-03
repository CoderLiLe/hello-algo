package DFS;

import common.TreeNode;

public class _112_路径总和 {
    public boolean hasPathSum(TreeNode root, int sum) {
        return dfs(root, sum);
    }

    private boolean dfs(TreeNode node, int sum) {
        if (node == null) return false;

        sum = sum - node.val;
        if (node.left == null && node.right == null) {
            return sum == 0;
        }

        return dfs(node.left, sum) || dfs(node.right, sum);
    }
}
