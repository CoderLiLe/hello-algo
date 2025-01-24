package com.lile.基础;

import com.lile.tools.Asserts;
import com.lile.tools.Times;

import java.util.Arrays;

public class _062_不同路径 {

	/**
	 * 一个机器人位于一个 m x n 网格的左上角 （起始点在下图中标记为“Start” ）。
	 * 机器人每次只能向下或者向右移动一步。机器人试图达到网格的右下角（在下图中标记为“Finish”）。
	 * 问总共有多少条不同的路径？
	 * 说明：m 和 n 的值均不超过 100。
	 */
	public static void main(String[] args) {
		_062_不同路径 obj = new _062_不同路径();
		Times.test("二维数组", () -> {
			Asserts.test(obj.uniquePaths1(3, 7) == 28);
			Asserts.test(obj.uniquePaths1(3, 2) == 3);
		});
		Times.test("两个一维数组", () -> {
			Asserts.test(obj.uniquePaths2(3, 7) == 28);
			Asserts.test(obj.uniquePaths2(3, 2) == 3);
		});
		Times.test("一个一维数组", () -> {
			Asserts.test(obj.uniquePaths3(3, 7) == 28);
			Asserts.test(obj.uniquePaths3(3, 2) == 3);
		});
		Times.test("数论方法", () -> {
			Asserts.test(obj.uniquePaths4(3, 7) == 28);
			Asserts.test(obj.uniquePaths4(3, 2) == 3);
		});
		Times.test("DFS", () -> {
			Asserts.test(obj.uniquePaths5(3, 7) == 28);
			Asserts.test(obj.uniquePaths5(3, 2) == 3);
		});
	}

	/**
	 * 二维dp动规五部曲：
	 * （1）dp[i][j]：表示从（0，0）出发，到(i, j) 有dp[i][j]条不同的路径
	 * （2）递推公式：dp[i][j] = dp[i - 1][j] + dp[i][j - 1]
	 * （3）dp数组初始化：dp[i][0] = 1;
	 * 				    dp[0][j] = 1;
	 * （4）遍历顺序：从左到右一层一层遍历
	 * （5）举例推导
	 *
	 * T = O(m * n), S = O(m * n)
	 */
	public int uniquePaths1(int m, int n) {
		int[][] dp = new int[m][n];
		for (int i = 0; i < m; i++) {
			dp[i][0] = 1;
		}
		for (int j = 0; j < n; j++) {
			dp[0][j] = 1;
		}
		for (int i = 1; i < m; i++) {
			for (int j = 1; j < n; j++) {
				dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
				//System.out.println("[" + i + ", " + j + "] = " + dp[i][j]);
			}
		}

		return dp[m - 1][n - 1];
	}

	public int uniquePaths2(int m, int n) {
		int[] pre = new int[n];
		int[] cur = new int[n];
		Arrays.fill(pre, 1);
		Arrays.fill(cur, 1);
		for (int i = 1; i < m; i++) {
			for (int j = 1; j < n; j++) {
				cur[j] = cur[j - 1] + pre[j];
			}
			pre = cur.clone();
		}

		return cur[n - 1];
	}
	
	/**
	 * 状态压缩：在二维dp数组中，当前值的计算只依赖正上方和正左方，因此可以压缩成一维数组
	 * T = O(m * n), S = O(n)
	 */
	public int uniquePaths3(int m, int n) {
        int[] dp = new int[n];
		// 初始化，第一行只能从正左方跳过来，所以只有一条路径。
        Arrays.fill(dp, 1);
        for (int i = 1; i < m; i++) {
			for (int j = 1; j < n; j++) {
				dp[j] = dp[j - 1] + dp[j];
			}
		}
        
		return dp[n - 1];
    }

	/**
	 * 计算从矩阵左上角到右下角的唯一路径数
	 * 使用数论方法优化计算过程，避免了传统的动态规划或者递归方法
	 * 在这个图中，可以看出一共m，n的话，无论怎么走，走到终点都需要 m + n - 2 步
	 * 在这m + n - 2 步中，一定有 m - 1 步是要向下走的，不用管什么时候向下走
	 * 可以转化为，给你m + n - 2个不同的数，随便取m - 1个数，有几种取法
	 * 所以，问题就转化为了求组合数：C(m + n - 2, m - 1) = C(m + n - 2, n - 1)
	 * T = O(m + n - 2), S = O(1)
	 *
	 * @param m 矩阵的行数
	 * @param n 矩阵的列数
	 * @return 返回从左上角到右下角的唯一路径数
	 */
	public int uniquePaths4(int m, int n) {
	    // 分子
	    long numerator = 1;
	    // 分母
	    int denominator = m - 1;
	    // 初始化计算组合数需要的循环次数
	    int count = m - 1;
	    // 初始化组合数计算中的t值
	    int t = m + n - 2;
	    // 循环计算组合数
	    while (count-- > 0) {
	        numerator *= t--;
	        // 简化分数，避免分子过大
	        while (denominator != 0 && numerator % denominator == 0) {
	            numerator /= denominator;
	            denominator--;
	        }
	    }
	    // 返回计算结果
	    return (int) numerator;
	}

	public int uniquePaths5(int m, int n) {
		return dfs(1, 1, m, n);
	}

	/**
	 * 使用深度优先搜索（DFS）算法来解决问题
	 * 这个深搜的算法，其实就是要遍历整个二叉树
	 * 这棵树的深度其实就是m+n-1（深度按从1开始计算）
	 * 那二叉树的节点个数就是 2^(m + n - 1) - 1。可以理解深搜的算法就是遍历了整个满二叉树（其实没有遍历整个满二叉树，只是近似而已）
	 * 所以上面深搜代码的时间复杂度为O(2^(m + n - 1) - 1)，可以看出，这是指数级别的时间复杂度，是非常大的
	 *
	 * @param i 当前位置的行坐标
	 * @param j 当前位置的列坐标
	 * @param m 行的上限
	 * @param n 列的上限
	 * @return 从当前位置到目标位置的路径数量
	 */
	private int dfs(int i, int j, int m, int n) {
	    // 越界了
	    if (i > m || j > n) {
	        return 0;
	    }

	    // 找到一种方法，相当于找到了叶子节点
	    if (i == m && j == n) {
	        return 1;
	    }

	    // 递归探索两条可能的路径：向下和向右
	    return dfs(i + 1, j, m, n) + dfs(i, j + 1, m, n);
	}


}
