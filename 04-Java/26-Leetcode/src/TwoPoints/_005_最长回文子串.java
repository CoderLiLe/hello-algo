package TwoPoints;

public class _005_最长回文子串 {
    /**
     * 双指针法
     * 寻找回文串的问题核心思想是：从中间开始向两边扩散来判断回文串
     *
     * T = O(N^2), S = O(1)
     *
     * @param s
     * @return
     */
    public String longestPalindrome(String s) {
        String res = "";
        for (int i = 0; i < s.length(); i++) {
            // 以 s[i] 为中心的最长回文子串
            String s1 = palindrome(s, i, i);
            // 以 s[i] 和 s[i + 1] 为中心的最长回文子串
            String s2 = palindrome(s, i, i + 1);

            if (s1.length() > res.length()) {
                res = s1;
            }

            if (s2.length() > res.length()) {
                res = s2;
            }
        }

        return res;
    }

    private String palindrome(String s, int l, int r) {
        while (l >= 0 && r < s.length() && s.charAt(l) == s.charAt(r)) {
            // 向两边扩展
            l--;
            r++;
        }
        return s.substring(l + 1, r);
    }

    public static void main(String[] args) {
        _005_最长回文子串 obj = new _005_最长回文子串();
        System.out.println(obj.longestPalindrome("abba"));
        System.out.println(obj.longestPalindrome("babad"));
        System.out.println(obj.longestPalindrome("cbbd"));
    }
}
