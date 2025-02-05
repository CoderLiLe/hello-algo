package 数学;

import com.lile.tools.Asserts;

public class _191位1的个数 {
    /**
     * 时间复杂度 O(logn) ： 此算法循环内部仅有 移位、与、加 等基本运算，占用 O(1) ；逐位判断需循环 logn 次，其中 logn 代表数字 n 最高位 1 的所在位数
     * 空间复杂度 O(1) ： 变量 res 使用常数大小额外空间。
     */
    public int hammingWeight(int n) {
        int res = 0;
        while (n != 0) {
            // 若 n&1=1 ，则统计数 res 加一
            res += n & 1;
            // 将二进制数字 n 无符号右移一位（ Java 中无符号右移为 ">>>" ）
            n >>>= 1;
        }
        return res;
    }

    /**
     * 巧用 n&(n−1)
     * (n−1) 作用： 二进制数字 n 最右边的 1 变成 0 ，此 1 右边的 0 都变成 1
     * n&(n−1) 作用： 二进制数字 n 最右边的 1 变成 0 ，其余不变
     */
    public int hammingWeight2(int n) {
        int res = 0;
        while (n != 0) {
            res++;
            n = n & (n - 1);
        }
        return res;
    }

    public static void main(String[] args) {
        _191位1的个数 obj = new _191位1的个数();

        int n = 11;
        Asserts.test(obj.hammingWeight(n) == 3);
        Asserts.test(obj.hammingWeight2(n) == 3);
    }
}
