package TwoPoints;

import tools.Asserts;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 给定两个字符串 s 和 p，找到 s 中所有 p 的异位词的子串，返回这些子串的起始索引。不考虑答案输出的顺序。
 */
public class _438找到字符串中所有字母异位词 {
    /**
     * 寻找字符串s中所有p的异位词的起始索引
     *
     * @param s 输入的字符串
     * @param p 指定的异位词模板
     * @return 返回一个列表，包含所有p的异位词在s中的起始索引
     */
    public List<Integer> findAnagrams(String s, String p) {
        // 初始化结果列表
        List<Integer> res = new ArrayList<>();
        // 获取s和p的长度
        int sLen = s.length(), pLen = p.length();
        // 如果s的长度小于p的长度，则s中不可能包含p的异位词，直接返回空结果列表
        if (sLen < pLen) {
            return res;
        }
        // 初始化s和p中每个字符的计数数组
        int[] sCount = new int[26];
        int[] pCount = new int[26];
        // 统计p和s的前pLen个字符中每个字符出现的次数
        for (int i = 0; i < pLen; i++) {
            sCount[s.charAt(i) - 'a']++;
            pCount[p.charAt(i) - 'a']++;
        }
        // 遍历s中每个可能的子串，检查是否为p的异位词
        for (int i = 0; i < sLen - pLen + 1; i++) {
            // 如果当前子串的字符计数与p的字符计数相同，则找到一个异位词
            if (Arrays.equals(sCount, pCount)) {
                res.add(i);
            }
            // 更新sCount以移除当前子串的第一个字符
            sCount[s.charAt(i) - 'a']--;
            // 如果当前子串后还有字符，则更新sCount以添加下一个字符
            if (i + pLen < sLen) {
                sCount[s.charAt(i + pLen) - 'a']++;
            }
        }
        // 返回所有找到的异位词的起始索引
        return res;
    }

    public static void main(String[] args) {
        _438找到字符串中所有字母异位词 obj = new _438找到字符串中所有字母异位词();
        Asserts.test(Arrays.asList(0, 6).equals(obj.findAnagrams("cbaebabacd", "abc")));
        Asserts.test(Arrays.asList(0, 1, 2).equals(obj.findAnagrams("abab", "ab")));
    }
}
