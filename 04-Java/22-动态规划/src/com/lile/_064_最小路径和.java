package com.lile;

import com.lile.tools.Asserts;
import com.lile.tools.Times;

public class _064_最小路径和 {

	/**
	 * 二维dp动规五部曲：
	 * （1）dp[i][j]：表示从（0，0）出发，到(i, j) 路径上的最小数字总和为dp[i][j]
	 * （2）递推公式：dp[i][j] = min(dp[i - 1][j], dp[i][j - 1]) + grid[i][j]
	 * （3）dp数组初始化：dp[0][0] = grid[0][0];
	 * 				    第一行 dp[0][col] = dp[0][col - 1] + grid[0][col];
	 * 				    第一列 dp[row][0] = dp[row - 1][0] + grid[row][0];
	 * （4）遍历顺序：从左到右一层一层遍历
	 * （5）举例推导
	 *
	 * T = O(m * n), S = O(m * n)
	 */
	public int minPathSum1(int[][] grid) {
		int rows = grid.length;
		int cols = grid[0].length;

		int[][] dp = new int[rows][cols];
		dp[0][0] = grid[0][0];
		for (int col = 1; col < cols; col++) {
			dp[0][col] = dp[0][col - 1] + grid[0][col];
		}
		for (int row = 1; row < rows; row++) {
			dp[row][0] = dp[row - 1][0] + grid[row][0];
		}
		for (int row = 1; row < rows; row++) {
			for (int col = 1; col < cols; col++) {
				dp[row][col] = Math.min(dp[row-1][col], dp[row][col-1]) + grid[row][col];
			}
		}
		return dp[rows-1][cols-1];
	}

	public int minPathSum2(int[][] grid) {
		int rows = grid.length;
		int cols = grid[0].length;

		int[][] dp = new int[rows][cols];
		for (int row = 0; row < rows; row++) {
			for (int col = 0; col < cols; col++) {
				if (row == 0 && col == 0) {
					dp[row][col] = grid[row][col];
				} else if (row == 0) {
					dp[row][col] = dp[row][col - 1] + grid[row][col];
				} else if (col == 0) {
					dp[row][col] = dp[row - 1][col] + grid[row][col];
				} else {
					dp[row][col] = Math.min(dp[row-1][col], dp[row][col-1]) + grid[row][col];
				}
			}
		}
		return dp[rows-1][cols-1];
	}

	/**
	 * 滚动数组优化空间复杂度
	 * @param grid
	 * @return
	 */
	public int minPathSum3(int[][] grid) {
		if (grid == null) return 0;

		int rows = grid.length;
		int cols = grid[0].length;

		int[] dp = new int[cols];
		for (int row = 0; row < rows; row++) {
			int left = 0;
			for (int col = 0; col < cols; col++) {
				if (row == 0 && col == 0) {
					dp[col] = grid[row][col];
				} else if (row == 0) {
					dp[col] = left + grid[row][col];
				} else if (col == 0) {
					dp[col] = dp[col] + grid[row][col];
				} else {
					dp[col] = Math.min(left, dp[col]) + grid[row][col];
				}
				left = dp[col];
			}
		}
		return dp[cols - 1];
	}

	public int minPathSum4(int[][] grid) {
		if (grid == null) return  0;

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
				
				if (row == 0 && col == 0) {
					dp[col] = grid[row][col];
				} else if (row == 0) {
					dp[col] = left + grid[row][col];
				} else if (col == 0) {
					dp[col] = up + grid[row][col];
				} else {
					dp[col] = Math.min(left, up) + grid[row][col];
				}
				System.out.println("[" + row + ", " + col + "] = " + dp[col]);
			}
		}
		return dp[cols-1];
    }
	
	public static void main(String[] args) {
		_064_最小路径和 obj = new _064_最小路径和();

		int[][] grid = {{1,3,1},{1,5,1},{4,2,1}};
		Times.test("二维数组1", () -> {
			Asserts.test(obj.minPathSum1(grid) == 7);
		});
		Times.test("二维数组2", () -> {
			Asserts.test(obj.minPathSum2(grid) == 7);
		});
		Times.test("一维数组1", () -> {
			Asserts.test(obj.minPathSum3(grid) == 7);
		});
		Times.test("一维数组2", () -> {
			Asserts.test(obj.minPathSum4(grid) == 7);
		});
	}
}
