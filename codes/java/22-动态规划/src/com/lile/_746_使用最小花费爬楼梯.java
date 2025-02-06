package com.lile;

public class _746_使用最小花费爬楼梯 {

	public static void main(String[] args) {
//		int[] cost = {10, 15, 20};
		int[] cost = {1, 100, 1, 1, 1, 100, 1, 1, 100, 1};
		System.out.println(minCostClimbingStairs(cost));
	}
	
	/**
	 * 递推关系：f[i] = cost[i] + min(f[i + 1], f[i + 2])
	 * T = O(n), S = O(1)
	 */
	static int minCostClimbingStairs(int[] cost) {
		int f1 = 0, f2 = 0;
		for (int i = cost.length - 1; i >= 0; --i) {
			int f0 = cost[i] + Math.min(f1, f2);
			f2 = f1;
			f1 = f0;
			System.out.println("f0 = " + f0 + ", f1 = " + f1 + ", f2 = " + f2);
		}
        return Math.min(f1, f2);
    }

}
