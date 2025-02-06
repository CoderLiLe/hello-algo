package 字符串;

import tools.Asserts;

import java.util.*;

/**
 描述
 输入一个只包含小写英文字母和数字的字符串，按照不同字符统计个数由多到少输出统计结果，如果统计的个数相同，则按照ASCII码由小到大排序输出。
 数据范围：字符串长度满足 1≤len(str)≤1000

 输入描述：
 一个只包含小写英文字母和数字的字符串。

 输出描述：
 一个字符串，为不同字母出现次数的降序表示。若出现次数相同，则按ASCII码的升序输出。
 */
public class HJ102字符统计 {
    public static void main(String[] args)
    {
        String str = "aaddccdc";
        TreeMap<Character, Integer> map = new TreeMap<>();
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            map.put(c, map.getOrDefault(c, 0) + 1);
        }
        List<Map.Entry> list = new ArrayList<>(map.entrySet());
        Collections.sort(list, (o1, o2) -> (int) o2.getValue() - (int) o1.getValue());
        StringBuilder sb = new StringBuilder();
        for (Map.Entry entry : list) {
            sb.append(entry.getKey());
        }
        Asserts.test(sb.toString().equals("cda"));
    }
}
