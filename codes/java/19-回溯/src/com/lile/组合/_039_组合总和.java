package com.lile.组合;

import com.lile.tools.Asserts;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 39. 组合总和
 * https://leetcode.cn/problems/combination-sum/description/
 * 难度：中等
 * 给你一个 无重复元素的整数数组 candidates 和一个目标整数 target ，找出 candidates 中可以使数字和为目标数 target 的
 * 所有不同组合，并以列表形式返回。你可以按任意顺序返回这些组合。
 * candidates 中的同一个数字可以无限制重复被选取 。如果至少一个数字的被选数量不同，则两种组合是不同的。
 * 对于给定的输入，保证和为 target 的不同组合数少于 150 个。
 *
 *
 * 示例 1：
 * 输入：candidates = [2,3,6,7], target = 7
 * 输出：[[2,2,3],[7]]
 * 解释：
 * 2 和 3 可以形成一组候选，2 + 2 + 3 = 7 。注意 2 可以使用多次。
 * 7 也是一个候选， 7 = 7 。
 * 仅有这两种组合。
 *
 * 示例 2：
 * 输入：candidates = [2,3,5], target = 8,
 * 所求解集为： [[2,2,2,2], [2,3,3], [3,5] ]
 *
 * 示例 3：
 * 输入: candidates = [2], target = 1
 * 输出: []
 *
 * 提示：
 * 1 <= candidates.length <= 30
 * 2 <= candidates[i] <= 40
 * candidates 的所有元素 互不相同
 * 1 <= target <= 40
 */
public class _039_组合总和 {

    /**
     * 方法一：选或不选
     *
     * 用 backtrack(begin,remain) 来回溯，设当前枚举到 candidates[i]，剩余要选的元素之和为 remain，按照选或不选分类讨论：
     *
     * 不选 candidates[begin]：递归到 backtrack(i+1,remain)。
     * 选 candidates[begin]：递归到 backtrack(i,remain−candidates[i])。注意 i 不变，表示在下次递归中可以继续选 candidates[i]。
     * 注：这个思路类似 完全背包。
     * 如果递归中发现 remain = 0 则说明找到了一个合法组合，复制一份 nums 加入答案。
     * 递归边界：如果 begin = n 或者 remain<0 则返回。
     * 递归入口：backtrack(0,target)。
     *
     * @param candidates
     * @param target
     * @return
     */
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        if (candidates == null || candidates.length == 0) {
            return null;
        }
        List<List<Integer>> list = new ArrayList<>();
        List<Integer> nums = new ArrayList<>();
        // 配合 begin 用于去重，保证数字是由小到大的顺序选择的
        Arrays.sort(candidates);
        backtrack_systemstack(0, target, candidates, nums, list);
        return list;
    }

    private void backtrack_systemstack(int begin, int remain, int[] candidates, List<Integer> nums, List<List<Integer>> list) {
        // 子集和等于 target 时，记录解
        if (remain == 0) {
            list.add(new ArrayList<>(nums));
            return;
        }

        if (begin == candidates.length || remain < 0) {
            return;
        }

        // 剪枝一： 如果 candidates[i] 超过 remain，那么后面的数值必然超过 remain
        if (remain < candidates[begin]) {
            return;
        }

        // 尝试选 candidates[begin]：做出选择，更新 begin, remain
        nums.add(candidates[begin]);
        // 进行下一轮选择
        backtrack_systemstack(begin, remain - candidates[begin], candidates, nums, list);
        // 回退：撤销选择，恢复到之前的状态
        nums.remove(nums.size() - 1);

        // 不选 candidates[begin]
        backtrack_systemstack(begin + 1, remain, candidates, nums, list);
    }

    /**
     * 方法二：枚举选哪个
     *
     * 时间复杂度: O(n * 2^n)，注意这只是复杂度的上界，因为剪枝的存在，真实的时间复杂度远小于此
     * 空间复杂度: O(target)
     *
     * @param candidates
     * @param target
     * @return
     */
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        if (candidates == null || candidates.length == 0) {
            return null;
        }
        List<List<Integer>> list = new ArrayList<>();
        List<Integer> nums = new ArrayList<>();
        // 配合 begin 用于去重，保证数字是由小到大的顺序选择的
        Arrays.sort(candidates);
        backtrack(0, target, candidates, nums, list);
        return list;
    }

    private void backtrack(int begin, int remain, int[] candidates, List<Integer> nums, List<List<Integer>> list) {
        if (remain < 0) {
            return;
        }
        // 子集和等于 target 时，记录解
        if (remain == 0) {
            list.add(new ArrayList<>(nums));
            return;
        }

        // 遍历所有选择
        // 剪枝二：从 start 开始遍历，避免生成重复子集
        for (int i = begin; i  < candidates.length; i++) {
            // 剪枝一： 如果 candidates[i] 超过 remain，那么后面的数值必然超过 remain
            if (remain < candidates[i]) {
                break;
            }

            // 尝试：做出选择，更新 begin, remain
            nums.add(candidates[i]);
            // 进行下一轮选择
            backtrack(i, remain - candidates[i], candidates, nums, list);
            // 回退：撤销选择，恢复到之前的状态
            nums.remove(nums.size() - 1);
        }
    }

    public static void main(String[] args) {
        _039_组合总和 obj = new _039_组合总和();
        int[] candidates = {2, 3, 6, 7};
        List<List<Integer>> res = new ArrayList<>();
        res.add(new ArrayList<>(Arrays.asList(2, 2, 3)));
        res.add(new ArrayList<>(Arrays.asList(7)));
        Asserts.test(obj.combinationSum(candidates, 7).equals(res));
        Asserts.test(obj.combinationSum2(candidates, 7).equals(res));
    }
}
