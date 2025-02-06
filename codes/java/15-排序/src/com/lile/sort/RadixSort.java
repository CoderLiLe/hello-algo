package com.lile.sort;

public class RadixSort extends Sort<Integer> {

	// T = O(d * (n + k))
	// S = O(n + k)
	@Override
	protected void sort() {
		// 找最大值
		int max = array[0];
		for (int i = 0; i < array.length; i++) {
			if (array[i] > max) {
				max = array[i];
			}
		}
		
		for (int divider = 1; divider <= max; divider *= 10) { // T = O(d)，d 是最大值的位数
			countingSort(divider);
		}
	}

	// T = 3 * O(n) + O(k) = O(n + k)
	// S = O(n + k)
	protected void countingSort(int divider) {
		// 开辟内存空间，存储次数
		int[] counts = new int[10]; // S = O(k)，k 是进制
		// 统计每个整数出现的次数
		for (int i = 0; i < array.length; i++) { // T = O(n)
			counts[array[i] / divider % 10]++;
		}
		
		// 累加次数
		for (int i = 1; i < counts.length; i++) { // T = O(k)，k 是进制
			counts[i] += counts[i - 1];
		}
		
		// 从后往前遍历元素，将它放到有序数组中的合适位置
		int[] newArray = new int[array.length]; // S = O(n)
		for (int i = array.length - 1; i >= 0; i--) { // T = O(n)
			newArray[--counts[array[i] / divider % 10]] = array[i];
		}
		
		// 将有序数组赋值到 array
		for (int i = 0; i < newArray.length; i++) { // T = O(n)
			array[i] = newArray[i];
		}
	}

}
