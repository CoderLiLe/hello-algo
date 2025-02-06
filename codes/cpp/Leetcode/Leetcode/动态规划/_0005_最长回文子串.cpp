//
//  _0005_最长回文子串.cpp
//  Leetcode
//
//  Created by LiLe on 2021/7/26.
//

#include "_0005_最长回文子串.hpp"
#include <vector>

#pragma mark 动态规划法
// T = O(n ^ 2), S = O(n ^ 2)
string longestPalindrome(string s) {
    if (s.size() <= 1) return s;
    
    // 最长回文子串的长度（至少是1）
    int maxLen = 1;
    // 最长回文子串的开始索引
    int begin = 0;
    vector<vector<bool>> dp(s.size(), vector<bool>(s.size(), false));
    // 从下到上（i由大到小）
    for (int i = s.size() - 1; i >= 0; i--) {
        // 从左到右（j由小到大）
        for (int j = i; j < s.size(); j++) {
            // s[i, j] 的长度
            int len = j - i + 1;
            dp[i][j] = (s[i] == s[j]) && (len <= 2 || dp[i + 1][j - 1]);
            if (dp[i][j] && len > maxLen) {
                maxLen = len;
                begin = i;
            }
        }
    }
    return s.substr(begin, maxLen);
}

/**
 扩展中心法
 */
#pragma mark - 扩展中心

int palindromeLength(string s, int l, int r) {
    while (l >= 0 && r < s.size() && s[l] == s[r]) {
        l--;
        r++;
    }
    return r - l - 1;
}

// 中心扩展法 T = O(n ^ 2) S = O(1)
string longestPalindrome2(string s) {
    if (s.size() <= 1) return s;
    // 最长回文子串的长度（至少是1）
    int maxLen = 1;
    // 最长回文子串的开始索引
    int begin = 0;
    for (int i = s.size() - 2; i >= 1; i--) {
        // 以字符为中心向左右扩展
        int len1 = palindromeLength(s, i - 1, i + 1);
        // 以字符右边的间隙为中心向左右扩展
        int len2 = palindromeLength(s, i, i + 1);
        len1 = max(len1, len2);
        if (len1 > maxLen) {
            maxLen = len1;
            begin = i - ((maxLen - 1) >> 1);
        }
    }
    // 以 0 号字符右边的间隙为中心的最长回文子串长度是2
    if (s[0] == s[1] & maxLen < 2) {
        // s[0, 1] 就是最长的回文子串
        begin = 0;
        maxLen = 2;
    }
    
    return s.substr(begin, maxLen);
}

#pragma mark - 扩展中心法2
string longestPalindrome3(string s) {
    if (s.size() <= 1) return s;
    // 最长回文子串的长度（至少是1）
    int maxLen = 1;
    // 最长回文子串的开始索引
    int begin = 0;
    
    int i = 0;
    while (i < s.size()) {
        int l = i - 1;
        // 找到右边第一个不等于 s[i] 的位置
        int r = i;
        while (++r < s.size() && s[r] == s[i]);
        // r 会成为新的 i
        i = r;
        
        // 从 l 向左，从 r 向右扩展
        while (l >= 0 && r < s.size() && s[l] == s[r]) {
            l--;
            r++;
        }
        
        // 扩展结束后，s[l + 1, r)就是刚才找到的最大回文子串
        // ++l后，l就是刚才找到的最大回文子串的开始索引
        int len = r - ++l;
        if (len > maxLen) {
            maxLen = len;
            begin = l;
        }
    }
    
    return s.substr(begin, maxLen);
}

#pragma mark - Manacher 算法（马拉车算法）
string preprocess(string oldStr) {
    string str;
    str += '^#';
    for (int i = 0; i < oldStr.size(); i++) {
        str += oldStr[i];
        str += '#';
    }
    str += '$';
    return str;
}

string longestPalindrome4(string s) {
    if (s.size() <= 1) return s;
    
    // 预处理
    string str = preprocess(s);
    
    int len = str.size();
    int *m = new int[len];
    memset(m, 0, len * sizeof(int));
    int c = 1, r = 1, lastIdx = len - 2;
    int maxLen = 0, idx = 0;
    for (int i = 2; i < lastIdx; i++) {
        if (r > i) {
            int li = (c << 1) - i;
            m[i] = (i + m[li] <= r) ? m[li] : (r - i);
        }
        
        // 以 i 为中心，向左右扩展
        while (str[i + m[i] + 1] == str[i - m[i] - 1]) {
            m[i]++;
        }
        
        // 更新 c、r
        if (i + m[i] > r) {
            c = i;
            r = i + m[i];
        }
        
        // 找出更大的回文子串
        if (m[i] > maxLen) {
            maxLen = m[i];
            idx = i;
        }
    }
    
    int begin = (idx - maxLen) >> 1;
    return s.substr(begin, maxLen);
}

