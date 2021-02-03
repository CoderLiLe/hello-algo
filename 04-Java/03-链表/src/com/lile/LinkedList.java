package com.lile;

public class LinkedList<E> extends AbstractList<E> {
	private Node<E> first;
	private Node<E> last;
	
	private static class Node<E> {
		E element;
		Node<E> prev;
		Node<E> next;
		public Node(E element, Node<E> prev, Node<E> next) {
			this.element = element;
			this.prev = prev;
			this.next = next;
		}
		
		@Override
		public String toString() {
			StringBuilder sb = new StringBuilder();
			
			if (prev != null) {
				sb.append(prev.element);
			} else {
				sb.append("null");
			}
			
			sb.append("-").append(element).append("-");
			
			if (next != null) {
				sb.append(next.element);
			} else {
				sb.append("null");
			}
			return sb.toString();
		}
	}

	@Override
	public void clear() {
		size = 0;
		
		// GC 机制
		first = null;
		last = null;
	}

	@Override
	public E get(int index) {
		rangeCheck(index);
		
		return node(index).element;
	}

	@Override
	public E set(int index, E element) {
		rangeCheck(index);
		
		Node<E> node = node(index);
		E old = node.element;
		node.element = element;
		return old;
	}

	@Override
	public void add(int index, E element) {
		rangeCheckForAdd(index);
		
		if (index == size) { // 往最后面添加元素
			Node<E> oldLast = last;
			last = new Node<>(element, oldLast, null);
			if (oldLast == null) { // 添加的第一个元素
				first = last;
			} else {
				oldLast.next = last;
			}
		} else {
			Node<E> next = node(index);
			Node<E> prev = next.prev;
			Node<E> node = new Node<>(element, prev, next);
			next.prev = node;
			 
			if (prev == null) { // index == 0
				first = node;
			} else {
				prev.next = node;
			}
		}
		
		size++;
	}

	@Override
	public E remove(int index) {
		rangeCheck(index);
		
		Node<E> node = node(index);
		Node<E> prev = node.prev;
		Node<E> next = node.next;
		
		if (prev == null) { // 要删除的是第一个元素
			first = next;
		} else {
			prev.next = next;
		}
		
		if (next == null) { // 要删除的是最后一个元素
			last = prev;
		} else {
			next.prev = prev;
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
	
	/**
	 * 获取 index 位置对应的节点
	 */
	private Node<E> node(int index) {
		rangeCheck(index);
		
		if (index < (size >> 1)) {
			Node<E> node = first;
			for (int i = 0; i < index; i++) {
				node = node.next;
			}
			return node;
		} else {
			Node<E> node = last;
			for (int i = size - 1; i > index; i--) {
				node = node.prev;
			}
			return node;
		}
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
			
			sb.append(node);
			node = node.next;
		}
		sb.append("]");
		return sb.toString();
	}

}
