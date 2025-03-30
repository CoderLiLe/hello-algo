package DFS;

import common.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class _113_路径总和II {
    // 此题的坑是：sum 和节点的 val 可能是负数，路径要求到达叶子节点
    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        List<List<Integer>> list = new ArrayList<>();
        if (root == null) return list;
        dfs(root, sum, new ArrayList<>(), list);
        return list;
    }

    private void dfs(TreeNode node, int remain, List<Integer> nums, List<List<Integer>> list) {
        if (node == null) return;
        remain -= node.val;
        nums.add(node.val);
        // remain == 0 不代表结束，还要求到达叶子节点
        if (node.left == null && node.right == null) {
            if (remain == 0) list.add(new ArrayList<>(nums));
        } else {
            dfs(node.left, remain, nums, list);
            dfs(node.right, remain, nums, list);
        }
        nums.remove(nums.size() - 1);
    }

    public List<List<Integer>> pathSum1(TreeNode root, int targetSum) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> path = new LinkedList<>();
        preorderDfs(root, targetSum, res, path);
        return res;
    }

    private void preorderDfs(TreeNode root, int targetSum, List<List<Integer>> res, List<Integer> path) {
        if (root == null) {
            return;
        }
        path.add(root.val);

        if (root.left == null && root.right == null) {
            if (root.val == targetSum) {
                res.add(new ArrayList(path));
            }
            return;
        }

        if (root.left != null) {
            preorderDfs(root.left, targetSum - root.val, res, path);
            path.removeLast();
        }

        if (root.right != null) {
            preorderDfs(root.right, targetSum - root.val, res, path);
            path.removeLast();
        }
    }

    public List<List<Integer>> pathSum2(TreeNode root, int targetSum) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> path = new LinkedList<>();
        inorderDfs(root, targetSum, res, path);
        return res;
    }

    private void inorderDfs(TreeNode root, int targetSum, List<List<Integer>> res, List<Integer> path) {
        if (root == null) {
            return;
        }
        path.add(root.val);

        if (root.left != null) {
            inorderDfs(root.left, targetSum - root.val, res, path);
            path.removeLast();
        }

        if (root.left == null && root.right == null) {
            if (root.val == targetSum) {
                res.add(new ArrayList(path));
            }
            return;
        }

        if (root.right != null) {
            inorderDfs(root.right, targetSum - root.val, res, path);
            path.removeLast();
        }
    }

    public List<List<Integer>> pathSum3(TreeNode root, int targetSum) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> path = new LinkedList<>();
        postorderDfs(root, targetSum, res, path);
        return res;
    }

    private void postorderDfs(TreeNode root, int targetSum, List<List<Integer>> res, List<Integer> path) {
        if (root == null) {
            return;
        }
        path.add(root.val);

        if (root.left != null) {
            postorderDfs(root.left, targetSum - root.val, res, path);
            path.removeLast();
        }

        if (root.right != null) {
            postorderDfs(root.right, targetSum - root.val, res, path);
            path.removeLast();
        }

        if (root.left == null && root.right == null) {
            if (root.val == targetSum) {
                res.add(new ArrayList(path));
            }
            return;
        }
    }
}
