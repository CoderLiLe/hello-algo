package 细节实现;

import tools.Asserts;

/**
 * 7. Reverse Integer
 * Given a signed 32-bit integer x, return x with its digits reversed. If reversing x causes the value to go outside the signed 32-bit integer range [-231, 231 - 1], then return 0.
 *
 * Assume the environment does not allow you to store 64-bit integers (signed or unsigned).
 *
 * Constraints:
 *
 * -2^31 <= x <= 2^31 - 1
 */
public class ReverseInteger {
    public static void main(String[] args) {
        Asserts.test(reverse(123) == 321);
        Asserts.test(reverse(-123) == -321);
        Asserts.test(reverse(120) == 21);
        Asserts.test(reverse(0) == 0);
        Asserts.test(reverse(1534236469) == 0);
        Asserts.test(reverse(2147483647) == 0);
        Asserts.test(reverse(2147483641) == 1463847412);
    }

    /**
     * T = O(logn), S = O(1)
     */
    private static int reverse(int x) {
        int res = 0;
        for (; x != 0; x /= 10) {
            int pop = x % 10;
            if (res > Integer.MAX_VALUE / 10 || (res == Integer.MAX_VALUE / 10 && pop > 7)) {
                return 0;
            }
            if (res < Integer.MIN_VALUE / 10 || (res == Integer.MIN_VALUE / 10 && pop < -8)) {
                return 0;
            }
            res = res * 10 + pop;
        }
        return res;
    }
}
