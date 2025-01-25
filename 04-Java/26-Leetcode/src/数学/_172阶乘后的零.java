package 数学;

import tools.Asserts;

public class _172阶乘后的零 {
    /**
     * 分析
     * 两个数相乘结果末尾有 0，一定是因为两个数中有因子 2 和 5，也就是说，问题转化为：n! 最多可以分解出多少个因子 2 和 5
     * 最多可以分解出多少个因子 2 和 5，主要取决于能分解出几个因子 5，因为每个偶数都能分解出因子 2，因子 2 肯定比因子 5 多得多。
     * 问题转化为：n! 最多可以分解出多少个因子 5？难点在于像 25，50，125 这样的数，可以提供不止一个因子 5，不能漏数了。
     *
     * 这样，我们假设 n = 125，来算一算 125! 的结果末尾有几个 0：
     * 首先，125 / 5 = 25，这一步就是计算有多少个像 5，15，20，25 这些 5 的倍数，它们一定可以提供一个因子 5。
     * 但是，这些足够吗？刚才说了，像 25，50，75 这些 25 的倍数，可以提供两个因子 5，那么我们再计算出 125! 中有 125 / 25 = 5 个 25 的倍数，它们每人可以额外再提供一个因子 5。
     * 够了吗？我们发现 125 = 5 x 5 x 5，像 125，250 这些 125 的倍数，可以提供 3 个因子 5，那么我们还得再计算出 125! 中有 125 / 125 = 1 个 125 的倍数，它还可以额外再提供一个因子 5。
     * 这下应该够了，125! 最多可以分解出 25 + 5 + 1 = 31 个因子 5，也就是说阶乘结果的末尾有 31 个 0。
     */
    public int trailingZeroes(int n) {
        int res = 0;
        long divisor = 5;
        while (divisor <= n) {
            res += n / divisor;
            divisor *= 5;
        }
        return res;
    }

    /**
     * 时间复杂度：O(n)。n! 中因子 5 的个数为 O(n)
     * 空间复杂度：O(1)。
     */
    public int trailingZeroes1(int n) {
        int ans = 0;
        for (int i = 5; i <= n; i += 5) {
            for (int x = i; x % 5 == 0; x /= 5) {
                ++ans;
            }
        }
        return ans;
    }

    /**
     * 时间复杂度：O(logn)。
     * 空间复杂度：O(1)。
     */
    public int trailingZeroes2(int n) {
        int ans = 0;
        while (n != 0) {
            n /= 5;
            ans += n;
        }
        return ans;
    }

    public static void main(String[] args) {
        _172阶乘后的零 obj = new _172阶乘后的零();
        Asserts.test(obj.trailingZeroes(3) == 0);
        Asserts.test(obj.trailingZeroes(5) == 1);
    }
}
