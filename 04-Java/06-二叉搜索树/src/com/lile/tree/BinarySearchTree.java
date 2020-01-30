package com.lile.tree;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.Queue;

@SuppressWarnings("unchecked")
public class BinarySearchTree<E> implements BinaryTreeInfo {
	private int size;
	private Node<E> root;
	private Comparator<E> comparator;
	
	public BinarySearchTree() {
		this(null);
	}
	
	public BinarySearchTree(Comparator<E> comparator) {
		this.comparator = comparator;
	}
	
	public void add(E element) {
		// 添加第一个节点
		if (root == null) {
			root = new Node<>(element, null);
			size++;
			return;
		}
		
		// 添加的不是第一个节点，则先找到待添加节点的父节点
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
		
		// 将新节点插入到父节点
		Node<E> newNode = new Node<>(element, parent);
		if (cmp < 0) {
			parent.left = newNode;
		} else {
			parent.right = newNode;
		}
		size++;
	}
	
	public void remove(E element) {
		remove(node(element));
	}
	
	private void remove(Node<E> node) {
		if (node == null) return;
		
		size--;
		
		if (node.hasTwoChildren()) { // 度为 2 的节点
			// 找到后继节点
			Node<E> s = successor(node);
			// 用后继节点的值覆盖度为 2 节点的值
			node.element = s.element;
			// 删除后继节点
			node = s;
		}
		
		// 删除后继节点（node 的度为 1 或 0）
		Node<E> replacement = node.left != null ? node.left : node.right;
		
		if (replacement != null) { // node 是度为 1 的节点
			// 更改 parent
			replacement.parent = node.parent;
			// 更改 parent 的 left、right 的指向
			if (node.parent == null) { // node 是度为 1 的节点且为根节点
				root = node;
			} else if (node == node.parent.left) {
				node.parent.left = replacement;
			} else { // node == node.parent.right
				node.parent.right = replacement;
			}
		} else if (node.parent == null) { // node 是叶子节点，并且为根节点
			root = null;
		} else { // node 是叶子节点，但不是根节点
			if (node == node.parent.left) {
				node.parent.left = null;
			} else {
				node.parent.right = null;
			}
		}
	}
	
	public boolean contains(E element) {
		return node(element) != null;
	}
	
	public void clear() {
		root = null;
		size = 0;
	}
	
	/**
	 * 前序遍历
	 */
	public void preorderTraversal() {
		System.out.println("-------前序遍历--------");
		preorderTraversal(root);
		System.out.println();
	}
	
	private void preorderTraversal(Node<E> node) {
		if (node == null) return;
		
		System.out.print(node.element + ", ");
		preorderTraversal(node.left);
		preorderTraversal(node.right);
	}
	
	/**
	 * 中序遍历
	 */
	public void inorderTraversal() {
		System.out.println("-------中序遍历--------");
		inorderTraversal(root);
		System.out.println();
	}
	
	private void inorderTraversal(Node<E> node) {
		if (node == null) return;
		
		inorderTraversal(node.left);
		System.out.print(node.element + ", ");
		inorderTraversal(node.right);
	}
	
	/**
	 * 后序遍历
	 */
	public void postorderTraversal() {
		System.out.println("-------后序遍历--------");
		postorderTraversal(root);
		System.out.println();
	}
	
	private void postorderTraversal(Node<E> node) {
		if (node == null) return;
		
		postorderTraversal(node.left);
		postorderTraversal(node.right);
		System.out.print(node.element + ", ");
	}
	
	/**
	 * 层序遍历
	 */
	public void levelOrderTraversal() {
		System.out.println("--------层序遍历-------");
		if (root == null) return;
		
		Queue<Node<E>> queue = new LinkedList<>();
		queue.offer(root);
		
		while (!queue.isEmpty()) {
			Node<E> node = queue.poll();
			System.out.print(node.element + ", ");
			
			if (node.left != null) {
				queue.offer(node.left);
			}
			
			if (node.right != null) {
				queue.offer(node.right);
			}
		}
		System.out.println();
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
	
	/**
	 * 获取前驱节点
	 */
	private Node<E> predecessor(Node<E> node) {
		if (node == null) return null;
		
		// 前驱节点在左子树中(left.right.right.right...)
		Node<E> p = node.left;
		if (p != null) {
			while (p.right != null) {
				p = p.right;
			}
			return p;
		}
		
		while (node.parent != null && node == node.parent.left) {
			node = node.parent;
		}
		
		return node.parent;
	}
	
	/**
	 * 获取后继节点
	 */
	private Node<E> successor(Node<E> node) {
		if (node == null) return null;
		
		Node<E> p = node.right;
		if (p != null) {
			while (p.right != null) {
				p = p.right;
			}
			return p;
		}
		
		// 从父节点、祖父节点中寻找前驱节点
		while (node.parent != null && node == node.parent.right) {
			node = node.parent;
		}
		
		return node.parent;
	}
	
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

	
	private static class Node<E> {
		E element;
		Node<E> left;
		Node<E> right;
		Node<E> parent;
		public Node(E element, Node<E> parent) {
			super();
			this.element = element;
			this.parent = parent;
		}
		
		public boolean isLeaf() {
			return left == null && right == null;
		}
		
		public boolean hasTwoChildren() {
			return left != null && right != null;
		}
	}

	@Override
	public Object root() {
		return root;
	}

	@Override
	public Object left(Object node) {
		return ((Node<E>)node).left;
	}

	@Override
	public Object right(Object node) {
		return ((Node<E>)node).right;
	}

	@Override
	public Object string(Object node) {
		Node<E> myNode = (Node<E>)node;
		String parentStr = "null";
		if (myNode.parent != null) {
			parentStr = myNode.parent.element.toString();
		}
		return myNode.element + "_p(" + parentStr + ")";
	}
}
