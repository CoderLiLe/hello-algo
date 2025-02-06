package com.lile.分割;

import com.lile.tools.Asserts;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
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
public class _131分割回文串 {
    /**
     * 切割问题类似组合问题
     */

    List<List<String>> res = new ArrayList<>();
    List<String> cur = new ArrayList<>();

    public List<List<String>> partition(String s) {
        backtracking(s, 0, new StringBuilder());
        return res;
    }

    /**
     * 使用回溯法来寻找字符串中的所有回文子串
     *
     * @param s 输入的字符串
     * @param start 当前搜索的起始位置
     * @param sb 用于构建和存储当前正在检查的子串
     */
    private void backtracking(String s, int start, StringBuilder sb) {
        // 当起始位置达到字符串末尾时，将当前的回文子串组合添加到结果中
        if (start == s.length()) {
            // 注意创建一个新的copy
            res.add(new ArrayList<>(cur));
            return;
        }
        // 从前往后搜索，如果发现回文，进入backtracking,起始位置后移一位，循环结束照例移除cur的末位
        for (int i = start; i < s.length(); i++) {
            sb.append(s.charAt(i));
            // 检查当前构建的字符串是否为回文
            if (isPalindrome(sb)) {
                cur.add(sb.toString());
                // 进入下一层递归，注意这里传入的是一个新的StringBuilder对象
                backtracking(s, i + 1, new StringBuilder());
                cur.remove(cur.size() - 1);
            }
        }
    }

    private boolean isPalindrome(StringBuilder sb) {
        for (int i = 0; i < sb.length() / 2; i++) {
            if (sb.charAt(i) != sb.charAt(sb.length() - 1 - i)) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        _131分割回文串 obj = new _131分割回文串();
        List<List<String>> res = new ArrayList<>();
        res.add(Arrays.asList("a", "a", "b"));
        res.add(Arrays.asList("aa", "b"));
        Asserts.test(obj.partition("aab").equals(res));
    }
}
