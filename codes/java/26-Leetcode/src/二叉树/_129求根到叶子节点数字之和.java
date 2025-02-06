package 二叉树;

import common.TreeNode;
import tools.Asserts;

import java.util.ArrayList;
import java.util.List;

public class _129求根到叶子节点数字之和 {
    List<Integer> path = new ArrayList<>();
    private int res = 0;

    public int sumNumbers(TreeNode root) {
        // 如果节点为0，那么就返回0
        if (root == null) return 0;
        // 首先将根节点放到集合中
        path.add(root.val);
        // 开始递归
        backtrack(root);
        return res;
    }

    public void backtrack(TreeNode root) {
        if (root.left == null && root.right == null) {
            // 当是叶子节点的时候，开始处理
            res += listToInt(path);
            return;
        }

        if (root.left != null) {
            // 注意有回溯
            path.add(root.left.val);
            backtrack(root.left);
            path.remove(path.size() - 1);
        }

        if (root.right != null) {
            // 注意有回溯
            path.add(root.right.val);
            backtrack(root.right);
            path.remove(path.size() - 1);
        }
    }

    public int listToInt(List<Integer> path) {
        int sum = 0;
        for (Integer num : path) {
            // sum * 10 表示进位
            sum = sum * 10 + num;
        }
        return sum;
    }

    public static void main(String[] args) {
        _129求根到叶子节点数字之和 obj = new _129求根到叶子节点数字之和();
        TreeNode root = new TreeNode(1);
        root.right = new TreeNode(2);
        root.right.left = new TreeNode(3);
        Asserts.test(obj.sumNumbers(root) == 25);
    }
}
