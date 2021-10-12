package com.lile.sort.cmp;

import com.lile.sort.Sort;

public class HeapSort<E extends Comparable<E>> extends Sort<E> {
	private int heapSize;

	// T = O(nlogn)
	// S = O(1)
	// 不稳定排序
	// 堆排序可以认为是对选择排序的一种优化
	@Override
	protected void sort() {
		// 原地建堆
		heapSize = array.length;
		for (int i = (heapSize >> 1) - 1; i >= 0; i--) {
			siftDown(i);
		}
		
		while (heapSize > 1) {
			// 交换堆顶元素和尾部元素
			swap(0, --heapSize);
			
			// 对 0 位置进行 siftDown（恢复堆的性质）
			siftDown(0);
		}
	}
	
	private void siftDown(int index) {
		E element = array[index];
		
		int half = heapSize >> 1;
		while (index < half) { // index 必须是非叶子节点
			// 默认是左边跟父节点比
			int childIndex = (index << 1) + 1;
			E child = array[childIndex];
			
			int rightIndex = childIndex + 1;
			// 右子节点比左子节点大
			if (rightIndex < heapSize && cmp(array[rightIndex], child) > 0) {
				child = array[childIndex = rightIndex];
			}
			
			// 大于等于子节点
			if (cmp(element, child) >= 0) break;
			
			array[index] = child;
			index = childIndex;
		}
		array[index] = element;
	}

}
