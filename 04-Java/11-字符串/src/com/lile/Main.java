package com.lile;

import com.lile.tool.Asserts;

public class Main {

	public static void main(String[] args) {
		Asserts.test(BruteForce2.indexOf("Hello World", "H") == 0);
		Asserts.test(BruteForce2.indexOf("Hello World", "d") == 10);
		Asserts.test(BruteForce2.indexOf("Hello World", "or") == 7);
		Asserts.test(BruteForce2.indexOf("Hello World", "abc") == -1);
	}

}
