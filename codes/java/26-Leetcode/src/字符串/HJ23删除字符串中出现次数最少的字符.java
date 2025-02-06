package 字符串;

import tools.Asserts;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * 描述
 * 对于给定的仅由小写字母构成的字符串，删除字符串中出现次数最少的字符。输出删除后的字符串，字符串中其它字符保持原来的顺序。
 * 特别地，若有多个字符出现的次数都最少，则把这些字符都删除。
 * 输入描述：
 * 在一行上输入一个长度为 1 ≦ length(s) ≦ 20 ，仅由小写字母构成的字符串 s ，代表待处理的字符串。
 * 输出描述：
 * 在一行上输出一个字符串，代表删除后的答案。保证这个字符串至少包含一个字符。
 */
public class HJ23删除字符串中出现次数最少的字符 {
    public static void main(String[] args) {
        Asserts.test(test1("aabcddd").equals("aaddd"));
        Asserts.test(test1("assssa").equals("ssss"));
    }

    private static String test1(String str) {
        Map<Character, Integer> map = new HashMap<>();
        char[] chars = str.toCharArray();
        for (char c : chars) {
            Integer num = map.getOrDefault(c, 0) + 1;
            map.put(c, num);
        }

        Integer min = map.values().stream().min(Integer::compareTo).get();

        for (Character c : map.keySet()) {
            if (map.get(c).equals(min)) {
                str = str.replaceAll(String.valueOf(c), "");
            }
        }
        return str;
    }
}
