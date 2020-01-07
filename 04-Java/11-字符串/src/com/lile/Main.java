package com.lile;

import com.lile.tool.Asserts;

public class Main {

	public static void main(String[] args) {
		Asserts.test(KMP.indexOf("Hello World", "H") == 0);
		Asserts.test(KMP.indexOf("Hello World", "d") == 10);
		Asserts.test(KMP.indexOf("Hello World", "or") == 7);
		Asserts.test(KMP.indexOf("Hello World", "abc") == -1);
	}

}
