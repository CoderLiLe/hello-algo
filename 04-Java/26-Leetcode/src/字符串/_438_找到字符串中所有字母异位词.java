package 字符串;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class _438_找到字符串中所有字母异位词 {
    public List<Integer> findAnagrams(String s, String t) {
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
        // 记录结果
        List<Integer> res = new ArrayList<>();
        while (right < sArray.length) {
            // c 是将移入窗口的字符
            char c = sArray[right];
            // 右移窗口
            right++;
            // 进行窗口内数据的一系列更新
            if (need.containsKey(c) && need.get(c) > 0) {
                window.put(c, window.getOrDefault(c, 0) + 1);
                if (window.get(c) == need.get(c)) {
                    valid++;
                }
            }

            // 判断左侧窗口是否要收缩
            while (right - left >= tArray.length) {
                // 当窗口符合条件时，把起始索引加入 res
                if (valid == need.size()) {
                    res.add(left);
                }
                // d 是将移出窗口的字符
                char d = sArray[left];
                // 左移窗口
                left++;
                // 进行窗口内数据的一系列更新
                if (need.containsKey(d) && need.get(d) > 0) {
                    if (window.get(d) == need.get(d)) {
                        valid--;
                    }
                    window.put(d, window.get(d) - 1);
                }
            }
        }

        return res;
    }
}
