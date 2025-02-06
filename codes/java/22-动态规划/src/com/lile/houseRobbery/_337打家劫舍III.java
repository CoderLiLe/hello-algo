package com.lile.houseRobbery;

import com.lile.tools.Asserts;
import com.lile.tools.TreeNode;

import java.util.HashMap;
import java.util.Map;

public class _337打家劫舍III {
    /**
     * 暴力法
     * 时间复杂度：O(n^2)
     * 空间复杂度：O(log n)，算上递推系统栈的空间
     */
    public int rob(TreeNode root) {
        if (root == null) {
            return 0;
        }
        if (root.left == null && root.right == null) {
            return root.val;
        }

        // 偷父节点
        int val1 = root.val;
        if (root.left != null) {
            val1  += rob(root.left.left) + rob(root.left.right);
        }
        if (root.right != null) {
            val1  += rob(root.right.left) + rob(root.right.right);
        }

        // 不偷父节点
        int val2 = rob(root.left) + rob(root.right);

        return Math.max(val1, val2);
    }

    Map<TreeNode, Integer> map = new HashMap<>();

    /**
     * 记忆化递推
     * 时间复杂度：O(n)
     * 空间复杂度：O(logn)，算上递推系统栈的空间
     */
    public int rob2(TreeNode root) {
        if (root == null) {
            return 0;
        }
        if (root.left == null && root.right == null) {
            return root.val;
        }

        if (map.containsKey(root)) {
            return map.get(root);
        }

        // 偷父节点
        int val1 = root.val;
        if (root.left != null) {
            val1  += rob(root.left.left) + rob(root.left.right);
        }
        if (root.right != null) {
            val1  += rob(root.right.left) + rob(root.right.right);
        }

        // 不偷父节点
        int val2 = rob(root.left) + rob(root.right);

        int currMax = Math.max(val1, val2);

        map.put(root, currMax);

        return currMax;
    }

    /**
     * 1、dp[0]：不偷该节点所得到的的最大金钱
     *    dp[1]：偷该节点所得到的的最大金钱
     * 2、终止条件：遇到空节点，无论偷还是不偷都是0
     * 3、遍历顺序：递归左节点，得到左节点偷与不偷的金钱
     *            递归右节点，得到右节点偷与不偷的金钱
     * 4、单层递归逻辑：
     *      如果偷当前节点，那么左右孩子就不能偷，val1 = cur->val + left[0] + right[0];
     *      如果不偷当前节点，那么左右孩子就可以偷，val2 = max(left[0], left[1]) + max(right[0], right[1]);
     *      最后当前节点的状态就是{val2, val1}; 即：{不偷当前节点得到的最大金钱，偷当前节点得到的最大金钱}
     *
     * 时间复杂度：O(n)，每个节点只遍历了一次
     * 空间复杂度：O(log n)，算上递推系统栈的空间
     */
    public int rob3(TreeNode root) {
        int[] result = robTree(root);
        return Math.max(result[0], result[1]);
    }

    private int[] robTree(TreeNode cur) {
        if (cur == null) {
            return new int[]{0, 0};
        }
        int[] left = robTree(cur.left);
        int[] right = robTree(cur.right);
        // 偷当前节点，则左右子节点就不能偷
        int val1 = cur.val + left[0] + right[0];
        // 不偷当前节点，则左右子节点可以偷
        int val2 = Math.max(left[0], left[1]) + Math.max(right[0], right[1]);
        return new int[]{val2, val1};
    }

    public static void main(String[] args) {
        _337打家劫舍III obj = new _337打家劫舍III();

        TreeNode root = new TreeNode(3);
        TreeNode node2 = new TreeNode(2);
        TreeNode node3 = new TreeNode(3);
        TreeNode node33 = new TreeNode(3);
        TreeNode node1 = new TreeNode(1);
        root.left = node2;
        root.right = node3;
        node2.right = node33;
        node3.right = node1;

        Asserts.test(obj.rob(root) == 7);
        Asserts.test(obj.rob2(root) == 7);
        Asserts.test(obj.rob3(root) == 7);
    }
}
