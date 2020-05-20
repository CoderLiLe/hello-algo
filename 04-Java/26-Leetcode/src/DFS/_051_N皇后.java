package DFS;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class _051_N皇后 {
    public List<List<String>> solveNQueens(int n) {
        List<List<String>> result = new ArrayList<>();
        char[][] board = new char[n][n];
        for (char[] chars : board) {
            Arrays.fill(chars, '.');
        }
        dfs(0, n, result, board);
        return result;
    }

    private void dfs(int row, int n, List<List<String>> result, char[][] board) {
        if (row == n) {
            result.add(charToString(board));
            return;
        }

        for (int col = 0; col < n; col++) {
            if (!isValid(board, row, col)) continue;
            board[row][col] = 'Q';
            dfs(row+1, n, result, board);
            board[row][col] = '.';
        }
    }

    private boolean isValid(char[][] board, int row, int col) {
        // up
        for (char[] chars : board) {
            if (chars[col] == 'Q') return false;
        }

        // up left
        for (int i = row-1, j = col - 1; i >= 0 && j >= 0; i--, j--) {
            if (board[i][j] == 'Q') return false;
        }

        // up right
        for (int i = row - 1, j = col + 1; i >= 0 && j < board.length ; i--, j++) {
            if (board[i][j] == 'Q') return false;
        }

        return true;
    }

    private List<String> charToString(char[][] board) {
        List<String> list = new ArrayList<>();
        for (char[] chars : board) {
            list.add(String.valueOf(chars));
        }
        return list;
    }
}
