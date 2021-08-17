package com.lile.sort.cmp;

import com.lile.sort.Sort;

public class SelectionSort<E extends Comparable<E>> extends Sort<E> {

	// T = O(n ^ 2)
	// S = O(1)
	// 不稳定排序
	// 交换次数远远少于冒泡排序，平均性能优于冒泡排序
	@Override
	protected void sort() {
		for (int end = array.length - 1; end > 0; end--) {
			int maxIndex = 0;
			for (int begin = 1; begin <= end; begin++) {
				// if (array[maxIndex] <= array[begin]) {
				// if (cmp(maxIndex, begin) <= 0) {
				if (cmp(maxIndex, begin) < 0) {
					maxIndex = begin;
				}
			}
			swap(maxIndex, end);
		}
	}

}
