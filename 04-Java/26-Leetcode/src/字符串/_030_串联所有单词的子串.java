package 字符串;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * 30 串联所有单词的子串
 *
 * 给定一个字符串 s 和一些长度相同的单词 words。找出 s 中恰好可以由 words 中所有单词串联形成的子串的起始位置。
 *
 * 注意子串要与 words 中的单词完全匹配，中间不能有其他字符，但不需要考虑 words 中单词串联的顺序。
 *
 * 示例 1：
 *
 * 输入：
 *   s = "barfoothefoobarman",
 *   words = ["foo","bar"]
 * 输出：[0,9]
 * 解释：
 * 从索引 0 和 9 开始的子串分别是 "barfoo" 和 "foobar" 。
 * 输出的顺序不重要, [9,0] 也是有效答案。
 *
 *
 * 示例 2：
 *
 * 输入：
 *   s = "wordgoodgoodgoodbestword",
 *   words = ["word","good","best","word"]
 * 输出：[]
 */

public class _030_串联所有单词的子串 {
    public static void main(String[] args) {
        String s1 = "barfoothefoobarman";
        String[] words1 = {"foo","bar"};
        System.out.println(findSubstring(s1, words1));
        System.out.println(findSubstring2(s1, words1));

        String s2 = "wordgoodgoodgoodbestword";
        String[] words2 = {"word","good","best","word"};
        System.out.println(findSubstring(s2, words2));
        System.out.println(findSubstring2(s2, words2));
    }

    /**
     * T = O(s.length() * words.length())
     * S = O(words.length())
     */
    public static List<Integer> findSubstring(String s, String[] words) {
        List<Integer> res = new ArrayList<>();
        int wordNum = words.length;
        if (wordNum == 0) {
            return res;
        }

        int wordLen = words[0].length();
        // HashMap1 存所有单词
        HashMap<String, Integer> allWords = new HashMap<>();
        for (String w : words) {
            allWords.put(w, allWords.getOrDefault(w, 0) + 1);
        }

        // 遍历所有子串
        for (int i = 0; i < s.length() - wordNum * wordLen + 1; i++) {
            // HashMap2 存当前扫描的字符串含有的单词
            HashMap<String, Integer> hasWords = new HashMap<>();
            int num = 0;
            // 判断该子串是否符合
            while (num < wordNum) {
                String word = s.substring(i + num * wordLen, i + (num + 1) * wordLen);
                // 判断该单词在 HashMap1 中
                if (allWords.containsKey(word)) {
                    hasWords.put(word, hasWords.getOrDefault(word, 0) + 1);
                    // 判断当前单词的 value 和 HashMap1 中该单词的 value
                    if (hasWords.get(word) > allWords.get(word)) {
                        break;
                    }
                } else {
                    break;
                }
                num++;
            }

            // 判断是不是所有的单词都符合条件
            if (num == wordNum) {
                res.add(i);
            }
        }
        return res;
    }

    /**
     * 时间复杂度：算法中外层的两个for 循环的次数肯定是所有的子串，假设是 n。考虑一下，最极端的情况，每个子串的判断都进了 while 循环，wordNum 等于 m。对于解法一，因为每次都是从头判断，所以 while 循环循环了 m 次。
     * 但这里我们由于没有清空，所以每次只判断新加入的单词就可以了，只需判断一次，所以时间复杂度是 O（n）。
     *
     * 或者换一种理解方式，判断子串是否符合，本质上也就是判断每个单词符不符合，假设 s 的长度是 n，那么就会大约有 n 个子串，也就是会有 n 个单词。而对于每个单词，我们只有刚开始判断符不符合的时候访问一次，还有就是把它
     * 移除的时候访问一次，所以每个单词最多访问 2 次，所以时间复杂度是 O（n）。
     *
     * 空间复杂度：没有变化，依旧是两个 HashMap, 假设 words 里有 m 个单词，就是 O（m）。
     */
    public static List<Integer> findSubstring2(String s, String[] words) {
        List<Integer> res = new ArrayList<>();
        int wordNum = words.length;
        if (wordNum == 0) {
            return res;
        }

        int wordLen = words[0].length();
        HashMap<String, Integer> allWords = new HashMap<>();
        for (String w : words) {
            allWords.put(w, allWords.getOrDefault(w, 0) + 1);
        }

        // 将所有移动分成 wordLen 类情况
        for (int i = 0; i < wordLen; i++) {
            HashMap<String, Integer> hasWords = new HashMap<>();
            // 记录当前 hasWords 中有多少个单词
            int num = 0;
            for (int j = i; j < s.length() - wordNum * wordLen + 1; j += wordLen) {
                // 防止情况三移除后，情况一继续移除
                boolean hasRemoved = false;
                while (num < wordNum) {
                    String word = s.substring(j + num * wordLen, j + (num + 1) * wordLen);
                    if (allWords.containsKey(word)) {
                        hasWords.put(word, hasWords.getOrDefault(word, 0) + 1);
                        // 出现情况三，遇到了符合的单词，但是次数超了
                        if (hasWords.get(word) > allWords.get(word)) {
                            hasRemoved = true;
                            int removeNum = 0;
                            // 一直移除单词，直到次数符合了
                            while (hasWords.get(word) > allWords.get(word)) {
                                String firstWord = s.substring(j + removeNum * wordLen, j + (removeNum + 1) * wordLen);
                                hasWords.put(firstWord, hasWords.get(firstWord) - 1);
                                removeNum++;
                            }
                            // +1 是因为把当前单词加入到了 hasWords 中
                            num = num - removeNum + 1;
                            // 这里依旧是考虑到了最外层的 for 循环，看情况二的解释
                            j = j + (removeNum - 1) * wordLen;
                            break;
                        }
                    } else {
                        // 出现情况二，遇到了不匹配的单词，直接将 i 移动到该单词的后边（但其实这里
                        // 只是移动到了出现问题单词的地方，因为最外层有 for 循环， i 还会移动一个单词
                        // 然后刚好就移动到了单词后边）
                        hasWords.clear();
                        j = j + num * wordLen;
                        num = 0;
                        break;
                    }
                    num++;
                }

                if (num == wordNum) {
                    res.add(j);
                }

                // 出现情况一，子串完全匹配，我们将上一个子串的第一个单词从 HashMap2 中移除
                if (num > 0 && !hasRemoved) {
                    String firstWord = s.substring(j, j + wordLen);
                    hasWords.put(firstWord, hasWords.get(firstWord) - 1);
                    num -= 1;
                }
            }
        }
        return res;
    }
}
