package com.lile.list;

public interface List<E> {
	static final int ELEMENT_NOT_FOUND = -1;
	
	/**
	 * 清除所有元素
	 */
	void clear();
	
	/**
	 * 获取元素的数量
	 * @return
	 */
	int size();
	
	/**
	 * 判断链表是否为空
	 * @return
	 */
	boolean isEmpty();
	
	/**
	 * 是否包含某个元素
	 */
	boolean contain(E element);
	
	/**
	 * 添加元素到尾部
	 */
	void add(E element);
	
	/**
	 * 获取 index 位置的元素
	 */
	E get(int index);
	
	/**
	 * 设置 index 位置的元素
	 */
	E set(int index, E element);
	
	/**
	 * 在 index 位置插入一个元素
	 */
	void add(int index, E element);
	
	/**
	 * 删除 index 位置的元素
	 */
	E remove(int index);
	
	/**
	 * 获取元素的索引
	 * @param element
	 * @return
	 */
	int indexOf(E element);
}
