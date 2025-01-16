package 图论;

import tools.Asserts;

/**
 * 【题目描述】
 * <p>
 * 小明喜欢去公园散步，公园内布置了许多的景点，相互之间通过小路连接，小明希望在观看景点的同时，能够节省体力，走最短的路径。
 * <p>
 * 给定一个公园景点图，图中有 N 个景点（编号为 1 到 N），以及 M 条双向道路连接着这些景点。每条道路上行走的距离都是已知的。
 * <p>
 * 小明有 Q 个观景计划，每个计划都有一个起点 start 和一个终点 end，表示他想从景点 start 前往景点 end。由于小明希望节省体力，他想知道每个观景计划中从起点到终点的最短路径长度。 请你帮助小明计算出每个观景计划的最短路径长度。
 * <p>
 * 【输入描述】
 * <p>
 * 第一行包含两个整数 N, M, 分别表示景点的数量和道路的数量。
 * <p>
 * 接下来的 M 行，每行包含三个整数 u, v, w，表示景点 u 和景点 v 之间有一条长度为 w 的双向道路。
 * <p>
 * 接下里的一行包含一个整数 Q，表示观景计划的数量。
 * <p>
 * 接下来的 Q 行，每行包含两个整数 start, end，表示一个观景计划的起点和终点。
 * <p>
 * 【输出描述】
 * <p>
 * 对于每个观景计划，输出一行表示从起点到终点的最短路径长度。如果两个景点之间不存在路径，则输出 -1。
 * <p>
 * 【输入示例】
 * <p>
 * 7 3 1 2 4 2 5 6 3 6 8 2 1 2 2 3
 * <p>
 * 【输出示例】
 * <p>
 * 4 -1
 * <p>
 * 【提示信息】
 * <p>
 * 从 1 到 2 的路径长度为 4，2 到 3 之间并没有道路。
 * <p>
 * 1 <= N, M, Q <= 1000.
 */
public class _097小明逛公园 {
    // 边的最大距离是10^4(不选用Integer.MAX_VALUE是为了避免相加导致数值溢出)
    public static int MAX_VAL = 10005;

    public static void main(String[] args) {
        _097小明逛公园 obj = new _097小明逛公园();

        int n = 7;
        int m = 3;
        int[][] edges = {
                {1, 2, 4},
                {2, 5, 6},
                {3, 6, 8}
        };
        int[][] finds = {
                {1, 2},
                {2, 3}
        };

        Asserts.test("[4,-1]".equals(obj.floyd(n, m, edges, finds)));
    }

    /**
     * 本题是经典的多源最短路问题。
     * 在这之前我们讲解过，dijkstra朴素版、dijkstra堆优化、Bellman算法、Bellman队列优化（SPFA） 都是单源最短路，即只能有一个起点。
     * 而本题是多源最短路，即 求多个起点到多个终点的多条最短路径。
     *
     * Floyd 算法对边的权值正负没有要求，都可以处理。
     * Floyd算法核心思想是动态规划。
     * Floyd算法的复杂度是 O(n^3)，其中 n 是节点的数量。
     *
     * Floyd算法的思路是：
     * 1. 确定dp数组及下表含义
     * grid[i][j][k] = m，表示 节点i 到 节点j 以[1...k] 集合中的一个节点为中间节点的最短距离为m。
     *
     * 2、确定递推公式
     * 分两种情况：
     * （1）节点i 到 节点j 的最短路径经过节点k
     *      grid[i][j][k] = grid[i][k][k - 1] + grid[k][j][k - 1]
     *      节点i 到 节点k 的最短距离 是不经过节点k，中间节点集合为[1...k-1]，所以 表示为grid[i][k][k - 1]
     *      节点k 到 节点j 的最短距离 也是不经过节点k，中间节点集合为[1...k-1]，所以表示为 grid[k][j][k - 1]
     * （2）节点i 到 节点j 的最短路径不经过节点k
     *      grid[i][j][k] = grid[i][j][k - 1]
     *      如果节点i 到 节点j的最短距离 不经过节点k，那么 中间节点集合[1...k-1]，表示为 grid[i][j][k - 1]
     * 因为我们是求最短路，对于这两种情况自然是取最小值。
     * 即： grid[i][j][k] = min(grid[i][k][k - 1] + grid[k][j][k - 1]， grid[i][j][k - 1])
     *
     * 3、dp数组如何初始化
     * grid[i][j][k] = m，表示 节点i 到 节点j 以[1...k] 集合为中间节点的最短距离为m。
     * grid[i][j][k] = grid[j][i][k] = MAX_VAL;
     *
     * 4、确定遍历顺序
     *
     * 时间复杂度： O(n^3)
     * 空间复杂度：O(n^2)
     *
     * floyd算法的时间复杂度相对较高，适合 稠密图且源点较多的情况。
     * 如果是稀疏图，floyd是从节点的角度去计算了，例如 图中节点数量是 1000，就一条边，那 floyd的时间复杂度依然是 O(n^3) 。
     * 如果 源点少，其实可以 多次dijsktra 求源点到终点。
     *
     * @param n
     * @param m
     * @param edges
     * @param finds
     * @return
     */
    private String floyd(int n, int m, int[][] edges, int[][] finds) {
        // ① dp定义（grid[i][j][k] 节点i到节点j 可能经过节点K（k∈[1,n]））的最短路径
        int[][][] grid = new int[n + 1][n + 1][n + 1];
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                for (int k = 0; k <= n; k++) {
                    // 其余设置为最大值
                    grid[i][j][k] = grid[j][i][k] = MAX_VAL;
                }
            }
        }

        // ② dp 推导：grid[i][j][k] = min{grid[i][k][k-1] + grid[k][j][k-1], grid[i][j][k-1]}
        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            int weight = edge[2];
            // 初始化（处理k=0的情况） ③ dp初始化
            grid[u][v][0] = grid[v][u][0] = weight;
        }

        // ④ dp推导：floyd 推导
        for (int k = 1; k <= n; k++) {
            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= n; j++) {
                    grid[i][j][k] = Math.min(grid[i][k][k - 1] + grid[k][j][k - 1], grid[i][j][k - 1]);
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = 0; i < finds.length; i++) {
            int[] find = finds[i];
            int src = find[0];
            int dst = find[1];
            // 根据floyd推导结果输出计划路径的最小距离
            if (grid[src][dst][n] == MAX_VAL) {
                sb.append("-1");
            } else {
                sb.append(grid[src][dst][n]);
            }
            if (i < finds.length - 1) {
                sb.append(",");
            }
        }
        sb.append("]");
        return sb.toString();
    }
}
