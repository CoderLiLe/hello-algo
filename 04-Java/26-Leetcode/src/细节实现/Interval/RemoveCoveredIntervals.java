package 细节实现.Interval;

/**
 * 1288. Remove Covered Intervals
 *
 * Given a list of intervals, remove all intervals that are covered by another interval in the list.
 *
 * Interval [a,b) is covered by interval [c,d) if and only if c <= a and b <= d.
 *
 * After doing so, return the number of remaining intervals.
 *
 *
 * Example 1:
 * Input: intervals = [[1,4],[3,6],[2,8]]
 * Output: 2
 * Explanation: Interval [3,6] is covered by [2,8], therefore it is removed.
 *
 * Example 2:
 * Input: intervals = [[1,4],[2,3]]
 * Output: 1
 *
 * Example 3:
 * Input: intervals = [[0,10],[5,12]]
 * Output: 2
 *
 * Example 4:
 * Input: intervals = [[3,10],[4,10],[5,11]]
 * Output: 2
 *
 * Example 5:
 * Input: intervals = [[1,2],[1,4],[3,4]]
 * Output: 1
 *  
 *
 * Constraints:
 *
 * 1 <= intervals.length <= 1000
 * intervals[i].length == 2
 * 0 <= intervals[i][0] < intervals[i][1] <= 10^5
 * All the intervals are unique.
 */

import tools.Asserts;

import java.util.Arrays;
import java.util.Comparator;

/**
 * 思路分析：本题和【Meeting Rooms】的做法一样～首先按照区间起始端点进行排序，然后遍历区间，将第一题的判断区间是否重叠改为判断区间是否覆盖即可
 *
 * 将所有区间按照左端点递增排序，那么对于排完序的列表中第 i 个区间 [Li, Ri):
 * （1）出现在其之前的任一区间 [Lj, Rj) 一定满足 Lj < Li。因此只要存在一个区间 【Lj, Rj）满足 j < i 且 Rj >= Ri，那么区间 [Li, Ri)一定会
 * 被覆盖。换句话说，只要出现在 [Li, Ri)之前的区间的右端点的最大值 Rmax = max(R1, R2, ..., Ri-1)满足 Rmax >= Ri，那么区间 [Li, Ri)一定
 * 会被覆盖。
 * （2）出现在其之后的任一区间 [Lj, Rj)一定满足 Lj > Li，因此区间 [Li, Ri)不可能被任何出现在其之后的区间覆盖。
 *
 * 对于区间左端点相同的情况，按照区间右端点为第二个关键字递减排序。
 */

public class RemoveCoveredIntervals {

    public static void main(String[] args) {
        int[][] intervals = {{1, 4}, {3, 6}, {2, 8}};
        Asserts.test(removeCoveredIntervals(intervals) == 2);

        int[][] intervals2 = {{1, 4}, {2, 3}};
        Asserts.test(removeCoveredIntervals(intervals2) == 1);

        int[][] intervals3 = {{0, 10}, {5, 12}};
        Asserts.test(removeCoveredIntervals(intervals3) == 2);

        int[][] intervals4 = {{3, 10}, {4, 10}, {5, 11}};
        Asserts.test(removeCoveredIntervals(intervals) == 2);

        int[][] intervals5 = {{1, 2}, {1, 4}, {3, 4}};
        Asserts.test(removeCoveredIntervals(intervals5) == 1);
    }

    public static int removeCoveredIntervals(int[][] intervals) {
        // 将区间按照起始端点进行排序
        Arrays.sort(intervals, (v1, v2) -> {
            if (v1[0] == v2[0]) {
                return v2[1] - v1[1];
            }
            return v1[0] - v2[0];
        });

        int len = intervals.length;
        int max = intervals[0][1];
        // 遍历区间，如果一个区间包含于前一个区间，则覆盖（删除）
        for (int i = 1; i < intervals.length; i++) {
            if (intervals[i][1] <= max) {
                len--;
            } else {
                max = intervals[i][1];
            }
        }
        return len;
    }
}
