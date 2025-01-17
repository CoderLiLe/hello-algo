package 动态规划;

import tools.Asserts;
import tools.Times;

import java.util.*;

/**
 * https://leetcode-cn.com/problems/word-break/
 *
 * 给你一个字符串 s 和一个字符串列表 wordDict 作为字典。如果可以利用字典中出现的一个或多个单词拼接出 s 则返回 true。
 *
 * 注意：不要求字典中出现的单词全部都使用，并且字典中的单词可以重复使用。
 *
 * 示例 1：
 * 输入: s = "leetcode", wordDict = ["leet", "code"]
 * 输出: true
 * 解释: 返回 true 因为 "leetcode" 可以由 "leet" 和 "code" 拼接成。
 *
 * 示例 2：
 * 输入: s = "applepenapple", wordDict = ["apple", "pen"]
 * 输出: true
 * 解释: 返回 true 因为 "applepenapple" 可以由 "apple" "pen" "apple" 拼接成。
 *      注意，你可以重复使用字典中的单词。
 *
 * 示例 3：
 * 输入: s = "catsandog", wordDict = ["cats", "dog", "sand", "and", "cat"]
 * 输出: false
 *
 *
 * 提示：
 * 1 <= s.length <= 300
 * 1 <= wordDict.length <= 1000
 * 1 <= wordDict[i].length <= 20
 * s 和 wordDict[i] 仅由小写英文字母组成
 * wordDict 中的所有字符串 互不相同
 */
public class _139单词拆分 {

    /**
     * 题意
     * 把 s 划分成若干段，使得每段都在 wordDict 中。
     * 判断能否划分。
     * 注意可以不划分，如果 s 在 wordDict 中，则返回 true。
     *
     * 一、寻找子问题
     * 例如 s=leetcode，枚举最后一段的长度：
     *
     * 长为 1，即子串 e，如果它在 wordDict 中，那么问题变成：能否把 leetcod 划分成若干段，使得每段都在 wordDict 中？
     * 长为 2，即子串 de，如果它在 wordDict 中，那么问题变成：能否把 leetco 划分成若干段，使得每段都在 wordDict 中？
     * 长为 3，即子串 ode，如果它在 wordDict 中，那么问题变成：能否把 leetc 划分成若干段，使得每段都在 wordDict 中？
     * 长为 4，即子串 code，如果它在 wordDict 中，那么问题变成：能否把 leet 划分成若干段，使得每段都在 wordDict 中？
     * ……
     * 这些问题都是和原问题相似的、规模更小的子问题，可以用递归解决。
     * 注：从右往左思考，主要是为了方便把递归翻译成递推。从左往右思考也是可以的。
     *
     * 二、状态定义与状态转移方程
     * 根据上面的讨论，定义状态为 dfs(i)，表示能否把前缀 s[:i]（表示 s[0] 到 s[i−1] 这段子串）划分成若干段，使得每段都在 wordDict 中。
     *
     * 枚举 s[:i] 最后一段的长度：
     *
     * 长为 1，即子串 s[i−1:i]，如果它在 wordDict 中，那么问题变成：能否把前缀 s[:i−1] 划分成若干段，使得每段都在 wordDict 中，即 dfs(i−1)。
     * 长为 2，即子串 s[i−2:i]，如果它在 wordDict 中，那么问题变成：能否把前缀 s[:i−2] 划分成若干段，使得每段都在 wordDict 中，即 dfs(i−2)。
     * 长为 3，即子串 s[i−3:i]，如果它在 wordDict 中，那么问题变成：能否把前缀 s[:i−3] 划分成若干段，使得每段都在 wordDict 中，即 dfs(i−3)。
     * ……
     * 设 wordDict 中字符串的最长长度为 maxLen，枚举的上限不超过 maxLen，因为更长的子串必然不在 wordDict 中。
     *
     * 枚举 j=i−1,i−2,i−3,…,max(i−maxLen,0)，只要其中一个 j 满足 s[j:i] 在 wordDict 中且 dfs(j)=true，那么 dfs(i) 就是 true。
     *
     * 递归边界：dfs(0)=true。递归到空串，说明 s 成功地划分完毕。
     *
     * 递归入口：dfs(n)，也就是答案。
     *
     * 代码实现时，可以把 wordDict 列表转成一个哈希集合，便于快速判断子串是否在 wordDict 中。
     *
     * 注：本题字符串比较短，可以直接用哈希集合判断。如果字符串比较长，可以用字典树加速。
     *
     */

