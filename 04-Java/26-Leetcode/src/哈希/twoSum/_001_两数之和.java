package 哈希.twoSum;

import print.ArrayPrint;
import tools.Asserts;

import java.util.Arrays;
import java.util.HashMap;

public class _001_两数之和 {
    public int[] twoSum1(int[] nums, int target) {
        int[] res = new int[2];
        if (null == nums || nums.length < 2) {
            return res;
        }

        for (int i = 0; i < nums.length - 1; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] + nums[j] == target) {
                    res[0] = i;
                    res[1] = j;
                    return res;
                }
            }
        }
        return res;
    }

    public int[] twoSum2(int[] nums, int target) {
        int len = nums.length;
        HashMap<Integer, Integer> index = new HashMap<>();
        // 构造一个哈希表：元素映射到对应的索引
        for (int i = 0; i < len; i++) {
            index.put(nums[i], i);
        }

        for (int i = 0; i < len; i++) {
            int other = target - nums[i];
            // 如果 other 存在且不是 nums[i] 本身
            if (index.containsKey(other) && index.get(other) != i) {
                return new int[] {i, index.get(other)};
            }
        }

        return new int[] {-1, -1};
    }

    public int[] twoSum3(int[] nums, int target) {
        int[] res = new int[2];
        if (null == nums || nums.length < 2) {
            return res;
        }

        int[] temp = new int[nums.length];
        System.arraycopy(nums, 0, temp, 0, nums.length);
        Arrays.sort(nums);
        Integer left = 0;
        Integer right = nums.length - 1;
        while (left < right) {
            if (nums[left] + nums[right] == target) {
                int firstIndex = -1;
                for (int i = 0; i < temp.length; i++) {
                    if (-1 == firstIndex && temp[i] == nums[left]) {
                        res[0] = i;
                        firstIndex = i;
                    } else if (temp[i] == nums[right]) {
                        res[1] = i;
                    }
                }
                return res;
            }

            if (nums[left] + nums[right] < target) {
                left++;
            } else {
                right--;
            }
        }

        return res;
    }

    public static void main(String[] args) {
        _001_两数之和 obj = new _001_两数之和();

        int[] nums = {2, 7, 11, 15};
        int target = 9;
        String res = "[0,1]";
        Asserts.test(ArrayPrint.toString(obj.twoSum1(nums, target)).equals(res));
        Asserts.test(ArrayPrint.toString(obj.twoSum2(nums, target)).equals(res));
        Asserts.test(ArrayPrint.toString(obj.twoSum3(nums, target)).equals(res));

        int[] nums2 = {3, 3};
        int target2 = 6;
        String res2 = "[0,1]";
        Asserts.test(ArrayPrint.toString(obj.twoSum1(nums2, target2)).equals(res2));
        Asserts.test(ArrayPrint.toString(obj.twoSum2(nums2, target2)).equals(res2));
        Asserts.test(ArrayPrint.toString(obj.twoSum3(nums2, target2)).equals(res2));

    }
}
