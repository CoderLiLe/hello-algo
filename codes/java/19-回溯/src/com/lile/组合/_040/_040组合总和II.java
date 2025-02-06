package com.lile.组合._040;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class _040组合总和II {
    List<List<Integer>> res = new ArrayList<>();
    LinkedList<Integer> path = new LinkedList<>();
    int sum = 0;

    public List<List<Integer>> combinationSum2( int[] candidates, int target ) {
        // 为了将重复的数字都放到一起，所以先进行排序
        Arrays.sort( candidates );
        backTracking( candidates, target, 0 );
        return res;
    }

    private void backTracking( int[] candidates, int target, int start ) {
        if ( sum == target ) {
            res.add( new ArrayList<>( path ) );
            return;
        }
        for ( int i = start; i < candidates.length && sum + candidates[i] <= target; i++ ) {
            // 正确剔除重复解的办法
            // 跳过同一树层使用过的元素
            if ( i > start && candidates[i] == candidates[i - 1] ) {
                continue;
            }

            sum += candidates[i];
            path.add(candidates[i]);
            // i+1 代表当前组内元素只选取一次
            backTracking( candidates, target, i + 1 );
            sum -= candidates[i];
            path.removeLast();
        }
    }

    public static void main(String[] args) {
        _040组合总和II obj = new _040组合总和II();
        List<List<Integer>> res = obj.combinationSum2( new int[]{1, 1, 2, 1}, 3);
        System.out.print("[");
        for (List<Integer> list : res) {
            System.out.print("[");
            for (Integer integer : list) {
                System.out.print(integer + " ");
            }
            System.out.print("]");
        }
        System.out.print("]");

    }
}
