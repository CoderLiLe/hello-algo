package 位运算;

import com.lile.tools.Asserts;

public class _461汉明距离 {
    /**
     * 内置位计数功能
     * 大多数编程语言都内置了计算二进制表达中1的数量的函数。在工程中，我们应该直接使用内置函数
     */
    public int hammingDistance(int x, int y) {
        return Integer.bitCount(x ^ y);
    }

    /**
     * 移位实现位计数
     *
     * 时间复杂度：O(logC)，其中C是元素的数据范围，在本题中 logC = log2^31 = 31。
     * 空间复杂度：O(1)。
     */
    public int hammingDistance2(int x, int y) {
        int s = x ^ y, res = 0;
        while (s != 0) {
            res += s & 1;
            s >>= 1;
        }
        return res;
    }

    /**
     * Brian Kernighan算法
     *
     * 时间复杂度：O(logC)，其中C是元素的数据范围，在本题中 logC = log2^31 = 31。
     * 空间复杂度：O(1)。
     */
    public int hammingDistance3(int x, int y) {
        int res = 0;
        int xor = x ^ y;
        while (xor != 0) {
            res++;
            xor = xor & (xor - 1);
        }
        return res;
    }

    public static void main(String[] args) {
        _461汉明距离 obj = new _461汉明距离();
        Asserts.test(obj.hammingDistance(1, 4) == 2);
        Asserts.test(obj.hammingDistance2(1, 4) == 2);
        Asserts.test(obj.hammingDistance3(1, 4) == 2);
    }
}
