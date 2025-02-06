package com.lile;

import com.lile.circle.CircleDeque;
import com.lile.circle.CircleQueue;

public class Main {

	public static void main(String[] args) {
//		testQueue();
//		testDeque();
//		testCircleQueue();
		testCircleDeque();
	}
	
	static void testQueue() {
		Queue<Integer> queue = new Queue<>();
		queue.enQueue(1);
		queue.enQueue(2);
		queue.enQueue(3);
		queue.enQueue(4);
		
		while (!queue.isEmpty()) {
			System.out.println(queue.deQueue());
		}
	}
	
	static void testDeque() {
		Deque<Integer> deque = new Deque<>();
		deque.enQueueFront(2);
		deque.enQueueFront(1);
		deque.enQueueRear(3);
		deque.enQueueRear(4);
		
		while (!deque.isEmpty()) {
			System.out.println(deque.deQueueFront());
		}
	}
	
	static void testCircleQueue() {
		CircleQueue<Integer> queue = new CircleQueue<Integer>();
		for (int i = 0; i < 10; i++) {
			queue.enQueue(i);
		}
		for (int i = 0; i < 5; i++) {
			queue.deQueue();
		}
		for (int i = 15; i < 20; i++) {
			queue.enQueue(i);
		}
		
		while (!queue.isEmpty()) {
			System.out.println(queue.deQueue());
		}
		System.out.println(queue);
	} 
	
	static void testCircleDeque() {
		CircleDeque<Integer> queue = new CircleDeque<>();
		// 头5 4 3 2 1  100 101 102 103 104 105 106 8 7 6 尾
		
		// 头 8 7 6  5 4 3 2 1  100 101 102 103 104 105 106 107 108 109 null null 10 9 尾
		for (int i = 0; i < 10; i++) {
			queue.enQueueFront(i + 1);
			queue.enQueueRear(i + 100);
		}
		
		// 头 null 7 6  5 4 3 2 1  100 101 102 103 104 105 106 null null null null null null null 尾
		for (int i = 0; i < 3; i++) {
			queue.deQueueFront();
			queue.deQueueRear();
		}
		
		// 头 11 7 6  5 4 3 2 1  100 101 102 103 104 105 106 null null null null null null 12 尾
		queue.enQueueFront(11);
		queue.enQueueFront(12);
		System.out.println(queue);
		while (!queue.isEmpty()) {
			System.out.println(queue.deQueueFront());
		}
	}

}
