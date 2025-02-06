package com.lile.基础;

import com.lile.tools.Asserts;
import com.lile.tools.Times;

public class _509斐波那契 {
    /**
     * 1、dp[i]：第i个数的斐波那契数是dp[i]
     * 2、递推公式：dp[i] = dp[i-1] + dp[i-2]
     * 3、初始化：dp[0] = 0, dp[1] = 1
     * 4、遍历顺序：从递推公式可知 dp[i] 依赖于 dp[i - 1] 和 dp[i - 2]，所以需要从后往前遍历。
     * 5、举例推导dp数组：0 1 1 2 3 5 8 13 21 34 55
     *
     * 时间复杂度：O(n)
     * 空间复杂度：O(n)
     */
    public int fib(int n) {
        if (n <= 1) {
            return n;
        }

        int[] dp = new int[n + 1];
        dp[0] = 0;
        dp[1] = 1;
        for (int i = 2; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[n];
    }

    /**
     * 动态规划（滚动数组）
     * 时间复杂度：O(n)
     * 空间复杂度：O(1)
     */
    public int fib2(int n) {
        if (n <= 1) {
            return n;
        }

        int[] dp = new int[2];
        dp[0] = 0;
        dp[1] = 1;
        for (int i = 2; i <= n; i++) {
            dp[i % 2] = dp[(i - 1) % 2] + dp[(i - 2) % 2];
        }
        return dp[n % 2];
    }

    /**
     * 动态规划（两个变量）
     * 时间复杂度：O(n)
     * 空间复杂度：O(1)
     */
    public int fib3(int n) {
        if (n <= 1) {
            return n;
        }

        int first = 0;
        int second = 1;
        for (int i = 2; i <= n; i++) {
            int curr = first + second;
            first = second;
            second = curr;
        }
        return second;
    }

    /**
     * 递归法
     * 时间复杂度：O(2^n)
     * 空间复杂度：O(n)，算上了编程语言中实现递归的系统栈所占空间
     */
    public int fib4(int n) {
        if (n <= 1) {
            return n;
        }
        return fib4(n - 1) + fib4(n - 2);
    }

    public static void main(String[] args) {
        _509斐波那契 obj = new _509斐波那契();

        Times.test("动态规划", () -> {
            Asserts.test(obj.fib(0) == 0);
            Asserts.test(obj.fib(1) == 1);
            Asserts.test(obj.fib(2) == 1);
            Asserts.test(obj.fib(10) == 55);
            Asserts.test(obj.fib(15) == 610);
            Asserts.test(obj.fib(20) == 6765);
            Asserts.test(obj.fib(25) == 75025);
            Asserts.test(obj.fib(30) == 832040);
            Asserts.test(obj.fib(35) == 9227465);
            Asserts.test(obj.fib(40) == 102334155);
        });

        Times.test("动态规划（滚动数组）", () -> {
            Asserts.test(obj.fib2(0) == 0);
            Asserts.test(obj.fib2(1) == 1);
            Asserts.test(obj.fib2(2) == 1);
            Asserts.test(obj.fib2(10) == 55);
            Asserts.test(obj.fib2(15) == 610);
            Asserts.test(obj.fib2(20) == 6765);
            Asserts.test(obj.fib2(25) == 75025);
            Asserts.test(obj.fib2(30) == 832040);
            Asserts.test(obj.fib2(35) == 9227465);
            Asserts.test(obj.fib2(40) == 102334155);
        });

        Times.test("动态规划（两个变量）", () -> {
            Asserts.test(obj.fib3(0) == 0);
            Asserts.test(obj.fib3(1) == 1);
            Asserts.test(obj.fib3(2) == 1);
            Asserts.test(obj.fib3(10) == 55);
            Asserts.test(obj.fib3(15) == 610);
            Asserts.test(obj.fib3(20) == 6765);
            Asserts.test(obj.fib3(25) == 75025);
            Asserts.test(obj.fib3(30) == 832040);
            Asserts.test(obj.fib3(35) == 9227465);
            Asserts.test(obj.fib3(40) == 102334155);
        });

        Times.test("递归法", () -> {
            Asserts.test(obj.fib4(0) == 0);
            Asserts.test(obj.fib4(1) == 1);
            Asserts.test(obj.fib4(2) == 1);
            Asserts.test(obj.fib4(10) == 55);
            Asserts.test(obj.fib4(15) == 610);
            Asserts.test(obj.fib4(20) == 6765);
            Asserts.test(obj.fib4(25) == 75025);
            Asserts.test(obj.fib4(30) == 832040);
            Asserts.test(obj.fib4(35) == 9227465);
            Asserts.test(obj.fib4(40) == 102334155);
        });
    }
}
