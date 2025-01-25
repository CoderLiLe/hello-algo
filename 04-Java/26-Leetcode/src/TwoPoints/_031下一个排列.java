package TwoPoints;

import tools.Asserts;

import java.util.Arrays;

public class _031下一个排列 {
    /**
     * 算法思想：
     * 将一个左边的「较小数」与一个右边的「较大数」交换，以能够让当前排列变大，从而得到下一个排列
     * 同时我们要让这个「较小数」尽量靠右，而「较大数」尽可能小。当交换完成后，「较大数」右边的数需要按照升序重新排列。
     * 这样可以在保证新排列大于原来排列的情况下，使变大的幅度尽可能小
     * <p>
     * 算法描述：
     * 1、首先从后向前查找第一个顺序对 (i,i+1)，满足 a[i]<a[i+1]。这样「较小数」即为 a[i]。此时 [i+1,n) 必然是下降序列。
     * 2、如果找到了顺序对，那么在区间 [i+1,n) 中从后向前查找第一个元素 j 满足 a[i]<a[j]。这样「较大数」即为 a[j]。
     * 3、交换 a[i] 与 a[j]，此时可以证明区间 [i+1,n) 必为降序。我们可以直接使用双指针反转区间 [i+1,n) 使其变为升序，而无需对该区间进行排序。
     *
     * 注意：
     * 如果在步骤 1 找不到顺序对，说明当前序列已经是一个降序序列，即最大的序列，我们直接跳过步骤 2 执行步骤 3，即可得到最小的升序序列。
     *
     * @param nums
     */
    public void nextPermutation(int[] nums) {
        int i = nums.length - 2;
        // 从后向前查找第一个顺序对 (i, i+1)，满足 a[i] < a[i+1]
        while (i >= 0 && nums[i] >= nums[i + 1]) {
            i--;
        }
        // 如果找到了顺序对
        if (i >= 0) {
            int j = nums.length - 1;
            // 在区间 [i+1, n) 中从后向前查找第一个元素 j 满足 a[i] < a[j]
            while (j >= 0 && nums[i] >= nums[j]) {
                j--;
            }
            // 交换 a[i] 与 a[j]
            swap(nums, i, j);
        }
        // 反转区间 [i+1, n) 使其变为升序
        reverse(nums, i + 1);
    }

    /**
     * 交换数组中的两个元素
     */
    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    /**
     * 反转数组的指定部分
     * @param nums
     * @param start
     */
    private void reverse(int[] nums, int start) {
        int i = start, j = nums.length - 1;
        while (i < j) {
            swap(nums, i, j);
            i++;
            j--;
        }
    }

    public void nextPermutation2(int[] nums) {
        for (int i = nums.length - 1; i >= 0; i--) {
            for (int j = nums.length - 1; j > i; j--) {
                if (nums[j] > nums[i]) {
                    // 交换
                    int temp = nums[i];
                    nums[i] = nums[j];
                    nums[j] = temp;
                    // [i + 1, nums.length) 内元素升序排序
                    Arrays.sort(nums, i + 1, nums.length);
                    return;
                }
            }
        }
        // 不存在下一个更大的排列，则将数字重新排列成最小的排列（即升序排列）。
        Arrays.sort(nums);
    }

    public static void main(String[] args) {
        _031下一个排列 obj = new _031下一个排列();

        int[] nums = {1, 2, 3};
        obj.nextPermutation(nums);
        Asserts.test(Arrays.equals(nums, new int[]{1, 3, 2}));

        int[] nums2 = {1, 2, 3};
        obj.nextPermutation(nums2);
        Asserts.test(Arrays.equals(nums2, new int[]{1, 3, 2}));
    }
}
