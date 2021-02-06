package 字符串;

import tools.Asserts;

import java.util.Collections;
import java.util.HashMap;

public class _159_至多包含两个不同字符的最长子串 {
    public static void main(String[] args) {
        String s1 = "eceba";
        Asserts.test(lengthOfLongestSubstringTwoDistinct(s1) == 3);

        String s2 = "ccaabbb";
        Asserts.test(lengthOfLongestSubstringTwoDistinct(s2) == 5);
    }

    /**
     * 字符串、哈希表、动态数组
     *
     * T = O(n), S = O(1)
     */
    private static int lengthOfLongestSubstringTwoDistinct(String s) {
        int len = s.length();
        if (len < 3) return len;

        int left = 0, right = 0;
        HashMap<Character, Integer> hashMap = new HashMap<>();

        int maxLen = 2;

        while (right < len) {
            if (hashMap.size() < 3) {
                hashMap.put(s.charAt(right), right++);
            }

            if (hashMap.size() == 3) {
                int delIdx = Collections.min(hashMap.values());
                hashMap.remove(s.charAt(delIdx));
                left = delIdx + 1;
            }

            maxLen = Math.max(maxLen, right - left);
        }

        return maxLen;
    }
}
