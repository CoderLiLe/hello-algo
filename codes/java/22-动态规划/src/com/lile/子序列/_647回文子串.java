package com.lile.子序列;

import com.lile.tools.Asserts;
import com.lile.tools.Times;

public class _647回文子串 {
    /**
     * 双指针法-中心扩展法
     *
     * 确定回文串，就是找中心然后向两边扩散看是不是对称的就可以了
     * 在遍历中心点的时候，中心点有两种情况：一个元素可以作为中心点，两个元素也可以作为中心点
     *
     * 时间复杂度：O(n^2)
     * 空间复杂度：O(1)
     */
    public int countSubstrings(String s) {
        int len = s.length();
        int res = 0;
        for (int center = 0; center < 2 * len - 1; center++) {
            // left和right指针和中心点的关系是？
            // 首先是left，有一个很明显的2倍关系的存在，其次是right，可能和left指向同一个（偶数时），也可能往后移动一个（奇数）
            // 大致的关系出来了，可以选择带两个特殊例子进去看看是否满足。
            int left = center / 2;
            int right = left + center % 2;

            while (left >= 0 && right < len && s.charAt(left) == s.charAt(right)) {
                //如果当前是一个回文串，则记录数量
                res++;
                left--;
                right++;
            }
        }
        return res;
    }

    /**
     * 动态规划解法
     * 1、dp[i][j]：表示s[i,j]是否是回文串
     * 2、递推公式：
     *  s[i] != s[j]时，dp[i][j] = false
     *  s[i] == s[j]时，有三种情况：
     *      情况一：下标i 与 j相同，同一个字符例如a
     *      情况二：下标i 与 j相差为1，例如aa，也是回文子串
     *      情况三：下标：i 与 j相差大于1的时候，就看dp[i + 1][j - 1]是否为true
     * 3、初始化：dp[i][j] = false;
     * 4、遍历顺序：dp[i][j]依赖dp[i + 1][j - 1]，所以要从下到上，从左到右遍历
     * 5、举例推导dp数组
     * ⚠️根据dp[i][j]的定义，j一定是大于等于i的，那么在填充dp[i][j]的时候一定是只填充右上半部分
     *
     * 时间复杂度：O(n^2)
     * 空间复杂度：O(n^2)
     */
    public int countSubstrings2(String s) {
        int len = s.length();
        int res = 0;
        boolean[][] dp = new boolean[len][len];
        for (int i = len - 1; i >= 0; i--) {
            for (int j = i; j < len; j++) {
                if (s.charAt(i) == s.charAt(j)) {
                    if (j - i <= 1) {
                        dp[i][j] = true;
                        res++;
                    } else if (dp[i + 1][j - 1]) {
                        dp[i][j] = true;
                        res++;
                    }
                } else {
                    dp[i][j] = false;
                }
            }
        }
        return res;
    }

    public static void main(String[] args) {
        _647回文子串 obj = new _647回文子串();

        String s1 = "abc";
        String s2 = "aaa";

        Times.test("双指针法-中心扩展法", () -> {
            Asserts.test(obj.countSubstrings(s1) == 3);
            Asserts.test(obj.countSubstrings(s2) == 6);
        });

        Times.test("动态规划", () -> {
            Asserts.test(obj.countSubstrings2(s1) == 3);
            Asserts.test(obj.countSubstrings2(s2) == 6);
        });
    }
}
