package 字符串;

import tools.Asserts;

import java.util.*;

public class _433最小基因变化 {
    private static char[] items = new char[]{'A', 'C', 'G', 'T'};
    /**
     * BFS + 哈希表
     * 算法流程：
     * 容易想到使用BFS进行求解，并使用「哈希表」记录到达某个状态所消耗的步数（同时为了快速判断某个状态是否合法，我们使用Set结构对bank[i]进行转存）。
     *
     * 令S=start、T=end，将每个基因序列视为「状态」
     *
     * 起始将S加入队列，并更新到达S所使用的步数为0，然后进行常规的BFS过程：每次取出队头元素，尝试替换当前状态的某一位，来得到新的状态（限定新状态必须合法，
     * 即必须出现在Set中），如果新状态合法并且没有在记录步数的哈希表中出现过，则将新状态入队并更新得到新状态所用步数，否则丢弃新状态。
     *
     * 重复上述过程直到找到T（返回具体步数） 或者队列为空（返回−1）。
     *
     * 时间复杂度：令n为bank的数组长度（合法状态数），将bank存入Set结构复杂度为O(n)，每个状态经过一步操作最多拓展出C=32个新基因（共有8个位置，每个位置有4个选择），BFS过程复杂度为O(C∗n)。整体复杂度为O(C∗n)
     * 空间复杂度：O(n), 哈希表存储了所有合法状态，因此哈希表大小为O(n)。
     */
    public int minMutation(String startGene, String endGene, String[] bank) {
        // 使用HashSet存储所有合法的基因序列，以便快速判断基因序列是否合法
        Set<String> set = new HashSet<>();
        for (String s : bank) {
            set.add(s);
        }

        // 使用双端队列作为辅助数据结构进行BFS
        Deque<String> queue = new ArrayDeque<>();
        // 使用HashMap记录到达某个状态所消耗的最小步数
        Map<String, Integer> map = new HashMap<>();
        // 将起始基因序列加入队列，并设置到达起始状态所使用的步数为0
        queue.offer(startGene);
        map.put(startGene, 0);

        // 开始进行BFS遍历
        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size-- > 0) {
                // 取出队头元素
                String str = queue.pollFirst();
                char[] cs = str.toCharArray();
                int step = map.get(str);
                // 尝试替换当前基因序列的某一位，以生成新的基因序列
                for (int i = 0; i < 8; i++) {
                    for (char c : items) {
                        if (cs[i] == c) {
                            continue;
                        }
                        char[] clone = cs.clone();
                        clone[i] = c;
                        String sub = String.valueOf(clone);
                        // 如果新生成的基因序列不合法，则跳过
                        if (!set.contains(sub)) {
                            continue;
                        }
                        // 如果新生成的基因序列已经在map中出现过，则跳过
                        if (map.containsKey(sub)) {
                            continue;
                        }
                        // 如果新生成的基因序列是目标基因序列，则返回到达该状态的步数
                        if (sub.equals(endGene)) {
                            return step + 1;
                        }
                        // 更新到达新状态所使用的步数，并将新状态加入队列
                        map.put(sub, step + 1);
                        queue.offer(sub);
                    }
                }
            }
        }

        // 如果无法找到目标基因序列，返回-1
        return -1;
    }

    /**
     * 双向 BFS
     * 双向BFS与常规BFS相比，能够有效解决「搜索空间爆炸」的问题
     */
    public int minMutation2(String startGene, String endGene, String[] bank) {
        // 创建一个哈希集合，用于存储 wordList 中的所有单词，便于后续快速查找
        Set<String> set = new HashSet<>();
        for (String word : bank) {
            set.add(word);
        }

        // 如果 endWord 不在 wordList 中，说明无法通过变换得到 endWord，直接返回 0
        if (!set.contains(endGene)) {
            return -1;
        }

        // 调用双向 BFS 辅助函数，尝试找到 beginWord 到 endWord 的最短转换路径
        return bfs(startGene, endGene, set);
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
            char[] cs = str.toCharArray();
            int step = beginMap.get(str);
            // 遍历字符串中的每个字符
            for (int i = 0; i < 8; i++) {
                // 尝试用items中的每个字符替换当前字符
                for (char c : items) {
                    if (cs[i] == c) {
                        continue;
                    }
                    char[] clone = cs.clone();
                    clone[i] = c;
                    String sub = String.valueOf(clone);
                    // 如果替换后的字符串不在set中或已经在beginMap中，则跳过
                    if (!set.contains(sub) || beginMap.containsKey(sub)) {
                        continue;
                    }
                    // 如果替换后的字符串在endMap中，说明找到了目标节点
                    if (endMap.containsKey(sub)) {
                        return endMap.get(sub) + step + 1;
                    }
                    // 将替换后的字符串加入队列和beginMap中
                    queue.addLast(sub);
                    beginMap.put(sub, step + 1);
                }
            }
        }
        // 如果没有找到目标节点，返回-1
        return -1;
    }

    public static void main(String[] args) {
        _433最小基因变化 obj = new _433最小基因变化();
        String startGene = "AACCGGTT";
        String endGene = "AACCGGTA";
        String[] bank = {"AACCGGTA"};
        int res = 1;

        Asserts.test(obj.minMutation(startGene, endGene, bank) == res);
        Asserts.test(obj.minMutation2(startGene, endGene, bank) == res);
    }
}
