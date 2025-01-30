package 哈希;

import tools.Asserts;

import java.util.HashMap;
import java.util.Map;

public class _290单词规律 {
    public boolean wordPattern(String pattern, String s) {
        char[] chars = pattern.toCharArray();
        String[] strs = s.split(" ");
        if (chars.length != strs.length) {
            return false;
        }
        Map<Character, String> c2Str = new HashMap<>();
        Map<String, Character> str2c = new HashMap<>();
        for (int i = 0; i < chars.length; i++) {
            char c = chars[i];
            String str = strs[i];
            if (!c2Str.containsKey(c)) {
                c2Str.put(c, str);
            }
            if (!str2c.containsKey(str)) {
                str2c.put(str, c);
            }
            if (!c2Str.get(c).equals(str) || !str2c.get(str).equals(c)) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        _290单词规律 obj = new _290单词规律();
        Asserts.test(obj.wordPattern("abba", "dog cat cat dog"));
        Asserts.test(!obj.wordPattern("abba", "dog cat cat fish"));
        Asserts.test(!obj.wordPattern("aaaa", "dog cat cat dog"));
        Asserts.test(!obj.wordPattern("abba", "dog dog dog dog"));
    }
}
