package com.lile;

public class Main {

	public static void main(String[] args) {
		BloomFilter<Integer> bf = new BloomFilter<>(1_00_0000, 0.05);
		for (int i = 1; i <= 1_00_0000; i++) {
			bf.put(i);
		}
		int count = 0;
		for (int i = 1_00_0001; i <= 2_00_0000; i++) {
			if (bf.contain(i)) {
				count++;
			}
		}
		System.out.println(count);
	}

}
