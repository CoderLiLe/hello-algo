package com.lile.sort.cmp;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import com.lile.sort.Sort;

public class ShellSort<E extends Comparable<E>> extends Sort<E> {
	@Override
	protected void sort() {
		List<Integer> stepSequence = sedgewichStepSequence();
		System.out.println(stepSequence);
		for (Integer step : stepSequence) {
			sort(step);
		}
	}
	
	/**
	 * 分成 step 列进行排序
	 * @param step
	 */
	private void sort(int step) {
		// col: 第几列
		
		for (int col = 0; col < step; col++) { // 对第 col 列进行排序
			// 对 [0, array.length) 范围的元素进行插入排序
			// col, col + step, col + 2 * step, col + 3 * step
			for (int begin = col + step; begin < array.length; begin += step) {
				int cur = begin;
				while (cur > col && cmp(cur, cur - step) < 0) {
					swap(cur, cur - step);
					cur -= step;
				}
			}
		}
	}
	
	private List<Integer> shellStepSequence() {
		List<Integer> stepSequence = new ArrayList<>();
		int step = array.length;
		
		while ((step = step >> 1) > 0) {
			stepSequence.add(step);
		}
		
		return stepSequence;
	}
	
	private List<Integer> sedgewichStepSequence() {
		List<Integer> stepSequence = new LinkedList<>();
		int k = 0, step = 0;
		while (true) {
			if (k % 2 == 0) {
				int pow = (int)Math.pow(2, k >> 1);
				step = 1 + 9 * (pow * pow - pow);
			} else {
				int pow1 = (int)Math.pow(2, (k - 1) >> 1);
				int pow2 = (int)Math.pow(2, (k + 1) >> 1);
				step = 1 + 8 * pow1 * pow2 - 6 * pow2;
			}
			if (step >= array.length) break;
			stepSequence.add(0, step);
			k++;
		}
		return stepSequence;
	}
}
