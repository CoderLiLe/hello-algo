package com.lile.tools;

import java.util.Arrays;
import java.util.Iterator;

public class Integers {
	
	public static Integer[] random(int count, int min, int max) {
		if (count <= 0 || min > max) return null;
		Integer[] array = new Integer[count];
		int delta = max - min + 1;
		for (int i = 0; i < count; i++) {
			array[i] = min + (int)(Math.random() * delta);
		}
		return array;
	}
	
	public static Integer[] same(int count, int unsameCount) {
		if (count <= 0 || unsameCount > count) return null;
		Integer[] array = new Integer[count];
		for (int i = 0; i < unsameCount; i++) {
			array[i] = unsameCount - i;
		}
		for (int i = unsameCount; i < count; i++) {
			array[i] = unsameCount + 1;
		}
		return array;
	}
	
	public static Integer[] ascOrder(int min, int max) {
		if (min > max) return null;
		Integer[] array = new Integer[max - min + 1];
		for (int i = 0; i < array.length; i++) {
			array[i] = min++;
		}
		return array;
	}
	
	public static Integer[] descOrder(int min, int max) {
		if (min > max) return null;
		Integer[] array = new Integer[max - min + 1];
		for (int i = 0; i < array.length; i++) {
			array[i] = max--;
		}
		return array;
	}
	
	public static Integer[] tailAscOrder(int min, int max, int disorderCount) {
		Integer[] array = ascOrder(min, max);
		if (disorderCount > array.length) return array;
		reverse(array, 0, disorderCount);
		return array;
	}
	
	public static boolean isAscOrder(Integer[] array) {
		if (array == null || array.length == 0) return false;
		for (int i = 1; i < array.length; i++) {
			if (array[i - 1] > array[i]) return false;
		}
		return true;
	}
	
	public static Integer[] copy(Integer[] array) {
		return Arrays.copyOf(array, array.length);
	}
	
	public static void println(Integer[] array) {
		if (array == null) return;
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < array.length; i++) {
			if (i != 0) sb.append("_");
			sb.append(array[i]);
		}
		System.out.println(sb);
	}
	
	/**
	 * 反转一个数组
	 * @param array
	 * @param begin
	 * @param end
	 */
	private static void reverse(Integer[] array, int begin, int end) {
		int count = (end - begin) >> 1;
		int sum = begin + end - 1;
		for (int i = begin; i < begin + count; i++) {
			int j = sum - i;
			int tmp = array[i];
			array[i] = array[j];
			array[j] = tmp;
		}
	}

}
