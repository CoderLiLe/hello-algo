package 字符串;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class _003_无重复字符的最长子串 {
    public int lengthOfLongestSubstring(String s) {
        if (s == null) return 0;
        char[] chars = s.toCharArray();
        if (chars.length == 0) return 0;

        // 用来保存每一个字符上一次出现的位置
        Map<Character, Integer> preIdxes = new HashMap<>();
        preIdxes.put(chars[0], 0);
        // 以 i - 1 位置字符结尾的最长不重复字符串的开始索引（最左索引）
        int li = 0;
        int max = 1;
        for (int i = 1; i < chars.length; i++) {
            // i 位置字符上一次出现的位置
            Integer pi = preIdxes.get(chars[i]);
            if (pi != null && li <= pi) {
                li = pi + 1;
            }
            // 存储这个字符出现的位置
            preIdxes.put(chars[i], i);
            // 求出最长不重复子串的长度
            max = Math.max(max, i - li + 1);
        }
        return max;
    }

    public int lengthOfLongestSubstring2(String s) {
        // 单个字符作为key，字符在字符串中的下标作为value
        Map<Character, Integer> map = new HashMap<>();
        // 最大值
        int maxLen = 0;
        // 左边界
        int left = 0;
        // 这个循环的一些想法，之前老喜欢把字符串转字符数组，然后用数组去操作，其实直接遍历索引也是可以的
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            /**
             * 要理解下面的代码首先要想明白获得最长字串的逻辑
             * 这里一共存在两个变量：
             *  - left：表示字串最左边字符的索引位置
             *  - i：遍历的每个字符索引位置，我们全靠这个字符来完成这道题，i这个变量存在三种情况需要考虑
             *      1） s.charAt(i)在[left, i)之间不存在重复字符
             *          - 就把s.charAt(i)放到map中，最长长度同时也加一
             *      2） s.charAt(i)在[left, i)之间存在重复字符
             *          - 获得[left, i)范围内重复字符的下标h，让left = h + 1，此时新的字串开始匹配，新的长度变成了i - left + 1 = i - h
             *          - 更新map中重复字符的索引位置为i
             *      3） s.charAt(i)在[0, left)之间存在重复字符
             *          - 我们在发现重复字符后都要更新该字符在map中的索引位置，就是为了避免之前的重复元素对当前判断造成影响
             *          - 但是譬如acbba这样一个字符：当遍历到第二个b时，可知h = map.get('b')，所以h=2，那么newLeft=h+1=3;
             *              之后遍历到字符a，h = map.get('a')，发现此时h=0，newLeft=h+1=1；这种情况明显不合理，所以要求left=Math.max(left, newLeft)
             *          - 更新map中重复字符的索引位置为i，最长长度同时也加一
             *
             */
            if (map.containsKey(c)) {
                left = Math.max(left, map.get(c) + 1);
            }
            map.put(c, i);
            maxLen = Math.max(maxLen, i - left + 1);
        }
        return maxLen;
    }

    public int lengthOfLongestSubstring3(String s) {
        // 特殊判断
        if (s == null || s.length() < 1) {
            return 0;
        }
        int left, right;
        left = right = 0;
        // 用于判断是否有重复字符
        Set<Character> set = new HashSet();
        // 定义最大值
        int max = 0;
        while (right < s.length()) {
            // 当窗口需要缩小时，"pwwkew"
            while (set.contains(s.charAt(right))) {
                set.remove(s.charAt(left));
                left++;
            }
            set.add(s.charAt(right));
            right++;
            max = Math.max(max, right - left);
        }
        return max;
    }

    public int lengthOfLongestSubstring4(String s) {
        if (s == null) return 0;
        char[] chars = s.toCharArray();
        if (chars.length == 0) return 0;

        // 用来保存每一个字符上一次出现的位置
        int[] prevIdxes = new int[128];
        for (int i = 0; i < prevIdxes.length; i++) {
            prevIdxes[i] = -1;
        }
        prevIdxes[chars[0]] = 0;
        // 以 i - 1 位置字符结尾的最长不重复字符串的开始索引（最左索引）
        int li = 0;
        int max = 1;
        for (int i = 1; i < chars.length; i++) {
            // i 位置字符上一次出现的位置
            int pi = prevIdxes[chars[i]];
            if (li <= pi) {
                li = pi + 1;
            }
            // 存储这个字符出现的位置
            prevIdxes[chars[i]] = i;
            // 求出最长不重复子串的长度
            max = Math.max(max, i - li + 1);
        }
        return max;
    }


}
