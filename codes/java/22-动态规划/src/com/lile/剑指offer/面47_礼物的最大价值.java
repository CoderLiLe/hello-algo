package com.lile.剑指offer;


import com.lile.tools.Asserts;
import com.lile.tools.Times;

public class 面47_礼物的最大价值 {
	public static void main(String[] args) {
		int[][] grid = {{1,3,1},{1,5,1},{4,2,1}};
		Times.test("二维数组1", () -> {
			Asserts.test(maxValue1(grid) == 12);
		});
		Times.test("二维数组2", () -> {
			Asserts.test(maxValue2(grid) == 12);
		});
		Times.test("一维数组", () -> {
			Asserts.test(maxValue3(grid) == 12);
		});
		Times.test("一维数组2", () -> {
			Asserts.test(maxValue4(grid) == 12);
		});
	}
	private static int maxValue1(int[][] grid) {
		if (grid == null) return 0;
		
		int[][] result = new int[grid.length][grid[0].length];
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[i].length; j++) {
				if (i == 0 && j == 0) {
                    result[i][j] = grid[i][j];
                } else if (i == 0) {
					result[i][j] = result[i][j - 1] + grid[i][j];
				} else if (j == 0) {
					result[i][j] = result[i - 1][j] + grid[i][j];
				} else {
					result[i][j] = Math.max(result[i - 1][j], result[i][j - 1]) + grid[i][j];
				}
			}
		}
		
		return result[grid.length - 1][grid[0].length - 1];
    }

	private static int maxValue2(int[][] grid) {
		if (grid == null) return 0;
		
		int rows = grid.length;
		int cols = grid[0].length;
		
		int[][] dp = new int[rows][cols];
		dp[0][0] = grid[0][0];
		// 第 0 行
		for (int col = 1; col < cols; col++) {
			dp[0][col] = dp[0][col - 1] + grid[0][col];
		}
		// 第 0 列
		for (int row = 1; row < rows; row++) {
			dp[row][0] = dp[row - 1][0] + grid[row][0];
		}
		for (int row = 1; row < rows; row++) {
			for (int col = 1; col < cols; col++) {
				dp[row][col] = Math.max(dp[row - 1][col], dp[row][col - 1]) + grid[row][col];
			}
		}
		
		return dp[rows - 1][cols - 1];
    }

	private static int maxValue3(int[][] grid) {
		if (grid == null) return 0;
		
		int rows = grid.length;
		int cols = grid[0].length;
		
		int[] dp = new int[cols];
		for (int row = 0; row < rows; row++) {
			for (int col = 0; col < cols; col++) {
				int left = 0;
				int up = 0;
				
				if (row > 0) {
					up = dp[col];
				}
				
				if (col > 0) {
					left = dp[col - 1];
				}
				
				dp[col] = Math.max(left, up) + grid[row][col];
			}
		}
		
		return dp[cols - 1];
    }

	private static int maxValue4(int[][] grid) {
		if (grid == null) return 0;

		int rows = grid.length;
		int cols = grid[0].length;
		int[] dp = new int[cols];
		for (int row = 0; row < rows; row++) {
			int left = 0;
			for (int col = 0; col < cols; col++) {
				dp[col] = Math.max(left, dp[col]) + grid[row][col];
				left = dp[col];
			}
		}
		return dp[cols - 1];
	}
}
