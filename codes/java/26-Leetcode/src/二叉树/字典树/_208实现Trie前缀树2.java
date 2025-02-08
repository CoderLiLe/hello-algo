package 二叉树.字典树;

import tools.Asserts;

import java.util.Arrays;

public class _208实现Trie前缀树2 {
    // 以下 static 成员独一份，被创建的多个 Trie 共用
    /**
     * 预估需要多少行
     * 1 <= word.length, prefix.length <= 2000
     * insert、search 和 startsWith 调用次数 总计 不超过 3 * 10^4 次
     * 假设每一次的调用都是 ，并且每一次调用都会使用到新的 2000 行。那么我们的行数需要开到 3 * 10^4 * 2000 行。
     *
     * 但由于我们的字符集大小只有 26，因此不太可能在 3 * 10^4 次调用中都用到新的 3 * 10^4 * 2000 行
     */
    private static int N = 100009;
    // 存储所有的单词字符
    private static int[][] trie = new int[N][26];
    // 记录某个格子被标记为结尾的次数（当idx编号的格子被标记为结尾n次，则count[idx] = n）
    private static int[] count = new int[N];
    // 记录用了多少个格子（相当于给用到的格子进行编号）
    private static int index = 0;


    // 在构造方法中完成重置 static 成员数组的操作
    // 这样做的目的是为减少 new 操作（无论有多少测试数据，上述 static 成员只会被 new 一次）
    public _208实现Trie前缀树2() {
        for (int row = index; row >= 0; row--) {
            Arrays.fill(trie[row], 0);
        }
        Arrays.fill(count, 0);
        index = 0;
    }

    public void insert(String word) {
        int idx = 0;
        for (int i = 0; i < word.length(); i++) {
            int u = word.charAt(i) - 'a';
            if (trie[idx][u] == 0) {
                trie[idx][u] = ++index;
            }
            idx = trie[idx][u];
        }
        count[idx]++;
    }

    public boolean search(String word) {
        int idx = 0;
        for (int i = 0; i < word.length(); i++) {
            int u = word.charAt(i) - 'a';
            if (trie[idx][u] == 0) {
                return false;
            }
            idx = trie[idx][u];
        }
        return count[idx] != 0;
    }

    public boolean startsWith(String prefix) {
        int idx = 0;
        for (int i = 0; i < prefix.length(); i++) {
            int u = prefix.charAt(i) - 'a';
            if (trie[idx][u] == 0) {
                return false;
            }
            idx = trie[idx][u];
        }
        return true;
    }

    public static void main(String[] args) {
        _208实现Trie前缀树2 trie = new _208实现Trie前缀树2();
        trie.insert("apple");
        Asserts.test(trie.search("apple"));
        Asserts.test(!trie.search("app"));
        Asserts.test(trie.startsWith("app"));
        trie.insert("app");
        Asserts.test(trie.search("app"));
    }
}
