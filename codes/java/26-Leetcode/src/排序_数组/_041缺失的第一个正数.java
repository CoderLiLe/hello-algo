package 排序_数组;

import tools.Asserts;

import java.util.HashSet;
import java.util.Set;

public class _041缺失的第一个正数 {
    /**
     * 方法一：哈希表（空间复杂度不符合要求）
     * 时间复杂度：O(N)，这里 N 表示数组的长度。第 1 次遍历了数组，第 2 次遍历了区间 [1, len] 里的元素。
     * 空间复杂度：O(N)，把 N 个数存在哈希表里面，使用了 N 个空间。
     *
     * @param nums
     * @return
     */
    public int firstMissingPositive(int[] nums) {
        if (nums == null || nums.length == 0) return 1;

        int len = nums.length;

        Set<Integer> set = new HashSet<>();
        for (int num : nums) {
            set.add(num);
        }

        for (int i = 1; i <= len; i++) {
            if (!set.contains(i)) {
                return i;
            }
        }

        return len + 1;
    }

    /**
     * 方法三：将数组视为哈希表
     * 就把 1 这个数放到下标为 0 的位置， 2 这个数放到下标为 1 的位置，按照这种思路整理一遍数组。
     * 然后我们再遍历一次数组，第 1 个遇到的它的值不等于下标的那个数，就是我们要找的缺失的第一个正数
     * 这个思想就相当于我们自己编写哈希函数，这个哈希函数的规则特别简单，那就是数值为 i 的数映射到下标为 i - 1 的位置。
     *
     * 时间复杂度：O(N)，这里 N 是数组的长度。
     * 空间复杂度：O(1)。
     * @param nums
     * @return
     */
    public int firstMissingPositive2(int[] nums) {
        if (nums == null || nums.length == 0) return 1;

        int len = nums.length;
        for (int i = 0; i < len; i++) {
            while (nums[i] > 0 && nums[i] <= len && nums[nums[i] - 1] != nums[i]) {
                // 满足在指定范围内、并且没有放在正确的位置上，才交换
                // 例如：数值 3 应该放在索引 2 的位置上
                swap(nums, nums[i] - 1, i);
            }
        }

        // [1, -1, 3, 4]
        for (int i = 0; i < len; i++) {
            if (nums[i] != i + 1) {
                return i + 1;
            }
        }

        return len + 1;
    }

    private void swap(int[] nums, int index1, int index2) {
        int temp = nums[index1];
        nums[index1] = nums[index2];
        nums[index2] = temp;
    }

    public static void main(String[] args) {
        _041缺失的第一个正数 obj = new _041缺失的第一个正数();

        int[] nums = {1, 2, 0};
        Asserts.test(obj.firstMissingPositive(nums) == 3);
    }
}
