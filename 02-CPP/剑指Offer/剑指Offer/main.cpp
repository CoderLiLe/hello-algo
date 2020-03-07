//
//  main.cpp
//  剑指Offer
//
//  Created by LiLe on 2020/2/22.
//  Copyright © 2020 lile. All rights reserved.
//

/**
 
 知识迁移能力（53 ～ 59）
    根据已掌握的知识、技术，能够快速学习、理解新的技术并应用到实际工作中。
    由简单问题的思路引出复杂类似问题的解法。【举一反三】
 
 抽象建模能力（60 ～ 63）
    1> 选择合适的数据结构来表述问题
    2> 分析模型中的内在规律
 
 发散思维能力（64 ～ 65）
    思维活动的多向性和变通性，思考问题时注重运用多思路、多方案、多途径解决问题。
 对于同一个问题，从不同的方向、侧面和层次，采用探索、转换、迁移、组合和分解等方法，
 提出多种创新的解法。
 
 */
#include <iostream>

/**
在 C++ 中，有两种类型的字符串表示形式：
1 C风格字符串
  C风格字符串实际上是使用 null 字符 '\0'终止的一维字符数组。
2 C++ 中引入的 string 类
  2.1 string ：是C++中的一个类，位于名称空间 std 中，相对 char * 的优势是内容可以动态拓展
    转化：
    string = char *
    string = const char *
    string = char
  2.2 char * ：指向字符串的指针，可以让它指向一串常量字符串
    转化：
    const char * = string.c_str()
    char * = <const_cast><char *>(const char *)
    char * = char
  2.3 const char * ：指向静态字符的指针，指向的是一个 const char 类型，不能通过当前的指针对字符串中的内容作出修改
    区别：char * const ：指向字符的静态指针
    转化：
    const char * = string.c_str()
    const char * = char *
    const char * = char
  2.4 char[] ：与 char * 有很多共同点，代表字符数组，可以对应一个字符串
    区别：char *是变量，char[] 是常量；char[] 名字代表数组首元素的地址，是常量
    转化：
    for (int i = 0; i < string.length(); i++) {
        char[i] = string[i]
    }
    strncpy_s(char, char *, n)
    strncpy_s(char, const char *, n)
 
 类型转化总结：
 1. 变成 string，直接赋值。
 2. char[] 变成别的，直接赋值。
 3. char * 变 const char * 容易，const char * 变 char * 麻烦。
 4. string 变 char * 要通过 const char * 中转。
 5. 变成 char[]。string 一个一个赋值，char *、const char * 通过 strncpy_s()
*/
void testString() {
    // 由于在数组的末尾存储了空字符，所以字符数组的大小比单词"Hello"的字符数多一个
    char greeting[6] = {'H', 'e', 'l', 'l', 'o', '\0'};
    // 其实不需要把 null 字符放在字符串常量的末尾。C++编译器会在初始化数组时，自动
    // 将'\0'放在字符串的末尾。所以也可以利用下面的形式进行初始化：
    char greeting2[] = "Hello";
}

int main(int argc, const char * argv[]) {
    // insert code here...
    std::cout << "Hello, World!\n";
    return 0;
}
