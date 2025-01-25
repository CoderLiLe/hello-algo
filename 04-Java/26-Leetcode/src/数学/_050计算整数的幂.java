package 数学;

import tools.Asserts;

public class _050计算整数的幂 {
    /**
     * 解题思路：
     * 求 x^n 最简单的方法是通过循环将 n 个 x 乘起来，依次求 x^1, x^2, ...,x^n−1, x^n，时间复杂度为 O(n) 。
     * 快速幂法 可将时间复杂度降低至 O(logn)
     *
     * @param x 底数
     * @param N 指数
     * @return x 的 N 次方
     */
    public double myPow(double x, int N) {
        // 初始化结果为1，即x^0
        double ans = 1;
        // 使用long类型保存指数，以处理负数情况
        long n = N;
        // 如果指数为负数，将底数取倒数，指数取正数
        if (n < 0) {
            n = -n;
            x = 1 / x;
        }

        // 从低到高枚举 n 的每个比特位
        while (n != 0) {
            // 如果当前比特位为1，将当前底数乘到结果中
            if ((n & 1) == 1) {
                ans *= x;
            }
            // 底数平方，准备下一轮乘积
            x *= x;
            // 继续枚举下一个比特位
            n >>= 1;
        }
        // 返回计算结果
        return ans;
    }

    public static void main(String[] args) {
        _050计算整数的幂 obj = new _050计算整数的幂();
        Asserts.test(Math.abs(obj.myPow(2.00000, 10) - 1024.00000) < 1e-7);
        Asserts.test(Math.abs(obj.myPow(2.10000, 3) - 9.26100) < 1e-7);
        Asserts.test(Math.abs(obj.myPow(2.00000, -2) - 0.25000) < 1e-7);
    }

}
