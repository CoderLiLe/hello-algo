package com.lile.棋盘问题;

import com.lile.tools.Asserts;

import java.util.*;

public class _212单词搜索II {
    private Set<String> set = new HashSet<>();
    private List<String> res = new ArrayList<>();
    char[][] board;
    int rows, cols;
    int[][] dirs = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    boolean[][] vis = new boolean[15][15];
    public List<String> findWords(char[][] board, String[] words) {
        this.board = board;
        rows = board.length;
        cols = board[0].length;

        for (String word : words) {
            set.add(word);
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                vis[i][j] = true;
                sb.append(board[i][j]);
                backtrack(i, j, sb);
                vis[i][j] = false;
                sb.deleteCharAt(sb.length() - 1);
            }
        }
        return res;
    }

    private void backtrack(int i, int j, StringBuilder sb) {
        if (sb.length() > 10) {
            return;
        }

        if (set.contains(sb.toString())) {
            res.add(sb.toString());
            set.remove(sb.toString());
        }

        for (int[] dir : dirs) {
            int dx = i + dir[0], dy = j + dir[1];
            if (dx < 0 || dx >= rows || dy < 0 || dy >= cols) {
                continue;
            }
            if (vis[dx][dy]) {
                continue;
            }
            vis[dx][dy] = true;
            sb.append(board[dx][dy]);
            backtrack(dx, dy, sb);
            vis[dx][dy] = false;
            sb.deleteCharAt(sb.length() - 1);
        }
    }

    public static void main(String[] args) {
        _212单词搜索II obj = new _212单词搜索II();
        char[][] board = {{'o','a','a','n'},{'e','t','a','e'},{'i','h','k','r'},{'i','f','l','v'}};
        String[] words = {"oath","pea","eat","rain"};
        List<String> res = new ArrayList<>(Arrays.asList("oath","eat"));
        Asserts.test(obj.findWords(board, words).equals(res));
    }
}
