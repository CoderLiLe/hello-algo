package 字符串;

import tools.Asserts;

public class _459重复的子字符串 {

    /**
     * 暴力枚举
     *
     * 时间复杂度：O(n^2)
     * 空间复杂度：O(1)
     */
    public boolean repeatedSubstringPattern(String s) {
        int n = s.length();
        for (int i = 1; i * 2 <= n; ++i) {
            if (n % i == 0) {
                boolean match = true;
                for (int j = i; j < n; ++j) {
                    if (s.charAt(j) != s.charAt(j - i)) {
                        match = false;
                        break;
                    }
                }
                if (match) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 字符串匹配
     */
    public boolean repeatedSubstringPattern1(String s) {
        return (s + s).indexOf(s, 1) != s.length();
    }

    /**
     * KMP算法
     */
    public boolean repeatedSubstringPattern2(String s) {
        if (s.equals("")) {
            return false;
        }

        int len = s.length();
        // 原串加个空格(哨兵)，使下标从1开始，这样j从0开始，也不用初始化了
        s = " " + s;
        char[] chars = s.toCharArray();
        int[] next = new int[len + 1];

        // 构造next数组
        for (int i = 2, j = 0; i <= len; i++) {
            // 匹配不成功，j回到前一位置 next 数组所对应的值
            while (j > 0 && chars[i] != chars[j + 1]) {
                j = next[j];
            }
            // 匹配成功，j往后移
            if (chars[i] == chars[j + 1]) {
                j++;
            }
            // 更新 next 数组的值
            next[i] = j;
        }

        // 最后判断是否是重复的子字符串，这里 next[len] 即代表next数组末尾的值
        if (next[len] > 0 && len % (len - next[len]) == 0) {
            return true;
        }
        return false;
    }

    /*
     * 充分条件：如果字符串s是由重复子串组成的，那么它的最长相等前后缀不包含的子串一定是s的最小重复子串。
     * 必要条件：如果字符串s的最长相等前后缀不包含的子串是s的最小重复子串，那么s必然是由重复子串组成的。
     * 推得：当字符串s的长度可以被其最长相等前后缀不包含的子串的长度整除时，不包含的子串就是s的最小重复子串。
     *
     * 时间复杂度：O(n)
     * 空间复杂度：O(n)
     */
    public boolean repeatedSubstringPattern3(String s) {
        // if (s.equals("")) return false;
        // 边界判断（可以去掉，因为题目给定范围是1 <= s.length <= 10^4）
        int n = s.length();

        // Step 1.构建KMP算法的前缀表
        int[] next = new int[n]; // 前缀表的值表示 以该位置结尾的字符串的最长相等前后缀的长度
        int j = 0;
        next[0] = 0;
        for (int i = 1; i < n; i++) {
            while (j > 0 && s.charAt(i) != s.charAt(j)) // 只要前缀后缀还不一致，就根据前缀表回退j直到起点为止
                j = next[j - 1];
            if (s.charAt(i) == s.charAt(j))
                j++;
            next[i] = j;
        }

        // Step 2.判断重复子字符串
        if (next[n - 1] > 0 && n % (n - next[n - 1]) == 0) { // 当字符串s的长度可以被其最长相等前后缀不包含的子串的长度整除时
            return true; // 不包含的子串就是s的最小重复子串
        } else {
            return false;
        }
    }

    public static void main(String[] args) {
        _459重复的子字符串 obj = new _459重复的子字符串();
        String s = "abab";
        Asserts.test(obj.repeatedSubstringPattern(s));
        Asserts.test(obj.repeatedSubstringPattern1(s));
        Asserts.test(obj.repeatedSubstringPattern2(s));
        Asserts.test(obj.repeatedSubstringPattern3(s));
    }
}
