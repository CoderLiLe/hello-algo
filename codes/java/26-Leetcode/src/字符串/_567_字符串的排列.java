package 字符串;

import java.util.HashMap;
import java.util.Map;

public class _567_字符串的排列 {
    public boolean checkInclusion(String t, String s) {
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
            while (right - left >= tArray.length) {
                // 在这里判断是否找到了合法的子串
                if (valid == need.size()) {
                    return true;
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

        // 未找到符合条件的子串
        return false;
    }
}
