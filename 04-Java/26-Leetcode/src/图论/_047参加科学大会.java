package 图论;

import common.EdgeInfo;
import tools.Asserts;

import java.util.*;

/**
 * https://github.com/youngyangyang04/leetcode-master/blob/master/problems/kamacoder/0047.%E5%8F%82%E4%BC%9Adijkstra%E6%9C%B4%E7%B4%A0.md
 * 【题目描述】
 *
 * 小明是一位科学家，他需要参加一场重要的国际科学大会，以展示自己的最新研究成果。
 *
 * 小明的起点是第一个车站，终点是最后一个车站。然而，途中的各个车站之间的道路状况、交通拥堵程度以及可能的自然因素（如天气变化）等不同，这些因素都会影响每条路径的通行时间。
 *
 * 小明希望能选择一条花费时间最少的路线，以确保他能够尽快到达目的地。
 *
 * 【输入描述】
 *
 * 第一行包含两个正整数，第一个正整数 N 表示一共有 N 个公共汽车站，第二个正整数 M 表示有 M 条公路。
 *
 * 接下来为 M 行，每行包括三个整数，S、E 和 V，代表了从 S 车站可以单向直达 E 车站，并且需要花费 V 单位的时间。
 *
 * 【输出描述】
 *
 * 输出一个整数，代表小明从起点到终点所花费的最小时间。
 *
 * 输入示例
 * 7 9
 * 1 2 1
 * 1 3 4
 * 2 3 2
 * 2 4 5
 * 3 4 2
 * 4 5 3
 * 2 6 4
 * 5 7 4
 * 6 7 9
 *
 * 输出示例：12
 *
 * 数据范围：
 *
 * 1 <= N <= 500; 1 <= M <= 5000;
 */
public class _047参加科学大会 {
    public static void main(String[] args) {
        int n = 7;
        int m = 9;
        int[][] input = {
                {1, 2, 1},
                {1, 3, 4},
                {2, 3, 2},
                {2, 4, 5},
                {3, 4, 2},
                {4, 5, 3},
                {2, 6, 4},
                {5, 7, 4},
                {6, 7, 9}
        };
    }

    private void shortestPath(int n, int m, int[][] input) {
        int[][] edges = new int[n + 1][n + 1];
        List<List<EdgeInfo<Integer, Integer>>> edgeInfos = new ArrayList<>();
        for (int i = 0; i < edges.length; i++) {
            Arrays.fill(edges[i], Integer.MAX_VALUE);
            edgeInfos.add(new ArrayList<>());
        }

        // 读取边的信息并填充邻接矩阵
        for (int i = 1; i <= m; i++) {
            int[] info = input[i - 1];
            int from = info[0];
            EdgeInfo<Integer, Integer> edgeInfo = new EdgeInfo<>(from, info[1], info[2]);
            edgeInfos.get(from).add(edgeInfo);

            edges[info[0]][info[1]] = info[2];
            edges[info[1]][info[0]] = info[2];
        }

        Asserts.test(12 == dijkstra(n, edges));
        Asserts.test(12 == dijkstra_adjacencyList(n, edgeInfos));
    }

