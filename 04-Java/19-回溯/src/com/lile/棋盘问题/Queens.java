package com.lile.棋盘问题;

public class Queens {

	public static void main(String[] args) {
		new Queens().placeQueens(4);
	}
	
	/**
	 * 数组索引是行号，数组元素是列号
	 */
	int[] cols;
	/**
	 * 一共有多少种摆法
	 */
	int ways;
	
	void placeQueens(int n) {
		if (n < 1) return;
		cols = new int[n];
		place(0);
		System.out.println(n + "皇后一共有" + ways + "种摆法");
	}
	
	/**
	 *  从第 row 行开始摆放皇后
	 * @param row
	 */
	void place(int row) {
		if (row == cols.length) {
			ways++; 
			show();
			return;
		}
		for (int col = 0; col < cols.length; col++) {
			if (isValid(row, col)) {
				// 在第 row 行第 col 列摆放皇后
				cols[row] = col;
				place(row + 1);
			}
		}
	}
	
	/**
	 * 判断第 row 行，第 col 列是否可以摆放皇后
	 */
	boolean isValid(int row, int col) {
		for (int i = 0; i < row; i++) {
			// 第 col 列已经有皇后
			if (cols[i] == col) {
				System.out.println("[" + row + "][" + col + "] = false");
				return false;
			}
			// 第 i 行的皇后跟第 row 行第 col 列格子处于同一斜线上
			if (row - i == Math.abs(col - cols[i])) {
				System.out.println("[" + row + "][" + col + "] = false");
				return false;
			}
		}
		System.out.println("[" + row + "][" + col + "] = true");
		return true;
	}
	
	/**
	 * 打印每一种皇后的摆放
	 */
	void show() {
		for (int row = 0; row < cols.length; row++) {
			for (int col = 0; col < cols.length; col++) {
				if (cols[row] == col) {
					System.out.print("1 ");
				} else {
					System.out.print("0 ");
				}
			}
			System.out.println();
		}
		System.out.println("-----------------------");
	}

}
