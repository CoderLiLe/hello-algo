package 滑动窗口;

import tools.Asserts;

import java.util.*;

public class _030串联所有单词的子串 {
    /**
     * 滑动窗口
     * 此题是「438. 找到字符串中所有字母异位词」的进阶版。不同的是第 438 题的元素是字母，而此题的元素是单词
     * @param s 主字符串
     * @param words 模式字符串数组
     * @return 返回所有匹配的子串的起始位置列表
     */
    public List<Integer> findSubstring(String s, String[] words) {
        List<Integer> res = new ArrayList<>();
        // m: 模式字符串数组的长度, n: 每个单词的长度, ls: 主字符串的长度
        int m = words.length, n = words[0].length(), ls = s.length();
        // 从0到n-1开始遍历，以覆盖所有可能的起始位置
        for (int i = 0; i < n; i++) {
            // 检查从当前位置开始是否有足够的长度容纳所有单词
            if (i + m * n > ls) {
                break;
            }
            // 存储当前窗口内单词的频率差异
            Map<String, Integer> differ = new HashMap<>();
            // 初始化窗口，统计前m个单词的频率
            for (int j = 0; j < m; j++) {
                String word = s.substring(i + j * n, i + (j + 1) * n);
                differ.put(word, differ.getOrDefault(word, 0) + 1);
            }
            // 减去模式字符串数组中单词的频率，初始化differ地图
            for (String word : words) {
                differ.put(word, differ.getOrDefault(word, 0) - 1);
                if (differ.get(word) == 0) {
                    differ.remove(word);
                }
            }
            // 遍历主字符串，使用滑动窗口技术
            for (int start = i; start < ls - m * n + 1; start += n) {
                // 更新窗口，移除前一个单词，添加新单词
                if (start != i) {
                    String word = s.substring(start + (m - 1) * n, start + m * n);
                    differ.put(word, differ.getOrDefault(word, 0) + 1);
                    if (differ.get(word) == 0) {
                        differ.remove(word);
                    }
                    word = s.substring(start - n, start);
                    differ.put(word, differ.getOrDefault(word, 0) - 1);
                    if (differ.get(word) == 0) {
                        differ.remove(word);
                    }
                }
                // 如果窗口内的单词频率与模式字符串数组完全匹配，记录起始位置
                if (differ.isEmpty()) {
                    res.add(start);
                }
            }
        }
        return res;
    }

    public static void main(String[] args) {
        _030串联所有单词的子串 obj = new _030串联所有单词的子串();
        String s = "barfoothefoobarman";
        String[] words = {"foo", "bar"};
        List<Integer> list = obj.findSubstring(s, words);
        Asserts.test(list.equals(new ArrayList<>(Arrays.asList(0, 9))));
    }
}
