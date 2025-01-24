package com.lile.子序列;

import com.lile.tools.Asserts;
import com.lile.tools.Times;

/**
 * https://leetcode-cn.com/problems/edit-distance/
 */
public class _072_编辑距离 {
	public static void main(String[] args) {
		_072_编辑距离 obj = new _072_编辑距离();

		Times.test("编辑距离", () -> {
			Asserts.test(obj.minDistance("horse", "ros") == 3);
			Asserts.test(obj.minDistance("intention", "execution") == 5);
		});

		Times.test("编辑距离2", () -> {
			Asserts.test(obj.minDistance2("horse", "ros") == 3);
			Asserts.test(obj.minDistance2("intention", "execution") == 5);
		});
	}

	/**
	 * 二维dp动规五部曲：
	 * （1）dp[i][j]：示以下标i-1为结尾的字符串word1，和以下标j-1为结尾的字符串word2，最近编辑距离为dp[i][j]。
	 * （2）递推公式：在确定递推公式的时候，首先要考虑清楚编辑的几种操作，整理如下：
	 * 		if (word1[i - 1] == word2[j - 1])
	 *     		不操作
	 *     		dp[i][j] = dp[i - 1][j - 1];
	 * 		if (word1[i - 1] != word2[j - 1])
	 *     		增
	 *     			word2添加一个元素，相当于word1删除一个元素
	 *     		删
	 *     			操作一：word1删除一个元素，那么就是以下标i - 2为结尾的word1 与 j-1为结尾的word2的最近编辑距离 再加上一个操作
	 *     			即 dp[i][j] = dp[i - 1][j] + 1;
	 *     			操作二：word2删除一个元素，那么就是以下标i - 1为结尾的word1 与 j-2为结尾的word2的最近编辑距离 再加上一个操作。
	 *     			即 dp[i][j] = dp[i][j - 1] + 1;
	 *     		换
	 *     			dp[i][j] = dp[i - 1][j - 1] + 1;
	 *     		dp[i][j] = min({dp[i - 1][j - 1], dp[i - 1][j], dp[i][j - 1]}) + 1;
	 * （3）dp数组初始化：dp[i][0] ：以下标i-1为结尾的字符串word1，和空字符串word2，最近编辑距离为dp[i][0]。
	 *					那么dp[i][0]就应该是i，对word1里的元素全部做删除操作，即：dp[i][0] = i;
	 *					同理dp[0][j] = j;
	 * （4）遍历顺序：从左到右一层一层遍历
	 * （5）举例推导
	 *
	 * T = O(m * n), S = O(m * n)
	 */
	public int minDistance(String word1, String word2) {
		int m = word1.length();
		int n = word2.length();
		int[][] dp = new int[m + 1][n + 1];
		// 初始化
		for (int i = 1; i <= m; i++) {
			dp[i][0] =  i;
		}
		for (int j = 1; j <= n; j++) {
			dp[0][j] = j;
		}
		for (int i = 1; i <= m; i++) {
			for (int j = 1; j <= n; j++) {
				// 因为dp数组有效位从1开始
				// 所以当前遍历到的字符串的位置为i-1 | j-1
				if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
					dp[i][j] = dp[i - 1][j - 1];
				} else {
					dp[i][j] = Math.min(Math.min(dp[i - 1][j - 1], dp[i][j - 1]), dp[i - 1][j]) + 1;
				}
			}
		}
		return dp[m][n];
	}


	public int minDistance2(String word1, String word2) {
		if (word1 == null || word2 == null) return 0;
		char[] cs1 = word1.toCharArray();
		char[] cs2 = word2.toCharArray();

		// dp[i][j] 代表 word1 中前 i 个字符，变换到 word2 中前 j 个字符，最短需要操作的次数
		int[][] dp = new int[cs1.length + 1][cs2.length + 1];

		// dp[0][0]代表 word1 的空子串转换为 word2 的空子串的最少操作数
		dp[0][0] = 0;

		// 预留 dp[0][j] 和 dp[i][0]
		// 第 0 列，word2 一个字母都没有，即全删除的情况
		for (int i = 1; i <= cs1.length; i++) {
			dp[i][0] = i;
		}
		// 第 0 行，word1 一个字母都没有，即全增加的情况
		for (int j = 1; j <= cs2.length; j++) {
			dp[0][j] = j;
		}
		// 其它行其它列
		for (int i = 1; i <= cs1.length; i++) {
			for (int j = 1; j <= cs2.length; j++) {
				// 删，dp[i][j] = dp[i - 1][j] + 1
				int top = dp[i - 1][j] + 1;
				// 增，dp[i][j] = dp[i][j - 1] + 1
				int left = dp[i][j - 1] + 1;

				int leftTop = 0;
				// 改，dp[i][j] = dp[i - 1][j - 1] + 1
				if (cs1[i - 1] != cs2[j - 1]) {
					leftTop = dp[i - 1][j - 1] + 1;
				} else {
					leftTop = dp[i - 1][j - 1];
				}

				dp[i][j] = Math.min(Math.min(top, left), leftTop);
			}
		}
		return dp[cs1.length][cs2.length];
    }
}
