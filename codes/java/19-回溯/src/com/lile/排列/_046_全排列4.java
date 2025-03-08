package com.lile.排列;

import java.util.ArrayList;
import java.util.List;

public class _046_全排列4 {
    private List<List<Integer>> res = new ArrayList<>();

    public List<List<Integer>> permute(int[] nums) {
        if (nums == null) {
            return null;
        }
        backtrack(nums, new ArrayList<>());
        return res;
    }

    private void backtrack(int[] nums, List<Integer> path) {
        if (path.size() == nums.length) {
            // 这里一定要注意⚠️
            res.add(new ArrayList<>(path));
            return;
        }

        for (int num : nums) {
            if (path.contains(num)) {
                continue;
            }
            path.add(num);
            backtrack(nums, path);
            path.removeLast();
        }
    }
}
