package com.lile;

@SuppressWarnings("unchecked")
public class ArrayList<E> {
	/**
	 * 元素的数量
	 */
	private int size;
	/**
	 * 存放所有的元素
	 */
	private E[] elements;
	
	private static final int DEFAULT_CAPACITY = 10;
	private static final int ELEMENT_NOT_FOUND = -1;
	
	public ArrayList(int capacity) {
		capacity = (capacity < DEFAULT_CAPACITY) ? DEFAULT_CAPACITY : capacity;
		elements = (E[]) new Object[capacity];
	}
	
	public ArrayList() {
		this(DEFAULT_CAPACITY);
	}
	
	/**
	 * 清除所有元素
	 */
	public void clear() {
		for (int i = 0; i < elements.length; i++) {
			elements[i] = null;
		}
		size = 0;
	}
	
	/**
	 * 返回数组中元素的数量
	 */
	public int size() {
		return size;
	}
	
	/**
	 * 判断动态数组是否为空
	 */
	public boolean isEmpty() {
		return size == 0;
	}
	
	/**
	 * 是否包含某个元素
	 */
	public boolean contain(E element) {
		return indexOf(element) != ELEMENT_NOT_FOUND;
	}
	
	/**
	 * 添加元素到尾部
	 */
	public void add(E element) {
		add(element, size);
	}
	
	/**
	 * 获取 index 位置的元素
	 */
	public E get(int index) {
		return elements[index];
	}
	
	/**
	 * 设置 index 位置的元素
	 */
	public E set(E element, int index) {
		rangeCheck(index);
		
		E old = elements[index];
		elements[index] = element;
		return old;
	}
	
	/**
	 * 在 index 位置插入一个元素
	 */
	public void add(E element, int index) {
		rangeCheckForAdd(index);
		entureCapacity(size + 1);
		
		for (int i = size; i > index; i--) {
			elements[i] = elements[i - 1];
		}
		elements[index] = element;
		size++;
	}
	
	/**
	 * 删除 index 位置的元素
	 */
	public E remove(int index) {
		rangeCheck(index);
		
		E old = elements[index];
		for (int i = index + 1; i < size; i++) {
			elements[i - 1] = elements[i];
		}
		elements[--size] = null;
		return old;
	}
	
	/**
	 * 查看元素的索引
	 */
	public int indexOf(E element) {
		if (element == null) {
			for (int i = 0; i < elements.length; i++) {
				if (elements[i] == null) return i;
			}
		} else {
			for (int i = 0; i < elements.length; i++) {
				if (elements[i].equals(element)) return i;
			}
		}
		return ELEMENT_NOT_FOUND;
	}
	
	/**
	 * 要保证有 capacity 的容量
	 * @param capacity
	 */
	private void entureCapacity(int capacity) {
		int oldCapacity = elements.length;
		if (oldCapacity >= capacity) return;
		
		// 新容量为旧容量的 1.5 倍
		int newCapacity = oldCapacity + (oldCapacity >> 1);
		E[] newElements = (E[]) new Object[newCapacity];
		for (int i = 0; i < size; i++) {
			newElements[i] = elements[i];
		}
		elements = newElements;
		System.out.println(oldCapacity + " 扩容为 " + newCapacity);
	}
	
	private void outOfBounds(int index) {
		throw new IndexOutOfBoundsException("Index: " + index + ", size: " + size);
	}
	
	private void rangeCheck(int index) {
		if (index <0 || index >= size) {
			outOfBounds(index);
		}
	}
	
	private void rangeCheckForAdd(int index) {
		if (index <0 || index > size) {
			outOfBounds(index);
		}
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
