package 图论;

import common.EdgeInfo;
import common.UnionFind;
import tools.Asserts;

import java.util.*;

/**
 * https://github.com/youngyangyang04/leetcode-master/blob/master/problems/kamacoder/0053.%E5%AF%BB%E5%AE%9D-prim.md
 * <p>
 * 题目描述
 * 在世界的某个区域，有一些分散的神秘岛屿，每个岛屿上都有一种珍稀的资源或者宝藏。国王打算在这些岛屿上建公路，方便运输。
 * <p>
 * 不同岛屿之间，路途距离不同，国王希望你可以规划建公路的方案，如何可以以最短的总公路距离将 所有岛屿联通起来（注意：这是一个无向图）。
 * <p>
 * 给定一张地图，其中包括了所有的岛屿，以及它们之间的距离。以最小化公路建设长度，确保可以链接到所有岛屿。
 * <p>
 * 输入描述
 * 第一行包含两个整数V 和 E，V代表顶点数，E代表边数 。顶点编号是从1到V。例如：V=2，一个有两个顶点，分别是1和2。
 * <p>
 * 接下来共有 E 行，每行三个整数 v1，v2 和 val，v1 和 v2 为边的起点和终点，val代表边的权值。
 * <p>
 * 输出描述
 * 输出联通所有岛屿的最小路径总距离
 * <p>
 * 输入示例：
 * 7 11
 * 1 2 1
 * 1 3 1
 * 1 5 2
 * 2 6 1
 * 2 4 2
 * 2 3 2
 * 3 4 1
 * 4 5 1
 * 5 6 2
 * 5 7 1
 * 6 7 1
 * <p>
 * 输出示例：
 * 6
 */
public class _053寻宝 {
    public static void main(String[] args) {
        _053寻宝 obj = new _053寻宝();

        // 示例输入
        int v = 7;
        int e = 11;
        int[][] intput = {
                {1, 2, 1},
                {1, 3, 1},
                {1, 5, 2},
                {2, 6, 1},
                {2, 4, 2},
                {2, 3, 2},
                {3, 4, 1},
                {4, 5, 1},
                {5, 6, 2},
                {5, 7, 1},
                {6, 7, 1}
        };

        obj.minimumSpanningTree(v, e, intput);
    }

    /**
     * prim 算法是维护节点的集合，而 Kruskal 是维护边的集合。
     * 如果 一个图中，节点多，但边相对较少，那么使用Kruskal 更优。
     *
     * @param v
     * @param e
     * @param intput
     * @return
     */
    private void minimumSpanningTree(int v, int e, int[][] intput) {
        int[][] edges = new int[v + 1][v + 1];
        for (int i = 0; i < edges.length; i++) {
            Arrays.fill(edges[i], 10001);
        }

        List<EdgeInfo<Integer, Integer>> edgeInfos = new ArrayList<>();
        // 读取边的信息并填充邻接矩阵
        for (int i = 1; i <= e; i++) {
            int[] info = intput[i - 1];
            EdgeInfo<Integer, Integer> edgeInfo = new EdgeInfo<>(info[0], info[1], info[2]);
            edgeInfos.add(edgeInfo);

            edges[info[0]][info[1]] = info[2];
            edges[info[1]][info[0]] = info[2];
        }

        Asserts.test(6 == prim(v, edges));
        Asserts.test(6 == kruskal(v, edgeInfos));
    }

