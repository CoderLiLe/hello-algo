package com.lile;

import java.util.Arrays;

import com.lile.sort.BucketSort;
import com.lile.sort.CountingSort;
import com.lile.sort.RadixSort;
import com.lile.sort.RadixSort2;
import com.lile.sort.Sort;
import com.lile.sort.cmp.BubbleSort1;
import com.lile.sort.cmp.BubbleSort2;
import com.lile.sort.cmp.BubbleSort3;
import com.lile.sort.cmp.HeapSort;
import com.lile.sort.cmp.InsertionSort1;
import com.lile.sort.cmp.InsertionSort2;
import com.lile.sort.cmp.InsertionSort3;
import com.lile.sort.cmp.MergeSort;
import com.lile.sort.cmp.QuickSort;
import com.lile.sort.cmp.SelectionSort;
import com.lile.sort.cmp.ShellSort;
import com.lile.tools.Asserts;
import com.lile.tools.Integers;
import com.lile.tools.Times;

@SuppressWarnings({"rawtypes", "unchecked"})
public class Main {

	public static void main(String[] args) {
//		int[] array = {2, 4, 8, 8, 8, 12, 14};
//		Asserts.test(BinarySearch.search(array, 5) == 2);
//		Asserts.test(BinarySearch.search(array, 1) == 0);
//		Asserts.test(BinarySearch.search(array, 15) == 7);
//		Asserts.test(BinarySearch.search(array, 8) == 5);
		
		testSort();
//		testBucketSort();
	}
	
	static void testSort() {
		Integer[] array = Integers.random(1000, 1, 2000);
		
		testSorts(array, 
				new BubbleSort1(), 
				new BubbleSort2(), 
				new BubbleSort3(), 
				new SelectionSort(), 
				new HeapSort(),
				new InsertionSort1(),
				new InsertionSort2(),
				new InsertionSort3(),
				new MergeSort(),
				new QuickSort(),
				new ShellSort(),
				new CountingSort(),
				new RadixSort(),
				new RadixSort2());
	}
	
	static void testBucketSort() {
		double[] array = {0.34, 0.47, 0.29, 0.84, 0.45, 0.38, 0.35, 0.76};
		
		BucketSort bucketSort = new BucketSort();
		bucketSort.sort(array);
		
		for (int i = 0; i < array.length; i++) {
			if (i < array.length - 1) {
				System.out.print(array[i] + "_");
			} else {
				System.out.print(array[i]);
			}
		}
	}
	
	static void testSorts(Integer[] array, Sort... sorts) {
		for (Sort sort : sorts) {
			Integer[] newArray = Integers.copy(array);
			sort.sort(newArray);
		}
		
		Arrays.sort(sorts);
		
		for (Sort sort : sorts) {
			System.out.println(sort);
		}
	}
	
	static void testSorts() {
		Integer[] array1 = Integers.random(10000, 1, 20000);
		Integer[] array2 = Integers.copy(array1);
		Integer[] array3 = Integers.copy(array1);
		
		Times.test("BubbleSort3", () -> {
			// bubbleSort3(array1);
			new BubbleSort3().sort(array1);
		});
		Times.test("SelectionSort", () -> {
			new SelectionSort().sort(array2);
		});
		Times.test("HeapSort", () -> {
			new HeapSort().sort(array3);
		});
	}
	
	static void bubbleSort1(Integer[] array) {
		for (int end = array.length-1; end > 1; end--) {
			for (int begin = 1; begin <= end; begin++) {
				if (array[begin] < array[begin-1]) {
					int tmp = array[begin];
					array[begin] = array[begin-1];
					array[begin-1] = tmp;
				}
			}
		}
	}
	
	static void bubbleSort2(Integer[] array) {
		for (int end = array.length-1; end > 1; end--) {
			boolean sorted = true;
			for (int begin = 1; begin <= end; begin++) {
				if (array[begin] < array[begin-1]) {
					int tmp = array[begin];
					array[begin] = array[begin-1];
					array[begin-1] = tmp;
					sorted = false;
				}
			}
			if (sorted) break;
		}
	}
	
	static void bubbleSort3(Integer[] array) {
		for (int end = array.length-1; end > 1; end--) {
			int sortedIndex = 1;
			for (int begin = 1; begin <= end; begin++) {
				if (array[begin] < array[begin-1]) {
					int tmp = array[begin];
					array[begin] = array[begin-1];
					array[begin-1] = tmp;
					sortedIndex = begin;
				}
			}
			end = sortedIndex;
		}
	}
	
	static void selectionSort(Integer[] array) {
		for (int end = array.length - 1; end > 0; end--) {
			int maxIndex = 0;
			for (int begin = 1; begin <= end; begin++) {
				if (array[maxIndex] <= array[begin]) {
					maxIndex = begin;
				}
			}
			int tmp = array[maxIndex];
			array[maxIndex] = array[end];
			array[end] = tmp;
		}
	}

}
