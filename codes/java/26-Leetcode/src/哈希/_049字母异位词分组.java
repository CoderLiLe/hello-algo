package 哈希;

import tools.Times;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 给你一个字符串数组，请你将 字母异位词 组合在一起。可以按任意顺序返回结果列表。
 *
 * 字母异位词 是由重新排列源单词的所有字母得到的一个新单词。
 */
public class _049字母异位词分组 {
    /**
     * 方法一：排序
     * 字母相同，但排列不同的字符串，排序后都一定是相同的。因为每种字母的个数都是相同的，那么排序后的字符串就一定是相同的。
     *
     * 时间复杂度：O(nklogk)，其中 n 是 strs 中的字符串的数量，k 是 strs 中的字符串的的最大长度。需要遍历 n 个字符串，
     * 对于每个字符串，需要 O(klogk) 的时间进行排序以及 O(1) 的时间更新哈希表，因此总时间复杂度是 O(nklogk)。
     *
     * 空间复杂度：O(nk)，其中 n 是 strs 中的字符串的数量，k 是 strs 中的字符串的的最大长度。需要用哈希表存储全部字符串。
     */
    public List<List<String>> groupAnagrams(String[] strs) {
        List<List<String>> res = new ArrayList<>();
        if (strs == null || strs.length == 0) {
            return res;
        }

        Map<String, List<String>> map = new HashMap<>();
        for (String str : strs) {
            char[] chars = str.toCharArray();
            Arrays.sort(chars);
            String key = new String(chars);
            map.putIfAbsent(key, new ArrayList<>());
            map.get(key).add(str);
       }

        for (List<String> list : map.values()) {
            res.add(list);
        }

        return res;
    }

    /**
     * 方法一：排序
     * 字母相同，但排列不同的字符串，排序后都一定是相同的。因为每种字母的个数都是相同的，那么排序后的字符串就一定是相同的。
     * 这里可以利用 stream 的 groupingBy 算子实现直接返回结果
     */
    public List<List<String>> groupAnagrams2(String[] strs) {
        List<List<String>> res = new ArrayList<>();
        if (strs == null || strs.length == 0) {
            return res;
        }

        // 注意 groupingBy 算子计算完以后，返回的是一个 Map<String, List<String>>，map 的键是每种排序后的字符串，
        // 值是聚合的原始字符串，我们只关心值，所以我们最后 new ArrayList<>(map.values())
        return new ArrayList<>(Arrays.stream(strs).collect(Collectors.groupingBy(str -> {
            char[] chars = str.toCharArray();
            // 返回 str 排序后的结果。
            // 按排序后的结果来grouping by，算子类似于 sql 里的 group by。
            Arrays.sort(chars);
            return new String(chars);
        })).values());
    }

    public static void main(String[] args) {
        _049字母异位词分组 obj = new _049字母异位词分组();
        Times.test("方法一：排序", () -> {
            List<List<String>> result = obj.groupAnagrams(new String[]{"eat", "tea", "tan", "ate", "nat", "bat"});
            System.out.println(result.toString());
        });

        Times.test("方法一：排序,利用 stream 的 groupingBy", () -> {
            List<List<String>> result2 = obj.groupAnagrams2(new String[]{"eat", "tea", "tan", "ate", "nat", "bat"});
            System.out.println(result2.toString());
        });

    }
}
