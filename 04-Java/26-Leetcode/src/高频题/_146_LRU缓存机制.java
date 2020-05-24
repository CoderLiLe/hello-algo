package 高频题;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class _146_LRU缓存机制 {
    private Map<Integer, Node> map;
    private int capacity;
    // 虚拟头节点
    private Node first;
    // 虚拟尾节点
    private Node last;

    public _146_LRU缓存机制(int capacity) {
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

        return (node == null) ? -1 : node.value;
    }

    public void put(int key, int value) {
        Integer v = get(key);
        Node node = map.get(key);
        if (node != null) {
            node.value = value;
            removeNode(node);
        } else { // 添加一对新的 key-value
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

    /**
     * 从双向链表中删除 node 节点
     */
    private void removeNode(Node node) {
        node.next.prev = node.prev;
        node.prev.next = node.next;
    }

    /**
     * 将 node 节点插入到 first 节点的后面
     */
    private void addAfterFirst(Node node) {
        // node 与 first.next
        node.next = first.next;
        first.next.prev = node;

        // node 与 first
        node.prev = first;
        first.next = node;
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

        public Node() { }
    }

    public static void main(String[] args) {

    }
}
