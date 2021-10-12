//
//  _0146_LRU缓存机制.hpp
//  Leetcode
//
//  Created by LiLe on 2021/7/20.
//

#ifndef _0146_LRU_____hpp
#define _0146_LRU_____hpp

#include <stdio.h>
#include <map>
using namespace std;

struct Node {
    int key;
    int value;
    Node* pre;
    Node* next;
    // 构造函数初始化
    Node(int key, int value) : key(key), value(value), pre(nullptr), next(nullptr) {
        
    }
};

class LRUCache {
private:
    int size; // 缓冲区大小
    Node* head;
    Node* tail;
    map<int, Node*> p;
    
    // 删除当前节点
    void remove(Node* cur);
    
    // 将当前节点插入到头部
    void setHead(Node* cur);
    
public:
    LRUCache(int capacity);
    
    // 获取缓冲区中 key 对应的 value
    int get(int key);
    
    // 将key-value值存入缓冲区
    void put(int key, int value);
};

#endif /* _0146_LRU_____hpp */
