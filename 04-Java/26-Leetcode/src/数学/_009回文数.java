package 数学;

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
public class _009回文数 {
    public static void main(String[] args) {
        _009回文数 obj = new _009回文数();

        Asserts.test(obj.isPalindrome(121));
        Asserts.test(!obj.isPalindrome(-121));
        Asserts.test(!obj.isPalindrome(10));
        Asserts.test(!obj.isPalindrome(-101));

        Asserts.test(obj.isPalindrome2(121));
        Asserts.test(!obj.isPalindrome2(-121));
        Asserts.test(!obj.isPalindrome2(10));
        Asserts.test(!obj.isPalindrome2(-101));
    }

    public boolean isPalindrome(int x) {
        String reversedStr = new StringBuilder(x + "").reverse().toString();
        return (x + "").equals(reversedStr);
    }

    public boolean isPalindrome2(int x) {
        // 假设：x == 1234
        if (x < 0) {
            return false;
        }
        int div = 1;
        while (x / div >= 10) {
            div *= 10;
        } // div == 1000;

        while (x > 0) {
            int left = x / div; // left == 1
            int right = x % 10; // right == 4
            if (left != right) {
                return false;
            }
            x = (x % div) / 10; // x == 23
            div /= 100; // div == 10
        }
        return true;
    }
}
