package com.lile.tree;

import java.util.Comparator;

public class BST<E> extends BinaryTree<E> {
	private Comparator<E> comparator;
	
	public BST() {
		this(null);
	}
	
	public BST(Comparator<E> comparator) {
		this.comparator = comparator;
	}
	
	public void add(E element) {
		elementNotNullCheck(element);
		
		// 添加第一个节点
		if (root == null) {
			root = createNode(element, null);
			size++;
			afterAdd(root);
			return;
		}
		
		// 添加的不是第一个节点
		
		// 找到父节点
		Node<E> parent = root;
		Node<E> node = root;
		int cmp = 0;
		do {
			cmp = compare(element, node.element);
			parent = node;
			if (cmp < 0) {
				node = node.left;
			} else if (cmp > 0) {
				node = node.right;
			} else {
				node.element = element;
				return;
			}
		} while (node != null);
		
		// 看看插入到父节点的那个位置
		Node<E> newNode = createNode(element, parent);
		if (cmp < 0) {
			parent.left = newNode;
		} else {
			parent.right = newNode;
		}
		size++;
		
		// 新添加节点之后的处理
		afterAdd(newNode);
	}
	
	public void remove(E element) {
		remove(node(element));
	} 
	
	public boolean contains(E element) {
		return node(element) != null;
	}
	
	private void remove(Node<E> node) {
		if (node == null) return;
		
		size--;
		
		if (node.hasTwoChildren()) {
			Node<E> s = sucessor(node);
			node.element = s.element;
			node = s;
		}
		
		Node<E> replacement = node.left != null ? node.left : node.right;
		
		if (replacement != null) {
			replacement.parent = node.parent;
			if (node.parent == null) {
				root = replacement;
			} else if (node == node.parent.left) {
				node.parent.left = replacement;
			} else {
				node.parent.right = replacement;
			}
		} else if (node.parent == null) {
			root = null;
		} else {
			if (node == node.parent.left) {
				node.parent.left = null;
			} else {
				node.parent.right = null;
			}
		}
		
		afterRemove(node);
	}
	
	/**
	 * 添加 node 后的调整
	 * @param node 待添加节点
	 */
	protected void afterAdd(Node<E> node) { }
	
	/**
	 * 删除 node 后的调整
	 * @param node 待删除节点
	 */
	protected void afterRemove(Node<E> node) { }
	
	private Node<E> node(E element) {
		Node<E> node = root;
		while (node != null) {
			int cmp = compare(element, node.element);
			if (cmp == 0) return node;
			if (cmp < 0) {
				node = node.left;
			} else {
				node = node.right;
			}
		}
		
		return null;
	}
	
	private int compare(E e1, E e2) {
		if (comparator != null) {
			return comparator.compare(e1, e2);
		}
		
		return ((Comparable<E>)e1).compareTo(e2);
	}
	
	private void elementNotNullCheck(E element) {
		if (element == null) {
			throw new IllegalArgumentException("element must not be null");
		}
	}
}
