package 栈_队列.单调栈;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;

public class _503_下一个更大元素II {

    /**
     * T = O(n)，其中 n 是序列的长度。我们需要遍历该数组中每个元素最多 2 次，每个元素出栈与入栈的总次数也不超过 4 次
     * S = O(n)，其中 n 是序列的长度。空间复杂度主要取决于栈的大小，栈的大小至多为 2n - 1
     */
    public int[] nextGreaterElements(int[] nums) {
        int len = nums.length;
        int[] res = new int[len];

        Arrays.fill(res, -1);

        Deque<Integer> stack = new LinkedList<Integer>();
        for (int i = 0; i < len * 2 - 1; i++) {
            while (!stack.isEmpty() && nums[stack.peek()] < nums[i % len]) {
                res[stack.pop()] = nums[i % len];
            }
            stack.push(i % len);
        }

        return res;
    }
}
