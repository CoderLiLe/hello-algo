package 字符串;

import tools.Asserts;

public class _125验证回文串 {
    public boolean isPalindrome(String s) {
        if (null == s || s.length() == 0) {
            return true;
        }

        // 先把所有字符转化成小写，并过滤掉空格和标点这类字符
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (Character.isLetterOrDigit(c)) {
                sb.append(Character.toLowerCase(c));
            }
        }

        // 然后对剩下的这些目标字符执行双指针算法，判断回文串
        s = sb.toString();

        int left = 0, right = s.length() - 1;
        while (left < right) {
            while (left < right && !Character.isLetterOrDigit(s.charAt(left))) {
                left++;
            }
            while (left < right && !Character.isLetterOrDigit(s.charAt(right))) {
                right--;
            }
            if (Character.toLowerCase(s.charAt(left)) != Character.toLowerCase(s.charAt(right))) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }

    public static void main(String[] args) {
        _125验证回文串 obj = new _125验证回文串();
        String s = "A man, a plan, a canal: Panama";
        Asserts.test(obj.isPalindrome(s));
        s = "race a car";
        Asserts.test(!obj.isPalindrome(s));
        s = " ";
        Asserts.test(obj.isPalindrome(s));
    }
}
