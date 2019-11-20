package com.lile.sort.cmp;

import com.lile.sort.Sort;

public class BubbleSort1<E extends Comparable<E>> extends Sort<E> {

	@Override
	protected void sort() {
		for (int end = array.length-1; end > 1; end--) {
			for (int begin = 1; begin <= end; begin++) {
				if (cmp(begin, begin - 1) < 0) {
					swap(begin, begin - 1);
				}
			}
		}
	}

}
