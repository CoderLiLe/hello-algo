package 图论;

import common.EdgeInfo;
import tools.Asserts;

import java.util.*;

/**
 * 题目描述
 *
 * 某国为促进城市间经济交流，决定对货物运输提供补贴。共有 n 个编号为 1 到 n 的城市，通过道路网络连接，网络中的道路仅允许从某个城市单向通行到另一个城市，不能反向通行。
 *
 * 网络中的道路都有各自的运输成本和政府补贴，道路的权值计算方式为：运输成本 - 政府补贴。
 *
 * 权值为正表示扣除了政府补贴后运输货物仍需支付的费用；权值为负则表示政府的补贴超过了支出的运输成本，实际表现为运输过程中还能赚取一定的收益。
 *
 * 请找出从城市 1 到城市 n 的所有可能路径中，综合政府补贴后的最低运输成本。
 *
 * 如果最低运输成本是一个负数，它表示在遵循最优路径的情况下，运输过程中反而能够实现盈利。
 *
 * 城市 1 到城市 n 之间可能会出现没有路径的情况，同时保证道路网络中不存在任何负权回路。
 *
 * |||负权回路是指一系列道路的总权值为负，这样的回路使得通过反复经过回路中的道路，理论上可以无限地减少总成本或无限地增加总收益。
 *
 * 输入描述
 *
 * 第一行包含两个正整数，第一个正整数 n 表示该国一共有 n 个城市，第二个整数 m 表示这些城市中共有 m 条道路。
 *
 * 接下来为 m 行，每行包括三个整数，s、t 和 v，表示 s 号城市运输货物到达 t 号城市，道路权值为 v（单向图）。
 *
 * 输出描述
 *
 * 如果能够从城市 1 到连通到城市 n， 请输出一个整数，表示运输成本。如果该整数是负数，则表示实现了盈利。如果从城市 1 没有路径可达城市 n，请输出 "unconnected"。
 *
 * 输入示例：
 * 6 7
 * 5 6 -2
 * 1 2 1
 * 5 3 1
 * 2 5 2
 * 2 4 -3
 * 4 6 4
 * 1 3 5
 * 输出示例：
 * 1
 *
 * 数据范围：
 * 1 <= n <= 1000; 1 <= m <= 10000; -100 <= v <= 100
 */
public class _094城市间货物运输I {

    /**
     * 思路
     * 本题依然是单源最短路问题，求 从 节点1 到节点n 的最小费用。 但本题不同之处在于 边的权值是有负数了。
     * 本题是经典的带负权值的单源最短路问题，此时就轮到Bellman_ford登场了
     * Bellman_ford算法的核心思想是 对所有边进行松弛n-1次操作（n为节点数量），从而求得目标最短路。
     *
     * 其实 Bellman_ford算法 也是采用了动态规划的思想，即：将一个问题分解成多个决策阶段，通过状态之间的递归关系最后计算出全局最优解。
     * 其算法核心思路是对 所有边进行 n-1 次 松弛
     */

    public static void main(String[] args) {
        int n = 6;
        int m = 7;
        int[][] input = {
                {5, 6, -2},
                {1, 2, 1},
                {5, 3, 1},
                {2, 5, 2},
                {2, 4, -3},
                {4, 6, 4},
                {1, 3, 5}
        };
        _094城市间货物运输I obj = new _094城市间货物运输I();
        obj.shortestPath(n, m, input);
    }

    private void shortestPath(int n, int m, int[][] input) {
        List<List<EdgeInfo<Integer, Integer>>> graph = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }

        List<int[]> edges = new ArrayList<>();
        List<EdgeInfo<Integer, Integer>> edgeInfos = new ArrayList<>();
        // 读取边的信息并填充邻接矩阵
        for (int i = 1; i <= m; i++) {
            int[] info = input[i - 1];
            int from = info[0];
            int to = info[1];
            int weight = info[2];

            edges.add(new int[]{from, to, weight});
            edgeInfos.add(new EdgeInfo<>(from, to, weight));
            graph.get(from).add(new EdgeInfo(from, to, weight));
        }

