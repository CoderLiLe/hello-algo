package com.lile.sort;

import java.util.LinkedList;
import java.util.List;

@SuppressWarnings("unchecked")
public class BucketSort {
	public void sort(double[] array) {
		long begin = System.currentTimeMillis();
		
		List<Double>[] buckets = new List[array.length];
		for (int i = 0; i < buckets.length; i++) {
			int bucketIndex = (int) (array[i] * array.length);
			List<Double> bucket = buckets[bucketIndex];
			if (bucket == null) {
				bucket = new LinkedList<>();
				buckets[bucketIndex] = bucket;
			}
			bucket.add(array[i]);
		}
		
		// 对每个桶进行排序
		int index = 0;
		for (int i = 0; i < buckets.length; i++) {
			if (buckets[i] == null) {
				continue;
			}
			
			buckets[i].sort(null);
			for (Double d : buckets[i]) {
				array[index++] = d;
			}
		}
		
		long time = System.currentTimeMillis() - begin;
		
		String timeStr = "耗时：" + (time / 1000.0) + "s(" + time + "ms)";
		System.out.println(timeStr);
	}

}
