package com.lile;

import tools.Asserts;
import tools.Times;

public class _005_最长回文子串 {
    // 暴力法
    public String longestPalindrome(String s) {
        // 如果字符串长度小于等于1，则直接返回该字符串（空串或单个字符都是回文）
        if (s.length() <= 1) return s;

        // 初始化最长回文子串的起始位置和长度
        int start = 0, dis = 1;

        // 将字符串转换为字符数组，以便后续操作
        char[] chars = s.toCharArray();

        // 外层循环，遍历字符串的每个字符作为回文子串的起点
        for (int i = 0; i < chars.length; i++) {
            // 内层循环，从字符串末尾开始向前遍历，作为回文子串的终点
            for (int j = chars.length - 1; j > i; j--) {
                // 如果当前子串长度小于等于已找到的最长回文子串长度，则无需继续检查
                if (j - i + 1 <= dis) break;

                // 检查当前子串是否为回文
                if (isPalindromic(chars, i, j)) {
                    // 更新最长回文子串的起始位置和长度
                    dis = j - i + 1;
                    start = i;
                }
            }
        }

        // 返回最长回文子串
        return s.substring(start, start + dis);
    }

    // 辅助函数，检查一个子串是否为回文
    boolean isPalindromic(char[] chars, int l, int r) {
        // 使用双指针法，从子串两端向中间遍历
        while (l < r) {
            // 如果两端字符不相等，则不是回文
            if (chars[l++] != chars[r--]) return false;
        }
        // 如果遍历完所有字符都相等，则是回文
        return true;
    }

    /**
     * 动态规划法
     */
    public String longestPalindrome1(String s) {
        if (s == null) return null;
        char[] cs = s.toCharArray();
        if (cs.length == 0) return s;

        // 最长回文子串的长度（至少是1）
        int maxLen = 1;
        // 最长回文子串的开始索引
        int begin = 0;
        boolean[][] dp = new boolean[cs.length][cs.length];
        // 从下到上（i由大到小）
        for (int i = cs.length - 1; i >= 0; i--) {
            // 从左到右（j由小到大）
            for (int j = i; j < cs.length; j++) {
                // cs[i, j] 的长度
                int len = j - i + 1;
                dp[i][j] = (cs[i] == cs[j]) && (len <= 2 || dp[i + 1][j - 1]);
                if (dp[i][j] && len > maxLen) { // 说明 cs[i, j] 是回文子串
                    maxLen = len;
                    begin = i;
                }
            }
        }
        return new String(cs, begin, maxLen);
    }

    /**
     * 动规五部曲：
     * （1）dp[l][r]：字符串[l,r]是否为回文串
     * （2）递推公式：s[l] == s[r]，如果r - l <= 2，则dp[l][r] = true；包含两种情况：对于长度为 1 的子串，它显然是个回文串；对于长度为 2 的子串，只要它的两个字母相同，它就是一个回文串符
     *                           dp[l][r] = dp[l + 1][r - 1]
     * （3）初始化：dp[l][r] = false
     * （4）遍历顺序：r从1开始，l < r
     */
    public String longestPalindrome11(String s) {
        if (s == null || s.length() < 2) {
            return s;
        }
        int strLen = s.length();
        // 最长回文串的起点
        int maxStart = 0;
        // 最长回文串的终点
        int maxEnd = 0;
        // 最长回文串的长度
        int maxLen = 1;

        // dp[l][r] 表示字符串从 l 到 r 这段是否为回文
        boolean[][] dp = new boolean[strLen][strLen];

        for (int r = 1; r < strLen; r++) {
            for (int l = 0; l < r; l++) {
                if (s.charAt(l) == s.charAt(r) && (r - l <= 2 || dp[l + 1][r - 1])) {
                    dp[l][r] = true;
                    if (r - l + 1 > maxLen) {
                        maxLen = r - l + 1;
                        maxStart = l;
                        maxEnd = r;
                    }
                }
            }
        }
        return s.substring(maxStart, maxEnd + 1);

    }

    /**
     * 扩展中心法
     */
    public String longestPalindrome2(String s) {
        if (s == null) return null;
        char[] cs = s.toCharArray();
        if (cs.length <= 1) return s;
        // 最长回文子串的长度（至少是1）
        int maxLen = 1;
        // 最长回文子串的开始索引
        int begin = 0;

        for (int i = cs.length - 2; i >= 1; i--) {
            // 以字符为中心向左右扩展
            int len1 = palindromeLength(cs, i - 1, i + 1);
            // 以字符右边的间隙为中心向左右扩展
            int len2 = palindromeLength(cs, i, i + 1);
            len1 = Math.max(len1, len2);
            if (len1 > maxLen) {
                maxLen = len1;
                begin = i - ((maxLen - 1) >> 1);
            }
        }
        // 以 0 号字符右边的间隙为中心的最长回文子串长度是2
        if (cs[0] == cs[1] && maxLen < 2) {
            // cs[0, 1] 就是最长的回文子串
            begin = 0;
            maxLen = 2;
        }
        return new String(cs, begin, maxLen);
    }

