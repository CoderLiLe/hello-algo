package DFS;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class _039_组合总和 {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        if (candidates == null || candidates.length == 0) return null;
        List<List<Integer>> list = new ArrayList<>();
        List<Integer> nums = new ArrayList<>();
        // 配合 begin 用于去重，保证数字是由小到大的顺序选择的
        Arrays.sort(candidates);
        dfs(0, target, candidates, nums, list);
        return list;
    }

    private void dfs(int begin, int remain, int[] candidates, List<Integer> nums, List<List<Integer>> list) {
        if (remain < 0) return;
        if (remain == 0) {
            list.add(new ArrayList<>(nums));
            return;
        }

        for (int i = begin; i  < candidates.length; i++) {
            // 如果 candidates[i] 超过 remain，那么后面的数值必然超过 remain
            if (remain < candidates[i]) return;
            nums.add(candidates[i]);
            dfs(i, remain - candidates[i], candidates, nums, list);
            nums.remove(nums.size() - 1);
        }
    }
}
