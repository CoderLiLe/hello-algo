package com.lile;


import com.lile.tools.Asserts;

import java.util.Arrays;
import java.util.LinkedList;

/**
 * 56. Merge Intervals
 * Given an array of intervals where intervals[i] = [starti, endi], merge all overlapping intervals, and return an array of the non-overlapping intervals that cover all the intervals in the input.
 * <p>
 * Example 1:
 * <p>
 * Input: intervals = [[1,3],[2,6],[8,10],[15,18]]
 * Output: [[1,6],[8,10],[15,18]]
 * Explanation: Since intervals [1,3] and [2,6] overlaps, merge them into [1,6].
 * Example 2:
 * <p>
 * Input: intervals = [[1,4],[4,5]]
 * Output: [[1,5]]
 * Explanation: Intervals [1,4] and [4,5] are considered overlapping.
 * <p>
 * Constraints:
 * <p>
 * 1 <= intervals.length <= 104
 * intervals[i].length == 2
 * 0 <= starti <= endi <= 104
 */

/**
 * 和【Meeting Rooms】一样，首先对区间按照起始端点进行升序排序，然后逐个判断当前区间是否与前一个区间重叠，如果不重叠的话将当前区间直接加入结果集，反之如果重叠的话，就将当前区间与前一个区间进行合并。
 */
public class _056合并区间 {

    public static void main(String[] args) {
        _056合并区间 obj = new _056合并区间();

        int[][] intervals1 = {{1, 3}, {2, 6}, {8, 10}, {15, 18}};
        int[][] res = {{1, 6}, {8, 10}, {15, 18}};
        Asserts.test(Arrays.deepEquals(obj.merge(intervals1), res));
        Asserts.test(Arrays.deepEquals(obj.merge2(intervals1), res));
    }

    /**
     * 一个区间可以表示为 [start, end]，先按区间的 start 排序
     * 对于几个相交区间合并后的结果区间 x，x.start 一定是这些相交区间中 start 最小的，x.end 一定是这些相交区间中 end 最大的
     *
     * 时间复杂度: O(nlogn)
     * 空间复杂度: O(logn)，排序需要的空间开销
     */
    public int[][] merge(int[][] intervals) {
        LinkedList<int[]> res = new LinkedList<>();
        // 按区间的 start 升序排列
        Arrays.sort(intervals, (a, b) -> {
            return a[0] - b[0];
        });

        res.add(intervals[0]);
        for (int i = 1; i < intervals.length; i++) {
            int[] curr = intervals[i];
            // res 中最后一个元素的引用
            int[] last = res.getLast();
            // 发现重叠区间
            if (curr[0] <= last[1]) {
                // 合并区间，只更新右边界就好，因为last的左边界一定是最小值，因为按照左边界排序的
                last[1] = Math.max(last[1], curr[1]);
            } else {
                // 区间不重叠，处理下一个待合并区间
                res.add(curr);
            }
        }
        return res.toArray(new int[0][0]);
    }

    public int[][] merge2(int[][] intervals) {
        // 按照区间起始位置排序
        Arrays.sort(intervals, (v1, v2) -> v1[0] - v2[0]);
        // 遍历区间
        int[][] res = new int[intervals.length][2];
        int idx = -1;
        for (int[] interval : intervals) {
            // 如果结果数组是空的，或者当前区间的起始位置 > 结果数组中最后区间的终止位置，
            // 则不合并，直接将当前区间加入结果数组。
            if (idx == -1 || interval[0] > res[idx][1]) {
                res[++idx] = interval;
            } else {
                // 反之说明重叠，则将当前区间合并至结果数组的最后区间
                res[idx][1] = Math.max(res[idx][1], interval[1]);
            }
        }
        return Arrays.copyOf(res, idx + 1);
    }
}
