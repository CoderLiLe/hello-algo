package DFS;

public class _130_被围绕的区域 {
    public void solve(char[][] board) {
        if (board == null) return;
        int rows = board.length;
        if (rows == 0) return;
        int cols = board[0].length;
        if (rows < 3 || cols < 3) return;

        // T = O(n^2)
        for (int i = 1; i < rows-1; i++) {
            for (int j = 1; j < cols-1; j++) {
                char c = board[i][j];
                /*
                * 被围绕的区间不会存在于边界上，换句话说，任何边界上的'O'都不会被填充为'X'。
                * 任何不在边界上，或不与边界上的'O'相连的'O'最终都会被填充为'X'。如果两
                * 个元素在水平或垂直方向相邻，则称它们是“相连”的。
                * */

                // left up
                if (i == 1 && j == 1) {
                    if (board[0][1] != 'O' && board[1][0] != 'O') board[1][1] = 'X';
                } else if (i == 1 && j == cols-2) { //  right up
                    if (board[1][cols-1] != 'O' && board[0][cols-2] != 'O') board[1][cols-2] = 'X';
                } else if (i == rows-2 && j == 1) { // left down
                    if (board[rows-2][0] != 'O' && board[rows-1][1] != 'O') board[rows-2][1] = 'X';
                } else if (i == rows-2 && j == cols-2) { // right down
                    if (board[rows-2][cols-1] != 'O' && board[rows-1][cols-2] != 'O') board[rows-2][cols-2] = 'X';
                } else {
                    if (board[i][j] == 'O') board[i][j] = 'X';
                }
            }
        }
    }

    private static final int[][] DIR = {{0, 1}, {1, 0}, {-1, 0}, {0, -1}};

    public void solve2(char[][] board) {
        if (board == null) {
            return;
        }

        int rows = board.length;
        int cols = board[0].length;

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                boolean isEdge = (i == 0 || j == 0 || i == rows - 1 || j == cols - 1);
                // 寻找和边界联通的 O
                if (isEdge && board[i][j] == 'O') {
                    backtracking(board, i, j);
                }
            }
        }

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (board[i][j] == 'O') {
                    board[i][j] = 'X';
                }
                if (board[i][j] == '#') {
                    board[i][j] = 'O';
                }
            }
        }
    }

    private void backtracking(char[][] board, int x, int y) {
        // 到达终点，board[i][j] == '#' 说明已经搜索过了
        if (x < 0 || x >= board.length || y < 0 || y >= board[0].length || board[x][y] == '#' || board[x][y] == 'X') {
            return;
        }

        board[x][y] = '#';

        for (int i = 0; i < DIR.length; i++) {
            int nextx = x + DIR[i][0];
            int nexty = y + DIR[i][1];

            backtracking(board, nextx, nexty);
        }
    }
}
