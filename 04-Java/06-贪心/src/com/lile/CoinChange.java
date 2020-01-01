package com.lile;

import java.util.Arrays;

public class CoinChange {

	public static void main(String[] args) {
//		coinChange1();
		coinChange2(new Integer[] {25, 5, 20, 1}, 41);
	}
	
	static void coinChange1() {
		int[] faces = {25, 5, 10, 1};
		Arrays.sort(faces);
		
		int money = 41, coins = 0;
		for (int i = faces.length - 1; i >= 0; i--) {
			if (money < faces[i]) {
				continue;
			}
			
			money -= faces[i];
			coins++;
			i = faces.length;
		}
		
		System.out.println("一共需要" + coins + "枚硬币");
	}
	
	static void coinChange2(Integer[] faces, int money) {
		Arrays.sort(faces, (Integer f1, Integer f2) -> f2 - f1);
		
		int coins = 0, i = 0;
		while (i < faces.length) {
			if (money < faces[i]) {
				i++;
				continue;
			}
			System.out.println(faces[i]);
			money -= faces[i];
			coins++;
		}
		
		System.out.println("一共需要" + coins + "枚硬币");
	}
}
