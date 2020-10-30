package com.lile.sort;

public class RadixSort2 extends Sort<Integer> {
	@Override
	protected void sort() {
		/**
		 * T = O(dn)
		 * S = O(kn + k)
		 */
		int max = array[0];
		for (int i = 0; i < array.length; i++) {
			if (array[i] > max) {
				max = array[i];
			}
		}
		
		// 桶数组
		int[][] buckets = new int[10][array.length];
		// 每个桶的元素数量
		int[] bucketSizes = new int[buckets.length];
		for (int divider = 1; divider < max; divider *= 10) {
			for (int i = 0; i < array.length; i++) {
				int no = array[i] / divider % 10;
				buckets[no][bucketSizes[no]++] = array[i];
			}
			int index = 0;
			for (int i = 0; i < buckets.length; i++) {
				for (int j = 0; j < bucketSizes[i]; j++) {
					array[index++] = buckets[i][j];
				}
				bucketSizes[i] = 0;
			}
		}
	}
}
