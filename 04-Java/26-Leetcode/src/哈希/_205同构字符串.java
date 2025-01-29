package 哈希;

import tools.Asserts;

import java.util.HashMap;
import java.util.Map;

public class _205同构字符串 {
    /**
     * 判断两个字符串是否为同构字符串
     * 同构字符串的定义：两个字符串中的每个字符可以被替换为另一个字符串中的对应字符，以保持字符串的结构相同
     * 例如，"egg"和"add"是同构的，"paper"和"title"也是同构的，而"foo"和"bar"不是同构的
     *
     * @param s 第一个字符串
     * @param t 第二个字符串
     * @return 如果两个字符串是同构的，则返回true；否则返回false
     */
    public boolean isIsomorphic(String s, String t) {
        // 创建两个哈希表，分别用于存储s到t和t到s的字符映射
        Map<Character, Character> s2t = new HashMap<>();
        Map<Character, Character> t2s = new HashMap<>();
        // 获取字符串的长度
        int len = s.length();
        // 遍历字符串中的每个字符
        for (int i = 0; i < len; ++i) {
            // 获取当前字符
            char x = s.charAt(i), y = t.charAt(i);
            if (!s2t.containsKey(x)) {
                s2t.put(x, y);
            }
            if (!t2s.containsKey(y)) {
                t2s.put(y, x);
            }
            // 检查当前字符的映射是否冲突
            if (s2t.get(x) != y || t2s.get(y) != x) {
                // 如果存在映射冲突，则两个字符串不是同构的，返回false
                return false;
            }
        }
        // 如果所有字符都没有映射冲突，则两个字符串是同构的，返回true
        return true;
    }

    public static void main(String[] args) {
        _205同构字符串 obj = new _205同构字符串();
        Asserts.test(obj.isIsomorphic("egg", "add"));
        Asserts.test(!obj.isIsomorphic("foo", "bar"));
        Asserts.test(!obj.isIsomorphic("paper", "title"));
    }
}
