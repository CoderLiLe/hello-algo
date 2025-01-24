package com.lile;

import com.lile.tools.Asserts;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class _763划分字母区间 {
    /**
     * 「同一字母最多出现在一个片段中」意味着，一个片段若要包含字母 a，那么所有的字母 a 都必须在这个片段中。
     *
     * 时间复杂度：O(n)，其中 n 是 s 的长度。
     * 空间复杂度：O(∣Σ∣)。其中 ∣Σ∣ 是字符集合的大小，本题字符均为小写字母，所以 ∣Σ∣=26。
     * @param s 输入的字符串
     * @return 返回分割后的片段长度列表
     */
    public List<Integer> partitionLabels(String s) {
        // 将字符串转换为字符数组，便于后续处理
        char[] sChars = s.toCharArray();
        int n = sChars.length;
        // 记录每个字母最后出现的下标
        int[] last = new int[26];
        for (int i = 0; i < n; i++) {
            // 每个字母最后出现的下标
            last[sChars[i] - 'a'] = i;
        }

        List<Integer> res = new ArrayList<>();
        // 初始化区间左右端点
        int start = 0, end = 0;
        for (int i = 0; i < n; i++) {
            // 更新当前区间右端点的最大值
            end = Math.max(end, last[sChars[i] - 'a']);
            // 当前区间合并完毕
            if (end == i) {
                // 区间长度加入答案
                res.add(end - start + 1);
                // 下一个区间的左端点
                start = i + 1;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        _763划分字母区间 obj = new _763划分字母区间();

        String s = "ababcbacadefegdehijhklij";
        List<Integer> res = Arrays.asList(9, 7, 8);
        Asserts.test(obj.partitionLabels(s).equals(res));
    }
}
