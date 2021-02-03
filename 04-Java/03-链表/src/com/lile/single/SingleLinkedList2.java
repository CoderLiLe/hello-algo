package com.lile.single;

import com.lile.AbstractList;

/**
 * 增加一个虚拟头节点
 * @author LiLe
 *
 * @param <E>
 */
public class SingleLinkedList2<E> extends AbstractList<E> {
	private Node<E> first;
	
	public SingleLinkedList2() {
		first = new Node<>(null, null);
	}
	
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

	@Override
	public E get(int index) {
		return node(index).element;
	}

	@Override
	public E set(int index, E element) {
		Node<E> node = node(index);
		E old = node.element;
		node.element = element;
		return old;
	}

	@Override
	public void add(int index, E element) {
		rangeCheckForAdd(index);
		
		Node<E> prev = index == 0 ? first : node(index - 1);
		prev.next = new Node<>(element, prev.next);
		
		size++;
	}

	@Override
	public E remove(int index) {
		rangeCheck(index);
		
		Node<E> prev = index == 0 ? first : node(index - 1);
		Node<E> node = prev.next;
		prev.next = node.next;
		
		size--;
		return node.element;
	}

	@Override
	public int indexOf(E element) {
		Node<E> node = first.next;
		if (element == null) {
			for (int i = 0; i < size; i++) {
				if (node.element == null) return i;
				
				node = node.next;
			}
		} else {
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
		Node<E> node = first.next;
		for (int i = 0; i < size; i++) {
			if (i != 0) {
				sb.append(", ");
			}
			sb.append(i + " = " + node.element);
			
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
		
		Node<E> node = first.next;
		for (int i = 0; i < index; i++) {
			node = node.next;
		}
		return node;
	}

}
