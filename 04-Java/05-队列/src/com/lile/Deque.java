package com.lile;


import com.lile.list.LinkedList;
import com.lile.list.List;

public class Deque<E> {
	private List<E> deque = new LinkedList<>();
	
	public int size() {
		return deque.size();
	}
	
	public boolean isEmpty() {
		return deque.isEmpty();
	}
	
	public void clear() {
		deque.clear();
	}
	
	public void enQueueRear(E element) {
		deque.add(element);
	}
	
	public void enQueueFront(E element) {
		deque.add(0, element);
	}
	
	public E deQueueRear() {
		return deque.remove(size() - 1);
	}
	
	public E deQueueFront() {
		return deque.remove(0);
	}
	
	public E front() {
		return deque.get(0);
	}
	
	public E rear() {
		return deque.get(size() - 1);
	}
}
