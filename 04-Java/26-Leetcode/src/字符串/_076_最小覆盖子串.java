package 字符串;

import java.util.HashMap;
import java.util.Map;

public class _076_最小覆盖子串 {
    /**
     * T = O(n)
     */
    public String minWindow(String s, String t) {
        char[] sArray = s.toCharArray();
        char[] tArray = t.toCharArray();

        Map<Character, Integer> need = new HashMap<>();
        Map<Character, Integer> window = new HashMap<>();

        for (char c : tArray) {
            need.put(c, need.getOrDefault(c, 0) + 1);
        }

        Integer left = 0, right = 0;
        // 记录窗口中满足 need 条件的字符个数
        Integer valid = 0;
        // 记录最小覆盖子串的起始索引及长度
        Integer start = 0, len = Integer.MAX_VALUE;
        while (right < sArray.length) {
            // c 是将移入窗口的字符
            char c = sArray[right];
            // 右移窗口
            right++;
            // 进行窗口内数据的一系列更新
            if (need.containsKey(c)) {
                window.put(c, window.getOrDefault(c, 0) + 1);
                if (window.get(c) == need.get(c)) {
                    valid++;
                }
            }

            // 判断左侧窗口是否要收缩
            while (valid == need.size()) {
                // 在这里更新最小覆盖子串
                if (right - left < len) {
                    start = left;
                    len = right - left;
                }
                // d 是将移出窗口的字符
                char d = sArray[left];
                // 左移窗口
                left++;
                // 进行窗口内数据的一系列更新
                if (need.containsKey(d)) {
                    if (window.get(d) == need.get(d)) {
                        valid--;
                    }
                    window.put(d, window.get(d) - 1);
                }
            }
        }

        // 返回最小覆盖子串
        return len == Integer.MAX_VALUE ? "" : s.substring(start, start + len);
    }

    public static void main(String[] args) {
        _076_最小覆盖子串 obj = new _076_最小覆盖子串();
        String result = obj.minWindow("ADOBECODEBANC", "ABC");
        System.out.println(result);
    }
}
