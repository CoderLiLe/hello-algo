package 图论;

import tools.Asserts;

import java.util.LinkedList;
import java.util.Queue;

/**
 * https://leetcode.cn/problems/number-of-islands/description/
 * <p>
 * 用遇到一个没有遍历过的节点陆地，计数器就加一，然后把该节点陆地所能遍历到的陆地都标记上。
 * <p>
 * 在遇到标记过的陆地节点和海洋节点的时候直接跳过。 这样计数器就是最终岛屿的数量。
 * <p>
 * 那么如何把节点陆地所能遍历到的陆地都标记上呢，就可以使用 DFS，BFS或者并查集
 */
public class _200岛屿数量 {
    public static int[][] DIR = {{0, 1}, {1, 0}, {-1, 0}, {0, -1}};

    public int numIslands(char[][] grid) {
        int rows = grid.length;
        int cols = grid[0].length;
        boolean[][] visited = new boolean[rows][cols];
        int res = 0;
        for (int x = 0; x < rows; x++) {
            for (int y = 0; y < cols; y++) {
                // 遇到一个没有遍历过的节点陆地，计数器就加一
                if (!visited[x][y] && grid[x][y] == '1') {
                    res++;
                    visited[x][y] = true;
                    dfs(visited, x, y, grid);
                }
            }
        }
        return res;
    }

    public void dfs(boolean[][] visited, int x, int y, char[][] grid) {
        for (int i = 0; i < DIR.length; i++) {
            int nextX = x + DIR[i][0];
            int nextY = y + DIR[i][1];

            // 边界判断
            if (nextY < 0 || nextX < 0 || nextX >= grid.length || nextY >= grid[0].length) {
                continue;
            }

            if (!visited[nextX][nextY] && grid[nextX][nextY] == '1') {
                visited[nextX][nextY] = true;
                dfs(visited, nextX, nextY, grid);
            }
        }
    }

    public int numIslands_bfs(char[][] grid) {
        int rows = grid.length;
        int cols = grid[0].length;
        boolean[][] visited = new boolean[rows][cols];
        int res = 0;
        for (int x = 0; x < rows; x++) {
            for (int y = 0; y < cols; y++) {
                // 遇到一个没有遍历过的节点陆地，计数器就加一
                if (!visited[x][y] && grid[x][y] == '1') {
                    res++;
                    visited[x][y] = true;
                    bfs(visited, x, y, grid, cols);
                }
            }
        }
        return res;
    }

    private void bfs(boolean[][] visited, int x, int y, char[][] grid, int cols) {
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(getIndex(x, y, cols));
        while (!queue.isEmpty()) {
            int cur = queue.poll();
            int curX = cur / cols;
            int curY = cur % cols;
            for (int k = 0; k < DIR.length; k++) {
                int nextX = curX + DIR[k][0];
                int nextY = curY + DIR[k][1];
                if (nextY < 0 || nextX < 0 || nextX >= grid.length || nextY >= grid[0].length) {
                    continue;
                }
               if (!visited[nextX][nextY] && grid[nextX][nextY] == '1') {
                    queue.offer(getIndex(nextX, nextY, cols));
                    // 特别注意：在放入队列以后，要马上标记成已经访问过，语义也是十分清楚的：反正只要进入了队列，迟早都会遍历到它
                    // 而不是在出队列的时候再标记，如果是出队列的时候再标记，会造成很多重复的结点进入队列，造成重复的操作，
                    // 这句话如果没有写对地方，代码会严重超时的
                    visited[nextX][nextY] = true;
               }
            }
        }
    }

    /**
     * 方法三：并查集
     * 关于连通性问题，并查集也是常用的数据结构。
     *
     * 思路：
     *
     * 并查集中维护连通分量的个数，在遍历的过程中：
     * （1）相邻的陆地（只需要向右看和向下看）合并，只要发生过合并，岛屿的数量就减少 1；
     * （2）在遍历的过程中，同时记录空地的数量；
     * （3）并查集中连通分量的个数 - 空地的个数，就是岛屿数量。
     *
     * 链接：https://leetcode.cn/problems/number-of-islands/solutions/12299/dfs-bfs-bing-cha-ji-python-dai-ma-java-dai-ma-by-l/
     */
    public int numIslands_unionFind(char[][] grid) {
        int rows = grid.length;
        int cols = grid[0].length;

        // 空地的数量
        int spaces = 0;
        UnionFind unionFind = new UnionFind(rows * cols);
        int[][] directions = {{1, 0}, {0, 1}};
        for (int x = 0; x < rows; x++) {
            for (int y = 0; y < cols; y++) {
                if (grid[x][y] == '0') {
                    spaces++;
                } else {
                    // 此时 grid[x][y] == '1'
                    for (int[] direction : directions) {
                        int newX = x + direction[0];
                        int newY = y + direction[1];
                        // 先判断坐标合法，再检查右边一格和下边一格是否是陆地
                        if (newX < rows && newY < cols && grid[newX][newY] == '1') {
                            unionFind.union(getIndex(x, y, cols), getIndex(newX, newY, cols));
                        }
                    }
                }
            }
        }
        return unionFind.getCount() - spaces;
    }

    private int getIndex(int x, int y, int cols) {
        return x * cols + y;
    }

    public static void main(String[] args) {
        _200岛屿数量 obj = new _200岛屿数量();
        char[][] grid = {
                {'1', '1', '1', '1', '0'},
                {'1', '1', '0', '1', '0'},
                {'1', '1', '0', '0', '0'},
                {'0', '0', '0', '0', '0'}
        };
        Asserts.test(obj.numIslands(grid) == 1);
        Asserts.test(obj.numIslands_bfs(grid) == 1);
        Asserts.test(obj.numIslands_unionFind(grid) == 1);
    }

    private class UnionFind {
        /**
         * 连通分量的个数
         */
        private int count;
        private int[] parent;

        public int getCount() {
            return count;
        }

        public UnionFind(int n) {
            this.count = n;
            parent = new int[n];
            for (int i = 0; i < n; i++) {
                parent[i] = i;
            }
        }

        private int find(int x) {
            while (x != parent[x]) {
                parent[x] = parent[parent[x]];
                x = parent[x];
            }
            return x;
        }

        public void union(int x, int y) {
            int xRoot = find(x);
            int yRoot = find(y);
            if (xRoot == yRoot) {
                return;
            }

            parent[xRoot] = yRoot;
            count--;
        }
    }
}
