package com.lile;

public class Hanoi {
	
	public static void main(String[] args) {
		new Hanoi().hanoi(3, "A", "B", "C");
	}

	/**
	 * 将 n 个碟子从 p1 挪动到 p3
	 * @param n 碟子数量
	 * @param p1 第一个柱子
	 * @param p2 第二个柱子（中间柱子）
	 * @param p3 第三个柱子
	 * T(n) = 2 * T(n - 1) + O(1)
	 * T(n) = 2 * [2 * T(n - 2) + O(1)] + O(1)
	 * T(n) = 2^2 * [2 * T(n - 3) + O(1)] + 2 * O(1) + O(1)
	 * T(n) = 2^3 * T(n - 3) + (2^2 + 2^1 + 2^0) * O(1)
	 * T(n) = 2^(n - 1) * O(1) + (2^(n - 2) + ... + 2^2 + 2^1 + 2^0) * O(1)
	 * T(n) = [2^(n - 1) + 2^(n - 2) + ... + 2^2 + 2^1 + 2^0] * O(1)
	 * T(n) = (2^n - 1) * O(1)
	 */
	void hanoi(int n, String p1, String p2, String p3) {
		if (n == 1) { 
			move(n, p1, p3);
			return;
		}
		hanoi(n - 1, p1, p3, p2);
		move(n, p1, p3);
		hanoi(n - 1, p2, p1, p3);
	}
	
	void move(int no, String from, String to) {
		System.out.println("将" + no + "号盘子从" + from + "移动到" + to);
	}
}
