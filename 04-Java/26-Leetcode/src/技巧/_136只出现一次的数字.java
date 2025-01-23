package 技巧;

public class _136只出现一次的数字 {
    /**
     * 题目要求时间复杂度 O(N) ，空间复杂度 O(1) ，因此首先排除 暴力法 和 辅助哈希表法
     *
     * 两个相同数字异或为 0
     * 若将 nums 中所有数字执行异或运算，留下的结果则为 出现一次的数字 x
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
