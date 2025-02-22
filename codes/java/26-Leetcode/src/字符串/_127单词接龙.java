package 字符串;

import tools.Asserts;

import java.util.*;

public class _127单词接龙 {
    /**
     * BFS
     * 计算转换单词 beginWord 到 endWord 所需的最少转换次数
     * 转换规则是每次只能改变一个字母，且转换过程中的每个临时单词都必须在 wordList 中
     *
     * @param beginWord 起始单词
     * @param endWord 目标单词
     * @param wordList 单词列表，包含所有可能的临时单词
     * @return 如果可以转换，返回最少转换次数；如果无法转换，返回 0
     */
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        // 创建一个集合，用于快速查找 wordList 中的单词
        Set<String> wordSet = new HashSet<>(wordList);
        // 如果 wordList 为空或者不包含 endWord，直接返回 0，因为无法完成转换
        if (wordSet.size() == 0 || !wordSet.contains(endWord)) {
            return 0;
        }
        // BFS队列，用于存储待处理的单词
        Queue<String> queue = new LinkedList<>();
        queue.offer(beginWord);
        // 记录单词对应路径长度，初始时 beginWord 的路径长度为 1
        Map<String, Integer> map = new HashMap<>();
        map.put(beginWord, 1);

        // 当队列不为空时，继续进行广度优先搜索
        while (!queue.isEmpty()) {
            // 取出队头单词
            String word = queue.poll();
            // 获取到该单词的路径长度
            int path = map.get(word);
            // 遍历单词的每个字符
            for (int i = 0; i < word.length(); i++) {
                char[] chars = word.toCharArray();
                // 尝试将当前字符替换为 a 到 z 中的每个字符
                for (char k = 'a'; k <= 'z'; k++) {
                    chars[i] = k;
                    String newWord = String.valueOf(chars);
                    // 如果新单词等于 endWord，说明找到了转换路径，返回当前路径长度加 1
                    if (newWord.equals(endWord)) {
                        return path + 1;
                    }

                    // 如果新单词在 wordSet 中，但是没有访问过，则加入队列，并更新到达该状态的步数
                    if (wordSet.contains(newWord) && !map.containsKey(newWord)) {
                        map.put(newWord, path + 1);
                        queue.offer(newWord);
                    }
                }
            }
        }
        // 如果无法转换到 endWord，返回 0
        return 0;
    }

    /**
     * 双向 BFS
     *
     * 「双向 BFS」的基本实现思路如下：
     * （1）创建「两个队列」分别用于两个方向的搜索；
     * （2）创建「两个哈希表」用于「解决相同节点重复搜索」和「记录转换次数」；
     * （3）为了尽可能让两个搜索方向“平均”，每次从队列中取值进行扩展时，先判断哪个队列容量较少；
     * （4）如果在搜索过程中「搜索到对方搜索过的节点」，说明找到了最短路径。
     *
     * 时间复杂度：令wordList长度为n，beginWord字符串长度为m。由于所有的搜索结果必须都在wordList出现过，因此算上起点最多有n+1节点，
     * 最坏情况下，所有节点都联通，搜索完整张图复杂度为O(n^2)；从beginWord出发进行字符替换，替换时进行逐字符检查，复杂度为O(m)。整体复杂度为O(m ∗ n^2)
     * 空间复杂度：同等空间大小。O(m ∗ n^2)
     *
     * 【小结】
     * 这本质其实是一个「所有边权均为1」最短路问题：将beginWord和所有在wordList出现过的字符串看做是一个点。
     * 每一次转换操作看作产生边权为1的边。问题求以beginWord为源点，以endWord为汇点的最短路径。
     */
    public int ladderLength2(String beginWord, String endWord, List<String> wordList) {
        // 创建一个哈希集合，用于存储 wordList 中的所有单词，便于后续快速查找
        Set<String> set = new HashSet<>();
        for (String word : wordList) {
            set.add(word);
        }

        // 如果 endWord 不在 wordList 中，说明无法通过变换得到 endWord，直接返回 0
        if (!set.contains(endWord)) {
            return 0;
        }

        // 调用双向 BFS 辅助函数，尝试找到 beginWord 到 endWord 的最短转换路径
        int res = bfs(beginWord, endWord, set);
        // 如果 res 为 -1，说明没有找到路径，返回 0；否则返回 res + 1，因为 res 代表转换次数，而题目要求返回转换路径的长度
        return res == -1 ? 0 : res + 1;
    }

    /**
     * 使用广度优先搜索（BFS）寻找从 beginWord 到 endWord 的最短转换序列长度
     * 每次转换只能改变一个字母，且转换过程中的单词必须在 set 中
     *
     * @param beginWord 转换序列的起始单词
     * @param endWord 转换序列的目标单词
     * @param set 包含所有可能转换的单词集合
     * @return 如果存在这样的转换序列，则返回最短转换序列的长度；如果不存在，则返回 -1
     */
    private int bfs(String beginWord, String endWord, Set<String> set) {
        // beginQueue 代表从起点 beginWord 开始搜索（正向）
        // endQueue 代表从结尾 endWord 开始搜索（反向）
        Deque<String> beginQueue = new ArrayDeque<>();
        Deque<String> endQueue = new ArrayDeque<>();

        // beginMap 代表从起点 beginWord 开始搜索（正向）出现的单词是经过多少次转换而来
        // endMap 代表从结尾 endWord 开始搜索（反向）出现的单词是经过多少次转换而来
        Map<String, Integer> beginMap = new HashMap<>();
        Map<String, Integer> endMap = new HashMap<>();

        beginQueue.add(beginWord);
        beginMap.put(beginWord, 0);
        endQueue.add(endWord);
        endMap.put(endWord, 0);

        /*
         * 只有两个队列都不空，才有必要继续往下搜索
         * 如果其中一个队列空了，说明从某个方向搜到底都搜不到该方向的目标节点
         * e.g.
         * 例如，如果 beginQueue 为空了，说明从 beginWord 搜索到底都搜索不到 endWord，反向搜索也没必要进行了
         */
        while (!beginQueue.isEmpty() && !endQueue.isEmpty()) {
            int count = -1;
            // 选择一个较小的队列进行扩展，以优化搜索性能
            if (beginQueue.size() <= endQueue.size()) {
                count = update(beginQueue, beginMap, endMap, set);
            } else {
                count = update(endQueue, endMap, beginMap, set);
            }

            // 如果 count 不为 -1，说明找到了转换序列，返回序列长度
            if (-1 != count) {
                return count;
            }
        }

        // 如果没有找到转换序列，返回 -1
        return -1;
    }

    /**
     * 更新队列，并返回是否找到目标节点
     * @param queue 双端队列，用于存储待处理的字符串
     * @param beginMap 从起始字符串开始的映射，键为字符串，值为从起始字符串到该字符串的步数
     * @param endMap 从目标字符串开始的映射，键为字符串，值为从目标字符串到该字符串的步数
     * @param set 存储所有有效字符串的集合
     * @return 如果找到目标节点，返回从起始字符串到目标字符串的步数；否则返回-1
     */
    private int update(Deque<String> queue, Map<String, Integer> beginMap, Map<String, Integer> endMap, Set<String> set) {
        int size = queue.size();
        while (size-- > 0) {
            String str = queue.pollFirst();
            int len = str.length();

            for (int i = 0; i < len; i++) {
                // 枚举将 i 替换成那个小写字母
                for (int j = 0; j < 26; j++) {
                    String sub = str.substring(0, i) + (char)('a' + j) + str.substring(i + 1);
                    if (set.contains(sub)) {
                        // 如果该字符串在「当前方向」被记录过（拓展过），跳过即可
                        if (beginMap.containsKey(sub) && beginMap.get(sub) <= beginMap.get(str) + 1) {
                            continue;
                        }

                        // 如果该字符串在「另一方向」出现过，说明找到了联通两个方向的最短路
                        if (endMap.containsKey(sub)) {
                            return beginMap.get(str) + 1 + endMap.get(sub);
                        } else {
                            // 否则加入 queue 队列
                            queue.addLast(sub);
                            beginMap.put(sub, beginMap.get(str) + 1);
                        }
                    }
                }
            }
        }

        return -1;
    }

    public static void main(String[] args) {
        _127单词接龙 obj = new _127单词接龙();

        String beginWord = "hit";
        String endWord = "cog";
        String[] wordList = {"hot","dot","dog","lot","log","cog"};
        int res = 5;

        Asserts.test(obj.ladderLength(beginWord, endWord, Arrays.asList(wordList)) == res);
        Asserts.test(obj.ladderLength2(beginWord, endWord, Arrays.asList(wordList)) == res);
    }
}
