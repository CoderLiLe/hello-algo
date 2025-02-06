package 排序_数组.二分查找;

import tools.Asserts;

public class _033搜索旋转排序数组 {

    /**
     * 使用二分法在旋转排序数组中搜索目标值
     * 将数组一分为二，其中一定有一个是有序的，另一个可能是有序，也能是部分有序。
     * 此时有序部分用二分法查找。无序部分再一分为二，其中一个一定有序，另一个可能有序，可能无序。就这样循环.
     * T = O(logn), S = O(1)
     *
     * @param nums   旋转排序数组
     * @param target 需要搜索的目标值
     * @return 目标值的索引，如果未找到则返回-1
     */
    public int search(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return -1;
        }
        int start = 0;
        int end = nums.length - 1;
        int mid;
        while (start <= end) {
            mid = start + ((end - start) >> 1);
            if (nums[mid] == target) {
                return mid;
            }
            // 前半部分有序,注意此处用小于等于
            if (nums[start] <= nums[mid]) {
                // target在前半部分
                if (target >= nums[start] && target < nums[mid]) {
                    end = mid - 1;
                } else {
                    start = mid + 1;
                }
            } else {
                if (target <= nums[end] && target > nums[mid]) {
                    start = mid + 1;
                } else {
                    end = mid - 1;
                }
            }

        }
        return -1;
    }

    /**
     * https://leetcode.cn/problems/search-in-rotated-sorted-array/solutions/2636954/javapython3cer-fen-cha-zhao-you-xu-de-ba-5g7e/?envType=study-plan-v2&envId=top-100-liked
     *
     * @param nums
     * @param target
     * @return
     */
    public int search2(int[] nums, int target) {
        // 二分查找左边界（左闭）
        int left = 0;
        // 二分查找右边界（右开）
        int right = nums.length;
        while (left < right) {
            int mid = left + ((right - left) >> 1);
            if (nums[mid] == target) {
                // 找到目标值，直接返回索引
                return mid;
            }
            if (nums[left] < nums[mid]) {
                // 左半区间有序
                if (nums[left] <= target && target < nums[mid]) {
                    // 目标值落入左半区间，更新右边界
                    right = mid;
                } else {
                    // 否则在右半区间查找
                    left = mid + 1;
                }
            } else {
                // 右半区间有序
                if (nums[mid] < target && target <= nums[right - 1]) {
                    // 目标值落入右半区间，更新左边界
                    left = mid + 1;
                } else {
                    // 否则在左半区间查找
                    right = mid;
                }
            }
        }
        // 如果退出循环，说明没有找到目标值
        return -1;
    }

    public static void main(String[] args) {
        _033搜索旋转排序数组 obj = new _033搜索旋转排序数组();
        int[] nums = new int[]{4, 5, 6, 7, 0, 1, 2};
        int target = 0;
        Asserts.test(obj.search(nums, target) == 4);
        Asserts.test(obj.search2(nums, target) == 4);
    }

}
