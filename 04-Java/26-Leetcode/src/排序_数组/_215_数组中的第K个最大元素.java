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

    /**
     * 根据K的不同，选最大堆和最小堆，目的是让堆中的元素更少
     * k 要是更靠近 0 的话，此时 k 是一个较小的数，用最大堆
     * k 要是更靠近 len 的话，用最小堆
     */
    public int findKthLargest3(int[] nums, int k) {
        if (nums == null || nums.length == 0 || (nums.length < k)) return -1;
        if (nums.length == 1) return nums[0];

        int len = nums.length;
        if (k <= len - k) {
            System.out.println("使用最小堆");
            PriorityQueue<Integer> minHeap = new PriorityQueue<>(k, (a, b) -> a - b);
            for (int i = 0; i < k; i++) {
                minHeap.add(nums[i]);
            }
            for (int i = k; i < len; i++) {
                Integer topEle = minHeap.peek();
                if (nums[i] > topEle) {
                    minHeap.poll();
                    minHeap.add(nums[i]);
                }
            }
            return minHeap.peek();
        } else {
            System.out.println("使用最大堆");
            assert k > len - k;
            // 特例：k = 100，用容量为 len - k + 1 的最大堆
            int capacity = len - k + 1;
            PriorityQueue<Integer> maxHeap = new PriorityQueue<>(capacity, (a, b) -> b - a);
            for (int i = 0; i < capacity; i++) {
                maxHeap.add(nums[i]);
            }
            for (int i = capacity; i < len; i++) {
                Integer topEle = maxHeap.peek();
                if (nums[i] < topEle) {
                    maxHeap.poll();
                    maxHeap.add(nums[i]);
                }
            }
            return maxHeap.peek();
        }
    }

    public static void main(String[] args) {
        _215_数组中的第K个最大元素 obj = new _215_数组中的第K个最大元素();
        int[] nums = new int[] {3, 2, 1, 5, 6, 4};
//        System.out.println(obj.findKthLargest(nums, 2));
        System.out.println(obj.findKthLargest2(nums, 2));
        System.out.println(obj.findKthLargest3(nums, 2));
        System.out.println(obj.findKthLargest3(nums, 5));
    }
}
