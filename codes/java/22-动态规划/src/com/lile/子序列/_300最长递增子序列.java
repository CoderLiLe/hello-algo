package com.lile.子序列;

import com.lile.tools.Asserts;
import com.lile.tools.Times;

public class _300最长递增子序列 {

	public static void main(String[] args) {
		_300最长递增子序列 obj = new _300最长递增子序列();
		Times.test("二分搜索", () -> {
			Asserts.test(obj.lengthOfLIS(new int[] {10, 2, 2, 5, 1, 7, 101, 18}) == 4);
		});
		Times.test("二分搜索2（优化）", () -> {
			Asserts.test(obj.lengthOfLIS2(new int[] {10, 2, 2, 5, 1, 7, 101, 18}) == 4);
		});
		Times.test("动态规划", () -> {
			Asserts.test(obj.lengthOfLIS1(new int[] {10, 2, 2, 5, 1, 7, 101, 18}) == 4);
		});
	}
	
	/**
	 * 牌顶
	 */
	public int lengthOfLIS(int[] nums) {
		if (nums == null || nums.length == 0) return 0;
		// 牌堆的数量
		int len = 0;
		// 牌顶数量
		int[] top = new int[nums.length];
		// 遍历所有的牌
		for (int num : nums) {
			int begin = 0;
			int end = len;
			while (begin < end) {
				int mid = (begin + end) >> 1;
				if (num <= top[mid]) {
					end = mid;
				} else {
					begin = mid + 1;
				}
			}
			// 覆盖牌顶
			top[begin] = num;
			// 检查是否要新建一个牌堆
			if (begin == len) len++;
		}
		return len;
	}
	
	/**
	 * 牌顶
	 */
	public int lengthOfLIS2(int[] nums) {
		if (nums == null || nums.length == 0) return 0;
		// 牌堆的数量
		int len = 0;
		// 牌顶数量
		int[] top = new int[nums.length];
		// 遍历所有的牌
		for (int num : nums) {
			int j = 0;
			while (j < len) {
				// 找到一个 >= num 的牌顶
				if (top[j] >= num) {
					top[j] = num;
					break;
				}
				// 牌顶 < num
				j++;
			}
			if (j== len) { // 新建一个牌堆
				len++;
				top[j] = num;
			}
		}
		return len;
	}

	/**
	 * 一维dp动规五部曲：
	 * （1）dp[i]含义：表示i之前包括i的以nums[i]结尾的最长递增子序列的长度
	 * （2）递推公式：位置i的最长升序子序列等于j从0到i-1各个位置的最长升序子序列 + 1 的最大值
	 * 				if (nums[i] > nums[j]) dp[i] = max(dp[i], dp[j] + 1);
	 * （3）dp数组初始化：每一个i，对应的dp[i]（即最长递增子序列）起始大小至少都是1.
	 * （4）遍历顺序：dp[i] 是有0到i-1各个位置的最长递增子序列 推导而来，那么遍历i一定是从前向后遍历
	 * 				j其实就是遍历0到i-1，那么是从前到后，还是从后到前遍历都无所谓
	 * （5）举例推导dp数组，输入：[0,1,0,3,2]，dp数组的变化如下：
	 *                   dp[0]   dp[1]   dp[2]   dp[3]   dp[4]
	 *                1    1       2       1	   1	   1
	 *                2    1       2	   1	   1       1
	 *                3    1       2	   1       3       1
	 *                4    1       2	   1       3       3
	 *
	 * 时间复杂度: O(n^2)
	 * 空间复杂度: O(n)
	 */
	public int lengthOfLIS1(int[] nums) {
		if (nums == null || nums.length == 0) return 0;
		int[] dp = new int[nums.length];
		int max = dp[0] = 1;
		for (int i = 1; i < nums.length; i++) {
			dp[i] = 1;
			for (int j = 0; j < i; j++) {
				if (nums[i] <= nums[j]) {
					continue;
				}
				dp[i] = Math.max(dp[i], dp[j] + 1);
			}
			max = Math.max(max, dp[i]);
		}
		return max;
	}
}
