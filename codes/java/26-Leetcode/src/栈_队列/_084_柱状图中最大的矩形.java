package 栈_队列;

import tools.Asserts;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Stack;

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
     * 双指针法
     */
    public int largestRectangleArea2(int[] heights) {
        int len = heights.length;
        int[] minLeftIndex = new int[len];
        int[] minRightIndex = new int[len];

        // 记录每个柱子 左边第一个小于该柱子的下标
        minLeftIndex[0] = -1;
        for (int i = 1; i < len; i++) {
            int left = i - 1;
            // 这里不是用if，而是不断向左寻找的过程
            while (left >= 0 && heights[left] >= heights[i]) {
                left = minLeftIndex[left];
            }
            minLeftIndex[i] = left;
        }

        // 记录每个柱子 右边第一个小于该柱子的下标
        minRightIndex[len - 1] = len;
        for (int i = len - 2; i >= 0; i--) {
            int right = i + 1;
            // 这里不是用if，而是不断向右寻找的过程
            while (right < len && heights[right] >= heights[i]) {
                right = minRightIndex[right];
            }
            minRightIndex[i] = right;
        }

        // 求和
        int result = 0;
        for (int i = 0; i < len; i++) {
            int sum = heights[i] * (minRightIndex[i] - minLeftIndex[i] - 1);
            result = Math.max(result, sum);
        }
        return result;
    }

    /**
     * 单调栈：T = O(n), S = O(n)
     */
    public int largestRectangleArea3(int[] heights) {
        // 数组扩容，在头部和尾部各加入一个元素
        int[] newHeights = new int[heights.length + 2];
        newHeights[0] = 0;
        newHeights[newHeights.length - 1] = 0;
        for (int i = 0; i < heights.length; i++) {
            newHeights[i + 1] = heights[i];
        }
        heights = newHeights;

        Stack<Integer> st = new Stack<>();
        st.push(0);

        int res = 0;
        // 从第二个元素开始，如果当前元素小于栈顶元素，则弹出栈顶元素，直到当前元素大于栈顶元素，然后将当前元素入栈
        for (int i = 1; i < heights.length; i++) {
            if (heights[i] > heights[st.peek()]) {
                st.push(i);
            } else if (heights[i] == heights[st.peek()]) {
                st.pop();
                st.push(i);
            } else {
                while (heights[i] < heights[st.peek()]) {
                    int mid = st.peek();
                    st.pop();
                    int left = st.peek();
                    int right = i;
                    int w = right - left - 1;
                    int h = heights[mid];
                    res = Math.max(res, w * h);
                }
            }
            st.push(i);
        }
        return res;
    }

    /**
     * 单调栈：T = O(n), S = O(n)
     */
    public int largestRectangleArea4(int[] heights) {
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

        _084_柱状图中最大的矩形 obj = new _084_柱状图中最大的矩形();
        Asserts.test(obj.largestRectangleArea1(heights) == 10);
        Asserts.test(obj.largestRectangleArea2(heights) == 10);
        Asserts.test(obj.largestRectangleArea3(heights) == 10);
        Asserts.test(obj.largestRectangleArea4(heights) == 10);
    }
}
