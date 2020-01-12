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
		bst.preorderTraversal(); // 7, 4, 2, 1, 3, 5, 9, 8, 11, 12
		bst.inorderTraversal(); // 1, 2, 3, 4, 5, 7, 8, 9, 11, 12
		bst.postorderTraversal();
		bst.levelOrderTraversal();
	}

}
