package 链表;

import java.util.HashMap;
import java.util.Map;

public class LRUCache {
    private Map<Integer, Node> map;
    private int capacity;
    // 虚拟头节点
    private Node first;
    // 虚拟尾节点
    private Node last;

    public LRUCache(int capacity) {
        map = new HashMap<>(capacity);
        this.capacity = capacity;
        first = new Node();
        last = new Node();
        first.next = last;
        last.prev = first;
    }

    public int get(int key) {
        Node node = map.get(key);
        if (node == null) return -1;

        removeNode(node);
        addAfterFirst(node);

        return node.value;
    }

    /**
     * 从双向链表中删除 node 节点
     * @param node
     */
    private void removeNode(Node node) {
        node.next.prev = node.prev;
        node.prev.next = node.next;
    }

    /**
     * 在虚拟头节点后边插入一个节点
     * @param node
     */
    private void addAfterFirst(Node node) {
        // node 与 first.next
        node.next = first.next;
        first.next.prev = node;

        // node 与 first
        first.next = node;
        node.prev = first;
    }

    public void put(int key, int value) {
        Node node = map.get(key);
        if (node != null) {
            node.value = value;
            removeNode(node);
        } else { // 添加一对新的 key - value
            if (map.size() == capacity) {
                // 淘汰最近最少使用的 key-value
                // map.remove(last.prev.key);
                // removeNode(last.prev);
                removeNode(map.remove(last.prev.key));
            }
            map.put(key, node = new Node(key, value));
        }
        addAfterFirst(node);
    }

    private static class Node {
        public int key;
        public int value;
        public Node prev;
        public Node next;
        public Node(int key, int value) {
            this.key = key;
            this.value = value;
        }
        public Node() {}
    }
}
