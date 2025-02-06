package 排序_数组;

import tools.Asserts;

import java.util.Arrays;

/**
 * 给定一个整数数组 nums，将数组中的元素向右轮转 k 个位置，其中 k 是非负数。
 *
 * 进阶：
 *
 * 尽可能想出更多的解决方案，至少有 三种 不同的方法可以解决这个问题。
 * 你可以使用空间复杂度为 O(1) 的 原地 算法解决这个问题吗？
 */
public class _189旋转数组 {
    /**
     * 暴力法
     * 每次往后面移动一位，一共操作k次
     *
     *  时间复杂度： O(nk)，其中 n 为数组的长度。
     *  空间复杂度： O(1)。
     *
     * @param nums
     * @param k
     */
    public void rotate(int[] nums, int k) {
        for (int i = 0; i < k; i++) {
            int lastNum = nums[nums.length - 1];
            for (int j = nums.length - 1; j > 0; j--) {
                nums[j] = nums[j - 1];
            }
            nums[0] = lastNum;
        }
    }

    /**
     * 使用额外的数组
     *
     * 时间复杂度： O(n)，其中 n 为数组的长度。
     * 空间复杂度： O(n)。
     * @param nums
     * @param k
     */
    public void rotate2(int[] nums, int k) {
        int[] newNums = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            newNums[(i + k) % nums.length] = nums[i];
        }
        System.arraycopy(newNums, 0, nums, 0, nums.length);
    }

    /**
     * 本题没有保证 k 小于数组长度 n。由于轮转 n 次等于没有轮转，轮转 n+1 等于轮转 1 次，依此类推，轮转 k 次等于轮转 kmodn 次。
     * 时间复杂度：O(n)，其中 n 是 nums 的长度。
     * 空间复杂度：O(1)。
     * @param nums
     * @param k
     */
    public void rotate3(int[] nums, int k) {
        int n = nums.length;
        // 轮转 k 次等于轮转 k%n 次
        k %= n;
        reverse(nums, 0, n - 1);
        reverse(nums, 0, k - 1);
        reverse(nums, k, n - 1);
    }

    private void reverse(int[] nums, int i, int j) {
        while (i < j) {
            int temp = nums[i];
            nums[i++] = nums[j];
            nums[j--] = temp;
        }
    }

    public static void main(String[] args) {
        _189旋转数组 obj = new _189旋转数组();
        int[] nums = new int[] {1,2,3,4,5,6,7};
        int[] res = new int[]{5,6,7,1,2,3,4};
        obj.rotate(nums, 3);
        Asserts.test(Arrays.equals(nums, res));
    }
}
