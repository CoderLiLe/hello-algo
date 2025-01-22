package 排序_数组;

import tools.Asserts;

import java.util.Arrays;

public class _034在排序数组中查找元素的第一个和最后一个位置 {
    /**
     * 前置题目：
     * 704.二分查找
     * 35.搜索插入位置
     *
     * 寻找target在数组里的左右边界，有如下三种情况：
     *
     * 情况一：target 在数组范围的右边或者左边，例如数组{3, 4, 5}，target为2或者数组{3, 4, 5},target为6，此时应该返回{-1, -1}
     * 情况二：target 在数组范围中，且数组中不存在target，例如数组{3,6,7},target为5，此时应该返回{-1, -1}
     * 情况三：target 在数组范围中，且数组中存在target，例如数组{3,6,7},target为6，此时应该返回{1, 1}
     */
    int[] searchRange(int[] nums, int target) {
        int leftBorder = getLeftBorder(nums, target);
        int rightBorder = getRightBorder(nums, target);

        // 情况一：target 在数组范围的右边或者左边
        if (leftBorder == -2 || rightBorder == -2) {
            return new int[]{-1, -1};
        }

        // 情况三：target 在数组范围中，且数组中存在 target
        if (rightBorder - leftBorder > 1) {
            return new int[]{leftBorder + 1, rightBorder - 1};
        }

        // 情况三：target 在数组范围中，且数组中存在 target
        return new int[]{-1, -1};
    }

    /**
     * 二分查找，寻找target的右边界（不包括target）
     * 如果rightBorder为没有被赋值（即target在数组范围的左边，例如数组[3,3]，target为2），为了处理情况一
     *
     * @param nums
     * @param target
     * @return
     */
    int getRightBorder(int[] nums, int target) {
        // 定义target在左闭右闭的区间里，[left, right]
        int left = 0;
        int right = nums.length - 1;
        // 记录一下rightBorder没有被赋值的情况
        int rightBorder = -2;
        // 当left==right，区间[left, right]依然有效
        while (left <= right) {
            int middle = left + ((right - left) >> 1);
            if (nums[middle] > target) {
                // target 在左区间，所以[left, middle - 1]
                right = middle - 1;
            } else {
                // 当nums[middle] == target的时候，更新left，这样才能得到target的右边界
                left = middle + 1;
                rightBorder = left;
            }
        }
        return rightBorder;
    }

    /**
     * 二分查找，寻找target的左边界leftBorder（不包括target）
     * 如果leftBorder没有被赋值（即target在数组范围的右边，例如数组[3,3],target为4），为了处理情况一
     *
     * @param nums
     * @param target
     * @return
     */
    int getLeftBorder(int[] nums, int target) {
        // 定义target在左闭右闭的区间里，[left, right]
        int left = 0;
        int right = nums.length - 1;
        // 记录一下leftBorder没有被赋值的情况
        int leftBorder = -2;
        while (left <= right) {
            int middle = left + ((right - left) / 2);
            // 寻找左边界，就要在nums[middle] == target的时候更新right
            if (nums[middle] >= target) {
                right = middle - 1;
                leftBorder = right;
            } else {
                left = middle + 1;
            }
        }
        return leftBorder;
    }

    /**
     * 解法2
     * 1、首先，在 nums 数组中二分查找 target；
     * 2、如果二分查找失败，则 binarySearch 返回 -1，表明 nums 中没有 target。此时，searchRange 直接返回 {-1, -1}；
     * 3、如果二分查找成功，则 binarySearch 返回 nums 中值为 target 的一个下标。然后，通过左右滑动指针，来找到符合题意的区间
     *
     * @param nums
     * @param target
     * @return
     */
    public int[] searchRange2(int[] nums, int target) {
        // 二分查找
        int index = binarySearch(nums, target);

        // nums 中不存在 target，直接返回 {-1, -1}
        if (index == -1) {
            // 匿名数组
            return new int[] {-1, -1};
        }
        // nums 中存在 target，则左右滑动指针，来找到符合题意的区间
        int left = index;
        int right = index;
        // 向左滑动，找左边界
        // 防止数组越界。逻辑短路，两个条件顺序不能换
        while (left - 1 >= 0 && nums[left - 1] == nums[index]) {
            left--;
        }
        // 向右滑动，找右边界
        while (right + 1 < nums.length && nums[right + 1] == nums[index]) {
            right++;
        }
        return new int[] {left, right};
    }

    /**
     * 二分查找
     * @param nums
     * @param target
     */
    public int binarySearch(int[] nums, int target) {
        int left = 0;
        // 不变量：左闭右闭区间
        int right = nums.length - 1;

        // 不变量：左闭右闭区间
        while (left <= right) {
            int mid = left + ((right - left) >> 1);
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else {
                // 不变量：左闭右闭区间
                right = mid - 1;
            }
        }
        // 不存在
        return -1;
    }

    public static void main(String[] args) {
        _034在排序数组中查找元素的第一个和最后一个位置 obj = new _034在排序数组中查找元素的第一个和最后一个位置();
        int[] nums = new int[] {5,7,7,8,8,10};
        int target = 8;
        int[] res = {3, 4};
        Asserts.test(Arrays.equals(obj.searchRange(nums, target), res));
        Asserts.test(Arrays.equals(obj.searchRange2(nums, target), res));

    }
}
