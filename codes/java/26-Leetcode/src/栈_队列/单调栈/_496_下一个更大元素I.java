package 栈_队列.单调栈;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;

public class _496_下一个更大元素I {

    /**
     * 先对 nums2 中的每一个元素，求出它的右边第一个更大的元素
     * 将上一步的对应关系放入哈希表（HashMap）中
     * 再遍历数组 nums1，根据哈希表找出答案
     */

    /**
     * T = O(N + M)，分别遍历数组 nums1 和数组 nums2 各一次即可，对于 nums2 中的每个元素，进栈一次，出栈一次
     * S = O(N)，遍历 nums2 时需要使用栈，以及哈希映射来临时存储答案
     */
    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        int len1 = nums1.length;
        int len2 = nums2.length;

        Deque<Integer> stack = new ArrayDeque<>();
        Map<Integer, Integer> map = new HashMap<>();

        // 先处理 nums2，把对应关系存入哈希表
        for (int i = 0; i < len2; i++) {
            while (!stack.isEmpty() && stack.peekLast() < nums2[i]) {
                map.put(stack.removeLast(), nums2[i]);
            }
            stack.addLast(nums2[i]);
        }

        // 遍历 nums1 得到结果
        int[] res = new int[len1];
        for (int i = 0; i < len1; i++) {
            res[i] = map.getOrDefault(nums1[i], -1);
        }

        return res;
    }
}
