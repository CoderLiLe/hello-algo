package DFS;

import java.util.ArrayList;
import java.util.List;

public class _047_全排列II {
    public List<List<Integer>> permuteUnique(int[] nums) {
        if (nums == null) return null;
        List<List<Integer>> list = new ArrayList<>();
        if (nums.length == 0) return list;
        dfs(0, nums, list);
        return list;
    }

    private void dfs(int idx, int[] nums, List<List<Integer>> list) {
        // 不能再往下搜索
        if (idx == nums.length) {
            List<Integer> resultList = new ArrayList<>();
            for (int value : nums) {
                resultList.add(value);
            }
            list.add(resultList);
            return;
        }

        // 枚举这一层所有可以做出的选择
        for (int i = idx; i < nums.length; i++) {
            // 要保证一个数字在 idx 位置只出现一次
            if (isRepeat(nums, idx, i)) continue;
            swap(nums, idx, i);
            dfs(idx + 1, nums, list);
            swap(nums, idx,  i);
        }
    }

    private boolean isRepeat(int[] nums, int idx, int i) {
        for (int j = idx; j < i; j++) {
            if (nums[j] == nums[i]) return true;
        }
        return false;
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    public static void main(String[] args) {
        _047_全排列II obj = new _047_全排列II();
        int[] nums = {1, 2, 3};
        List<List<Integer>> list = obj.permuteUnique(nums);
        System.out.println(list);

        int[] nums2 = {1, 1, 3};
        list = obj.permuteUnique(nums2);
        System.out.println(list);
    }
}
