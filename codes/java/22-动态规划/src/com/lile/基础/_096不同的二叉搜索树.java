package com.lile.基础;

import com.lile.tools.Asserts;

public class _096不同的二叉搜索树 {
    /**
     * 1、dp[i]：1到i个节点组成的二叉搜索树的个数为dp[i]
     * 2、递推公式：dp[i] += dp[以j为头结点左子树节点数量] * dp[以j为头结点右子树节点数量]，j相当于是头结点的元素，从1遍历到i为止。
     *            dp[i] += dp[j - 1] * dp[i - j]; ，j-1 为j为头结点左子树节点数量，i-j 为以j为头结点右子树节点数量
     * 3、初始化：从定义上来讲，空节点也是一棵二叉树，也是一棵二叉搜索树，这是可以说得通的，dp[0] = 1
     * 4、确定遍历顺序：遍历i里面每一个数作为头结点的状态，用j来遍历
     * 5、举例推导dp数组
     *
     * 时间复杂度：O(n^2)
     * 空间复杂度：O(n)
     */
    public int numTrees(int n) {
        int[] dp = new int[n + 1];
        dp[0] = 1;
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= i; j++) {
                dp[i] += dp[j - 1] * dp[i - j];
            }
        }
        return dp[n];
    }

    public static void main(String[] args) {
        _096不同的二叉搜索树 obj = new _096不同的二叉搜索树();
        Asserts.test(obj.numTrees(3) == 5);
    }
}
