//
//  _0151_翻转字符串里的单词.cpp
//  Leetcode
//
//  Created by LiLe on 2021/7/23.
//

#include "_0151_翻转字符串里的单词.hpp"

/**
 * 将 [li, ri)范围内的字符串进行逆序
 */
void reverse(string& s, int li, int ri) {
    ri--;
    while (li < ri) {
//        char tmp = s[li];
//        s[li] = s[ri];
//        s[ri] = tmp;
        
//        swap(s[li], s[ri]);
        
        s[li] ^= s[ri];
        s[ri] ^= s[li];
        s[li] ^= s[ri];
        
        li++;
        ri--;
    }
}

string reverseWords(string s) {
    if (s.empty()) return "";
    
    // 1、消除多余的空格
    // 字符串最终的有效长度
    int len = 0;
    // 当前用来存放字符的位置
    int cur = 0;
    // 前一个字符是否为空格字符
    bool space = true;
    for (int i = 0; i < s.size(); i++) {
        if (s[i] != ' ') { // s[i] 是非空格字符
            s[cur++] = s[i];
            space = false;
        } else if (space == false) { // s[i] 是空格字符，s[i - 1] 是非空格字符
            s[cur++] = ' ';
            space = true;
        }
    }
    len = space ? (cur - 1) : cur;
    if (len <= 0) return "";
    
    // 2、对整个有效字符串进行逆序
    reverse(s, 0, len);
    
    // 3、对每一个单词进行逆序
    // 前一个空格字符的位置（哨兵）
    int prevSpaceIdx = -1;
    for (int i = 0; i < len; i++) {
        if (s[i] != ' ') continue;
        // i 是空格字符的位置
        reverse(s, prevSpaceIdx + 1, i);
        prevSpaceIdx = i;
    }
    
    reverse(s, prevSpaceIdx + 1, len);
    
    return s.substr(0, len);
}