        Asserts.test(Objects.equals(bellmanFord(n, edges), 1));
        Asserts.test(Objects.equals(bellmanFord_adjacencyList(n, edgeInfos), 1));
        Asserts.test(Objects.equals(bellmanFord_adjacencyList_queue(n, graph), 1));
    }

    /**
     * 时间复杂度： O(N * E) , N为节点数量，E为图中边的数量
     * 空间复杂度： O(N) ，即 minDist 数组所开辟的空间
     *
     * @param n
     * @param edges
     * @return
     */
    private Integer bellmanFord(int n, List<int[]> edges) {
        // Represents the minimum distance from the current node to the original node
        int[] minDist = new int[n + 1];

        // Initialize the minDist array
        Arrays.fill(minDist, Integer.MAX_VALUE);
        minDist[1] = 0;

        // 对所有边 松弛 n-1 次
        for (int i = 1; i < n; i++) {
            // 每一次松弛，都是对所有边进行松弛
            for (int[] edge : edges) {
                // 边的出发点
                int from = edge[0];
                // 边的到达点
                int to = edge[1];
                if (from == to || from == Integer.MAX_VALUE || to == Integer.MAX_VALUE) {
                    continue;
                }
                // 边的权值
                int price = edge[2];
                // 松弛操作
                // minDist[from] != Integer.MAX_VALUE 防止从未计算过的节点出发
                // 代码就是 Bellman_ford算法的核心操作
                if (minDist[from] != Integer.MAX_VALUE && (minDist[from] + price) < minDist[to]) {
                    minDist[to] = minDist[from] + price;
                }
            }
        }

        if (minDist[n] == Integer.MAX_VALUE) {
            // 不能到达终点
            return null;
        } else {
            // 到达终点最短路径
            return minDist[n];
        }
    }

    /**
     * 时间复杂度： O(N * E) , N为节点数量，E为图中边的数量
     * 空间复杂度： O(N) ，即 minDist 数组所开辟的空间
     *
     * @param n
     * @param edges
     * @return
     */
    private Integer bellmanFord_adjacencyList(int n, List<EdgeInfo<Integer, Integer>> edges) {
        // Represents the minimum distance from the current node to the original node
        int[] minDist = new int[n + 1];

        // Initialize the minDist array
        Arrays.fill(minDist, Integer.MAX_VALUE);
        minDist[1] = 0;

        // 对所有边 松弛 n-1 次
        for (int i = 1; i < n; i++) {
            // 每一次松弛，都是对所有边进行松弛
            for (EdgeInfo<Integer, Integer> edge : edges) {
                // 边的出发点
                int from = edge.getFrom();
                // 边的到达点
                int to = edge.getTo();
                // 边的权值
                int price = edge.getWeight();
                // 松弛操作
                // minDist[from] != Integer.MAX_VALUE 防止从未计算过的节点出发
                // 代码就是 Bellman_ford算法的核心操作
                if (minDist[from] != Integer.MAX_VALUE && (minDist[from] + price) < minDist[to]) {
                    minDist[to] = minDist[from] + price;
                }
            }
        }

        if (minDist[n] == Integer.MAX_VALUE) {
            // 不能到达终点
            return null;
        } else {
            // 到达终点最短路径
            return minDist[n];
        }
    }

    /**
     * Queue improved Bellman-Ford
     * Bellman_ford 队列优化算法 ，也叫SPFA算法（Shortest Path Faster Algorithm）
     * 基于队列优化的算法，要比bellman_ford 算法 减少很多无用的松弛情况，特别是对于边数众多的大图 优化效果明显
     *
     * 时间复杂度： O(N * E) , N为节点数量，E为图中边的数量
     * 空间复杂度： O(N) ，即 minDist 数组所开辟的空间
     *
     * @param n
     * @param graph
     * @return
     */
    private Integer bellmanFord_adjacencyList_queue(int n, List<List<EdgeInfo<Integer, Integer>>> graph) {
        // Represents the minimum distance from the current node to the original node
        int[] minDist = new int[n + 1];

        // Initialize the minDist array
        Arrays.fill(minDist, Integer.MAX_VALUE);
        minDist[1] = 0;

        Queue<Integer> queue = new LinkedList<>();
        queue.offer(1);

        boolean[] isInQueue = new boolean[n + 1];

        while (!queue.isEmpty()) {
            int cur = queue.poll();
            isInQueue[cur] = false;
            for (EdgeInfo<Integer, Integer> edge : graph.get(cur)) {
                // 边的出发点
                int from = edge.getFrom();
                // 边的到达点
                int to = edge.getTo();
                // 边的权值
                int price = edge.getWeight();
                if (minDist[from] + price < minDist[to]) {
                    minDist[to] = minDist[from] + price;
                    if (!isInQueue[to]) {
                        queue.offer(to);
                        isInQueue[to] = true;
                    }
                }
            }
        }

        if (minDist[n] == Integer.MAX_VALUE) {
            // 不能到达终点
            return null;
        } else {
            // 到达终点最短路径
            return minDist[n];
        }
    }
}
