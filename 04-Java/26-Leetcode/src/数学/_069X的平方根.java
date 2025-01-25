package 数学;

import tools.Asserts;

/**
 * https://leetcode.cn/problems/sqrtx
 * 输入非负整数 x，返回最大的非负整数 m，满足 m^2 ≤ x。
 */
public class _069X的平方根 {

    /**
     * 二分查找
     * 算法思想：
     * 对于非负整数 m 来说，由于 m 越小越满足 m^2 ≤ x，m 越大越不满足 m^2 ≤ x，有单调性，可以二分答案
     *
     * 算法步骤：
     * 如果 m^2 ≤ x 成立，那么最终答案大于等于 m，更新 left。
     * 如果 m^2 > x 成立，那么最终答案小于 m，更新 right。
     *
     * 时间复杂度：O(logx)，即为二分查找需要的次数。
     * 空间复杂度：O(1)。
     */
    public int mySqrt(int x) {
        int l = 0, r = x, res = -1;
        while (l <= r) {
            int mid = l + (r - l) / 2;
            if ((long) mid * mid <= x) {
                res = mid;
                l = mid + 1;
            } else {
                r = mid - 1;
            }
        }
        return res;
    }

    public int mySqrt2(int x) {
        // 输入验证
        if (x < 0) {
            // 表示无效输入
            return -1;
        }

        // 特殊情况处理
        if (x == 0 || x == 1) {
            return x;
        }

        // 使用二分查找法计算平方根
        int left = 1, right = x / 2;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            long square = (long) mid * mid;

            if (square == x) {
                return mid;
            } else if (square < x) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        // 返回最接近的整数平方根
        return right;
    }

    /**
     * 「袖珍计算器算法」是一种用指数函数 exp 和对数函数 ln 代替平方根函数的方法。我们通过有限的可以使用的数学函数，得到我们想要计算的结果。
     */
    public int mySqrt3(int x) {
        if (x == 0) {
            return 0;
        }
        int ans = (int) Math.exp(0.5 * Math.log(x));
        return (long) (ans + 1) * (ans + 1) <= x ? ans + 1 : ans;
    }

    /**
     * 牛顿迭代法是一种可以用来快速求解函数零点的方法。
     * 它的思路是，从初始值 x0 开始，不断迭代 x0 的值，直到满足 x0 的值与 x 的差值小于一个给定的阈值。
     * 在牛顿迭代中，每次迭代，x0 的值都会根据函数 f(x) 的导数 f'(x) 来更新，即 x0 = x0 - f(x0) / f'(x0)。
     * 迭代的终止条件是 x0 的值与 x 的差值小于一个给定的阈值。
     * 时间复杂度：O(logx)，即为牛顿迭代的次数。
     * 空间复杂度：O(1)。
     */
    public int mySqrt4(int x) {
        if (x == 0) {
            return 0;
        }

        double C = x, x0 = x;
        while (true) {
            double xi = 0.5 * (x0 + C / x0);
            if (Math.abs(x0 - xi) < 1e-7) {
                break;
            }
            x0 = xi;
        }
        return (int) x0;
    }


    public static void main(String[] args) {
        _069X的平方根 obj = new _069X的平方根();

        Asserts.test(obj.mySqrt(1) == 1);
        Asserts.test(obj.mySqrt(4) == 2);
        Asserts.test(obj.mySqrt(8) == 2);
    }
}
