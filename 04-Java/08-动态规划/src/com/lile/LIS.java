package com.lile;

public class LIS {

	public static void main(String[] args) {
		System.out.println(lengthOfLIS(new int[] {10, 2, 2, 5, 1, 7, 101, 18}));
	}

	static int lengthOfLIS(int[] nums) {
		if (nums == null || nums.length == 0) return 0;
		int[] dp = new int[nums.length];
		int max = dp[0] =1;
		for (int i = 1; i < nums.length; i++) {
			dp[i] = 1;
			for (int j = 0; j < i; j++) {
				if (nums[i] <= nums[j]) continue;
				dp[i] = Math.max(max, dp[j] + 1);
			}
			max = Math.max(max, dp[i]);
		}
		return max;
	}
}
