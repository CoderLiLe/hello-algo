package 字符串;

import tools.Asserts;

import java.util.Arrays;

public class _014最长公共前缀 {
    public String longestCommonPrefix(String[] strs) {
        int m = strs.length;
        // 以第一行的列数为基准
        int n = strs[0].length();
        for (int col = 0; col < n; col++) {
            for (int row = 1; row < m; row++) {
                String thisStr = strs[row], prevStr = strs[row - 1];
                // 判断每个字符串的 col 索引是否都相同
                if (col >= thisStr.length() || col >= prevStr.length() ||
                        thisStr.charAt(col) != prevStr.charAt(col)) {
                    // 发现不匹配的字符，只有 strs[row][0..col-1] 是公共前缀
                    return strs[row].substring(0, col);
                }
            }
        }
        return strs[0];
    }

    /**
     * 按字典排序数组，比较第一个，和最后一个单词，有多少前缀相同。
     * @param strs
     * @return
     */
    public String longestCommonPrefix2(String[] strs) {
        if (strs == null || strs.length == 0) return "";
        StringBuilder res = new StringBuilder();
        Arrays.sort(strs);
        // 字符串转数组
        char[] a = strs[0].toCharArray();
        char[] b = strs[strs.length - 1].toCharArray();
        for (int i = 0; i < a.length; i++) {
            if (i < b.length && a[i] == b[i]) {
                res.append(a[i]);
            }
            else{
                break;
            }
        }
        return res.toString();
    }

    public static void main(String[] args) {
        _014最长公共前缀 obj = new _014最长公共前缀();
        Asserts.test(obj.longestCommonPrefix(new String[]{"flower", "flow", "flight"}) == "fl");
        Asserts.test(obj.longestCommonPrefix(new String[]{"dog", "racecar", "car"}) == "");
    }
}
