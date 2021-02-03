package 动态规划;

public class _070_爬楼梯 {

	public static void main(String[] args) {
		System.out.println(climbStairs4(3));
	}
	
	/**
	 * 假设你正在爬楼梯。需要 n 阶你才能到达楼顶。每次你可以爬 1 或 2 个台阶。你有多少种不同的方法可以爬到楼顶呢？
	 * 注意：给定 n 是一个正整数
	 * 
	 * climbStairs(i, n) = (i + 1, n) + climbStairs(i + 2, n)
	 */
	
	/**
	 * 暴力法
	 * T = O(2^n), S = O(n)
	 */
	static int climbStairs1(int n) {
		return climb_stairs1(0, n);
    }
	
	static int climb_stairs1(int i, int n) {
		System.out.println(i);
		if (i > n) {
			return 0;
		}
		if (i == n) {
			return 1;
		}
		return climb_stairs1(i + 1,  n) + climb_stairs1(i + 2,  n);
	}
	
	// (0, 3) = (1, 3) + (2, 3)
	//        = (2, 3) + (3, 3) + (3, 3) + (4, 3)
	//        = (3, 3) + (4, 3) + (3, 3) + (3, 3) + (4, 3)
	//        = 1 + 0 + 1 + 1 + 0
	//        = 3
	
	/**
	 * 记忆化递归
	 * T = O(n), S = O(n)
	 */
	static int climbStairs2(int n) {
		int[] mem = new int[n + 1];
		return climb_stairs2(0, n, mem);
	}
	
	static int climb_stairs2(int i, int n, int[] mem) {
		if (i > n) {
			return 0;
		}
		if (i == n) {
			return 1;
		}
		if (mem[i] > 0) {
			System.out.println(i + " : " + mem[i]);
			return mem[i];
		}
		mem[i] = climb_stairs2(i + 1, n, mem) + climb_stairs2(i + 2, n, mem);
		return mem[i];
	}
	
	/**
	 * 动态规划
	 * dp[i] = dp[i - 1] + dp[i - 2]
	 * T = O(n), S = O(n)
	 */
	static int climbStairs3(int n) {
		if (n == 1) return 1;
		int[] dp = new int[n + 1];
		dp[1] = 1;
		dp[2] = 2;
		for (int i = 3; i <= n; i++) {
			dp[i] = dp[i - 1] + dp[i - 2];
		}
		return dp[n];
	}
	
	/**
	 * 斐波那契数
	 * T = O(n), S = O(1)
	 */
	static int climbStairs4(int n) {
		if (n == 1) return 1;
		int first = 1;
		int second = 2;
		for (int i = 3; i <= n; i++) {
			int third = first + second;
			first = second;
			second = third;
		}
		return second;
	}

}
