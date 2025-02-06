package com.lile;

import com.lile.list.LinkedList;
import com.lile.list.List;

public class Queue<E> {
	private List<E> queue = new LinkedList<>();
	
	public int size() {
		return queue.size();
	}
	
	public boolean isEmpty() {
		return queue.isEmpty();
	}
	
	public void clear() {
		queue.clear();
	}
	
	public void enQueue(E element) {
		queue.add(element);
	}
	
	public E deQueue() {
		return queue.remove(0);
	} 
	
	public E front() {
		return queue.get(0);
	}
}
