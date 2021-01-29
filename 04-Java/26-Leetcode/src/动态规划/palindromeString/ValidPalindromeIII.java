package 动态规划.palindromeString;

import java.util.Arrays;

/**
 * 【1216. Valid Palindrome III】
 * difficult
 * https://leetcode-cn.com/problems/valid-palindrome-iii
 *
 * Given a string s and an integer k, find out if the given string is a K-Palindrome or not.
 *
 * A string is K-Palindrome if it can be transformed into a palindrome by removing at most k characters from it.
 *
 * Example 1:
 *
 * Input: s = "abcdeca", k = 2
 * Output: true
 * Explanation: Remove 'b' and 'e' characters.
 *
 *
 * Constraints:
 *
 * 1 <= s.length <= 1000
 * s has only lowercase English letters.
 * 1 <= k <= s.length
 *
 *
 * Hide tips:
 *
 * Can you reduce this problem to a classic problem?
 *
 * The problem is equivalent to finding any palindromic subsequence of length at least N-K where N is the length of the string.
 *
 * Try to find the longest palindromic subsequence.
 *
 * Use DP to do that.
 * /

/**
 * 【1216. 验证回文字符串 III】
 * 难度：困难
 *
 * 给出一个字符串 s 和一个整数 k，请你帮忙判断这个字符串是不是一个「K 回文」。
 *
 * 所谓「K 回文」：如果可以通过从字符串中删去最多 k 个字符将其转换为回文，那么这个字符串就是一个「K 回文」。
 *
 * 示例：
 * 输入：s = "abcdeca", k = 2
 * 输出：true
 * 解释：删除字符 “b” 和 “e”。
 *
 * 约束条件：
 * 1 <= s.length <= 1000
 * s 中只含有小写英文字母
 * 1 <= k <= s.length
 */
public class ValidPalindromeIII {

    public static void main(String[] args) {
        String s = "abcdeca";
        int k = 2;
        System.out.println(isValidPalindrome(s, k));
    }

    /**
     * dp[i][j]：字符串从 i 到 j 需要成为回文串的最少需要删除的字符个数
     * 状态计算：
     * （1）s[i] == s[j]，则 dp[i][j] = dp[i + 1][j - 1]
     * （2）s[i] != s[j]，则 dp[i][j] = min(dp[i + 1][j] + 1, dp[i][j - 1] + 1)
     */
    public static boolean isValidPalindrome(String s, int k) {
        // int n = s.length(), INF = 0x3f3f3f3f;
        // 方便打印看结果
        int n = s.length(), INF = 9;
        int[][] dp = new int[n][n];
        for (int i = 0; i < n; i++) {
            Arrays.fill(dp[i], INF);
        }
        char[] cs = s.toCharArray();
        for (int i = 0; i < n; i++) {
            dp[i][i] = 0;
        }
        for (int len = 2; len <= n; len++) {
            for (int i = 0; i + len - 1 < n; i++) {
                int j = i + len - 1;
                if (cs[i] == cs[j]) {
                    if (len == 2) {
                        dp[i][j] = 0;
                    } else {
                        dp[i][j] = dp[i + 1][j - 1];
                    }
                } else {
                    dp[i][j] = Math.min(dp[i + 1][j] + 1, dp[i][j - 1] + 1);
                }
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(dp[i][j] + " ");
            }
            System.out.println();
        }

        return dp[0][n - 1] <= k;
    }
}
