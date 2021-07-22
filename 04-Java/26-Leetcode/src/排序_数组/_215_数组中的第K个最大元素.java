package 排序_数组;

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Random;

public class _215_数组中的第K个最大元素 {

    /**
     * 暴力法
     * 升序排序以后，目标元素的索引是 len - k
     */
    public int findKthLargest(int[] nums, int k) {
        int len = nums.length;
        // JDK 默认使用快速排序，因此时间复杂度为 O(NlogN)
        // 这里是原地排序，没有借助额外的辅助空间
        Arrays.sort(nums);
        return nums[len - k];
    }

    public int findKthLargest1(int[] nums, int k) {
        if (nums == null || nums.length == 0 || (nums.length < k)) return -1;
        if (nums.length == 1) return nums[0];
        return quickSelect(nums, 0, nums.length - 1, nums.length - k);
    }

    /**
     * 快速选择
     * 步骤：
     * ① 随机选择一个枢纽
     * ② 使用划分算法将枢纽放在合适的位置 pivot_index ，将小于枢纽的元素移动到左边，大于枢纽的元素移动到右边
     * ③ pivot_index == n - k，找到了
     *    pivot_index < n - k，pivot_index 右边找
     *    pivot_index > n - k，pivot_index 左边找
     *
     *  T(n) = O(n)
     *  S(n) = O(1)
     */
    private int quickSelect(int[] nums, int left, int right, int k_smallest) {
        if (left == right) return nums[left];

        // ① 随机选择一个枢纽
        Random random = new Random();
        System.out.println("left = " + left + ", right = " + right);
        int pivot_index = left + random.nextInt(right - left);
        System.out.println("32-pivot_index = "+ pivot_index);

        // ② 使用划分算法将枢纽放在合适的位置 pos ，将小于枢纽的元素移动到左边，大于枢纽的元素移动到右边
        pivot_index = partition(nums, left, right, pivot_index);

        // ③ pivot_index == n - k，找到了
        //    pivot_index < n - k，pivot_index 右边找
        //    pivot_index > n - k，pivot_index 左边找
        if (pivot_index == k_smallest) {
            return nums[pivot_index];
        } else if (pivot_index < k_smallest) {
            return quickSelect(nums, pivot_index+1, right, k_smallest);
        } else {
            return quickSelect(nums, left, pivot_index-1, k_smallest);
        }
    }

    private int partition(int[] nums, int left, int right, int pivot_index) {
        int pivot = nums[pivot_index];

        swap(nums, pivot_index, right);
        int store_index = left;

        for (int i = left; i <= right; i++) {
            if (nums[i] < pivot) swap(nums, store_index++, i);
        }

        swap(nums, store_index, right);

        return store_index;
    }

    private void swap(int[] nums, int a, int b) {
        int tmp = nums[a];
        nums[a] = nums[b];
        nums[b] = tmp;
    }

    /**
     * 堆排序算法 T(n) = O(Nlogk) S(n) = O(k)
     */
    public int findKthLargest2(int[] nums, int k) {
        if (nums == null || nums.length == 0 || (nums.length < k)) return -1;
        if (nums.length == 1) return nums[0];

        PriorityQueue<Integer> heap = new PriorityQueue<>();

        for (int num : nums) {
            heap.add(num);
            if (heap.size() > k) heap.poll();
        }

        return heap.poll();
    }

    public static void main(String[] args) {
        _215_数组中的第K个最大元素 obj = new _215_数组中的第K个最大元素();
        int[] nums = new int[] {3, 2, 1, 5, 6, 4};
//        System.out.println(obj.findKthLargest(nums, 2));
        System.out.println(obj.findKthLargest2(nums, 2));
    }
}
