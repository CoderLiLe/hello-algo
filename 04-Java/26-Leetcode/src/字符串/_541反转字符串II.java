package 字符串;

import tools.Asserts;

/**
 * https://leetcode.cn/problems/reverse-string-ii/description/
 */
public class _541反转字符串II {
    /**
     * 要反转的下标区间为：[0,k),[2k,3k),[4k,5k),⋯
     * 注意最后一个区间的右开端点要和 n 取最小值，防止下标越界。
     * 时间复杂度：O(n)，其中 n 是 s 的长度。
     * 空间复杂度：O(n) 或 O(1)。如果可以原地修改字符串，则空间复杂度为 O(1)。
     *
     * @param S
     * @param k
     * @return
     */
    public String reverseStr(String S, int k) {
        char[] s = S.toCharArray();
        int n = s.length;
        for (int i = 0; i < n; i += k * 2) {
            reverse(s, i, Math.min(i + k, n) - 1);
        }
        return new String(s);
    }

    private void reverse(char[] s, int left, int right) {
        while (left < right) {
            char tmp = s[left];
            s[left++] = s[right];
            s[right--] = tmp;
        }
    }

    public static void main(String[] args) {
        _541反转字符串II obj = new _541反转字符串II();
        Asserts.test(obj.reverseStr("abcdefg", 2).equals("badcfeg"));
    }
}
