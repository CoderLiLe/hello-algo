package com.lile;

import java.util.Arrays;

import com.lile.tree.BinarySearchTree;

public class Main {

	public static void main(String[] args) {
		test7();
	}
	
	static void test1() {
		Integer[] data = new Integer[] {7, 4, 9, 2, 5, 8, 11, 3, 12, 1};
		Arrays.sort(data);
		BinarySearchTree<Integer> bst = new BinarySearchTree<Integer>();
		for (int i = 0; i < data.length; i++) {
			bst.add(data[i]);
		}
		bst.preorderTraversal(); // 7, 4, 2, 1, 3, 5, 9, 8, 11, 12
		bst.inorderTraversal(); // 1, 2, 3, 4, 5, 7, 8, 9, 11, 12
		bst.postorderTraversal(); // 1, 3, 2, 5, 4, 8, 12, 11, 9, 7
		bst.levelOrderTraversal(); // 7, 4, 9, 2, 5, 8, 11, 1, 3, 12
	}
	
	static void test7() {
		Integer[] data = new Integer[] {7, 4, 9, 2, 5, 8, 11, 3, 12, 1};
		BinarySearchTree<Integer> bst = new BinarySearchTree<Integer>();
		for (int i = 0; i < data.length; i++) {
			bst.add(data[i]);
		}
		bst.preorderTraversal(); // 7, 4, 2, 1, 3, 5, 9, 8, 11, 12
		bst.remove(1);
		bst.remove(5);
		bst.remove(12);
		bst.preorderTraversal();
	}

}
