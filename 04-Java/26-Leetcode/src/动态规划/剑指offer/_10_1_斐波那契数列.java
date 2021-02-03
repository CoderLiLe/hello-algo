package 动态规划.剑指offer;

import tools.Asserts;

public class _10_1_斐波那契数列 {
    public static void main(String[] args) {
        Asserts.test(fib(5) == 5);
    }

    public static int fib(int n) {
        int x = 0, y = 1, sum = 0;
        for (int i = 0; i < n; i++) {
            sum = (x + y) % 1000000007;
            x = y;
            y = sum;
        }
        return x;
    }
}
