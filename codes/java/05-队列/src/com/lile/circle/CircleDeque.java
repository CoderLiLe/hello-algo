package com.lile.circle;

public class CircleDeque<E> {
	private int front;
	private int size;
	private E[] elements;
	private static final int DEFAULT_CAPACITY = 10;
	
	public CircleDeque() {
		elements = (E[]) new Object[DEFAULT_CAPACITY];
	}
	
	public int size() {
		return size;
	}
	
	public boolean isEmpty() {
		return size == 0;
	}
	
	public void clear() {
		for (int i = 0; i < elements.length; i++) {
			elements[index(i)] = null;
		}
		front = 0;
		size = 0;
	}
	
	public void enQueueRear(E element) {
		ensureCapacity(size + 1);
		
		elements[index(size)] = element;
		size++;
	}
	
	public void enQueueFront(E element) {
		ensureCapacity(size + 1);
		
		front = index(-1);
		elements[front] = element;
		size++;
	}
	
	public E deQueueFront() {
		E frontElement = elements[front];
		elements[front] = null;
		front = index(1);
		size--;
		return frontElement;
	}
	
	public E deQueueRear() {
		int rearIndex = size - 1;
		E rearElement = elements[rearIndex];
		elements[rearIndex] = null;
		size--;
		return rearElement;
	}
	
	public E front() {
		return elements[front];
	}
	
	public E rear() {
		return elements[index(size - 1)];
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("capacity = ").append(elements.length)
		.append(", size = ").append(size)
		.append(", front = ").append(front)
		.append(", [");
		for (int i = 0; i < elements.length; i++) {
			if (i != 0) {
				sb.append(", ");
			}
			sb.append(elements[i]);
		}
		sb.append("]");
		return sb.toString();
	}
	
	private int index(int index) {
		index += front;
		if (index < 0) {
			return index + elements.length;
		}
		return index - (index >= elements.length ? elements.length : 0);
	}

	private void ensureCapacity(int capacity) {
		int oldCapacity = elements.length;
		if (oldCapacity >= capacity) return;
		
		// 新容量扩展为旧容量的 1.5 倍
		int newCapacity = oldCapacity + (oldCapacity >> 1);
		E[] newElements = (E[]) new Object[newCapacity];
		for (int i = 0; i < size; i++) {
			newElements[i] = elements[i];
		}
		elements = newElements;
		
		// 重置 front
		front = 0;
		
		System.out.println(oldCapacity + " 扩容为 " + newCapacity);
	}
	
}
