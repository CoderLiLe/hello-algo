package 技巧;

public class _136只出现一次的数字 {
    /**
     * 题目要求时间复杂度 O(N) ，空间复杂度 O(1) ，因此首先排除 暴力法 和 辅助哈希表法
     *
     * 两个相同数字异或为 0
     * 若将 nums 中所有数字执行异或运算，留下的结果则为 出现一次的数字 x
     *
     * 一个数和它本身做异或运算结果为 0，即 a ^ a = 0；一个数和 0 做异或运算的结果为它本身，即 a ^ 0 = a
     * 只要把所有数字进行异或，成对儿的数字就会变成 0，落单的数字和 0 做异或还是它本身，所以最后异或的结果就是只出现一次的元素
     *
     * @param nums
     * @return
     */
    public int singleNumber(int[] nums) {
        int ans = 0;
        for(int num: nums) {
            ans ^= num;
        }
        return ans;
    }
}
