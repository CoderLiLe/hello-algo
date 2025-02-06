package com.lile.子序列;

import com.lile.tools.Asserts;
import com.lile.tools.Times;

/**
 * 最长公共子序列 Longest Common Subsequence
 * @author LiLe
 * Leetcode_1143
 *
 * 给定两个字符串 text1 和 text2，返回这两个字符串的最长 公共子序列 的长度。如果不存在 公共子序列 ，返回 0 。
 *
 * 一个字符串的 子序列 是指这样一个新的字符串：它是由原字符串在不改变字符的相对顺序的情况下删除某些字符（也可以不删除任何字符）后组成的新字符串。
 *
 * 例如，"ace" 是 "abcde" 的子序列，但 "aec" 不是 "abcde" 的子序列。
 * 两个字符串的 公共子序列 是这两个字符串所共同拥有的子序列。
 */
public class _1143LongestCommonSubsequence {

	public static void main(String[] args) {
		_1143LongestCommonSubsequence obj = new _1143LongestCommonSubsequence();

		int[] nums1 = new int[] {1, 4, 5, 9, 10};
		int[] nums2 = new int[] {1, 4, 9, 10};

		Times.test("递归实现", () -> {
			Asserts.test(obj.lcs1(nums1, nums2) == 4);
		});
		Times.test("非递归实现", () -> {
			Asserts.test(obj.lcs2(nums1, nums2) == 4);
		});
		Times.test("非递归实现 + 滚动数组", () -> {
			Asserts.test(obj.lcs3(nums1, nums2) == 4);
		});
		Times.test("非递归实现 + 滚动数组", () -> {
			Asserts.test(obj.lcs4(nums1, nums2) == 4);
		});
		Times.test("非递归实现 + 滚动数组 优化", () -> {
			Asserts.test(obj.lcs(nums1, nums2) == 4);
		});

		String text1 = "abcde", text2 = "ace";
		Times.test("字符串-二维数组", () -> {
			Asserts.test(obj.longestCommonSubsequence1(text1, text2) == 3);
		});
		Times.test("字符串-一维数组", () -> {
			Asserts.test(obj.longestCommonSubsequence2(text1, text2) == 3);
		});
	}
	
	// 非递归实现 + 滚动数组 优化
	public int lcs(int[] nums1, int[] nums2) {
		if (nums1 == null || nums1.length == 0) return 0;
		if (nums2 == null || nums2.length == 0 ) return 0;
		
		int[] rowNums = nums1, colNums = nums2;
		if (nums1.length < nums2.length) {
			rowNums = nums2;
			colNums = nums1;
		}
	
		int[] dp = new int[colNums.length + 1];
		
		for (int i = 1; i <= rowNums.length; i++) {
			int cur = 0;
			for (int j = 1; j <= colNums.length; j++) {
				int leftTop = cur;
				cur = dp[j];
				if (rowNums[i - 1] == colNums[j - 1]) {
					dp[j] = leftTop + 1;
				} else {
					dp[j] = Math.max(dp[j], dp[j - 1]);
				}
			}
		}
		return dp[colNums.length];
	}
	
	// 非递归实现 + 滚动数组 
	public int lcs4(int[] nums1, int[] nums2) {
		if (nums1 == null || nums1.length == 0) return 0;
		if (nums2 == null || nums2.length == 0 ) return 0;
		int[] dp = new int[nums2.length + 1];
		for (int i = 1; i <= nums1.length; i++) {
			int cur = 0;
			for (int j = 1; j <= nums2.length; j++) {
				int leftTop = cur;
				cur = dp[j];
				if (nums1[i - 1] == nums2[j - 1]) {
					dp[j] = leftTop + 1;
				} else {
					dp[j] = Math.max(dp[j], dp[j - 1]);
				}
			}
		}
		return dp[nums2.length];
	}
	
	// 非递归实现 + 滚动数组 
	public int lcs3(int[] nums1, int[] nums2) {
		if (nums1 == null || nums1.length == 0) return 0;
		if (nums2 == null || nums2.length == 0 ) return 0;
		int[][] dp = new int[2][nums2.length + 1];
		for (int i = 1; i <= nums1.length; i++) {
			int row = i & 1;
			int preRow = (i - 1) & 1;
			for (int j = 1; j <= nums2.length; j++) {
				if (nums1[i - 1] == nums2[j - 1]) {
					dp[row][j] = dp[preRow][j - 1] + 1;
				} else {
					dp[row][j] = Math.max(dp[preRow][j], dp[row][j - 1]);
				}
			}
		}
		return dp[nums1.length % 2][nums2.length];
	}
	
