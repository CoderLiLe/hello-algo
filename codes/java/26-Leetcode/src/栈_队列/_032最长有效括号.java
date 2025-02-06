package 栈_队列;

import tools.Asserts;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 难度：困难
 * 标签：栈 字符串 动态规划
 * https://leetcode.cn/problems/longest-valid-parentheses
 * 给你一个只包含 '(' 和 ')' 的字符串，找出最长有效（格式正确且连续）括号子串的长度。
 * <p>
 * 示例 1：
 * 输入：s = "(()"
 * 输出：2
 * 解释：最长有效括号子串是 "()"
 * <p>
 * 示例 2：
 * 输入：s = ")()())"
 * 输出：4
 * 解释：最长有效括号子串是 "()()"
 */
public class _032最长有效括号 {
    /**
     * https://leetcode.cn/problems/longest-valid-parentheses/solutions/206995/dong-tai-gui-hua-si-lu-xiang-jie-c-by-zhanganan042/?envType=study-plan-v2&envId=top-100-liked
     * 二维dp动规五部曲：
     * （1）dp[i]：表示以下标 i 字符结尾的最长有效括号的长度
     * （2）递推公式：s[i]=‘)’ 且 s[i−1]=‘(’，也就是字符串形如 “……()”，我们可以推出： dp[i] = dp[i−2] + 2
     *              s[i]=‘)’ 且 s[i−1]=‘)’，也就是字符串形如 “……))”，我们可以推出：如果 s[i−dp[i−1]−1]=‘(’，
     *                                                                     那么dp[i] = dp[i−1] + dp[i−dp[i−1]−2] + 2
     * （3）dp数组初始化：dp 数组全部初始化为 0
     *     边界情况：需要保证计算过程中：i−2 >= 0 和 i−dp[i−1]−2 >= 0
     * （4）遍历顺序：从左到右一层一层遍历
     * （5）举例推导
     * <p>
     * 时间复杂度： O(n)，其中 n 为字符串的长度。我们只需遍历整个字符串一次，即可将 dp 数组求出来。
     * 空间复杂度： O(n)。我们需要一个大小为 n 的 dp 数组。
     */
    public int longestValidParentheses(String s) {
        int res = 0;
        int[] dp = new int[s.length()];
        for (int i = 1; i < s.length(); i++) {
            if (s.charAt(i) == ')') {
                if (s.charAt(i - 1) == '(') {
                    dp[i] = 2;
                    if (i - 2 >= 0) {
                        dp[i] = dp[i] + dp[i -2];
                    }
                }
                // s[i−1]位置必然是有效的括号对，否则s[i]无法和前面对字符组成有效括号对
                else if (dp[i - 1] > 0) {
                    if (i - dp[i - 1] - 1 >= 0 && s.charAt(i - dp[i - 1] - 1) == '(') {
                        dp[i] = dp[i - 1] + 2;
                        if ((i - dp[i - 1] - 2) >= 0) {
                            dp[i] = dp[i] + dp[i - dp[i - 1] - 2];
                        }
                    }
                }
                res = Math.max(res, dp[i]);
            }
        }
        return res;
    }

    /**
     * 始终保持栈底元素为当前已经遍历过的元素中「最后一个没有被匹配的右括号的下标」，这样的做法主要是考虑了边界条件的处理，
     * 栈里其他元素维护左括号的下标：
     * <p>
     * 对于遇到的每个 ‘(’ ，我们将它的下标放入栈中
     * 对于遇到的每个 ‘)’ ，我们先弹出栈顶元素表示匹配了当前右括号：
     * 如果栈为空，说明当前的右括号为没有被匹配的右括号，我们将其下标放入栈中来更新我们之前提到的「最后一个没有被匹配的右括号的下标」
     * 如果栈不为空，当前右括号的下标减去栈顶元素即为「以该右括号为结尾的最长有效括号的长度」
     * <p>
     * 时间复杂度： O(n)，n 是给定字符串的长度。我们只需要遍历字符串一次即可。
     * 空间复杂度： O(n)。栈的大小在最坏情况下会达到 n，因此空间复杂度为 O(n)
     *
     * @param s
     * @return
     */
    public int longestValidParentheses2(String s) {
        int res = 0;
        Deque<Integer> stack = new LinkedList<>();
        stack.push(-1);
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                // 左括号的索引，入栈
                stack.push(i);
            } else { // 遍历到右括号
                // 栈顶的左括号被匹配，出栈
                stack.pop();
                if (stack.isEmpty()) { // 栈空了
                    // 入栈充当参照
                    stack.push(i);
                } else {
                    // 计算有效连续长度
                    res = Math.max(res, i - stack.peek());
                }
            }
        }
        return res;
    }

    public int longestValidParentheses3(String s) {
        int left = 0, right = 0, maxlength = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                left++;
            } else {
                right++;
            }
            if (left == right) {
                maxlength = Math.max(maxlength, 2 * right);
            } else if (right > left) {
                left = right = 0;
            }
        }
        left = right = 0;
        for (int i = s.length() - 1; i >= 0; i--) {
            if (s.charAt(i) == '(') {
                left++;
            } else {
                right++;
            }
            if (left == right) {
                maxlength = Math.max(maxlength, 2 * left);
            } else if (left > right) {
                left = right = 0;
            }
        }
        return maxlength;
    }

    public static void main(String[] args) {
        _032最长有效括号 obj = new _032最长有效括号();
        Asserts.test(obj.longestValidParentheses("(()") == 2);
        Asserts.test(obj.longestValidParentheses(")()())") == 4);

        Asserts.test(obj.longestValidParentheses2("(()") == 2);
        Asserts.test(obj.longestValidParentheses2(")()())") == 4);

        Asserts.test(obj.longestValidParentheses3("(()") == 2);
        Asserts.test(obj.longestValidParentheses3(")()())") == 4);
    }
}
