package TwoPoints;

/**
 * 76. Minimum Window Substring
 * 难
 * Given two strings s and t, return the minimum window in s which will contain all the characters in t. If there is no
 * such window in s that covers all characters in t, return the empty string "".
 *
 * Note that If there is such a window, it is guaranteed that there will always be only one unique minimum window in s.
 *
 *  
 *
 * Example 1:
 * Input: s = "ADOBECODEBANC", t = "ABC"
 * Output: "BANC"
 *
 * Example 2:
 * Input: s = "a", t = "a"
 * Output: "a"
 *  
 *
 * Constraints:
 *
 * 1 <= s.length, t.length <= 105
 * s and t consist of English letters.
 *  
 *
 * Follow up: Could you find an algorithm that runs in O(n) time?
 */



public class MinimumWindowSubstring {
    public static void main(String[] args) {
        String s = "ADOBECODEBANC";
        String t = "ABC";
        System.out.println(minWindow(s, t));

        System.out.println("--------");

        String s2 = "a";
        String t2 = "a";
        System.out.println(minWindow(s2, t2));
    }

    private static final int ASCII_MAX = 256;

    /**
     * 分析：双指针，动态维护一个区间。尾指针不断往后扫，当扫到有一个窗口包含了所有 T 的字符后，
     * 然后再收缩头指针，直到不能再收缩为止。最后记录所有可能的情况中窗口最小的
     *
     * T = O(n), S = O(1)
     */
    private static String minWindow(String s, String t) {
        if (s.isEmpty()) return "";
        if (s.length() < t.length()) return "";

        int[] appearedCount = new int[ASCII_MAX];
        int[] expectedCount = new int[ASCII_MAX];

        for (int i = 0; i < t.length(); i++) {
            expectedCount[t.charAt(i)]++;
        }

        int minWidth = Integer.MAX_VALUE, minStart = 0; // 窗口大小、起点
        int start = 0;
        int appeared = 0; // 完整包含了一个 t
        // 尾指针不断往后扫
        for (int end = 0; end < s.length(); end++) {
            if (expectedCount[s.charAt(end)] > 0) { // this char is a part of t
                appearedCount[s.charAt(end)]++;
                if (appearedCount[s.charAt(end)] <= expectedCount[s.charAt(end)]) {
                    appeared++;
                }
            }

            // 完整包含了一个 t
            if (appeared == t.length()) {
                // 收缩头指针
                while (appearedCount[s.charAt(start)] > expectedCount[s.charAt(start)] || expectedCount[s.charAt(start)] == 0) {
                    appearedCount[s.charAt(start)]--;
                    start++;
                }
                if (minWidth > (end - start + 1)) {
                    minWidth = end - start + 1;
                    minStart = start;
                }
            }
        }

        if (minWidth == Integer.MAX_VALUE) return "";
        return s.substring(minStart, minStart + minWidth);
    }
}
