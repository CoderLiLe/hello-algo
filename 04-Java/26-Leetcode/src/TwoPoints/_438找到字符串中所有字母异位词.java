package TwoPoints;

import tools.Asserts;

import java.util.*;

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

    /**
     * 定长滑窗：维护长为 n 的子串 s' 的每种字母的出现次数。如果 s′的每种字母的出现次数，
     * 和 p 的每种字母的出现次数都相同，那么 s'是 p 的异位词，把 s′左端点下标加入答案
     *
     * 时间复杂度：O(∣Σ∣m+n)，其中 m 是 s 的长度，n 是 p 的长度，∣Σ∣=26 是字符集合的大小。
     * 空间复杂度：O(∣Σ∣)。返回值不计入。
     *
     * @param s
     * @param p
     * @return
     */
    public List<Integer> findAnagrams2(String s, String p) {
        List<Integer> ans = new ArrayList<>();
        // 统计 s 的长为 p.length() 的子串 s' 的每种字母的出现次数
        int[] cntS = new int[26];
        // 统计 p 的每种字母的出现次数
        int[] cntP = new int[26];

        // 统计 p 的字母
        for (char c : p.toCharArray()) {
            cntP[c - 'a']++;
        }

        // 遍历 s 的每个字符
        for (int right = 0; right < s.length(); right++) {
            // 1. 右端点字母进入窗口
            cntS[s.charAt(right) - 'a']++;
            int left = right - p.length() + 1;
            // 窗口长度不足 p.length()
            if (left < 0) {
                continue;
            }

            // 2. 更新结果，s' 和 p 的每种字母的出现次数都相同
            if (Arrays.equals(cntS, cntP)) {
                // s' 左端点下标加入答案
                ans.add(left);
            }

            // 3. 左端点字母离开窗口
            cntS[s.charAt(left) - 'a']--;
        }
        return ans;
    }

    /**
     * 寻找字符串s中所有t的字母异位词的起始索引
     *
     * @param s 输入的字符串
     * @param t 目标字母异位词
     * @return 包含所有字母异位词起始索引的列表
     */
    public List<Integer> findAnagrams3(String s, String t) {
        // 存储需要匹配的字符及其出现次数
        Map<Character, Integer> need = new HashMap<>();
        // 存储当前窗口中字符及其出现次数
        Map<Character, Integer> window = new HashMap<>();

        // 初始化need map
        for (char c : t.toCharArray()) {
            need.put(c, need.getOrDefault(c, 0) + 1);
        }

        // 左右指针初始化
        int left = 0, right = 0;
        // 记录窗口中满足need条件的字符数量
        int valid = 0;
        // 存储符合条件的起始索引
        List<Integer> res = new ArrayList<>();

        // 开始滑动窗口遍历字符串s
        while (right < s.length()) {
            char c = s.charAt(right);
            right++;
            // 进行窗口内数据的一系列更新
            if (need.containsKey(c)) {
                window.put(c, window.getOrDefault(c, 0) + 1);
                if (window.get(c).equals(need.get(c))) {
                    valid++;
                }
            }
            // 判断左侧窗口是否要收缩
            while (right - left >= t.length()) {
                // 当窗口符合条件时，把起始索引加入 res
                if (valid == need.size()) {
                    res.add(left);
                }
                char d = s.charAt(left);
                left++;
                // 进行窗口内数据的一系列更新
                if (need.containsKey(d)) {
                    if (window.get(d).equals(need.get(d))) {
                        valid--;
                    }
                    window.put(d, window.get(d) - 1);
                }
            }
        }
        return res;
    }

    public static void main(String[] args) {
        _438找到字符串中所有字母异位词 obj = new _438找到字符串中所有字母异位词();
        Asserts.test(Arrays.asList(0, 6).equals(obj.findAnagrams("cbaebabacd", "abc")));
        Asserts.test(Arrays.asList(0, 1, 2).equals(obj.findAnagrams("abab", "ab")));

        Asserts.test(Arrays.asList(0, 6).equals(obj.findAnagrams2("cbaebabacd", "abc")));
        Asserts.test(Arrays.asList(0, 1, 2).equals(obj.findAnagrams2("abab", "ab")));
    }
}
