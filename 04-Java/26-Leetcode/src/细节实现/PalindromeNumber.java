package 细节实现;

import tools.Asserts;

/**
 * 9. 回文数
 * 给你一个整数 x ，如果 x 是一个回文整数，返回 ture ；否则，返回 false 。
 *
 * 回文数是指正序（从左向右）和倒序（从右向左）读都是一样的整数。例如，121 是回文，而 123 不是。
 *
 * 不断地取第一位和最后一位（10 进制下）进行比较，相等则取第二位和倒数第
 * 二位，直到完成比较或者中途找到了不一致的位
 */
public class PalindromeNumber {
    public static void main(String[] args) {
        Asserts.test(isPalindrome(121) == true);
        Asserts.test(isPalindrome(-121) == false);
        Asserts.test(isPalindrome(10) == false);
        Asserts.test(isPalindrome(-101) == false);
    }

    private static boolean isPalindrome(int x) {
        if (x < 0) return false;
        int d = 1; // divisor
        while (x / d >= 10) d *= 10;

        while (x > 0) {
            int q = x / d; // quotient
            int r = x % 10; // remainder
            if (q != r) {
                return false;
            }
            x = x % d / 10;
            d /= 100;
        }
        return true;
    }
}
