package 图论;

import tools.Asserts;

import java.util.LinkedList;
import java.util.Queue;

public class _909蛇梯棋 {
    public int snakesAndLadders(int[][] board) {
        int n = board.length;
        boolean[] visited = new boolean[n * n + 1];
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{1, 0});
        while (!queue.isEmpty()) {
            int[] p = queue.poll();
            for (int i = 1; i <= 6; ++i) {
                int next = p[0] + i;
                if (next > n * n) { // 超出边界
                    break;
                }
                int[] rc = id2rc(next, n); // 得到下一步的行列
                if (board[rc[0]][rc[1]] > 0) { // 存在蛇或梯子
                    next = board[rc[0]][rc[1]];
                }
                if (next == n * n) { // 到达终点
                    return p[1] + 1;
                }
                if (!visited[next]) {
                    visited[next] = true;
                    queue.offer(new int[]{next, p[1] + 1}); // 扩展新状态
                }
            }
        }
        return -1;
    }

    public int[] id2rc(int id, int n) {
        int r = (id - 1) / n, c = (id - 1) % n;
        if (r % 2 == 1) {
            c = n - 1 - c;
        }
        return new int[]{n - 1 - r, c};
    }

    public static void main(String[] args) {
        _909蛇梯棋 obj = new _909蛇梯棋();

        int[][] board = {
                {-1, -1, -1, -1, -1, -1},
                {-1, -1, -1, -1, -1, -1},
                {-1, -1, -1, -1, -1, -1},
                {-1, 35, -1, -1, 13, -1},
                {-1, -1, -1, -1, -1, -1},
                {-1, 15, -1, -1, -1, -1}
        };
        Asserts.test(obj.snakesAndLadders(board) == 4);
    }
}
