package 链表;

import java.util.LinkedHashMap;

public class LRUCache2 {
    int cap;
    LinkedHashMap<Integer, Integer> cache = new LinkedHashMap<>();

    public LRUCache2(int capacity) {
        this.cap = capacity;
    }

    public int get(int key) {
        if (!cache.containsKey(key)) {
            return -1;
        }

        // 将 key 变为最近使用
        makeRecently(key);
        return cache.get(key);
    }

    public void put(int key, int value) {
       if (cache.containsKey(key)) {
           cache.put(key, value);
           makeRecently(key);
           return;
       }

       if (cache.size() >= cap) {
           // 链表头部就是最久未使用的 key
           int oldestKey = cache.keySet().iterator().next();
           cache.remove(oldestKey);
       }

       // 将新的 key 添加到链表尾部
       cache.put(key, value);
    }

    private void makeRecently(int key) {
        int val = cache.get(key);
        // 将 key 删除重新放入队尾
        cache.remove(key);
        cache.put(key, val);
    }
}
