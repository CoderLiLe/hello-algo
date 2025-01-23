package 排序_数组;

import tools.Asserts;

/**
 * https://leetcode.cn/problems/find-minimum-in-rotated-sorted-array/description/
 */
public class _153寻找旋转排序数组中的最小值 {
    public int findMin(int[] nums) {
        int left = 0;
        // 左闭右闭区间，如果用右开区间则不方便判断右值
        int right = nums.length - 1;
        // 循环不变式，如果left == right，则循环结束
        while (left < right) {
            // 地板除，mid更靠近left
            int mid = left + (right - left) / 2;
            // 中值 > 右值，最小值在右半边，收缩左边界
            if (nums[mid] > nums[right]) {
                // 因为中值 > 右值，中值肯定不是最小值，左边界可以跨过mid
                left = mid + 1;
            }
            // 明确中值 < 右值，最小值在左半边，收缩右边界
            else if (nums[mid] < nums[right]) {
                // 因为中值 < 右值，中值也可能是最小值，右边界只能取到mid处
                right = mid;
            }
        }
        // 循环结束，left == right，最小值输出nums[left]或nums[right]均可
        return nums[left];
    }

    public int findMin2(int[] nums) {
        int left = 0;
        int right = nums.length - 1;
        while (left < right) {
            // 先加一再除，mid更靠近右边的right
            int mid = left + (right - left + 1) / 2;
            if (nums[left] < nums[mid]) {
                // 向右移动左边界
                left = mid;
            } else if (nums[left] > nums[mid]) {
                // 向左移动右边界
                right = mid - 1;
            }
        }
        // 最大值向右移动一位就是最小值了（需要考虑最大值在最右边的情况，右移一位后对数组长度取余）
        return nums[(right + 1) % nums.length];
    }

    public int findMin3(int[] nums) {
        int left = 0;
        int right = nums.length - 1;
        // 循环的条件选为左闭右闭区间left <= right
        while (left <= right) {
            int mid = left + (right - left) / 2;
            // 注意是当中值大于等于右值时，
            if (nums[mid] >= nums[right]) {
                // 将左边界移动到中值的右边
                left = mid + 1;
            }
            // 当中值小于右值时
            else {
                // 将右边界移动到中值处
                right = mid;
            }
        }
        // 最小值返回nums[right]
        return nums[right];
    }

    public static void main(String[] args) {
        _153寻找旋转排序数组中的最小值 obj = new _153寻找旋转排序数组中的最小值();
        int[] nums = new int[] {4, 5, 6, 7, 0, 1, 2};
        Asserts.test(obj.findMin(nums) == 0);
        Asserts.test(obj.findMin2(nums) == 0);
        Asserts.test(obj.findMin3(nums) == 0);
        nums = new int[] {1, 2, 3, 4, 5, 6, 7};
        Asserts.test(obj.findMin(nums) == 1);
        Asserts.test(obj.findMin2(nums) == 1);
        Asserts.test(obj.findMin3(nums) == 1);
    }
}
