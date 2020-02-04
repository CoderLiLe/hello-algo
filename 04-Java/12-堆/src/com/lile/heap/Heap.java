package com.lile.heap;

public interface Heap<E> {
	// 元素的数量
	int size();
	// 是否为空
	boolean isEmpty();
	// 清空
	void clear();
	// 添加元素
	void add(E element);
	// 获取堆顶元素
	E get();
	// 删除堆顶元素
	E remove();
	// 删除堆顶元素的同时插入一个新元素
	E replace(E element);
}
 