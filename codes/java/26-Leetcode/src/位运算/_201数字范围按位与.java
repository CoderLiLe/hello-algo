package 位运算;

import tools.Asserts;

public class _201数字范围按位与 {
    /**
     * 位移
     * 求出两个给定数字的二进制字符串的公共前缀
     * 将两个数字不断向右移动，直到数字相等，即数字被缩减为它们的公共前缀。然后，通过将公共前缀向左移动，将零添加到公共前缀的右边以获得最终结果。
     *
     * 时间复杂度：O(logn)。算法的时间复杂度取决于m和n的二进制位数，由于m≤n，因此时间复杂度取决于n的二进制位数。
     * 空间复杂度：O(1)。我们只需要常数空间存放若干变量。
     */
    public int rangeBitwiseAnd(int left, int right) {
        int shift = 0;
        while (left < right) {
            left >>= 1;
            right >>= 1;
            shift++;
        }
        return left << shift;
    }

    /**
     * Brian Kernighan 算法：用于清除二进制串中最右边的1
     * Brian Kernighan 算法的关键在于我们每次对number和number−1之间进行按位与运算后，number中最右边的1会被抹去变成0
     * 基于上述技巧，我们可以用它来计算两个二进制字符串的公共前缀
     *
     * 其思想是，对于给定的范围[m,n]（m<n），我们可以对数字n迭代地应用上述技巧，清除最右边的1，直到它小于或等于m，此时非公共前缀部分的1均被消去。因此最后我们返回n即可。
     *
     * 时间复杂度：O(logn)。和位移方法类似，算法的时间复杂度取决于m和n二进制展开的位数。尽管和位移方法具有相同的渐近复杂度，但 Brian Kernighan 的算法需要的迭代次数会更少，因为它跳过了两个数字之间的所有零位。
     * 空间复杂度：O(1)。我们只需要常数空间存放若干变量。
     */
    public int rangeBitwiseAnd2(int left, int right) {
        while (left < right) {
            // 抹去最右边的 1
            right = right & (right - 1);
        }
        return right;
    }

    public static void main(String[] args) {
        _201数字范围按位与 obj = new _201数字范围按位与();

        Asserts.test(obj.rangeBitwiseAnd(5, 7) == 4);
        Asserts.test(obj.rangeBitwiseAnd2(5, 7) == 4);
    }
}
