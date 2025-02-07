package 二叉树.字典树;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class _212单词搜索II {
    private TrieNode2 root;
    private Set<String> set = new HashSet<>();
    char[][] board;
    int rows, cols;
    int[][] dirs = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    boolean[][] vis = new boolean[15][15];

    public _212单词搜索II() {
        root = new TrieNode2();
    }

    public void insert(String word) {
        TrieNode2 cur = root;
        for (char c : word.toCharArray()) {
            if (cur.son[c - 'a'] == null) {
                cur.son[c - 'a'] = new TrieNode2();
            }
            cur = cur.son[c - 'a'];
        }
        cur.s = word;
    }

    public List<String> findWords(char[][] board, String[] words) {
        this.board = board;
        rows = board.length;
        cols = board[0].length;

        for (String w : words) {
            insert(w);
        }

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                int c = board[i][j] - 'a';
                if (root.son[c] != null) {
                    vis[i][j] = true;
                    dfs(i, j, root.son[c]);
                    vis[i][j] = false;
                }
            }
        }

        List<String> res = new ArrayList<>();
        for (String s : set) {
            res.add(s);
        }
        return res;
    }

    private void dfs(int i, int j, TrieNode2 node) {
        if (node.s != null) {
            set.add(node.s);
        }
        for (int[] dir : dirs) {
            int dx = i + dir[0], dy = j + dir[1];
            if (dx < 0 || dx >= rows || dy < 0 || dy >= cols) {
                continue;
            }
            if (vis[dx][dy]) {
                continue;
            }

            int c = board[dx][dy] - 'a';
            if (node.son[c] != null) {
                vis[dx][dy] = true;
                dfs(dx, dy, node.son[c]);
                vis[dx][dy] = false;
            }
        }
    }
}
