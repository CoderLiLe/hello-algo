package 数学;

import com.lile.tools.Asserts;

public class _190颠倒二进制位 {
    /**
     * 将 n 视作一个长为 32 的二进制串，从低位往高位枚举 n 的每一位，将其倒序添加到翻转结果 res 中
     * 时间复杂度：O(logn)。
     * 空间复杂度：O(1)。
     */
    public int reverseBits(int n) {
        int res = 0;
        // 迭代32次 或 n为0则提前结束
        for (int i = 0; i < 32 && n != 0; ++i) {
            // (n & 1): 用于获取n的最右边一位的值
            // << (31 - i): 用于将结果移动到第(31 - i)位上，从31,30,...0位
            // |=: 用于将计算出的值与当前rev值 按位或 运算
            res |= (n & 1) << (31 - i);
            // n >>= 1: 将n逻辑右移一位，相当于舍弃最右边一位，高位用0填充
            n >>= 1;
        }
        return res;
    }

    /**
     * 分而治之，类似于归并排序
     * 其思想是分而治之，把数字分为两半，然后交换这两半的顺序；
     * 然后把前后两个半段都再分成两半，交换内部顺序……直至最后交换顺序的时候，交换的数字只有 1 位
     * 时间复杂度：O(1)
     * 空间复杂度：O(1)
     *
     * 32位无符号整数，如 1111 1111 1111 1111 1111 1111 1111 1111
     * 表示成16进制        f    f    f    f    f    f    f   f
     * 一个16进制的f代表二进制的4位
     * ffff ffff右移16位，变成 0000 ffff
     * ffff ffff左移16位，变成 ffff 0000
     * 它们俩相或，就可以完成低16位与高16位的交换
     *
     * 之后的每次分治，都要先与上一个掩码，再进行交换
     *
     * 对于递归的最底层，我们需要交换所有奇偶位：
     * （1）取出所有奇数位和偶数位；
     * （2）将奇数位移到偶数位上，偶数位移到奇数位上。
     *
     */
    private static final int M1 = 0x55555555; // 01010101010101010101010101010101
    private static final int M2 = 0x33333333; // 00110011001100110011001100110011
    private static final int M4 = 0x0f0f0f0f; // 00001111000011110000111100001111
    private static final int M8 = 0x00ff00ff; // 00000000111111110000000011111111

    public int reverseBits2(int n) {
        n = n >>> 1 & M1 | (n & M1) << 1;
        n = n >>> 2 & M2 | (n & M2) << 2;
        n = n >>> 4 & M4 | (n & M4) << 4;
        n = n >>> 8 & M8 | (n & M8) << 8;
        return n >>> 16 | n << 16;
    }

    public int reverseBits3(int n) {
        return Integer.reverse(n);
    }

    public static void main(String[] args) {
        _190颠倒二进制位 obj = new _190颠倒二进制位();
        Asserts.test(obj.reverseBits(43261596) == 964176192);
        Asserts.test(obj.reverseBits2(43261596) == 964176192);
        Asserts.test(obj.reverseBits3(43261596) == 964176192);
    }
}
