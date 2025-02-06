package TwoPoints;

/**
 * https://leetcode.cn/problems/remove-duplicates-from-sorted-array-ii
 */
public class _080_删除有序数组中的重复项II {
    /**
     * 这道题和 26. 删除有序数组中的重复项 解法非常类似，只不过这道题说重复两次以上的元素才需要被去除。
     *
     * 本题解法依然使用快慢指针技巧，在之前的解法中添加一个 count 变量记录每个数字重复出现的次数
     * @param nums
     * @return
     */
    public int removeDuplicates(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        // 快慢指针，维护 nums[0..slow] 为结果子数组
        int slow = 0, fast = 0;
        // 记录一个元素重复的次数
        int count = 0;
        while (fast < nums.length) {
            if (nums[fast] != nums[slow]) {
                // 此时，对于 nums[0..slow] 来说，nums[fast] 是一个新的元素，加进来
                slow++;
                nums[slow] = nums[fast];
            } else if (slow < fast && count < 2) {
                // 此时，对于 nums[0..slow] 来说，nums[fast] 重复次数小于 2，也加进来
                slow++;
                nums[slow] = nums[fast];
            }
            fast++;
            count++;
            if (fast < nums.length && nums[fast] != nums[fast - 1]) {
                // fast 遇到新的不同的元素时，重置 count
                count = 0;
            }
        }
        // 数组长度为索引 + 1
        return slow + 1;
    }

    /**
     * 为了让解法更具有一般性，我们将原问题的「保留 2 位」修改为「保留 k 位」。
     *
     * 对于此类问题，我们应该进行如下考虑：
     * 由于是保留 k 个相同数字，对于前 k 个数字，我们可以直接保留
     * 对于后面的任意数字，能够保留的前提是：与当前写入的位置前面的第 k 个元素进行比较，不相同则保留
     */
    public int removeDuplicates2(int[] nums) {
        return process(nums, 2);
    }

    int process(int[] nums, int k) {
        int u = 0;
        for (int x : nums) {
            if (u < k || nums[u - k] != x) {
                nums[u++] = x;
            }
        }
        return u;
    }
}
