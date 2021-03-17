package TwoPoints;

import tools.Asserts;

import java.util.Arrays;

public class _016_最接近的三数之和 {
    public static void main(String[] args) {
        int[] nums = {-1,2,1,-4};
        Asserts.test(threeSumClosest(nums, 1) == 2);

        int[] nums1 = {1,1,2,3,4,5,6,10};
        System.out.println(threeSumClosest(nums, 12));
//        Asserts.test(threeSumClosest(nums, 12) == 12);
    }

    // T = O(nlogn) + O(n^2) = O(n^2), S = O(1)
    private static int threeSumClosest(int[] nums, int target) {
        // O(nlogn)
        Arrays.sort(nums);
        int res = nums[0] + nums[1] + nums[2];
        // O(n^2)
        for (int i = 0; i < nums.length - 2; i++) { // n
            int start = i + 1, end = nums.length - 1;
            while (start < end) { // n
                int min = nums[i] + nums[start] + nums[start + 1];
                if (target < min) {
                    if (Math.abs(res - target) > Math.abs(min - target)) {
                        res = min;
                    }
                    break;
                }

                int max = nums[i] + nums[end] + nums[end - 1];
                if (target > max) {
                    if (Math.abs(res - target) > Math.abs(max - target)) {
                        res = max;
                    }
                    break;
                }

                int sum = nums[start] + nums[end] + nums[i];

                if (sum == target) {
                    return sum;
                }

                if (Math.abs(target - sum) < Math.abs(target - res)) {
                    res = sum;
                }

                if (sum > target) {
                    end--;
                    // 优化nums[end] 重复
                    while (start < end && nums[end] == nums[end + 1]) {
                        end--;
                    }
                } else {
                    start++;
                    // 优化nums[start]重复
                    while (start < end && nums[start] == nums[start - 1]) {
                        start--;
                    }
                }
            }

            // 优化nums[i]重复
            while (i < nums.length - 2 && nums[i] == nums[i + 1]) {
                i++;
            }
        }

        return res;
    }
}
