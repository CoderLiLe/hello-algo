package com.lile.single;

import com.lile.AbstractList;

public class SingleLinkedList1<E> extends AbstractList<E> {
	private Node<E> first;
	
	private static class Node<E> {
		E element;
		Node<E> next;
		public Node(E element, Node<E> next) {
			this.element = element;
			this.next = next;
		}
	}

	
	@Override
	public void clear() {
		size = 0;
		first = null;
	}

	/**
	 * 时间复杂度：
	 * 最好：O(1)
	 * 最坏：O(n)
	 * 平均：O(n)
	 */
	@Override
	public E get(int index) {
		return node(index).element;
	}

	/**
	 * 时间复杂度：
	 * 最好：O(1)
	 * 最坏：O(n)
	 * 平均：O(n)
	 */
	@Override
	public E set(int index, E element) {
		Node<E> node = node(index);
		E old = node.element;
		node.element = element;
		return old;
	}

	/**
	 * 时间复杂度：
	 * 最好：O(1)
	 * 最坏：O(n)
	 * 平均：O(n)
	 */
	@Override
	public void add(int index, E element) {
		rangeCheckForAdd(index);
		
		if (index == 0) {
			first = new Node<>(element, first);
		} else {
			Node<E> prev = node(index - 1);
			prev.next = new Node<>(element, prev.next);
		}
		
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
		
		Node<E> node = first;
		if (index == 0) {
			first = first.next;
		} else {
			Node<E> prev = node(index - 1);
			node = prev.next;
			prev.next = node.next;
		}
		
		size--;
		return node.element;
	}

	@Override
	public int indexOf(E element) {
		if (element == null) {
			Node<E> node = first;
			for (int i = 0; i < size; i++) {
				if (node.element == null) return i;
				
				node = node.next;
			}
		} else {
			Node<E> node = first;
			for (int i = 0; i < size; i++) {
				if (node.element.equals(element)) return i;
				
				node = node.next;
			}
		}
		return ELEMENT_NOT_FOUND;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("size = ").append(size).append(", [");
		Node<E> node = first;
		for (int i = 0; i < size; i++) {
			if (i != 0) {
				sb.append(", ");
			}
			sb.append(node.element);
			
			node = node.next;
		}
		sb.append("]");
		
//		Node<E> node1 = first;
//		while (node1 != null) {
//			
//			 
//			node1 = node1.next;
//		}
		
		return sb.toString();
	}
	
	/**
	 * 获取 index 位置对应的节点对象
	 */
	private Node<E> node(int index) {
		rangeCheck(index);
		
		Node<E> node = first;
		for (int i = 0; i < index; i++) {
			node = node.next;
		}
		return node;
	}

}
