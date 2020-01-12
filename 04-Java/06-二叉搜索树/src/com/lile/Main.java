package com.lile;

import com.lile.tree.BinarySearchTree;

public class Main {

	public static void main(String[] args) {
		test1();
	}
	
	static void test1() {
		Integer[] data = new Integer[] {7, 4, 9, 2, 5, 8, 11, 3, 12, 1};
		BinarySearchTree<Integer> bst = new BinarySearchTree<Integer>();
		for (int i = 0; i < data.length; i++) {
			bst.add(data[i]);
		}
		bst.preorderTraversal();
	}

}
