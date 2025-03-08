package com.lile.排列;

import java.util.ArrayList;
import java.util.List;

public class _046_全排列3 {
    public List<List<Integer>> permute(int[] nums) {
        if (nums == null) return null;
        List<List<Integer>> list = new ArrayList<>();
        if (nums.length == 0) return list;
        backtrack(0, nums, list);
        return list;
    }

    private void backtrack(int idx, int[] nums, List<List<Integer>> list) {
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
            swap(nums, idx, i);
            backtrack(idx + 1, nums, list);
            swap(nums, idx, i);
        }
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
