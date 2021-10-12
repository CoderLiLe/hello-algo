package 栈_队列;

import java.util.ArrayDeque;
import java.util.Deque;

public class _084_柱状图中最大的矩形 {
    // 参考题解：https://leetcode-cn.com/problems/largest-rectangle-in-histogram/solution/bao-li-jie-fa-zhan-by-liweiwei1419/

    /**
     * 暴力法（超时），T = O(n^2), S = O(1)
     */
    public int largestRectangleArea1(int[] heights) {
        int len = heights.length;
        if (0 == len) return 0;

        int res = 0;
        for (int i = 0; i < len; i++) {
            int curHeight = heights[i];

            // 找左边最后一个大于等于 heights[i] 的下标
            int left = i;
            while (left > 0 && heights[left - 1] >= curHeight) {
                --left;
            }

            // 找右边最后一个大于等于 heights[i] 的下标
            int right = i;
            while (right < len - 1 && heights[right + 1] >= curHeight) {
                ++right;
            }

            int width = right - left + 1;
            res = Math.max(res, width * curHeight);
        }

        return res;
    }

    /**
     * 单调栈：T = O(n), S = O(n)
     */
    public int largestRectangleArea2(int[] heights) {
        int len = heights.length;
        if (0 == len) return 0;

        if (1 == len) return heights[0];

        int[] newHeights = new int[len + 2];
        newHeights[0] = 0;
        System.arraycopy(heights, 0, newHeights, 1, len);
        newHeights[len + 1] = 0;
        len += 2;

        int res = 0;

        Deque<Integer> stack = new ArrayDeque<>(len);
        // 先放入哨兵，在循环里就不用做非空判断
        stack.addLast(0);
        for (int i = 0; i < len; i++) {
            while (newHeights[i] < newHeights[stack.peekLast()]) {
                int curHeight = newHeights[stack.pollLast()];
                int curWidth = i - stack.peekLast() - 1;
                res = Math.max(res, curHeight * curWidth);
            }
            stack.addLast(i);
        }

        return res;
    }

    public static void main(String[] args) {
         int[] heights = {2, 1, 5, 6, 2, 3};
//        int[] heights = {1, 1};

        _084_柱状图中最大的矩形 obj = new _084_柱状图中最大的矩形();
        int res = obj.largestRectangleArea2(heights);
        System.out.println(res);
    }
}
