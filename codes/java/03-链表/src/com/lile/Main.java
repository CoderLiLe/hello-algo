package com.lile;

import com.lile.circle.CircleLinkedList;
import com.lile.circle.SingleCircleLinkedList;
import com.lile.single.SingleLinkedList1;
import com.lile.single.SingleLinkedList2;

public class Main {

	public static void main(String[] args) {
		
//		testArrayListCapacity();
		
//		testList(new ArrayList<Integer>());
//		testList(new SingleLinkedList1<Integer>());
//		testList(new SingleLinkedList2<Integer>());
//		testList(new LinkedList<Integer>());
//		testList(new SingleCircleLinkedList<Integer>());
		testList(new CircleLinkedList<>());
//		josephus();
	}
	
	static void testArrayListCapacity() {
		List<Integer> list = new ArrayList<>();
		
		for (int i = 0; i < 50; i++) {
			list.add(i);
		}
		
		for (int i = 0; i < 50; i++) {
			list.remove(0);
		}
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
