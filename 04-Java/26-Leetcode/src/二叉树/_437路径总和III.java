package 二叉树;

import common.TreeNode;
import tools.Asserts;

import java.util.HashMap;
import java.util.Map;

public class _437路径总和III {
    /**
     * 深度优先搜索
     * 使用深度优先搜索算法计算从根节点到叶子节点的所有路径中，和为给定值的路
     * 时间复杂度：O(N^2)，其中 N 为该二叉树节点的个数。对于每一个节点，求以该节点为起点的路径数目时，
     * 则需要遍历以该节点为根节点的子树的所有节点，因此求该路径所花费的最大时间为 O(N)，我们会对每个节
     * 点都求一次以该节点为起点的路径数目，因此时间复杂度为 O(N^2)。
     * 空间复杂度：O(N)，考虑到递归需要在栈上开辟空间。
     *
     * @param root 树的根节点
     * @param targetSum 目标和
     * @return 满足条件的路径总数
     */
    public int pathSum(TreeNode root, int targetSum) {
        // 如果根节点为空，说明没有路径，返回0
        if (root == null) {
            return 0;
        }

        // 计算包括当前根节点的路径中，满足条件的路径数量
        int ret = rootSum(root, targetSum);
        // 递归计算左子树中满足条件的路径数量，并累加到ret中
        ret += pathSum(root.left, targetSum);
        // 递归计算右子树中满足条件的路径数量，并累加到ret中
        ret += pathSum(root.right, targetSum);
        // 返回总的满足条件的路径数量
        return ret;
    }

    /**
     * 计算从根节点到叶节点的路径中，所有节点值之和等于给定目标和的路径数量
     *
     * @param root 树的根节点
     * @param targetSum 目标和
     * @return 满足条件的路径数量
     */
    public int rootSum(TreeNode root, long targetSum) {
        // 初始化满足条件的路径数量为0
        int ret = 0;

        // 如果根节点为空，则没有路径，返回0
        if (root == null) {
            return 0;
        }
        // 获取当前节点的值
        int val = root.val;
        // 如果当前节点的值等于目标和，找到一条满足条件的路径，结果加1
        if (val == targetSum) {
            ret++;
        }

        // 递归计算左子树中满足条件的路径数量，并累加到结果中
        ret += rootSum(root.left, targetSum - val);
        // 递归计算右子树中满足条件的路径数量，并累加到结果中
        ret += rootSum(root.right, targetSum - val);
        // 返回满足条件的路径数量
        return ret;
    }


    /**
     * 前缀和
     * 计算给定二叉树中所有从根节点到叶子节点的路径中，路径和等于给定目标和的路径数量
     *
     * @param root 二叉树的根节点
     * @param targetSum 目标和
     * @return 满足条件的路径数量
     */
    public int pathSum2(TreeNode root, int targetSum) {
        // 使用哈希表记录从根节点到当前节点的路径上所有节点的前缀和及其出现次数
        Map<Long, Integer> prefix = new HashMap<>();
        // 初始化前缀和为0的情况，表示从根节点开始的路径
        prefix.put(0L, 1);
        // 调用深度优先搜索函数，从根节点开始遍历
        return dfs(root, prefix, 0, targetSum);
    }

    /**
     * 使用深度优先搜索（DFS）计算从根节点到叶节点的路径中，满足路径总和等于给定目标和的路径数量
     *
     * @param root 树的根节点
     * @param prefix 前缀和的哈希表，用于存储从根节点到当前节点的路径上的前缀和
     * @param curr 当前路径的累加和
     * @param targetSum 目标和
     * @return 满足路径总和等于目标和的路径数量
     */
    public int dfs(TreeNode root, Map<Long, Integer> prefix, long curr, int targetSum) {
        // 如果当前节点为空，说明已经到达树的底部，返回0
        if (root == null) {
            return 0;
        }

        int ret = 0;
        // 更新当前路径的累加和
        curr += root.val;

        // 检查是否存在前缀和等于当前累加和减去目标和，如果存在，说明找到了一条满足条件的路径
        ret = prefix.getOrDefault(curr - targetSum, 0);

        // 更新前缀和的哈希表，将当前累加和加入到哈希表中
        prefix.put(curr, prefix.getOrDefault(curr, 0) + 1);

        // 递归处理左子树和右子树，累加满足条件的路径数量
        ret += dfs(root.left, prefix, curr, targetSum);
        ret += dfs(root.right, prefix, curr, targetSum);

        // 回溯，将当前累加和从哈希表中减去1，避免影响其他路径的计算
        prefix.put(curr, prefix.getOrDefault(curr, 0) - 1);

        // 返回满足条件的路径数量
        return ret;
    }

    public static void main(String[] args) {
        _437路径总和III obj = new _437路径总和III();

        TreeNode root = new TreeNode(10);
        TreeNode node5 = new TreeNode(5);
        TreeNode node3 = new TreeNode(3);
        TreeNode node2 = new TreeNode(2);
        TreeNode node1 = new TreeNode(1);
        TreeNode node33 = new TreeNode(3);
        TreeNode node_2 = new TreeNode(-2);
        TreeNode node_3 = new TreeNode(-3);
        TreeNode node11 = new TreeNode(11);
        root.left = node5;
        root.right = node_3;
        node5.left = node3;
        node5.right = node2;
        node3.left = node33;
        node3.right = node_2;
        node2.right = node1;
        node_3.right = node11;

        Asserts.test(obj.pathSum(root, 8) == 3);
        Asserts.test(obj.pathSum2(root, 8) == 3);
    }
}
