package com.lile;

public class ArrayList<E> extends AbstractList<E> {
	// 存放所有元素
	private E[] elements;
	private static final int DEFAULT_CAPACITY = 10;
	
	public ArrayList(int capacity) {
		capacity = (capacity < DEFAULT_CAPACITY) ? DEFAULT_CAPACITY : capacity;
		elements = (E[]) new Object[capacity];
	}
	
	public ArrayList() {
		this(DEFAULT_CAPACITY);
	}

	@Override
	public void clear() {
		for (int i = 0; i < size; i++) {
			elements[i] = null;
		}
		size = 0;
		
		// 动态缩容
		if (elements != null && elements.length > DEFAULT_CAPACITY) {
			elements = (E[]) new Object[DEFAULT_CAPACITY];
		}
	}

	@Override
	public E get(int index) {
		rangeCheck(index);
		return elements[index];
	}

	@Override
	public E set(int index, E element) {
		rangeCheck(index);
		
		E old = elements[index];
		elements[index] = element;
		return old;
	}

	/**
	 * 时间复杂度
	 * 最好：O(1)
	 * 最坏：O(m)
	 * 平均：O(n)
	 */
	@Override
	public void add(int index, E elemnt) {
		rangeCheckForAdd(index);
		ensureCapacity(size + 1);
		
		for (int i = size; i > index; i--) {
			elements[i] = elements[i - 1];
		}
		
		elements[index] = elemnt;
		size++;
	}

	/**
	 * 时间复杂度：
	 * 最好：O(1)
	 * 最坏：O(n)
	 * 平均：O(n)
	 */
	@Override
	public E remove(int index) {
		rangeCheck(index);
		
		E old = elements[index];
		for (int i = index + 1; i < size; i++) {
			elements[i - 1] = elements[i];
		}
		elements[--size] = null;
		
		trim();
		
		return old;
	}

	@Override
	public int indexOf(E element) {
		if (element == null) {
			for (int i = 0; i < size; i++) {
				if (elements[i] == null) return i;
			}
		} else {
			for (int i = 0; i < size; i++) {
				if (elements[i].equals(element)) return i;
			}
		}
		return ELEMENT_NOT_FOUND;
	}
	
	private void ensureCapacity(int capacity) {
		int oldCapacity = elements.length;
		if (oldCapacity >= capacity) return;
		
		// 新容量为旧容量的 1.5 倍
		int newCapacity = oldCapacity + (oldCapacity >> 1);
		E[] newElements = (E[]) new Object[newCapacity];
		for (int i = 0; i < size; i++) {
			newElements[i] = elements[i];
		}
		elements = newElements;
		System.out.println(oldCapacity + "扩容为" + newCapacity);
	}
	
	private void trim() {
		int oldCapacity = elements.length;
		int newCapacity = oldCapacity >> 1;
		if (size > newCapacity || oldCapacity <= DEFAULT_CAPACITY) return;
		
		E[] newElements = (E[]) new Object[newCapacity];
		for (int i = 0; i < size; i++) {
			newElements[i] = elements[i];
		}
		elements = newElements;
		
		System.out.println(oldCapacity + "缩容为" + newCapacity);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("size = ").append(size).append(", [");
		for (int i = 0; i < size; i++) {
			if (i != 0) {
				sb.append(", ");
			}
			sb.append(elements[i]);
		}
		sb.append("]");
		return sb.toString();
	}
}
