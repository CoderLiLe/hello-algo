package TwoPoints;

import tools.Asserts;

import java.util.ArrayList;
import java.util.List;

/**
 * 228. Summary Ranges
 * You are given a sorted unique integer array nums.
 *
 * Return the smallest sorted list of ranges that cover all the numbers in the array exactly. That is, each element of nums is covered by exactly one of the ranges,
 * and there is no integer x such that x is in one of the ranges but not in nums.
 *
 * Each range [a,b] in the list should be output as:
 *
 * "a->b" if a != b
 * "a" if a == b
 *  
 *
 * Example 1:
 * Input: nums = [0,1,2,4,5,7]
 * Output: ["0->2","4->5","7"]
 * Explanation: The ranges are:
 * [0,2] --> "0->2"
 * [4,5] --> "4->5"
 * [7,7] --> "7"
 *
 * Example 2:
 * Input: nums = [0,2,3,4,6,8,9]
 * Output: ["0","2->4","6","8->9"]
 * Explanation: The ranges are:
 * [0,0] --> "0"
 * [2,4] --> "2->4"
 * [6,6] --> "6"
 * [8,9] --> "8->9"
 *
 * Example 3:
 * Input: nums = []
 * Output: []
 *
 * Example 4:
 * Input: nums = [-1]
 * Output: ["-1"]
 *
 * Example 5:
 * Input: nums = [0]
 * Output: ["0"]
 *  
 *
 * Constraints:
 *
 * 0 <= nums.length <= 20
 * -2^31 <= nums[i] <= 2^31 - 1
 * All the values of nums are unique.
 * nums is sorted in ascending order.
 */

/**
 * 从数组的位置 0 出发，向右遍历。每次遇到相邻元素之间的差值大于 1 时，我们就找到了一个区间。遍历完数组之后，就能得到一系列的区间的列表。
 *
 * 在遍历过程中，维护下标 low 和 high 分别记录区间的起点和终点，对于任何区间都有 low ≤ high。当得到一个区间时，根据 low 和 high 的值生成区间的字符串表示。
 *
 * 当 low < high 时，区间的字符串表示为 "low→high"；
 * 当 low=high 时，区间的字符串表示为 "low"。
 *
 * T = O(n), S = O(1)
 */
public class SummaryRanges {

    public static void main(String[] args) {
        int[] nums1 = {0,1,2,4,5,7};
        System.out.println(summaryRanges(nums1));
        int[] nums2 = {0,2,3,4,6,8,9};
        System.out.println(summaryRanges(nums2));
        int[] nums3 = {};
        System.out.println(summaryRanges(nums3));
        int[] nums4 = {-1};
        System.out.println(summaryRanges(nums4));
        int[] nums5 = {0};
        System.out.println(summaryRanges(nums5));
    }

    private static List<String> summaryRanges(int[] nums) {
        List<String> res = new ArrayList<>();
        int i = 0;
        int n = nums.length;
        while (i < n) {
            int low = i;
            i++;
            while (i < n && nums[i] == nums[i - 1] + 1) {
                i++;
            }
            int high = i - 1;
            StringBuffer sb = new StringBuffer(Integer.toString(nums[low]));
            if (low < high) {
                sb.append("->");
                sb.append(Integer.toString(nums[high]));
            }
            res.add(sb.toString());
        }
        return res;
    }
}
