package 高频题;

import tools.Asserts;

import java.util.*;

public class _015_三数之和 {
    /**
     * 时间复杂度 O(N^2)：其中固定指针k循环复杂度 O(N)，双指针 i，j 复杂度 O(N)。
     * 空间复杂度 O(1)：指针使用常数大小的额外空间。
     */
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        if (nums == null || nums.length < 3) {
            return res;
        }

        // 排序
        Arrays.sort(nums);

        // i 用来扫描三元组的的第一个元素
        int lastIdx = nums.length - 3;
        int lastR = nums.length - 1;
        for (int i = 0; i <= lastIdx; i++) {
            if(nums[i] > 0) {
                break;
            }
            // 去重
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            int l = i + 1, r = lastR, remain = -nums[i];
            while (l < r) {
                int sumLr = nums[l] + nums[r];
                if (sumLr == remain) { // 找到了符合条件的三元组
                    res.add(Arrays.asList(nums[i], nums[l], nums[r]));
                    // 跳过相同的值（去重）
                    while (l < r && nums[l] == nums[l + 1]) {
                        l++;
                    }
                    while (l < r && nums[r] == nums[r - 1]) {
                        r--;
                    }
                    // 往中间逼近
                    l++;
                    r--;
                } else if (sumLr < remain) {
                    l++;
                } else {
                    r--;
                }
            }
        }

        return res;
    }

    public List<List<Integer>> threeSum2(int[] nums) {
        // 特殊判断
        if (nums == null || nums.length < 3) {
            return Collections.EMPTY_LIST;
        }

        Set<List> set = new HashSet();
        for (int i = 0; i < nums.length - 2; i++) {
            int target = -nums[i];
            Map<Integer, Integer> map = new HashMap();
            for (int j = i + 1; j < nums.length; j++) {
                if (map.containsKey(target - nums[j])) {
                    List<Integer> list = new ArrayList();
                    list.add(nums[i]);
                    list.add(target - nums[j]);
                    list.add(nums[j]);
                    list.sort(Comparator.naturalOrder());
                    set.add(list);
                } else {
                    map.put(nums[j], j);
                }
            }
        }
        return new ArrayList(set);
    }

    public static void main(String[] args) {
        _015_三数之和 obj = new _015_三数之和();
        int[] nums = {-1, 0, 1, 2, -1, -4};
        System.out.println(obj.threeSum(nums));
        System.out.println(obj.threeSum2(nums));
    }
}