	// 非递归实现
	public int lcs2(int[] nums1, int[] nums2) {
		if (nums1 == null || nums1.length == 0) return 0;
		if (nums2 == null || nums2.length == 0 ) return 0;
		int[][] dp = new int[nums1.length + 1][nums2.length + 1];
		for (int i = 1; i <= nums1.length; i++) {
			for (int j = 1; j <= nums2.length; j++) {
				if (nums1[i - 1] == nums2[j - 1]) {
					dp[i][j] = dp[i - 1][j - 1] + 1;
				} else {
					dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
				}
			}
		}
		return dp[nums1.length][nums2.length];
	}
	
	// 递归实现
	public int lcs1(int[] nums1, int[] nums2) {
		if (nums1 == null || nums1.length == 0) return 0;
		if (nums2 == null || nums2.length == 0 ) return 0;
		return lcs1(nums1, nums1.length, nums2, nums2.length);
	}
	
	/**
	 * 求 nums1 前 i 个元素和 nums2 前 j 个元素的最长公共子序列长度
	 */
	public int lcs1(int[] nums1, int i, int[] nums2, int j) {
		if (i == 0 || j == 0) return 0;
		if (nums1[i - 1] == nums2[j - 1]) {
			return lcs1(nums1, i - 1, nums2, j - 1) + 1;
		}
		return Math.max(lcs1(nums1, i, nums2, j - 1), lcs1(nums1, i - 1, nums2, j));
	}
	

	/**
	 * 二维dp动规五部曲：
	 * （1）dp[i][j]：长度为[0, i - 1]的字符串text1与长度为[0, j - 1]的字符串text2的最长公共子序列为dp[i][j]
	 * （2）递推公式：如果text1[i - 1] 与 text2[j - 1]相同，那么找到了一个公共元素，所以dp[i][j] = dp[i - 1][j - 1] + 1;
	 * 				如果text1[i - 1] 与 text2[j - 1]不相同，那就看看text1[0, i - 2]与text2[0, j - 1]的最长公共子序列
	 * 				和 text1[0, i - 1]与text2[0, j - 2]的最长公共子序列，取最大的。即：dp[i][j] = max(dp[i - 1][j], dp[i][j - 1]);
	 * （3）dp数组初始化：dp[i][0] = 0;
	 * 				    dp[0][j] = 0;
	 * （4）遍历顺序：从左到右一层一层遍历
	 * （5）举例推导
	 *
	 * T = O(m * n), S = O(m * n)
	 */
	public int longestCommonSubsequence1(String text1, String text2) {
		// 先对dp数组做初始化操作
		int[][] dp = new int[text1.length() + 1][text2.length() + 1];
		for (int i = 1 ; i <= text1.length() ; i++) {
			char char1 = text1.charAt(i - 1);
			for (int j = 1; j <= text2.length(); j++) {
				char char2 = text2.charAt(j - 1);
				// 开始列出状态转移方程
				if (char1 == char2) {
					dp[i][j] = dp[i - 1][j - 1] + 1;
				} else {
					dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
				}
			}
		}
		return dp[text1.length()][text2.length()];
	}

	// 字符串-一维数组
	public int longestCommonSubsequence2(String text1, String text2) {
		if (text1 == null || text2 == null) return 0;
		char[] chars1 = text1.toCharArray();
		if (chars1.length == 0) return 0;
		char[] chars2 = text2.toCharArray();
		if (chars2.length == 0) return 0;

		char[] rowChars = chars1, colChars = chars2;
		if (chars1.length < chars2.length) {
			rowChars = chars2;
			colChars = chars1;
		}

		int[] dp = new int[colChars.length + 1];

		for (int i = 1; i <= rowChars.length; i++) {
			int cur = 0;
			for (int j = 1; j <= colChars.length; j++) {
				int leftTop = cur;
				cur = dp[j];
				if (rowChars[i - 1] == colChars[j - 1]) {
					dp[j] = leftTop + 1;
				} else {
					dp[j] = Math.max(dp[j], dp[j - 1]);
				}
			}
		}
		return dp[colChars.length];
	}

}
