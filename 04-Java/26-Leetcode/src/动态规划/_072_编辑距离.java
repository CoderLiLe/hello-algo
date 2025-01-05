package 动态规划;

import tools.Asserts;
import tools.Times;

/**
 * https://leetcode-cn.com/problems/edit-distance/
 */
public class _072_编辑距离 {
	public static void main(String[] args) {
		Times.test("编辑距离", () -> {
			Asserts.test(minDistance("horse", "ros") == 3);
			Asserts.test(minDistance("intention", "execution") == 5);
		});
	}
	private static int minDistance(String word1, String word2) {
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
