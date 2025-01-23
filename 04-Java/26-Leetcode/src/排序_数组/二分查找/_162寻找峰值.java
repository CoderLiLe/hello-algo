package 排序_数组.二分查找;

import tools.Asserts;

/**
 * https://leetcode.cn/problems/find-peak-element/
 */
public class _162寻找峰值 {
    /**
     * 如果 i<n−1 且 nums[i]<nums[i+1]，那么在下标 [i+1,n−1] 中一定存在至少一个峰值
     * 如果 i<n−1 且 nums[i]>nums[i+1]，那么在 [0,i] 中一定存在至少一个峰值
     * <p>
     * 这个可以用反证法，如果这句不成立，比如mid 的元素大于mid +1，那么左边一定有峰值。如果没有，你从mid -1，开始往前推，会发现，
     * 要保证这点，就会出现元素0>1>2>...>mid -2>mid-1>mid。即使这样，由于-1是无穷小，那么0这个点也是峰值。
     *
     * @param nums
     * @return
     */
    public int findPeakElement(int[] nums) {
        int left = 0, right = nums.length - 1;
        while (left < right) {
            int mid = left + (right - left) / 2;
            // 根据左右指针计算中间位置 m，并比较 m 与 m+1 的值，如果 m 较大，则左侧存在峰值，r = m，
            // 如果 m + 1 较大，则右侧存在峰值，l = m + 1
            if (nums[mid] > nums[mid + 1]) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }

    public int findPeakElement2(int[] nums) {
        int left = 0;
        int right = nums.length - 2;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] > nums[mid + 1]) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }

    public static void main(String[] args) {
        _162寻找峰值 obj = new _162寻找峰值();
        int[] nums = new int[]{1, 2, 3, 1};
        Asserts.test(obj.findPeakElement(nums) == 2);
        Asserts.test(obj.findPeakElement2(nums) == 2);
    }
}
