package TwoPoints;

import tools.Asserts;

/**
 * 给你字符串 s 和整数 k 。
 *
 * 请返回字符串 s 中长度为 k 的单个子字符串中可能包含的最大元音字母数。
 *
 * 英文中的 元音字母 为（a, e, i, o, u）。
 */
public class _1456定长子串中元音的最大数目 {

    /**
     * 暴力法：遍历字符串，计算每个滑动窗口中元音字母的数量，并更新最大值。
     * 计算字符串中最多的连续元音字母的数量
     *
     * @param s 输入的字符串
     * @param k 滑动窗口的大小，用于检查连续的子字符串
     * @return 返回在任何连续k个字符中元音字母的最大数量
     */
    public int maxVowels(String s, int k) {
        // 用于存储元音字母的最大数量
        int res = 0;
        // 用于临时存储当前滑动窗口中元音字母的数量
        int vowel = 0;

        // 遍历字符串，但不包括最后k个字符，因为我们将以每个字符作为窗口的起始点
        for (int i = 0; i < s.length(); i++) {
            // 遍历当前滑动窗口中的字符，检查是否为元音
            for (int j = i; j < i + k && j < s.length(); j++) {
                // 如果当前字符是元音，则增加计数
                if (isVowel(s.charAt(j))) {
                    vowel++;
                }
            }

            // 更新元音字母的最大数量
            res = Math.max(res, vowel);
            // 重置临时变量，为下一个滑动窗口做准备
            vowel = 0;
        }
        // 返回元音字母的最大数量
        return res;
    }

    /**
     * 定长滑窗
     *
     * @param s 输入的字符串
     * @param k 滑动窗口的大小，用于检查连续的子字符串
     * @return 返回在任何连续k个字符中元音字母的最大数量
     */
    public int maxVowels2(String s, int k) {
        // 用于存储元音字母的最大数量
        int res = 0;
        // 用于临时存储当前滑动窗口中元音字母的数量
        int vowel = 0;

        // 遍历字符串，但不包括最后k个字符，因为我们将以每个字符作为窗口的起始点
        for (int i = 0; i < s.length(); i++) {
            // 1. 进入窗口
            // 如果当前字符是元音，则增加计数
            if (isVowel(s.charAt(i))) {
                vowel++;
            }
            // 窗口大小不满足k
            if (i < k - 1) {
                continue;
            }

            // 2. 更新结果
            res = Math.max(res, vowel);

            // 3. 离开窗口
            char out = s.charAt(i - k + 1);
            if (isVowel(out)) {
                vowel--;
            }
        }

        return res;
    }


    /**
     * 判断字符是否为元音字母
     *
     * @param c 需要判断的字符
     * @return 如果字符是元音字母，则返回true；否则返回false
     */
    private boolean isVowel(char c) {
        // 将字符转换为小写，以实现大小写不敏感的比较
        c = Character.toLowerCase(c);
        // 英语元音字母包括'a', 'e', 'i', 'o', 'u'
        return c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u';
    }

    public static void main(String[] args) {
        _1456定长子串中元音的最大数目 obj = new _1456定长子串中元音的最大数目();
        Asserts.test(3 == obj.maxVowels("abciiidef", 3));
        Asserts.test(3 == obj.maxVowels2("abciiidef", 3));

        Asserts.test(1 == obj.maxVowels("a", 1));
        Asserts.test(1 == obj.maxVowels2("a", 1));
    }
}
