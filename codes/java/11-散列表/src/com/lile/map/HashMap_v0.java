package com.lile.map;

import java.util.LinkedList;
import java.util.Objects;
import java.util.Queue;

import com.lile.print.BTPrinter;
import com.lile.print.BinaryTreeInfo;

@SuppressWarnings("unchecked")
public class HashMap_v0<K, V> implements Map<K, V> {
	private static final boolean RED = false;
	private static final boolean BLACK = true;
	private int size;
	private Node<K, V>[] table;
	private static final int DEFAULT_CAPACITY = 1 << 4;
	
	public HashMap_v0() {
		table = new Node[DEFAULT_CAPACITY];
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public boolean isEmpty() {
		return size == 0;
	}

	@Override
	public void clear() {
		if (0 == size) return;
		
		size = 0;
		for (int i = 0; i < table.length; i++) {
			table[i] = null;
		}
	}

	@Override
	public V put(K key, V value) {
		int index = index(key);
		// 取出 index 位置的红黑树根节点
		Node<K, V> root = table[index];
		if (root == null) {
			root = new Node<>(key, value, null);
			table[index] = root;
			size++;
//			System.out.println(key + ":" + value + "; size = " + size);
			afterPut(root);
			return null; 
		}
		
		// 添加新的节点到红黑树上
		Node<K, V> parent = root;
		Node<K, V> node = root;
		int cmp = 0;
		K k1 = key;
		int h1 = k1 == null ? 0 : k1.hashCode();
		Node<K, V> result = null;
		boolean searched = false; // 是否已经搜索过这个 key
		do {
			parent = node;
			K k2 = node.key;
			int h2 = node.hash;
			if (h1 > h2) {
				cmp = 1;
			} else if (h1 < h2) {
				cmp = -1;
			} else if (Objects.equals(k1, k2)) {
				cmp = 0;
			} else if (k1 != null && k2 != null 
					&& k1.getClass() == k2.getClass() 
					&& k1 instanceof Comparable 
					&& (cmp = ((Comparable<K>)k1).compareTo(k2)) != 0) {
				
			} else if (searched) {
				cmp = System.identityHashCode(k1) - System.identityHashCode(k2);;
			} else { // searched == false; 还没有扫描，然后再根据内存地址大小决定左右
				if ((node.left != null && (result = node(node.left, k1)) != null) 
						|| (node.right != null && (result = node(node.right, k1)) != null)) {
					// 已经存在这个key
					node = result;
					cmp = 0;
				} else { // 不存在这个key
					searched = true;
					cmp = System.identityHashCode(k1) - System.identityHashCode(k2);
				}
			}
			
			if (cmp < 0) {
				node = node.left;
			} else if (cmp > 0) {
				node = node.right;
			} else {
				V oldValue = node.value;
				node.key = key;
				node.value = value;
				//node.hash = h1;
				return oldValue;
			}
		} while (node != null);
		
		// 看插入到父节点的那个位置
		Node<K, V> newNode = new Node<>(key, value, parent);
		if (cmp < 0) {
			parent.left = newNode;
		} else {
			parent.right = newNode;
		}
		size++;
//		System.out.println(key + ":" + value + "; size = " + size + "; cmp = " + cmp);
		
		afterPut(newNode);
		return null;
	}
	
	/*
	@Override
	public V put(K key, V value) {
		int index = index(key);
		// 取出 index 位置的红黑树根节点
		Node<K, V> root = table[index];
		if (root == null) {
			root = new Node<>(key, value, null);
			table[index] = root;
			size++;
			afterPut(root);
			return null; 
		}
		
		// 添加新的节点到红黑树上
		Node<K, V> parent = root;
		Node<K, V> node = root;
		int cmp = 0;
		int h1 = key == null ? 0 : key.hashCode();
		do {
			cmp = compare(key, node.key, h1, node.hash);
			parent = node;
			if (cmp < 0) {
				node = node.left;
			} else if (cmp > 0) {
				node = node.right;
			} else {
				V oldValue = node.value;
				node.key = key;
				node.value = value;
				return oldValue;
			}
		} while (node != null);
		
		// 看插入到父节点的那个位置
		Node<K, V> newNode = new Node<>(key, value, parent);
		if (cmp < 0) {
			parent.left = newNode;
		} else {
			parent.right = newNode;
		}
		size++;
		
		afterPut(newNode);
		return null;
	}
	*/

	@Override
	public V get(K key) {
		Node<K, V> node = node(key);
		return node != null ? node.value : null;
	}

	@Override
	public V remove(K key) {
		return remove(node(key));
	}

	@Override
	public boolean containsKey(K key) {
		return node(key) != null;
	}

	@Override
	public boolean containsValue(V value) {
		if (size == 0) return false;
		Queue<Node<K, V>> queue = new LinkedList<>();
		for (int i = 0; i < table.length; i++) {
			if (table[i] == null) continue;
			
			queue.offer(table[i]);
			
			while (!queue.isEmpty()) {
				Node<K, V> node = queue.poll();
				if (Objects.equals(value, node.value)) return true;
				
				if (node.left != null) {
					queue.offer(node.left);
				}
				
				if (node.right != null) {
					queue.offer(node.right);
				}
			}
		}
		return false;
	}

	@Override
	public void traversal(Visitor<K, V> visitor) {
		if (size == 0 || visitor == null) return;
		Queue<Node<K, V>> queue = new LinkedList<>();
		for (int i = 0; i < table.length; i++) {
			if (table[i] == null) continue;
			
			queue.offer(table[i]);
			
			while (!queue.isEmpty()) {
				Node<K, V> node = queue.poll();
				if (visitor.visit(node.key, node.value)) return;
				
				if (node.left != null) {
					queue.offer(node.left);
				}
				
				if (node.right != null) {
					queue.offer(node.right);
				}
			}
		}
	}

	public void print() {
		if (size == 0) return;
		for (int i = 0; i < table.length; i++) {
			final Node<K, V> root = table[i];
			System.out.println("【index = " + i + "】");
			BTPrinter.print(new BinaryTreeInfo() {
				@Override
				public Object string(Object node) {
					return node;
				}
				
				@Override
				public Object root() {
					return root;
				}
				
				@Override
				public Object right(Object node) {
					return ((Node<K, V>)node).right;
				}
				
				@Override
				public Object left(Object node) {
					return ((Node<K, V>)node).left;
				}
			});
			System.out.println("---------------------------------------------------");
		}
	}
	
	private V remove(Node<K, V> node) {
		if (node == null) return null;
		
		size--;
		
		V oldValue = node.value;
		
		if (node.hasTwoChildren()) {
			Node<K, V> s = sucessor(node);
			node.key = s.key;
			node.value = s.value;
			node.hash = s.hash;
			node = s;
		}
		
		Node<K, V> replacement = node.left != null ? node.left : node.right;
		int index = index(node);
		
		if (replacement != null) {
			replacement.parent = node.parent;
			if (node.parent == null) {
				table[index] = replacement;
			} else if (node == node.parent.left) {
				node.parent.left = replacement;
			} else {
				node.parent.right = replacement;
			}
			afterRemove(replacement);
		} else if (node.parent == null) {
			table[index] = null;
		} else {
			if (node == node.parent.left) {
				node.parent.left = null;
			} else {
				node.parent.right = null;
			}
			afterRemove(node);
		}
		
		return oldValue;
	}
	
	private void afterPut(Node<K, V> node) {
		Node<K, V> parent = node.parent;
		
		// 添加的是根节点 或者 上溢到达了根节点
		if (parent == null) {
			black(node);
			return;
		}
		
		// 如果父节点是黑色，则直接返回
		if (isBlack(parent)) return;
		
		// 叔父节点
		Node<K, V> uncle = parent.sibling();
		// 祖父节点
		Node<K, V> grand = red(parent.parent);
		if (isRed(uncle)) { // 叔父节点是红色【B树节点上溢】
			black(parent);
			black(uncle);
			// 把祖父节点当作是新添加的节点
			afterPut(grand);
			return;
		}
		
		// 叔父节点不是红色
		if (parent.isLeftChild()) { // L
			if (node.isLeftChild()) { // LL
				black(parent);
			} else { // LR
				black(node);
				rotateLeft(parent);
			}
			rotateRight(grand);
		} else { // R
			if (node.isLeftChild()) { // RL
				black(node);
				rotateRight(parent);
			} else { // RR
				black(parent);
			}
			rotateLeft(grand);
		}
	}
	
	private void afterRemove(Node<K, V> node) {
		// 用以取代 node 的子节点是红色
		// 或者 用以取代删除节点的子节点是红色
		if (isRed(node)) {
			black(node);
			return;
		}
		
		Node<K, V> parent = node.parent;
		// 删除的是根节点
		if (parent == null) return;
		
		// 删除的是黑色叶子节点【下溢】
		// 判断被删除的 node 是左还是右
		boolean left = parent.left == null || node.isLeftChild();
		Node<K, V> sibling = left ? parent.right : parent.left;
		if (left) {
			if (isRed(sibling)) { // 兄弟节点是红色
				black(sibling);
				red(parent);
				rotateLeft(parent);
				sibling = parent.right;
			}
			
			// 兄弟节点必然是黑色
			if (isBlack(sibling.left) && isBlack(sibling.right)) {
				// 兄弟节点没有一个红色子节点，父节点要向下跟兄弟节点合并
				boolean parentBlack = isBlack(parent);
				red(sibling);
				black(parent);
				if (parentBlack) {
					afterRemove(parent);
				}
			} else { // 兄弟节点至少有 1 个红色子节点，向兄弟节点借元素
				// 兄弟节点的左边是黑色，兄弟节点要先旋转
				if (isBlack(sibling.right)) {
					rotateRight(sibling);
					sibling = parent.right;
				}
				
				color(sibling, colorOf(parent));
				black(sibling.right);
				black(parent);
				rotateLeft(parent);
			}
		} else { // 被删除的节点在右边，兄弟节点在左边
			if (isRed(sibling)) { // 兄弟节点是红色
				black(sibling);
				red(parent);
				rotateRight(parent);
				sibling = parent.left;
			}
			
			// 兄弟节点必然是黑色
			if (isBlack(sibling.left) && isBlack(sibling.right)) {
				// 兄弟节点没有一个红色子节点，父节点要向下跟兄弟节点合并
				boolean parentBlack = isBlack(parent);
				red(sibling);
				black(parent);
				if (parentBlack) {
					afterRemove(parent);
				}
			} else { // 兄弟节点至少有 1 个红色子节点，向兄弟节点借元素
				// 兄弟节点的左边是黑色，兄弟节点要先旋转
				if (isBlack(sibling.left)) {
					rotateLeft(sibling);
					sibling = parent.left;
				}
				
				color(sibling, colorOf(parent));
				black(sibling.left);
				black(parent);
				rotateRight(parent);
			}
		}
	}
	
	private void rotateLeft(Node<K, V> grand) {
		Node<K, V> parent = grand.right;
		Node<K, V> child = parent.left;
		grand.right = child;
		parent.left = grand;
		afterRotate(grand, parent, child);
	}
	
	private void rotateRight(Node<K, V> grand) {
		Node<K, V> parent = grand.left;
		Node<K, V> child = parent.right;
		grand.left = child;
		parent.right = grand;
		afterRotate(grand, parent, child);
	}
	
	private void afterRotate(Node<K, V> grand, Node<K, V> parent, Node<K, V> child) {
		// 让 parent 成为子树的根节点
		parent.parent = grand.parent;
		if (grand.isLeftChild()) {
			grand.parent.left = parent;
		} else if (grand.isRightChild()) {
			grand.parent.right = parent;
		} else { // grand 是 root 节点
			table[index(grand)] = parent;
		}
		
		if (child != null) {
			child.parent = grand;
		}
		
		grand.parent = parent;
	}
	
	/**
	 * 根据 key 生成对应的索引（在桶数组中的位置）
	 */
	private int index(K key) {
		if (key == null) return 0;
		int hash = key.hashCode();
		return (hash ^ (hash >>> 16)) & (table.length - 1);
	}
	
	private int index(Node<K, V> node) {
		return (node.hash ^ (node.hash >>> 16)) & (table.length - 1);
	}
	
	private Node<K, V> node(K key) {
		Node<K, V> root = table[index(key)];
		return root == null ? null : node(root, key);
	}
	
	private Node<K, V> node(Node<K, V> node, K k1) {
		int h1 = k1 == null ? 0 : k1.hashCode();
		// 存储查找结果
		Node<K, V> result = null;
		int cmp = 0;
		while (node != null) {
			K k2 = node.key;
			int h2 = node.hash;
			// 先比较哈希值
			if (h1 > h2) {
				node = node.right;
			} else if (h1 < h2) {
				node = node.left;
			} else if (Objects.equals(k1, k2)) {
				return node;
			} else if (k1 != null && k2 != null 
				&& k1.getClass() == k2.getClass()
				&& k1 instanceof Comparable
				&& (cmp = ((Comparable) k1).compareTo(k2)) != 0) {
				node = cmp > 0 ? node.right : node.left;
			} else if (node.right != null && (result = node(node.right, k1)) != null) { 
				return result;
			} else { // 只能往左边找
				node = node.left;
			}
//			} else if (node.left != null && (result = node(node.left, k1)) != null) { 
//				return result;
//			} else {
//				return null;
//			}
		}
		return null;
	}
	
	private Node<K, V> node1(K key) {
		Node<K, V> node = table[index(key)];
		int h1 = key == null ? 0 : key.hashCode();
		while (node != null) {
			int cmp = compare(key, node.key, h1, node.hash);
			if (cmp == 0) return node;
			if (cmp < 0) {
				node = node.left;
			} else {
				node = node.right;
			}
		}
		return null;
	}
	
	private Node<K, V> sucessor(Node<K, V> node) {
		if (node == null) return null;
		
		Node<K, V> p = node.right;
		if (p != null) {
			while (p.left != null) {
				p = p.left;
			}
			return p;
		}
		
		while (node.parent != null && node == node.parent.right) {
			node = node.parent;
		}
		
		return node.parent;
	}
	
	private int compare(K k1, K k2, int h1, int h2) {
		// 比较哈希值
		int result = h1 - h2;
		if (result != 0) return result;
		
		// 比较 equals
		if (Objects.equals(k1, k2)) return 0;
		
		// 哈希值相等，但是不 equals
		if (k1 != null &&  k2 != null 
				&& k1.getClass() != k2.getClass() 
				&& k1 instanceof Comparable) {
			// 同一种类型，并且具备可比较性
			return ((Comparable) k1).compareTo(k2);
		}
		
		return System.identityHashCode(k1) - System.identityHashCode(k2);
	}
	
	private Node<K, V> color(Node<K, V> node, boolean color) {
		if (node == null) return node;
		node.color = color;
		return node;
	}
	
	private Node<K, V> red(Node<K, V> node) {
		return color(node, RED);
	}
	
	private Node<K, V> black(Node<K, V> node) {
		return color(node, BLACK);
	}
	
	private boolean colorOf(Node<K, V> node) {
		return node == null ? BLACK : node.color;
	}
	
	private boolean isBlack(Node<K, V> node) {
		return colorOf(node) == BLACK;
	}
	
	private boolean isRed(Node<K, V> node) {
		return colorOf(node) == RED;
	}
	
	private static class Node<K, V> {
		K key;
		V value;
		boolean color = RED;
		Node<K, V> left;
		Node<K, V> right;
		Node<K, V> parent;
		int hash;
		public Node(K key, V value, Node<K, V> parent) {
			super();
			this.key = key;
			this.value = value;
			this.parent = parent;
			this.hash = key == null ? 0 : key.hashCode(); 
		}
		
		public boolean hasTwoChildren() {
			return left != null && right != null;
		}
		
		public boolean isLeftChild() {
			return parent != null && this == parent.left;
		}
		
		public boolean isRightChild() {
			return parent != null && this == parent.right;
		}
		
		public Node<K, V> sibling() {
			if (isLeftChild()) {
				return parent.right;
			}
			
			if (isRightChild()) {
				return parent.left;
			}
			
			return null;
		}
		
		@Override
		public String toString() {
			return "Node_" + key + "_" + value;
		}
	}

}
