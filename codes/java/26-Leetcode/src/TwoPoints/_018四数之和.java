package TwoPoints;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class _018四数之和 {
    public List<List<Integer>> fourSum(int[] nums, int target) {
        Arrays.sort(nums);  // 排序数组
        List<List<Integer>> result = new ArrayList<>();  // 结果集
        for (int k = 0; k < nums.length; k++) {
            // 剪枝处理
            if (nums[k] > target && nums[k] >= 0) {
                break;    // 此处的break可以等价于return result;
            }
            // 对nums[k]去重
            if (k > 0 && nums[k] == nums[k - 1]) {
                continue;
            }
            for (int i = k + 1; i < nums.length; i++) {
                // 第二级剪枝
                if (nums[k] + nums[i] > target && nums[k] + nums[i] >= 0) {
                    break;    // 注意是break到上一级for循环，如果直接return result;会有遗漏
                }
                // 对nums[i]去重
                if (i > k + 1 && nums[i] == nums[i - 1]) {
                    continue;
                }
                int left = i + 1;
                int right = nums.length - 1;
                while (right > left) {
                    long sum = (long) nums[k] + nums[i] + nums[left] + nums[right];
                    if (sum > target) {
                        right--;
                    } else if (sum < target) {
                        left++;
                    } else {
                        result.add(Arrays.asList(nums[k], nums[i], nums[left], nums[right]));
                        // 对nums[left]和nums[right]去重
                        while (right > left && nums[right] == nums[right - 1]) right--;
                        while (right > left && nums[left] == nums[left + 1]) left++;
                        right--;
                        left++;
                    }
                }
            }
        }
        return result;
    }

    public List<List<Integer>> fourSum2(int[] nums, int target) {
        Arrays.sort(nums);
        // n 为 4，从 nums[0] 开始计算和为 target 的四元组
        return nSumTarget(nums, 4, 0, target);
    }

    // 注意：调用这个函数之前一定要先给 nums 排序
    // n 填写想求的是几数之和，start 从哪个索引开始计算（一般填 0），target 填想凑出的目标和
    private List<List<Integer>> nSumTarget(int[] nums, int n, int start, long target) {
        int sz = nums.length;
        List<List<Integer>> res = new ArrayList<>();
        // 至少是 2Sum，且数组大小不应该小于 n
        if (n < 2 || sz < n) return res;
        // 2Sum 是 base case
        if (n == 2) {
            // 双指针那一套操作
            int lo = start, hi = sz - 1;
            while (lo < hi) {
                int sum = nums[lo] + nums[hi];
                int left = nums[lo], right = nums[hi];
                if (sum < target) {
                    while (lo < hi && nums[lo] == left) {
                        lo++;
                    }
                } else if (sum > target) {
                    while (lo < hi && nums[hi] == right) {
                        hi--;
                    }
                } else {
                    res.add(new ArrayList<>(Arrays.asList(left, right)));
                    while (lo < hi && nums[lo] == left) {
                        lo++;
                    }
                    while (lo < hi && nums[hi] == right) {
                        hi--;
                    }
                }
            }
        } else {
            // n > 2 时，递归计算 (n-1)Sum 的结果
            for (int i = start; i < sz; i++) {
                List<List<Integer>> sub = nSumTarget(nums, n - 1, i + 1, target - nums[i]);
                for (List<Integer> arr : sub) {
                    // (n-1)Sum 加上 nums[i] 就是 nSum
                    arr.add(nums[i]);
                    res.add(arr);
                }
                while (i < sz - 1 && nums[i] == nums[i + 1]) {
                    i++;
                }
            }
        }
        return res;
    }


    public static void main(String[] args) {
        _018四数之和 obj = new _018四数之和();
        obj.fourSum(new int[]{1, 0, -1, 0, -2, 2}, 0);
        obj.fourSum2(new int[]{1, 0, -1, 0, -2, 2}, 0);
    }
}