    /**
     * 朴素版dijkstra
     * dijkstra算法：在有权图（权值非负数）中求从起点到其他节点的最短路径算法。
     * dijkstra 算法 同样是贪心的思路，不断寻找距离 源点最近的没有访问过的节点
     * prim是求 非访问节点到最小生成树的最小距离，而 dijkstra是求 非访问节点到源点的最小距离
     *
     * 需要注意两点：
     * （1）dijkstra 算法可以同时求 起点到所有节点的最短路径
     * （2）权值不能为负数
     *
     * dijkstra三部曲：
     * 第一步，选源点到哪个节点近且该节点未被访问过
     * 第二步，该最近节点被标记访问过
     * 第三步，更新非访问节点到源点的距离（即更新minDist数组）
     *
     * minDist数组 用来记录 每一个节点距离源点的最小距离。
     *
     * 时间复杂度为 O(n^2)，可以看出时间复杂度 只和 n （节点数量）有关系。
     *
     * @param n
     * @param edges
     * @return
     */
    private int dijkstra(int n, int[][] edges) {
        // 存储从源点到每个节点的最短距离
        int[] minDist = new int[n + 1];
        Arrays.fill(minDist, Integer.MAX_VALUE);

        // 记录顶点是否被访问过
        boolean[] visited = new boolean[n + 1];

        // 起始点到自身的距离为0
        minDist[1] = 0;

        for (int i = 1; i <= n; i++) { // 遍历所有节点

            int minVal = Integer.MAX_VALUE;
            int cur = 1;

            // 1、选距离源点最近且未访问过的节点
            for (int v = 1; v <= n; ++v) {
                if (!visited[v] && minDist[v] < minVal) {
                    minVal = minDist[v];
                    cur = v;
                }
            }

            // 2、标记该节点已被访问
            visited[cur] = true;

            // 3、第三步，更新非访问节点到源点的距离（即更新minDist数组）
            for (int v = 1; v <= n; v++) {
                if (!visited[v] && edges[cur][v] != Integer.MAX_VALUE && minDist[cur] + edges[cur][v] < minDist[v]) {
                    // 因为 minDist表示 节点到源点的最小距离，所以 新节点 cur 的加入，需要使用 源点到cur的距离
                    // （minDist[cur]） + cur 到 节点 v 的距离 （grid[cur][v]），才是 源点到节点v的距离
                    minDist[v] = minDist[cur] + edges[cur][v];
                }
            }
        }

        if (minDist[n] == Integer.MAX_VALUE) {
            // 不能到达终点
            return -1;
        } else {
            // 到达终点最短路径
            return minDist[n];
        }
    }

    /**
     * 邻接矩阵版本的堆优化dijkstra代码
     *
     * 时间复杂度：O(E * (N + logE)) E为边的数量，N为节点数量
     * 空间复杂度：O(log(N^2))
     *
     * @param n
     * @param edges
     * @return
     */
    private int dijkstra_adjacencyList(int n, List<List<EdgeInfo<Integer, Integer>>> edges) {
        // 存储从源点到每个节点的最短距离
        int[] minDist = new int[n + 1];
        Arrays.fill(minDist, Integer.MAX_VALUE);

        // 记录顶点是否被访问过
        boolean[] visited = new boolean[n + 1];

        // 优先队列中存放 Pair<节点，源点到该节点的权值>
        PriorityQueue<Pair<Integer, Integer>> pq = new PriorityQueue<>(new Comparator<Pair<Integer, Integer>>() {
            @Override
            public int compare(Pair<Integer, Integer> o1, Pair<Integer, Integer> o2) {
                return Integer.compare(o1.second, o2.second);
            }
        });

        // 初始化队列，源点到源点的距离为0，所以初始为0
        pq.add(new Pair<>(0, 0));

        // 起始点到自身的距离为0
        minDist[0] = 0;

        while (!pq.isEmpty()) {
            // 1. 第一步，选源点到哪个节点近且该节点未被访问过（通过优先级队列来实现）
            // <节点， 源点到该节点的距离>
            Pair<Integer, Integer> cur = pq.poll();

            if (visited[cur.first]) continue;

            // 2. 第二步，该最近节点被标记访问过
            visited[cur.first] = true;

            // 3. 第三步，更新非访问节点到源点的距离（即更新minDist数组）
            for (EdgeInfo<Integer, Integer> edge : edges.get(cur.first)) { // 遍历 cur指向的节点，cur指向的节点为 edge
                // cur指向的节点edge.to，这条边的权值为 edge.val
                if (!visited[edge.getTo()] && minDist[cur.first] + edge.getWeight() < minDist[edge.getTo()]) { // 更新minDist
                    minDist[edge.getTo()] = minDist[cur.first] + edge.getWeight();
                    pq.add(new Pair<>(edge.getTo(), minDist[edge.getTo()]));
                }
            }
        }

        if (minDist[n] == Integer.MAX_VALUE) {
            // 不能到达终点
            return -1;
        } else {
            // 到达终点最短路径
            return minDist[n];
        }
    }

    public class Pair<F, S> {
        private final F first;
        private final S second;

        public Pair(F first, S second) {
            this.first = first;
            this.second = second;
        }

        public F getFirst() {
            return first;
        }

        public S getSecond() {
            return second;
        }

        @Override
        public String toString() {
            return "(" + first + ", " + second + ")";
        }
    }
}
