package 动态规划;

public class _64_最小路径和 {
	public int minPathSum(int[][] grid) {
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
		_64_最小路径和 obj = new _64_最小路径和();
		int[][] grid = {{1,3,1}, {1,5,1}, {4,2,1}};
		System.out.println(obj.minPathSum2(grid));
	}
}