    /**
     * 递归搜索 + 保存递归返回值 = 记忆化搜索
     *
     * 时间复杂度：O(mL+nL^2)，其中 m 是 wordDict 的长度，L 是 wordDict 中字符串的最长长度，n 是 s 的长度。创建哈希集合需要 O(mL) 的时间。由于每个状态只
     * 会计算一次，动态规划的时间复杂度 = 状态个数 × 单个状态的计算时间。本题状态个数等于 O(n)，单个状态的计算时间为 O(L^2)（注意判断子串是否在哈希集合中需要
     * O(L) 的时间），所以记忆化搜索的时间复杂度为 O(nL^2)。
     * 空间复杂度：O(mL+n)。哈希集合需要 O(mL) 的空间。记忆化搜索需要 O(n) 的空间。
     *
     * 考虑到整个递归过程中有大量重复递归调用（递归入参相同）。由于递归函数没有副作用，同样的入参无论计算多少次，算出来的结果都是一样的，因此可以用记忆化搜索来优化：
     *
     * 如果一个状态（递归入参）是第一次遇到，那么可以在返回前，把状态及其结果记到一个 memo 数组中。
     * 如果一个状态不是第一次遇到（memo 中保存的结果不等于 memo 的初始值），那么可以直接返回 memo 中保存的结果。
     * 注意：memo 数组的初始值一定不能等于要记忆化的值！例如初始值设置为 0（表示 false），并且要记忆化的 dfs(i) 也等于 0（表示 false），那就没法判断 0 到底
     * 表示第一次遇到这个状态，还是表示之前遇到过了，从而导致记忆化失效。一般把初始值设置为 −1。
     *
     * @param s 输入的字符串
     * @param wordDict 字典，包含可分割的单词列表
     * @return 如果字符串可以被字典中的单词分割，则返回 true；否则返回 false
     */
    public boolean wordBreak(String s, List<String> wordDict) {
        // 预处理字典中的单词，找出最长的单词长度，用于后续优化递归搜索的范围
        int maxLen = 0;
        for (String word : wordDict) {
            maxLen = Math.max(maxLen, word.length());
        }

        // 使用 HashSet 存储字典中的单词，以实现快速查找
        Set<String> wordSet = new HashSet<>(wordDict);

        int sLen = s.length();
        // memo 数组用于记忆化搜索，避免重复计算同一个状态
        int[] memo = new int[sLen + 1];
        // -1 表示未计算过，0 表示 false，1 表示 true
        Arrays.fill(memo, -1);
        // 调用深度优先搜索函数，判断从字符串末尾开始向前搜索，是否能找到字典中的单词将字符串完全分割
        return dfs(sLen, maxLen, s, wordSet, memo) == 1;
    }

    /**
     * 使用深度优先搜索进行单词拆分
     *
     * @param i 当前考察的字符串的起始位置
     * @param maxLen 字典中单词的最大长度
     * @param s 输入的字符串
     * @param wordSet 字典，包含所有可能的单词
     * @param memo 记忆化数组，记录每个位置是否可以成功拆分
     * @return 如果可以从当前位置开始成功拆分，则返回1，否则返回0
     */
    private int dfs(int i, int maxLen, String s, Set<String> wordSet, int[] memo) {
        // 成功拆分
        if (i == 0) {
            return 1;
        }

        // 之前计算过，直接返回结果
        if (memo[i] != -1) {
            return memo[i];
        }

        // 从当前位置向前搜索，但不超过单词最大长度和字符串起始位置
        for (int j = i - 1; j >= Math.max(i - maxLen, 0); j--) {
            // 如果找到一个有效的单词，并且之前的字符串也可以成功拆分，则当前位置可以成功拆分
            if (wordSet.contains(s.substring(j, i)) && dfs(j, maxLen, s, wordSet, memo) == 1) {
                return memo[i] = 1;
            }
        }
        // 如果没有找到有效的拆分方法，则当前位置不能成功拆分
        return memo[i] = 0;
    }