    /**
     * Prim算法
     * 时间复杂度为O(n^2)，其中n为节点数量。
     * <p>
     * 关于prim算法，我自创了三部曲，来帮助大家理解：
     * 第一步，选距离生成树最近节点
     * 第二步，最近节点加入生成树
     * 第三步，更新非生成树节点到生成树的距离（即更新minDist数组）
     * <p>
     * minDist数组是prim算法的灵魂，它帮助prim算法完成最重要的一步，就是如何找到距离最小生成树最近的点
     * minDist数组里记录的其实也是最小生成树的边的权值。
     *
     * @param v     节点数量
     * @param edges 边的权值矩阵，edges[i][j]表示节点i到节点j的边的权值
     * @return 返回最小生成树的总权值
     */
    private int prim(int v, int[][] edges) {
        // 所有节点到最小生成树的最小距离
        int[] minDist = new int[v + 1];
        Arrays.fill(minDist, 10001);

        // 这个节点是否在树里
        boolean[] isInTree = new boolean[v + 1];
        Arrays.fill(isInTree, false);

        // 我们只需要循环 n-1次，建立 n - 1条边，就可以把n个节点的图连在一起
        for (int i = 1; i < v; i++) {
            // 1、prim三部曲，第一步：选距离生成树最近节点
            int cur = -1; // 选中哪个节点 加入最小生成树
            int minVal = Integer.MAX_VALUE;
            for (int j = 1; j <= v; j++) { // 1 - v，顶点编号，这里下标从1开始
                // 选取最小生成树节点的条件：
                //（1）不在最小生成树里
                //（2）距离最小生成树最近的节点
                if (!isInTree[j] && minDist[j] < minVal) {
                    minVal = minDist[j];
                    cur = j;
                }
            }

            // 2、prim三部曲，第二步：最近节点（cur）加入生成树
            isInTree[cur] = true;

            // 3、prim三部曲，第三步：更新非生成树节点到生成树的距离（即更新minDist数组）
            // cur节点加入之后， 最小生成树加入了新的节点，那么所有节点到 最小生成树的距离（即minDist数组）需要更新一下数据了
            // 由于cur是新加入到最小生成树的节点，那么只需要关心与cur相连的非生成树节点的距离是否比原来非生成树节点到生成树节点的距离更小了
            for (int j = 1; j <= v; j++) {
                // 更新的条件：
                // （1）节点是 非生成树里的节点
                // （2）与cur相连的某节点的权值比该某节点距离最小生成树的距离小
                // 其实就是cur是新加入最小生成树的节点，那么所有非生成树的节点距离生成树节点的最近距离由于cur的新加入，需要更新一下数据了
                if (!isInTree[j] && edges[cur][j] < minDist[j]) {
                    minDist[j] = edges[cur][j];
                }
            }
        }

        // 统计结果
        int result = 0;
        for (int i = 2; i <= v; i++) { // 不计第一个顶点，因为统计的是边的权值，v个节点有 v-1条边
            result += minDist[i];
        }

        return result;
    }

    /**
     * Kruskal算法
     * <p>
     * kruscal的思路：
     * <p>
     * （1）边的权值排序，因为要优先选最小的边加入到生成树里
     * （2）遍历排序后的边
     * a. 如果边首尾的两个节点在同一个集合，说明如果连上这条边图中会出现环
     * b. 如果边首尾的两个节点不在同一个集合，加入到最小生成树，并把两个节点加入同一个集合
     * <p>
     * 时间复杂度 为 nlogn，其中n 为边的数量，适用稀疏图
     *
     * @param v         顶点数量
     * @param edgeInfos 所有边的数组，每个元素为一个长度为3的数组，表示两个顶点和边的权重
     * @return 返回最小生成树的权重和
     */
    private int kruskal(int v, List<EdgeInfo<Integer, Integer>> edgeInfos) {
        // 按照边的权重升序排序
        edgeInfos.sort(Comparator.comparingInt(EdgeInfo::getWeight));

        // 使用并查集来检测环路
        // 初始化 Union-Find
        UnionFind<Integer> uf = new UnionFind<>();
        for (int i = 1; i <= v; i++) {
            uf.makeSet(i);
        }

        // 初始化最小生成树的权重和
        int mstWeight = 0;
        // 初始化已添加到最小生成树的边的数量
        int edgeCount = 0;

        // 遍历所有边，尝试构建最小生成树
        for (EdgeInfo<Integer, Integer> edge : edgeInfos) {
            Integer from = edge.getFrom();
            Integer to = edge.getTo();
            Integer weight = edge.getWeight();

            // 如果两个顶点不在同一个集合中，添加这条边到最小生成树
            if (!uf.find(from).equals(uf.find(to))) {
                uf.union(from, to);
                mstWeight += weight;
                if (++edgeCount == v - 1) {
                    // 如果已经找到 v-1 条边，则已经形成最小生成树
                    break;
                }
            }
        }

        // 检查是否形成了完整的最小生成树
        if (edgeCount != v - 1) {
            return -1; // 无法形成最小生成树
        }

        // 返回最小生成树的权重和
        return mstWeight;
    }
}
