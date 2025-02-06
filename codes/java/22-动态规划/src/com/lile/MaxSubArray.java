package com.lile;

import com.lile.tools.Asserts;
import com.lile.tools.Times;

public class MaxSubArray {

	public static void main(String[] args) {
		Times.test("动态规划", () -> {
			Asserts.test(maxSubArray1(new int[] {-2, 1, -3, 4, -1, 2, 1, -5, 4}) == 6);
		});
		Times.test("动态规划（优化）", () -> {
			Asserts.test(maxSubArray2(new int[] {-2, 1, -3, 4, -1, 2, 1, -5, 4}) == 6);
		});
	}
	
	static int maxSubArray2(int[] nums) {
		if (nums == null || nums.length == 0) return 0;
		int dp = nums[0];
		int max = nums[0];
		for (int i = 1; i < nums.length; i++) {
			if (dp <= 0) {
				dp = nums[i];
			} else {
				dp = dp + nums[i];
			}
			max = Math.max(max, dp);
			System.out.println(dp);
		}
		return max;
	}
	
	static int maxSubArray1(int[] nums) {
		if (nums == null || nums.length == 0) return 0;
		int[] dp = new int[nums.length];
		dp[0] = nums[0];
		int max = nums[0];
		for (int i = 1; i < dp.length; i++) {
			int prev = dp[i - 1];
			if (prev <= 0) {
				dp[i] = nums[i];
			} else {
				dp[i] = prev + nums[i];
			}
			max = Math.max(max, dp[i]);
			System.out.println(dp[i]);
		}
		return max;
	}

}