    /**
     * 动态规划解法
     *
     * 翻译成递推,可以去掉递归中的「递」，只保留「归」的部分，即自底向上计算
     * dp[i] 的定义和 dfs(i) 的定义是一样的，都表示能否把前缀 s[:i]（表示 s[0] 到 s[i−1]）划分成若干段，使得每段都在 wordDict 中。
     *
     * 同样地，枚举 j=i−1,i−2,i−3,…,max(i−maxLen,0)，只要其中一个 j 满足 s[j:i] 在 wordDict 中且 dp[j]=true，那么 dp[i] 就是 true。
     * 初始值 dp[0]=true，翻译自递归边界 dfs(0)=true。
     * 答案为 dp[n]，翻译自递归入口 dfs(n)。
     *
     * 时间复杂度：O(mL+nL^2)，其中 m 是 wordDict 的长度，L 是 wordDict 中字符串的最长长度，n 是 s 的长度。理由同上。
     * 空间复杂度：O(mL+n)。
     *
     * @param s 输入的字符串
     * @param wordDict 字典，包含可拼接的单词列表
     * @return 如果 s 可以由 wordDict 中的单词拼接而成，返回 true；否则返回 false
     */
    public boolean wordBreak_dp(String s, List<String> wordDict) {
        // 预处理字典中的单词，找出最长的单词长度，用于后续优化递归搜索的范围
        int maxLen = 0;
        for (String word : wordDict) {
            maxLen = Math.max(maxLen, word.length());
        }

        // 使用 HashSet 存储字典中的单词，以实现快速查找
        Set<String> wordSet = new HashSet<>(wordDict);

        int sLen = s.length();
        boolean[] dp = new boolean[sLen + 1];
        dp[0] = true;
        for (int i = 1; i <= sLen; i++) {
            for (int j = i - 1; j >= Math.max(i - maxLen, 0); j--) {
                if (dp[j] && wordSet.contains(s.substring(j, i))) {
                    dp[i] = true;
                    break;
                }
            }
        }
        return dp[sLen];
    }

    public static void main(String[] args) {
        _139单词拆分 obj = new _139单词拆分();

        String s = "leetcode";
        List<String> wordDict = new ArrayList<>();
        wordDict.add("leet");
        wordDict.add("code");

        String s2= "applepenapple";
        List<String> wordDict2 = new ArrayList<>();
        wordDict2.add("apple");
        wordDict2.add("pen");

        String s3= "catsandog";
        List<String> wordDict3 = new ArrayList<>();
        wordDict3.add("cats");
        wordDict3.add("dog");
        wordDict3.add("sand");
        wordDict3.add("and");
        wordDict3.add("cat");

        Times.test("递归搜索 + 保存递归返回值 = 记忆化搜索", () -> {
            Asserts.test(obj.wordBreak(s, wordDict));
            Asserts.test(obj.wordBreak(s2, wordDict2));
            Asserts.test(!obj.wordBreak(s3, wordDict3));
        });

        Times.test("动态规划解法(翻译成递推：自底向上计算)", () -> {
            Asserts.test(obj.wordBreak_dp(s, wordDict));
            Asserts.test(obj.wordBreak_dp(s2, wordDict2));
            Asserts.test(!obj.wordBreak_dp(s3, wordDict3));
        });

    }
}
