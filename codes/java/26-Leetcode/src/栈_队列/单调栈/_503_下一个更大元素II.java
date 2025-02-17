package 栈_队列.单调栈;

import tools.Asserts;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;

public class _503_下一个更大元素II {

    public int[] nextGreaterElements(int[] nums) {
        int len = nums.length;
        // 拼接一个新的nums
        int[] newNums = new int[len * 2];
        System.arraycopy(nums, 0, newNums, 0, len);
        System.arraycopy(nums, 0, newNums, len, len);

        // 用新的nums大小来初始化result
        int[] result = new int[newNums.length];
        Arrays.fill(result, -1);
        if (0 == newNums.length) {
            return result;
        }

        // 开始单调栈
        Deque<Integer> st = new LinkedList<>();
        st.push(0);
        for (int i = 1; i < newNums.length; i++) {
            if (newNums[i] < newNums[st.peek()]) {
                st.push(i);
            }
            else if (newNums[i] == newNums[st.peek()]) {
                st.push(i);
            }
            else {
                while (!st.isEmpty() && newNums[i] > newNums[st.peek()]) {
                    result[st.pop()] = newNums[i];
                }
                st.push(i);
            }
        }
        // 最后再把结果集即result数组resize到原数组大小
        return Arrays.copyOf(result, len);
    }

    public int[] nextGreaterElements2(int[] nums) {
        int len = nums.length;

        // 用新的nums大小来初始化result
        int[] result = new int[len];
        Arrays.fill(result, -1);
        if (0 == len) {
            return result;
        }

        // 开始单调栈
        Deque<Integer> st = new LinkedList<>();
        st.push(0);
        for (int i = 1; i < len * 2; i++) {
            if (nums[i % len] < nums[st.peek()]) {
                st.push(i % len);
            }
            else if (nums[i % len] == nums[st.peek()]) {
                st.push(i % len);
            }
            else {
                while (!st.isEmpty() && nums[i % len] > nums[st.peek()]) {
                    result[st.pop()] = nums[i % len];
                }
                st.push(i % len);
            }
        }
        return result;
    }

    /**
     * T = O(n)，其中 n 是序列的长度。我们需要遍历该数组中每个元素最多 2 次，每个元素出栈与入栈的总次数也不超过 4 次
     * S = O(n)，其中 n 是序列的长度。空间复杂度主要取决于栈的大小，栈的大小至多为 2n - 1
     */
    public int[] nextGreaterElements3(int[] nums) {
        int len = nums.length;

        // 用新的nums大小来初始化result
        int[] result = new int[len];
        Arrays.fill(result, -1);
        if (0 == len) {
            return result;
        }

        // 开始单调栈
        Deque<Integer> st = new LinkedList<>();
        st.push(0);
        for (int i = 1; i < len * 2; i++) {
            while (!st.isEmpty() && nums[i % len] > nums[st.peek()]) {
                result[st.pop()] = nums[i % len];
            }
            st.push(i % len);
        }
        return result;
    }


    public static void main(String[] args) {
        _503_下一个更大元素II obj = new _503_下一个更大元素II();
        test1(obj);
        test2(obj);
        test3(obj);
    }

    private static void test1(_503_下一个更大元素II obj) {
        int[] nums = {1, 2, 1};
        int[] res = obj.nextGreaterElements(nums);
        int[] expect = {2, -1, 2};
        Asserts.test(Arrays.equals(res, expect));
    }

    private static void test2(_503_下一个更大元素II obj) {
        int[] nums = {1, 2, 1};
        int[] res = obj.nextGreaterElements2(nums);
        int[] expect = {2, -1, 2};
        Asserts.test(Arrays.equals(res, expect));
    }

    private static void test3(_503_下一个更大元素II obj) {
        int[] nums = {1, 2, 1};
        int[] res = obj.nextGreaterElements3(nums);
        int[] expect = {2, -1, 2};
        Asserts.test(Arrays.equals(res, expect));
    }
}
