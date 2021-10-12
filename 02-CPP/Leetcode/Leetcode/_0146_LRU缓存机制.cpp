//
//  _0146_LRU缓存机制.cpp
//  Leetcode
//
//  Created by LiLe on 2021/7/20.
//

#include "_0146_LRU缓存机制.hpp"

LRUCache::LRUCache(int capacity) {
    this->size = capacity;
    head = nullptr;
    tail = nullptr;
}

// 获取缓冲区中 key 对应的 value
int LRUCache::get(int key) {
    // 1.当该 key 值存在
    if(p.count(key) > 0)
    {
        // 删除该 key 对应的原来节点
        Node* cur = p[key];
        int value = cur->value;
        // 这里仅仅删除哈希双向链表中的节点，不必删除哈希表中的
        remove(cur);
        // 将节点重现插入到缓冲区的头部
        setHead(cur);
        return value;
    }
    // 2.当该 key 值不存在
    return -1;
}

// 将key-value值存入缓冲区
void LRUCache::put(int key, int value) {
    // 1.当该 key 值存在
    if(p.count(key) > 0)
    {
        // 删除该 key 对应的原来节点
        Node* cur = p[key];
        cur->value = value;
        remove(cur);    // 这里仅仅删除哈希双向链表中的节点，不必删除哈希表中的
        // 将节点重现插入到缓冲区的头部
        setHead(cur);
    }
    else // 2.当该 key 值不存在
    {
        Node* node = new Node(key, value);
        // 判断当前缓冲区大小已经满了
        if(p.size() >= size)
        {
            // 删除尾部节点
            // map<int, Node*>::iterator it = p.find(tail->key);
            // 这里erase 函数参数是迭代器类型，所以上面需要使用迭代器类型
            // p.erase(it);
            p.erase(tail->key);
            remove(tail);
        }
        
        setHead(node);
        p[key] = node;
    }
}

// 删除当前节点
void LRUCache::remove(Node* cur)
{
    // 当前节点是 head
    if (cur == head)
        head = cur->next;
    else if(cur == tail) // 当前节点是 tail
        tail = cur->pre;
    else // 当前节点是一般节点
    {
        cur->next->pre = cur->pre;
        cur->pre->next = cur->next;
    }
}

// 将当前节点插入到头部
void LRUCache::setHead(Node* cur)
{
    cur->next = head;
    if(head != nullptr)
        head->pre = cur;
    head = cur;//重现更新head
    
    if(tail == nullptr)
        tail = head;
}
