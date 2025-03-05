package 排序_数组;

import java.util.Arrays;

public class _977_有序数组的平方 {
	/**
	 * 暴力法
	 * T = O(n + nlogn)
	 * S = O(1)
	 */
	public int[] sortedSquares(int[] nums) {
		for (int i = 0; i < nums.length; i++) {
			nums[i] = nums[i] * nums[i];
		}
		Arrays.sort(nums);
		return nums;
	}

	/**
	 * 双指针法
	 * 数组平方的最大值就在数组的两端，不是最左边就是最右边，不可能是中间
	 * T = O(n)
	 */
	public int[] sortedSquares2(int[] nums) {
		int len = nums.length;
		int left = 0, right = len - 1;
		int index = len - 1;
		int[] res = new int[len];
		while (left <= right) {
			if (nums[left] * nums[left] > nums[right] * nums[right]) {
				res[index--] = nums[left] * nums[left];
				left++;
			} else {
				res[index--] = nums[right] * nums[right];
				right--;
			}
		}
		return res;
	}

	public int[] sortedSquares3(int[] nums) {
		int len = nums.length;
		int left = 0, right = len - 1;
		int index = len - 1;
		int[] res = new int[len];
		while (left <= right) {
			if (nums[left] * nums[left] > nums[right] * nums[right]) {
				res[index--] = nums[left] * nums[left++];
			} else {
				res[index--] = nums[right] * nums[right--];
			}
		}
		return res;
	}
}

