package com.lile;

import com.lile.circle.CircleLinkedList;
import com.lile.circle.SingleCircleLinkedList;

public class Main {

	public static void main(String[] args) {
//		testList(new ArrayList<Integer>());
//		testList(new LinkedList<Integer>());
//		testList(new SingleLinkedList<Integer>());
//		testList(new SingleCircleLinkedList<Integer>());
		josephus();
	}
	
	static void testList(List<Integer> list) {
		list.add(1);
		list.add(2);
		list.add(3);
		list.add(4);
		
		list.add(0, 5); 
		list.add(2, 6);
		list.add(list.size(), 7); // {5, 1, 6, 2, 3, 4, 7}
		
		list.remove(0);
		list.remove(2);
		list.remove(list.size() - 1); // {1, 6, 3, 4}
		
		Asserts.test(list.indexOf(4) == 3);
		Asserts.test(list.indexOf(2) == List.ELEMENT_NOT_FOUND);
		Asserts.test(list.contain(3));
		Asserts.test(list.get(0) == 1);
		Asserts.test(list.get(1) == 6);
		Asserts.test(list.get(list.size() - 1) == 4);
		
		System.out.println(list);
	}
	
	static void josephus() {
		CircleLinkedList<Integer> list = new CircleLinkedList<>();
		for (int i = 1; i <= 8; i++) {
			list.add(i);
		}
		
		// 指向头结点（指向1）
		list.reset();
		
		while (!list.isEmpty()) {
			list.next();
			list.next();
			System.out.println(list.remove());
		}
	}

}
