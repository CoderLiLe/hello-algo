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

    /*
    * 设计一个算法求 x 的 y 次幂模 z 的结果: x^y % z
    * 假设 x、y 都可能是很大的整数
    * y >= 0, z != 0
    *
    * 公式：
    * (a * b) % p = ((a % p) * (b % p)) % p
    *
    * */

    public static int powMod(int x, int y, int z) {
        if (y < 0 || z == 0) return 0;
        int res = 1 % z;
        x %= z;
        while (y > 0) {
            if ((y & 1) == 1) {
                // 如果最后一个二进制位是1，就累乘上 x
                res = (res * x) % z;
            }
            x = (x * x) % z;
            // 舍弃掉最后一个二进制位
            y >>= 1;
        }
        return res;
    }

    // 2^100 % 6 = (2^50 * 2^50) % 6 = ((2^50 % 6) * (2^50 % 6)) % 6
    // 2^101 % 6 = (2^50 * 2^50 * 2) % 6 = ((2^50 % 6) * (2^50 % 6) * (2 % 6)) % 6
    public static int powMod2(int x, int y, int z) {
        if (y < 0 || z == 0) return 0;
        if (y == 0) return 1 % z;
        int half = powMod2(x, y >> 1, z);
        half *= half;
        if ((y & 1) == 0) { // 偶数
            return half % z;
        } else { // 奇数
            return (half * (x % z)) % z;
        }
    }

    public static void main(String[] args) {
        System.out.println(powMod(123, 455, 789));
        System.out.println(powMod2(123, 455, 789));
    }
}
