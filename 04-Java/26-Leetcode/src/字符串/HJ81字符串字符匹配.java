package 字符串;

import java.util.HashSet;
import java.util.Set;

/**
 描述
 判断短字符串S中的所有字符是否在长字符串T中全部出现。
 请注意本题有多组样例输入。
 数据范围: 1 ≤ len(S), len(T) ≤ 200
 进阶：时间复杂度： O(n) ，空间复杂度：O(n)

 输入描述：
 输入两个字符串。第一个为短字符串，第二个为长字符串。两个字符串均由小写字母组成。

 输出描述：
 如果短字符串的所有字符均在长字符串中出现过，则输出字符串"true"。否则输出字符串"false"。

 示例1
 输入：
 bc
 abc

 输出：
 true

 说明：
 其中abc含有bc，输出"true"
 */
public class HJ81字符串字符匹配 {
    public static void main(String[] args)
    {
        String s = "bc";
        String t = "abc";
        System.out.println(isMatch(s, t));

        String s1 = "okekwgktczxeposiirjmquypjbohexyinlktaunjyhkjw";
        String t1 = "yjynxoawaobtbpyxhbqpzdqjehydzfistxtlzrqzdotglpcunfmpvaparnxkmsybwo";
        System.out.println(isMatch(s1, t1));
    }

    private static boolean isMatch(String s, String t)
    {
        Set<Character> set = new HashSet<>();
        for (char ch : t.toCharArray()) {
            set.add(ch);
        }
        for (char ch : s.toCharArray()) {
            if (!set.contains(ch)) {
                return false;
            }
        }
        return true;
    }

}
