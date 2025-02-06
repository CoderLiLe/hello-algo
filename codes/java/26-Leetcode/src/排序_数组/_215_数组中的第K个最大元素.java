package 排序_数组;

import tools.Asserts;

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
        if (left == right) {
            return nums[left];
        }

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

    /**
     * 根据基准元素对数组进行分区
     *
     * 该方法的目标是将数组分成两部分，使得基准元素左边的所有元素都小于基准元素，
     * 右边的所有元素都大于基准元素这种方法在快速排序等算法中非常有用
     *
     * @param nums 待分区的数组
     * @param left 分区开始的索引
     * @param right 分区结束的索引
     * @param pivot_index 基准元素的索引
     * @return 基准元素最终所在的索引
     */
    private int partition(int[] nums, int left, int right, int pivot_index) {
        // 选择pivot_index位置的元素作为基准元素
        int pivot = nums[pivot_index];

        // 将基准元素移动到数组的末尾
        swap(nums, pivot_index, right);
        // 初始化store_index为left，用于记录小于基准元素的部分的下一个位置
        int store_index = left;

        // 遍历数组，比较每个元素与基准元素的大小
        for (int i = left; i <= right; i++) {
            // 如果当前元素小于基准元素，则将其与store_index位置的元素交换，
            // 并将store_index向后移动一位
            if (nums[i] < pivot) {
                swap(nums, store_index++, i);
            }
        }

        // 将基准元素从末尾移动到正确的位置，即store_index处
        swap(nums, store_index, right);

        // 返回基准元素最终所在的索引
        return store_index;
    }

    public int findKthLargest11(int[] nums, int k) {
        if (nums == null || nums.length == 0 || (nums.length < k)) {
            return -1;
        }
        if (nums.length == 1) {
            return nums[0];
        }
        return quickSort2(nums, 0, nums.length - 1, nums.length - k);
    }

    /**
     * 快速排序，基准优化
     *
     * @param nums
     * @param left
     * @param right
     * @param k_smallest
     * @return
     */
    private int quickSort2(int[] nums, int left, int right, int k_smallest) {
        // 子数组长度为 1 时终止递归
        if (left >= right) {
            return nums[left];
        }

        // 哨兵划分
        int pivot = partition(nums, left, right);

        // 递归左子数组、右子数组
        if (pivot == k_smallest) {
            return nums[pivot];
        } else if (pivot < k_smallest) {
            return quickSort2(nums, pivot+1, right, k_smallest);
        } else {
            return quickSort2(nums, left, pivot-1, k_smallest);
        }
    }

    /**
     * 哨兵划分（三数取中值）
     *
     * @param nums
     * @param left
     * @param right
     * @return
     */
    private int partition(int[] nums, int left, int right) {
        // 选取三个候选元素的中位数
        int med = medianThree(nums, left, (left + right) / 2, right);
        // 将中位数交换至数组最左端
        swap(nums, left, med);
        // 以 nums[left] 为基准数
        int i = left, j = right;
        while (i < j) {
            while (i < j && nums[j] >= nums[left]) {
                // 从右向左找首个小于基准数的元素
                j--;
            }
            while (i < j && nums[i] <= nums[left]) {
                // 从左向右找首个大于基准数的元素
                i++;
            }
            // 交换这两个元素
            swap(nums, i, j);
        }
        // 将基准数交换至两子数组的分界线
        swap(nums, i, left);
        // 返回基准数的索引
        return i;
    }

    /**
     * 选取三个候选元素的中位数
     *
     * @param nums
     * @param left
     * @param mid
     * @param right
     * @return
     */
    private int medianThree(int[] nums, int left, int mid, int right) {
        int l = nums[left], m = nums[mid], r = nums[right];
        if ((l <= m && m <= r) || (r <= m && m <= l)) {
            // m 在 l 和 r 之间
            return mid;
        }
        if ((m <= l && l <= r) || (r <= l && l <= m)) {
            // l 在 m 和 r 之间
            return left;
        }
        return right;
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
        if (nums == null || nums.length == 0 || (nums.length < k)) {
            return -1;
        }
        if (nums.length == 1) {
            return nums[0];
        }

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
        if (nums == null || nums.length == 0 || (nums.length < k)) {
            return -1;
        }
        if (nums.length == 1) {
            return nums[0];
        }

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
        Asserts.test(obj.findKthLargest11(nums, 2) == 5);
    }
}
