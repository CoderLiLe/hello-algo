package com.lile.排列;

import java.util.ArrayList;
import java.util.List;

public class _046_全排列 {
    private List<List<Integer>> list;
    private int[] nums;
    /**
     * 用来保存每一层选择的数字
     */
    private int[] result;
    /**
     * 用来标记nums中的 数字是否被使用过了
     */
    private boolean[] used;

    public List<List<Integer>> permute(int[] nums) {
        if (nums == null) return null;

        list = new ArrayList<>();
        if (nums.length == 0) return list;

        this.nums = nums;
        result = new int[nums.length];
        used = new boolean[nums.length];

        backtrack(0);
        return list;
    }

    private void backtrack(int idx) {
        // 不能再往下搜索
        if (idx == nums.length) {
            List<Integer> resultList = new ArrayList<>();
            for (int value : result) {
                resultList.add(value);
            }
            list.add(resultList);
            return;
        }

        // 枚举这一层所有可以做出的选择
        for (int i = 0; i < nums.length; i++) {
            if (used[i]) continue;
            result[idx] = nums[i];
            used[i] = true;
            backtrack(idx + 1);
            used[i] = false;
        }
    }
}