    private int palindromeLength(char[] cs, int l, int r) {
        while (l >= 0 && r < cs.length && cs[l] == cs[r]) {
            l--;
            r++;
        }
        return r - l - 1;
    }

    /**
     * 扩展中心法2
     */
    public String longestPalindrome3(String s) {
        if (s == null) return null;
        char[] cs = s.toCharArray();
        if (cs.length <= 1) return s;
        // 最长回文子串的长度（至少是1）
        int maxLen = 1;
        // 最长回文子串的开始索引
        int begin = 0;
        int i = 0;
        while (i < cs.length) {
            int l = i - 1;
            // 找到右边第一个不等于 cs[i] 的位置
            int r = i;
            while (++r < cs.length && cs[r] == cs[i]) ;
            // r会成为新的 i
            i = r;

            // 从 l 向左，从 r 向右扩展
            while (l >= 0 && r < cs.length && cs[l] == cs[r]) {
                l--;
                r++;
            }

            // 扩展结束后，cs[l + 1, r)就是刚才找到的最大回文子串
            // ++l 后，l就是刚才找到的最大回文子串的开始索引
            int len = r - ++l;
            if (len > maxLen) {
                maxLen = len;
                begin = l;
            }
        }
        return new String(cs, begin, maxLen);
    }

    /**
     * 中心扩散法
     */
    public String longestPalindrome4(String s) {

        if (s == null || s.length() == 0) {
            return "";
        }
        int strLen = s.length();
        int left = 0;
        int right = 0;
        int len = 1;
        int maxStart = 0;
        int maxLen = 0;

        for (int i = 0; i < strLen; i++) {
            left = i - 1;
            right = i + 1;
            while (left >= 0 && s.charAt(left) == s.charAt(i)) {
                len++;
                left--;
            }
            while (right < strLen && s.charAt(right) == s.charAt(i)) {
                len++;
                right++;
            }
            while (left >= 0 && right < strLen && s.charAt(right) == s.charAt(left)) {
                len = len + 2;
                left--;
                right++;
            }
            if (len > maxLen) {
                maxLen = len;
                maxStart = left;
            }
            len = 1;
        }
        return s.substring(maxStart + 1, maxStart + maxLen + 1);

    }

    /**
     * 马拉车算法
     */
    public String longestPalindrome5(String s) {
        if (s == null) return null;
        char[] oldCs = s.toCharArray();
        if (oldCs.length <= 1) return s;

        // 预处理
        char[] cs = preprocess(oldCs);
        // 构建 m 数组
        int[] m = new int[cs.length];
        int c = 1, r = 1, lastIdx = m.length - 2;
        int maxLen = 0, idx = 0;
        for (int i = 2; i < lastIdx; i++) {
            if (r > i) {
                int li = (c << 1) - i;
                m[i] = (i + m[li] <= r) ? m[li] : (r - i);
            }

            // 以 i 为中心，向左右扩展
            while (cs[i + m[i] + 1] == cs[i - m[i] - 1]) {
                m[i]++;
            }

            // 更新 c、r
            if (i + m[i] > r) {
                c = i;
                r = i + m[i];
            }

            // 找出更大的回文子串
            if (m[i] > maxLen) {
                maxLen = m[i];
                idx = i;
            }
        }
        int begin = (idx - maxLen) >> 1;
        return new String(oldCs, begin, maxLen);
    }

    private char[] preprocess(char[] oldCs) {
        char[] cs = new char[(oldCs.length << 1) + 3];
        cs[0] = '^';
        cs[1] = '#';
        cs[cs.length - 1] = '$';
        for (int i = 0; i < oldCs.length; i++) {
            int idx = (i + 1) << 1;
            cs[idx] = oldCs[i];
            cs[idx + 1] = '#';
        }
        return cs;
    }

    public static void main(String[] args) {
        _005_最长回文子串 obj = new _005_最长回文子串();
        String s = "babad";
        String result1 = "bab";
        String result2 = "aba";
        Times.test("暴力法", () -> {
            String res = obj.longestPalindrome(s);
            Asserts.test(res.equals(result1) || res.equals(result2));
        });
        Times.test("动态规划法", () -> {
            String res = obj.longestPalindrome1(s);
            Asserts.test(res.equals(result1) || res.equals(result2));
        });
        Times.test("动态规划法2", () -> {
            String res = obj.longestPalindrome11(s);
            Asserts.test(res.equals(result1) || res.equals(result2));
        });
        Times.test("扩展中心法", () -> {
            String res = obj.longestPalindrome2(s);
            Asserts.test(res.equals(result1) || res.equals(result2));
        });
        Times.test("扩展中心法2", () -> {
            String res = obj.longestPalindrome3(s);
            Asserts.test(res.equals(result1) || res.equals(result2));
        });
        Times.test("中心扩散法", () -> {
            String res = obj.longestPalindrome4(s);
            Asserts.test(res.equals(result1) || res.equals(result2));
        });
        Times.test("马拉车算法", () -> {
            String res = obj.longestPalindrome5(s);
            Asserts.test(res.equals(result1) || res.equals(result2));
        });
    }
}
