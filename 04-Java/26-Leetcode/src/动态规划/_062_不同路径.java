package 动态规划;

import java.util.Arrays;

public class _062_不同路径 {

	/**
	 * 一个机器人位于一个 m x n 网格的左上角 （起始点在下图中标记为“Start” ）。
	 * 机器人每次只能向下或者向右移动一步。机器人试图达到网格的右下角（在下图中标记为“Finish”）。
	 * 问总共有多少条不同的路径？
	 * 说明：m 和 n 的值均不超过 100。
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(uniquePaths3(3, 2));
	}
	
	/**
	 * dp[i][j] = dp[i - 1][j] + dp[i][j - 1]
	 * T = O(m * n), S = O(n)
	 * 巧妙的使用滚动数组来节省空间
	 */
	static int uniquePaths3(int m, int n) {   
        int[] cur = new int[n];
        Arrays.fill(cur, 1);
        for (int i = 1; i < m; i++) {
			for (int j = 1; j < n; j++) {
				cur[j] = cur[j - 1] + cur[j];
			}
		}
        
		return cur[n - 1];
    }
	
	/**
	 * dp[i][j] = dp[i - 1][j] + dp[i][j - 1]
	 * T = O(m * n), S = O(2 * n)
	 */
	static int uniquePaths2(int m, int n) {   
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
	 * dp[i][j] = dp[i - 1][j] + dp[i][j - 1]
	 * T = O(m * n), S = O(m * n)
	 */
	static int uniquePaths1(int m, int n) {   
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
				System.out.println("[" + i + ", " + j + "] = " + dp[i][j]);
			}
		}
        
		return dp[m - 1][n - 1];
    }

}
