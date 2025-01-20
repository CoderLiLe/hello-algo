package com.lile.其它;

import com.lile.tools.Asserts;

import java.util.ArrayList;
import java.util.List;

/**
 * 22.括号生成
 * 题目难度：中等
 * https://leetcode.cn/problems/generate-parentheses/description
 *
 * 数字 n 代表生成括号的对数，请你设计一个函数，用于能够生成所有可能的并且 有效的 括号组合。
 *
 * 示例 1：
 * 输入：n = 3
 * 输出：["((()))","(()())","(())()","()(())","()()()"]
 *
 * 示例 2：
 * 输入：n = 1
 * 输出：["()"]
 *
 * 提示：
 * 1 <= n <= 8
 */
public class _022_括号生成 {
    public List<String> generateParenthesis(int n) {
        List<String> list = new ArrayList<>();
        if (n < 0) return list;
        backtrack(0, n, n, new char[n << 1], list);
        return list;
    }

    private void backtrack(int idx, int leftRemain, int rightRemain, char[] string, List<String> list) {
        if (idx == string.length) {
            list.add(new String(string));
            return;
        }

        // 枚举这一层所有可能的选择
        // 选择一种可能之后进入下一层搜索

        if (leftRemain > 0) {
            string[idx] = '(';
            backtrack(idx + 1, leftRemain - 1, rightRemain, string, list);
        }

        if (rightRemain > 0 && leftRemain != rightRemain) {
            string[idx] = ')';
            backtrack(idx + 1, leftRemain, rightRemain - 1, string, list);
        }
    }

    public static void main(String[] args) {
        _022_括号生成 obj = new _022_括号生成();
        List<String> res = new ArrayList<>();
        res.add("((()))");
        res.add("(()())");
        res.add("(())()");
        res.add("()(())");
        res.add("()()()");
        Asserts.test(obj.generateParenthesis(3).equals(res));
    }
}
