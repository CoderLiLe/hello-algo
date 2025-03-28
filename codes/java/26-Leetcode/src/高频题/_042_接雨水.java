package 高频题;

import tools.Asserts;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Stack;

public class _042_接雨水 {
    /**
     * 动态数组
     * T = O(n), S = O(n)
     */
    public int trap(int[] height) {
        if (height == null || height.length == 0) return 0;

        int lastIdx = height.length - 2;

        // 计算第left位置柱子左边的最大高度
        int[] leftMaxes = new int[height.length];
        for (int left = 1; left <= lastIdx; left++) {
            // 如果第left-1位置柱子的高度比第left-1位置柱子左边的最大高度大，则第left位置柱子左边的最大高度为第left-1位置柱子的高度
            leftMaxes[left] = Math.max(leftMaxes[left - 1], height[left - 1]);
        }

        // 计算第right位置柱子右边的最大高度
        int[] rightMaxes = new int[height.length];
        for (int right = lastIdx; right >= 1; right--) {
            // 如果第right+1位置柱子的高度比第right+1位置柱子右边的最大高度大，则第right位置柱子右边的最大高度为第right+1位置柱子的高度
            rightMaxes[right] = Math.max(rightMaxes[right + 1], height[right + 1]);
        }

        // 遍历每一根柱子，看看每一根柱子能放多少水
        int water = 0;
        for (int i = 1; i <= lastIdx; i++) {
            // 求出左边最大、右边最大中的较小者
            int min = Math.min(leftMaxes[i], rightMaxes[i]);
            // 说明这根柱子不能放水
            if (min <= height[i]) continue;
            // 说明这根柱子能放水
            water += min - height[i];
        }

        return water;
    }

    /**
     * 动态数组，trap 的改进版本，只统计 rightMaxes，leftMax 在从前往后遍历时计算
     * T = O(n), S = O(n)
     */
    public int trap2(int[] height) {
        if (height == null || height.length == 0) return 0;

        int lastIdx = height.length - 2;

        int[] rightMaxes = new int[height.length];
        for (int i = lastIdx; i >= 1; i--) {
            rightMaxes[i] = Math.max(rightMaxes[i + 1], height[i + 1]);
        }

        // 遍历每一根柱子，看看每一根柱子能放多少水
        int water = 0, leftMax = 0;
        for (int i = 1; i <= lastIdx; i++) {
            leftMax = Math.max(leftMax, height[i - 1]);
            // 求出左边最大、右边最大中的较小者
            int min = Math.min(leftMax, rightMaxes[i]);
            // 说明这根柱子不能放水
            if (min <= height[i]) continue;
            // 说明这根柱子能放水
            water += min - height[i];
        }

        return water;
    }

    /**
     * 双指针法
     * <p>
     * T = O(n), S = O(1)
     * <p>
     * 交替求左边、右边最大值（那边小，就求那边的最大值）
     * 只要 rightMax[i] > leftMax[i]，积水高度就是由 leftMax[i]决定
     * 只要 rightMax[i] < leftMax[i]，积水高度就是由 rightMax[i]决定
     */
    public int trap3(int[] height) {
        if (height == null || height.length == 0) return 0;

        int l = 0, r = height.length - 1, lowerMax = 0, water = 0;
        while (l < r) {
            // height[l] height[r]中较小的那个
            // int lower = height[l] <= height[r] ? height[l++] : height[r--];
            int lower = height[height[l] <= height[r] ? l++ : r--];
            // 目前为止遇到的最大的 lower
            lowerMax = Math.max(lowerMax, lower);
            water += lowerMax - lower;
        }
        return water;
    }

    /**
     * 双指针法
     * <p>
     * T = O(n), S = O(1)
     */
    public int trap4(int[] height) {
        int left = 0, right = height.length - 1;
        int res = 0;
        int leftMax = 0, rightMax = 0;
        while (left < right) {
            if (height[left] < height[right]) {
                if (height[left] >= leftMax) {
                    leftMax = height[left];
                } else {
                    res += (leftMax - height[left]);
                }
                ++left;
            } else {
                if (height[right] >= rightMax) {
                    rightMax = height[right];
                } else {
                    res += (rightMax - height[right]);
                }
                --right;
            }
        }
        return res;
    }

    /**
     * 暴力法
     * 对数组中的每个元素，找出下雨后水能达到的最高位置，等于两边最大高度的较小值减去当前高度的值
     * <p>
     * T = O(n^2), S = O(1)
     */
    public int trap5(int[] height) {
        int res = 0;
        int size = height.length;
        for (int i = 1; i < size - 1; i++) {
            int maxLeft = 0, maxRight = 0;
            for (int j = i; j >= 0; j--) {
                maxLeft = Math.max(maxLeft, height[j]);
            }
            for (int j = i; j < size; j++) {
                maxRight = Math.max(maxRight, height[j]);
            }
            res += Math.min(maxLeft, maxRight) - height[i];
        }
        return res;
    }

    public int trap6(int[] height) {
        int size = height.length;

        if (size <= 2) return 0;

        // in the stack, we push the index of array
        // using height[] to access the real height
        Stack<Integer> stack = new Stack<Integer>();
        stack.push(0);

        int sum = 0;
        for (int index = 1; index < size; index++){
            int stackTop = stack.peek();
            if (height[index] < height[stackTop]){
                stack.push(index);
            }else if (height[index] == height[stackTop]){
                // 因为相等的相邻墙，左边一个是不可能存放雨水的，所以pop左边的index, push当前的index
                stack.pop();
                stack.push(index);
            }else{
                //pop up all lower value
                int heightAtIdx = height[index];
                while (!stack.isEmpty() && (heightAtIdx > height[stackTop])){
                    int mid = stack.pop();

                    if (!stack.isEmpty()){
                        int left = stack.peek();

                        int h = Math.min(height[left], height[index]) - height[mid];
                        int w = index - left - 1;
                        int hold = h * w;
                        if (hold > 0) sum += hold;
                        stackTop = stack.peek();
                    }
                }
                stack.push(index);
            }
        }

        return sum;
    }

    /**
     * 【栈的应用】
     * （1）如果当前的条形块 <= 栈顶的条形块，将条形块的索引入栈，意思是当前的条形块被栈中的前一个条形块界定
     * （2）如果发现一个条形块长于栈顶，可以确定栈顶的条形块被当前条形块和栈的前一个条形块界定，可以弹出栈顶
     * 元素并且累加到 res
     * <p>
     * T = O(n), S = O(n)
     */
    public int trap7(int[] height) {
        int res = 0, cur = 0;
        Deque<Integer> stack = new LinkedList<>();
        while (cur < height.length) {
            while (!stack.isEmpty() && height[cur] > height[stack.peek()]) {
                int top = stack.pop();
                if (stack.isEmpty()) {
                    break;
                }
                int distance = cur - stack.peek() - 1;
                int boundedHeight = Math.min(height[cur], height[stack.peek()]) - height[top];
                res += distance * boundedHeight;
            }
            stack.push(cur++);
        }
        return res;
    }

    public static void main(String[] args) {
        int[] height = {0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1};
        _042_接雨水 catchRain = new _042_接雨水();
        Asserts.test(catchRain.trap(height) == 6);
        Asserts.test(catchRain.trap2(height) == 6);
        Asserts.test(catchRain.trap3(height) == 6);
        Asserts.test(catchRain.trap4(height) == 6);
        Asserts.test(catchRain.trap5(height) == 6);
        Asserts.test(catchRain.trap6(height) == 6);
        Asserts.test(catchRain.trap7(height) == 6);
    }
}
