
package 排序_数组.二分查找;

import tools.Asserts;

public class _081搜索旋转排序数组II {

    /**
     * 使用二分法在旋转排序数组中搜索目标值
     * 将数组一分为二，其中一定有一个是有序的，另一个可能是有序，也能是部分有序。
     * 此时有序部分用二分法查找。无序部分再一分为二，其中一个一定有序，另一个可能有序，可能无序。就这样循环.
     *
     * 对于数组中有重复元素的情况，二分查找时可能会有 a[l]=a[mid]=a[r]，此时无法判断区间 [l,mid] 和区间 [mid+1,r] 哪个是有序的。
     * 对于这种情况，我们只能将当前二分区间的左边界加一，右边界减一，然后在新区间上继续二分查找。
     *
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     *
     * T = O(logn), S = O(1)
     *
     * @param nums   旋转排序数组
     * @param target 需要搜索的目标值
     * @return 目标值的索引，如果未找到则返回-1
     */
    public boolean search(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return false;
        }
        int start = 0;
        int end = nums.length - 1;
        int mid;
        while (start <= end) {
            mid = start + ((end - start) >> 1);
            if (nums[mid] == target) {
                return true;
            }
            if (nums[start] == nums[mid] && nums[mid] == nums[end]) {
                start++;
                end--;
            }
            // 前半部分有序,注意此处用小于等于
            else if (nums[start] <= nums[mid]) {
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
        return false;
    }

    public boolean search2(int[] nums, int target) {
        // 二分查找左边界（左闭）
        int left = 0;
        // 二分查找右边界（右开）
        int right = nums.length;
        while (left < right) {
            int mid = left + ((right - left) >> 1);
            if (nums[mid] == target) {
                // 找到目标值，直接返回索引
                return true;
            }
            if (nums[left] == nums[mid]) {
                left++;
            } else if (nums[left] < nums[mid]) {
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
        return false;
    }

    public static void main(String[] args) {
        _081搜索旋转排序数组II obj = new _081搜索旋转排序数组II();
        int[] nums = new int[]{2,5,6,0,0,1,2};
        int target = 0;
        Asserts.test(obj.search(nums, target));
        Asserts.test(obj.search2(nums, target));

        nums = new int[]{1};
        target = 0;
        Asserts.test(!obj.search(nums, target));
        Asserts.test(!obj.search2(nums, target));
    }

}
