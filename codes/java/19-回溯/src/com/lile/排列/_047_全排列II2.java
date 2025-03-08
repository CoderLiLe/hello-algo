package com.lile.排列;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class _047_全排列II2 {
    private List<List<Integer>> res = new ArrayList<>();

    public List<List<Integer>> permuteUnique(int[] nums) {
        if (nums == null) {
            return null;
        }

        Arrays.sort(nums);
        boolean[] used = new boolean[nums.length];
        Arrays.fill(used, false);
        backtrack(nums, new ArrayList<>(), used);
        return res;
    }

    private void backtrack(int[] nums, List<Integer> path, boolean[] used) {
        if (path.size() == nums.length) {
            // 这里一定要注意⚠️
            res.add(new ArrayList<>(path));
            return;
        }

        for (int i = 0; i < nums.length; i++) {
            // 如果同⼀树层nums[i - 1]使⽤过则直接跳过
            if (i > 0 && nums[i] == nums[i - 1] && used[i - 1] == false) {
                continue;
            }
            if (used[i] == true) {
                continue;
            }

            // 标记同⼀树⽀nums[i]使⽤过，防止同一树枝重复使用
            used[i] = true;
            path.add(nums[i]);
            backtrack(nums, path, used);
            // 回溯，说明同⼀树层nums[i]使⽤过，防止下一树层重复
            path.remove(path.size() - 1);
            // 回溯
            used[i] = false;
        }
    }

    public static void main(String[] args) {
        _047_全排列II2 obj = new _047_全排列II2();
        int[] nums = {1, 1, 2};
        List<List<Integer>> list = obj.permuteUnique(nums);
    }
}
