package 排序_数组.二分查找;

import tools.Asserts;

public class _704二分查找 {
    public int search(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        while (left <= right) {
            int mid = left + ((right - left) >> 1);
            if (nums[mid] < target) {
                left = mid + 1;
            } else if (nums[mid] > target) {
                right = mid - 1;
            } else {
                return mid;
            }
        }
        return -1;
    }

    public int search2(int[] nums, int target) {
        int left = 0, right = nums.length;
        while (left < right) {
            int mid = left + ((right - left) >> 1);
            if (nums[mid] < target) {
                left = mid + 1;
            } else if (nums[mid] > target) {
                right = mid;
            } else {
                return mid;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        _704二分查找 obj = new _704二分查找();

        int[] nums = {-1, 0, 3, 5, 9, 12};
        int target = 9;
        int res = 4;
        Asserts.test(obj.search(nums, target) == res);
        Asserts.test(obj.search2(nums, target) == res);
    }
}
