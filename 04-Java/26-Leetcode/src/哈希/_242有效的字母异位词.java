package 哈希;

import tools.Asserts;

import java.util.Arrays;

public class _242有效的字母异位词 {
    /**
     * 时间复杂度：O(nlogn)
     * 空间复杂度：O(logn)
     *
     * @param s
     * @param t
     * @return
     */
    public boolean isAnagram(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }
        char[] strs1 = s.toCharArray();
        char[] strs2 = t.toCharArray();
        Arrays.sort(strs1);
        Arrays.sort(strs2);
        return Arrays.equals(strs1, strs2);
    }

    /**
     * 判断两个字符串是否为字母异位词
     * 使用哈希表记录每个字符的出现次数
     * 时间复杂度：O(n)，其中 n 为 s 的长度
     * 空间复杂度：O(S)，其中 S 为字符集大小，此处 S=26
     *
     * @param s 第一个字符串
     * @param t 第二个字符串
     * @return 如果 s 和 t 是字母异位词，返回 true；否则返回 false
     */
    public boolean isAnagram2(String s, String t) {
        // 如果两个字符串长度不同，则它们不可能是字母异位词
        if (s.length() != t.length())
            return false;
        // 创建一个数组来记录每个字母的出现次数
        int[] alpha = new int[26];
        // 遍历字符串 s 和 t，更新数组 alpha
        for (int i = 0; i < s.length(); i++) {
            alpha[s.charAt(i) - 'a']++;
            alpha[t.charAt(i) - 'a']--;
        }
        // 遍历数组 alpha，如果某个元素不为 0，则 s 和 t 不是字母异位词
        for (int i = 0; i < 26; i++)
            if (alpha[i] != 0)
                return false;
        // 如果数组 alpha 所有元素都为 0，则 s 和 t 是字母异位词
        return true;
    }

    public static void main(String[] args) {
        _242有效的字母异位词 obj = new _242有效的字母异位词();
        Asserts.test(obj.isAnagram("anagram", "nagaram"));
        Asserts.test(!obj.isAnagram("rat", "car"));
        Asserts.test(!obj.isAnagram("ab", "a"));
        Asserts.test(!obj.isAnagram("aacc", "ccac"));
        Asserts.test(!obj.isAnagram("aacc", "cca"));
    }
}
