package com.lile;

import java.util.ArrayList;
import java.util.List;

import com.lile.print.BTPrinter;
import com.lile.tools.Times;
import com.lile.tree.AVLTree;
import com.lile.tree.BST;
import com.lile.tree.RBTree;

public class Main {

	public static void main(String[] args) {
//		test1();
//		test2();
		test3();
	}
	
	static void test1() {
		Integer data[] = new Integer[] {67, 52, 92, 96, 53, 95, 13, 63, 34, 82, 76, 54, 9, 68, 39};
		AVLTree<Integer> avl = new AVLTree<>();
		for (int i = 0; i < data.length; i++) {
			avl.add(data[i]);
//			System.out.println("【" + data[i] + "】");
//			BTPrinter.println(avl);
//			System.out.println("---------------------------------------");
		}
		
//		for (int i = 0; i < data.length; i++) {
//			avl.remove(data[i]);
//			System.out.println("【" + data[i] + "】");
//			BTPrinter.println(avl);
//			System.out.println("---------------------------------------");
//		}
		
		BTPrinter.println(avl);
	}
	
	static void test2() {
		List<Integer> data = new ArrayList<>();
		for (int i = 0; i < 100_0000; i++) {
			data.add((int)(Math.random() * 100_0000));
		}
		
		BST<Integer> bst = new BST<>();
		Times.test("BST - add", () -> {
			for (int i = 0; i < data.size(); i++) {
				bst.add(data.get(i));
			}
		});
		Times.test("BST - contains", () -> {
			for (int i = 0; i < data.size(); i++) {
				bst.contains(data.get(i));
			}
		});
		Times.test("BST - remove", () -> {
			for (int i = 0; i < data.size(); i++) {
				bst.remove(data.get(i));
			}
		});
		
		
		AVLTree<Integer> avl = new AVLTree<>();
		Times.test("AVL - add", () -> {
			for (int i = 0; i < data.size(); i++) {
				avl.add(data.get(i));
			}
		});
		Times.test("AVL - contains", () -> {
			for (int i = 0; i < data.size(); i++) {
				avl.contains(data.get(i));
			}
		});
		Times.test("AVL - remove", () -> {
			for (int i = 0; i < data.size(); i++) {
				avl.remove(data.get(i));
			}
		});
	}
	
	static void test3() {
		Integer data[] = new Integer[] {55, 87, 56, 74, 96, 22, 62, 20, 70, 68, 90, 50};
		RBTree<Integer> rb = new RBTree<>();
		for (int i = 0; i < data.length; i++) {
			rb.add(data[i]);
//			System.out.println("【" + data[i] + "】");
//			BTPrinter.println(avl);
//			System.out.println("---------------------------------------");
		}
		
//		for (int i = 0; i < data.length; i++) {
//			avl.remove(data[i]);
//			System.out.println("【" + data[i] + "】");
//			BTPrinter.println(avl);
//			System.out.println("---------------------------------------");
//		}
		
		BTPrinter.println(rb);
	}

}
