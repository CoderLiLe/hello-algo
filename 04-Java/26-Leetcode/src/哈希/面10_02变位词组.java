package 哈希;

import java.util.*;

/**
 * 面10_02变位词组
 * 中等
 * 编写一种方法，对字符串数组进行排序，将所有变位词组合在一起。变位词是指字母相同，但排列不同的字符串。
 *
 * 注意：本题相对原题稍作修改
 *
 * 示例:
 *
 * 输入: ["eat", "tea", "tan", "ate", "nat", "bat"],
 * 输出:
 * [
 *   ["ate","eat","tea"],
 *   ["nat","tan"],
 *   ["bat"]
 * ]
 * 说明：
 *
 * 所有输入均为小写字母。
 * 不考虑答案输出的顺序。
 */

/**
 * 遍历字符串数组，以 HashMap 的键为排序后的字符串，值是字符串结果集中的索引，每个索引对应内层字符串列表，每个列表存放同类变位词集合
 */

public class 面10_02变位词组 {

    public static void main(String[] args) {
        String[] strs = {"eat", "tea", "tan", "ate", "nat", "bat"};
        List<List<String>> res = groupAnagrams(strs);
        System.out.println(res);
    }

    /**
     * T = O(nlogn)
     */
    public static List<List<String>> groupAnagrams(String[] strs) {
        List<List<String>> res = new ArrayList<>();
        Map<String, Integer> hash = new HashMap<>();
        int index = 0;
        for (String s : strs) {
            char[] cs = s.toCharArray();
            Arrays.sort(cs);
            String sortStr = new String(cs);
            if (!hash.containsKey(sortStr)) {
                hash.put(sortStr, index++);
                List<String> l = new ArrayList<>();
                l.add(s);
                res.add(l);
            } else {
                res.get(hash.get(sortStr)).add(s);
            }
        }

        return res;
    }
}
