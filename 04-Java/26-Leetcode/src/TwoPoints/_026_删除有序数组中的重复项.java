package TwoPoints;

/**
 * https://leetcode.cn/problems/remove-duplicates-from-sorted-array
 */
public class _026_删除有序数组中的重复项 {
    public int removeDuplicates(int[] nums) {
        if (0 == nums.length) {
            return 0;
        }

        int slow = 0, fast = 0;
        while (fast < nums.length) {
            if (nums[fast] != nums[slow]) {
                slow++;
                // 维护 nums[0..slow] 无重复
                nums[slow] = nums[fast];
            }
            fast++;
        }

        // 数组长度为索引 + 1
        return slow + 1;
    }

    /**
     * 双指针解法
     * 时间复杂度：O(n)
     * 空间复杂度：O(1)
     *
     * @param nums
     * @return
     */
    public int removeDuplicates2(int[] nums) {
        int n = nums.length;
        int j = 0;
        for (int i = 0; i < n; i++) {
            if (nums[i] != nums[j]) {
                nums[++j] = nums[i];
            }
        }
        return j + 1;
    }

    /**
     * 通用解法
     * 为了让解法更具有一般性，我们将原问题的「最多保留 1 位」修改为「最多保留 k 位」。
     * 由于是保留 k 个相同数字，对于前 k 个数字，我们可以直接保留。
     * 对于后面的任意数字，能够保留的前提是：与当前写入的位置前面的第 k 个元素进行比较，不相同则保留。
     *
     * 时间复杂度：O(n)
     * 空间复杂度：O(1)
     *
     * @param nums
     * @return
     */
    public int removeDuplicates3(int[] nums) {
        return process(nums, 1);
    }
    int process(int[] nums, int k) {
        int idx = 0;
        for (int x : nums) {
            if (idx < k || nums[idx - k] != x) {
                nums[idx++] = x;
            }
        }
        return idx;
    }

    /**
     * 基于上述解法我们还能做一点小剪枝：利用目标数组的最后一个元素必然与原数组的最后一个元素相同进行剪枝，
     * 从而确保当数组有超过 k 个最大值时，数组不会被完整扫描。
     *
     * 但需要注意这种「剪枝」同时会让我们单次循环的常数变大，所以仅作为简单拓展。
     */
    public int removeDuplicates4(int[] nums) {
        int n = nums.length;
        if (n <= 1) {
            return n;
        }
        return process4(nums, 1, nums[n - 1]);
    }
    int process4(int[] nums, int k, int max) {
        int idx = 0;
        for (int x : nums) {
            if (idx < k || nums[idx - k] != x) {
                nums[idx++] = x;
            }
            if (idx - k >= 0 && nums[idx - k] == max) {
                break;
            }
        }
        return idx;
    }
}
