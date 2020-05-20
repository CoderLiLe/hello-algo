package 高频题;

public class _050_Pow {
    // T(n) = T(n/2) + O(1) = O(logn)
    // S(n) = O(logn)
    public double myPow(double x, int n) {
        if (n == 0) return 1;
        if (n == -1) return 1/x;

        // 是否为奇数
        boolean odd = (n & 1) == 1;
        double half = myPow(x, n >> 1);
        half *= half;
        return odd ? (half * x) : half;
    }

    // T(n) = T(n/2) + O(1) = O(logn)
    // S(n) = O(1)
    public double myPow2(double x, int n) {
        boolean neg = n < 0;
        long y = neg ? -((long)n) : n;
        double res = 1.0;
        while (y > 0) {
            if ((y & 1) == 1) {
                // 如果最后一个二进制位是1，就要乘上 x
                res *= x;
            }

            x *= x;

            // 舍弃掉最后一个二进制位
            y >>= 1;
        }

        return neg ? (1 / res) : res;
    }
}
