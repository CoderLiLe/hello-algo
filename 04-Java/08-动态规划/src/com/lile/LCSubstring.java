package com.lile;

public class LCSubstring {

	public static void main(String[] args) {
		System.out.println(lcs("ABCD", "BABC"));
	}
	
	static int lcs(String str1, String str2) {
		if (str1 == null || str2 == null) return 0;
		char[] chars1 = str1.toCharArray();
		if (chars1.length == 0) return 0;
		char[] chars2 = str2.toCharArray();
		if (chars2.length == 0) return 0;
		
		char[] rowChars = chars1, colChars = chars2;
		if (chars1.length < chars2.length) {
			rowChars = chars2;
			colChars = chars1;
		}
		
		int[] dp = new int[colChars.length + 1];
		int max = 0;
		for (int row = 1; row < rowChars.length; row++) {
			for (int col = colChars.length; col >= 1; col--) {
				if (rowChars[row - 1] != colChars[col - 1]) {
					dp[col] = 0;
				} else {
					dp[col] = dp[col - 1] + 1;
					max = Math.max(dp[col], max);
				}
			}
		}
		return max;
	}
	
	static int lcs2(String str1, String str2) {
		if (str1 == null || str2 == null) return 0;
		char[] chars1 = str1.toCharArray();
		if (chars1.length == 0) return 0;
		char[] chars2 = str2.toCharArray();
		if (chars2.length == 0) return 0;
		
		char[] rowChars = chars1, colChars = chars2;
		if (chars1.length < chars2.length) {
			rowChars = chars2;
			colChars = chars1;
		}
		
		int[] dp = new int[colChars.length + 1];
		int max = 0;
		for (int row = 1; row < rowChars.length; row++) {
			for (int col = 1; col < colChars.length; col++) {
				if (rowChars[row - 1] != colChars[col - 1]) continue;
				dp[col] = dp[col - 1] + 1;
				max = Math.max(dp[col], max);
			}
		}
		return max;
	}
	
	static int lcs1(String str1, String str2) {
		if (str1 == null || str2 == null) return 0;
		char[] chars1 = str1.toCharArray();
		if (chars1.length == 0) return 0;
		char[] chars2 = str2.toCharArray();
		if (chars2.length == 0) return 0;
		
		int[][] dp = new int[chars1.length + 1][chars2.length + 1];
		int max = 0;
		for (int i = 1; i < chars1.length; i++) {
			for (int j = 1; j < chars2.length; j++) {
				if (chars1[i - 1] != chars2[j - 1]) continue;
				dp[i][j] = dp[i - 1][j - 1] + 1;
				max = Math.max(dp[i][j], max);
			}
		}
		return max;
	}

}
