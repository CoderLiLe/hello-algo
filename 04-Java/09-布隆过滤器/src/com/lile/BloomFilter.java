package com.lile;

public class BloomFilter<T> {
	/**
	 * 二进制向量的长度（一共有多少个二进制位）
	 */
	private int bitSize;
	/**
	 * 二进制向量
	 */
	private long[] bits;
	/**
	 * 哈希函数的个数
	 */
	private int hashSize;
	
	/**
	 * 构造函数
	 * @param n 数据规模
	 * @param p 误判率，取值范围 (0, 1)
	 */
	public BloomFilter(int n, double p) {
		if (n <= 0 || p <= 0 || p >= 1) {
			throw new IllegalArgumentException("wrong n or p");
		} 
		
		double ln2 = Math.log(2);
		// 求出二进制向量的长度
		bitSize = (int) (-(n * Math.log(p)) / (ln2 * ln2));
		// 求出哈希函数的个数
		hashSize = (int) (bitSize * ln2 / n);
		// bits 数组的长度
		bits = new long[(bitSize + Long.SIZE - 1) / Long.SIZE];
		
		System.out.println((bitSize + Long.SIZE - 1) / Long.SIZE);
	}
	
	/**
	 * 添加元素
	 */
	public boolean  put(T value) {
		nullCheck(value);
		// 利用 value 生成两个整数
		int hash1 = value.hashCode();
		int hash2 = hash1 >>> 16;
		boolean result = false;
		for (int i = 1; i < hashSize; i++) {
			int combinedHash = hash1 + (i * hash2);
			if (combinedHash < 0) {
				combinedHash = ~combinedHash;
			}
			// 生成一个二进制位的索引
			int index = combinedHash % bitSize;
			// 设置 index 位置的二进制位为1
			if (set(index)) result = true;
		}
		return result;
	}
	
	/**
	 * 判断一个元素是否存在
	 */
	public boolean contain(T value) {
		nullCheck(value);
		// 利用 value 生成两个整数
		int hash1 = value.hashCode();
		int hash2 = hash1 >>> 16;
		
		for (int i = 1; i < hashSize; i++) {
			int combinedHash = hash1 + (i * hash2);
			if (combinedHash < 0) {
				combinedHash = ~combinedHash;
			}
			// 生成一个二进制位的索引
			int index = combinedHash % bitSize;
			// 查询 index 位置的二进制位是否为0
			if (!get(index)) return false;
		}
		return true;
	}
	
	/**
	 * 设置 index 位置的二进制位为1
	 */
	private boolean set(int index) {
		long value = bits[index / Long.SIZE];
		int bitValue = 1 << (index % Long.SIZE);
		bits[index / Long.SIZE] = value | bitValue;
		return (value & bitValue) == 0;
	}
	
	/**
	 * 查看 index 位置的二进制位的值
	 * @return true 代表1，false 代表0
	 */
	private boolean get(int index) {
		long value = bits[index / Long.SIZE];
		return (value & (1 << (index % Long.SIZE))) != 0;
	}
	
	private void nullCheck(T value) {
		if (value == null) {
			throw new IllegalArgumentException("Value must not be null.");
		}
	}
}
