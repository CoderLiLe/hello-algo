package com.lile.分割;

import com.lile.tools.Asserts;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * https://leetcode.cn/problems/palindrome-partitioning/
 * 给你一个字符串 s，请你将 s 分割成一些子串，使每个子串都是
 * 回文串
 * 。返回 s 所有可能的分割方案。
 * <p>
 * 示例 1：
 * 输入：s = "aab"
 * 输出：[["a","a","b"],["aa","b"]]
 * <p>
 * 提示：
 * 1 <= s.length <= 16
 * s 仅由小写英文字母组成
 */
public class _131分割回文串2 {
    /**
     * 切割问题类似组合问题
     */

    List<List<String>> result;
    LinkedList<String> path;
    boolean[][] dp;
    public List<List<String>> partition(String s) {
        result = new ArrayList<>();
        char[] str = s.toCharArray();
        path = new LinkedList<>();
        dp = new boolean[str.length + 1][str.length + 1];
        isPalindrome(str);
        backtracking(s, 0);
        return result;
    }

    /**
     * 使用回溯法分割字符串，寻找所有可能的回文子串组合
     * 回溯法是一种通过试错来寻找解决方案的算法，它通过增量方式构建候选解决方案，并在确定某一部分解不会导致最终解时放弃该部分解
     *
     * @param str 要进行分割的字符串
     * @param startIndex 当前的起始位置，用于避免重复分割
     */
    public void backtracking(String str, int startIndex) {
        // 如果起始位置大于s的大小，说明找到了一组分割方案
        if (startIndex >= str.length()) {
            result.add(new ArrayList<>(path));
        } else {
            for (int i = startIndex; i < str.length(); ++i) {
                // 检查当前子串是否为回文
                if (dp[startIndex][i]) {
                    // 是回文子串，进入下一步递归
                    // 先将当前子串保存入path
                    path.addLast(str.substring(startIndex, i + 1));
                    // 起始位置后移，保证不重复
                    backtracking(str, i + 1);
                    // 回溯，移除最后添加的子串，尝试其他分割可能
                    path.pollLast();
                } else {
                    // 不是回文子串，跳过
                }
            }
        }
    }

    /**
     * 通过动态规划判断是否是回文串,参考动态规划篇 647. 回文子串
     * https://leetcode.cn/problems/palindromic-substrings/description/
     *
     * @param str 待检查的字符串
     */
    public void isPalindrome(char[] str) {
        // 初始化dp数组，对角线上的元素设为true，表示单个字符是回文串
        for (int i = 0; i <= str.length; ++i) {
            dp[i][i] = true;
        }
        // 从第二个字符开始，逐步扩展到整个字符串
        for (int i = 1; i < str.length; ++i) {
            // 从当前字符向前检查，寻找可能的回文子串
            for (int j = i; j >= 0; --j) {
                // 当前检查的两个字符相同，可能是回文子串
                if (str[j] == str[i]) {
                    // 如果这两个字符是相邻的或同一个字符，肯定是回文子串
                    if (i - j <= 1) {
                        dp[j][i] = true;
                    } else if (dp[j + 1][i - 1]) {
                        // 如果这两个字符之间的部分也是回文子串，则当前检查的也是回文子串
                        dp[j][i] = true;
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        _131分割回文串2 obj = new _131分割回文串2();
        List<List<String>> res = new ArrayList<>();
        res.add(Arrays.asList("a", "a", "b"));
        res.add(Arrays.asList("aa", "b"));
        Asserts.test(obj.partition("aab").equals(res));
    }
}
