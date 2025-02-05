package com.lile;

import com.lile.tools.Asserts;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class _120三角形最小路径和 {
    /**
     * 一维dp动规五部曲：
     * （1）dp[i][j]含义：表示i行第j列位置的最小路径和
     * （2）递推公式：dp[i][j] = min(dp[i + 1][j], dp[i + 1][j + 1]) + triangle.get(i).get(j);
     * （3）dp数组初始化：dp[i][j] = 0
     * （4）遍历顺序：行从下到上，列从左到右，从下往上思考
     * （5）举例推导dp数组
     *
     * 时间复杂度: O(n^2)
     * 空间复杂度: O(n^2)
     */
    public int minimumTotal(List<List<Integer>> triangle) {
        if (triangle == null || triangle.size() == 0) {
            return 0;
        }
        int len = triangle.size();
        int[][] dp = new int[len + 1][len + 1];
        for (int i = len - 1; i >= 0; i--) {
            for (int j = 0; j < triangle.get(i).size(); j++) {
                dp[i][j] = Math.min(dp[i + 1][j], dp[i + 1][j + 1]) + triangle.get(i).get(j);
            }
        }
        return dp[0][0];
    }

    public int minimumTotal2(List<List<Integer>> triangle) {
        if (triangle == null || triangle.size() == 0) {
            return 0;
        }
        int len = triangle.size();
        int[] dp = new int[len + 1];
        for (int i = len - 1; i >= 0; i--) {
            for (int j = 0; j < triangle.get(i).size(); j++) {
                dp[j] = Math.min(dp[j], dp[j + 1]) + triangle.get(i).get(j);
            }
        }
        return dp[0];
    }

    public static void main(String[] args) {
        _120三角形最小路径和 obj = new _120三角形最小路径和();

        List<List<Integer>> triangle = new ArrayList<>();
        triangle.add(new ArrayList<>(Arrays.asList(2)));
        triangle.add(new ArrayList<>(Arrays.asList(3, 4)));
        triangle.add(new ArrayList<>(Arrays.asList(6, 5, 7)));
        triangle.add(new ArrayList<>(Arrays.asList(4, 1, 8, 3)));
        Asserts.test(obj.minimumTotal(triangle) == 11);
        Asserts.test(obj.minimumTotal2(triangle) == 11);
    }
}
