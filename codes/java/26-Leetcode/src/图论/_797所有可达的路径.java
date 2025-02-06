package 图论;

import java.util.*;

/**
 * https://leetcode.cn/problems/all-paths-from-source-to-target/description/
 */
public class _797所有可达的路径 {
    // 收集符合条件的路径
    private static List<List<Integer>> result = new ArrayList<>();
    // 1节点到终点的路径
    private static List<Integer> path = new ArrayList<>();
    private static void adjacencyMatrix() {
        Scanner sc = new Scanner(System.in);
        // 表示图中拥有 N 个节点，M 条边
        int n = sc.nextInt();
        int m = sc.nextInt();

        int[][] graph = new int[n + 1][m + 1];

        for (int i = 0; i < m; i++) {
            int s = sc.nextInt();
            int t = sc.nextInt();
            // 使用临接矩阵表示五向图，1表示 s 与 t 是相连的
            graph[s][t] = 1;
        }

        // 无论什么路径都是从1节点出发的
        path.add(1);
        dfs(graph, 1, n);

        // 输出结果
        if (result.isEmpty()) {
            System.out.println(-1);
        }
        for (List<Integer> list : result) {
            for (int i = 0; i < list.size() - 1; i++) {
                System.out.print(list.get(i) + " ");
            }
            System.out.println(list.get(list.size() - 1));
        }
    }

    private static void adjacencyList() {
        Scanner sc = new Scanner(System.in);
        // 表示图中拥有 N 个节点，M 条边
        int n = sc.nextInt();
        int m = sc.nextInt();

        // 节点编号从1到n，所以申请 n+1 这么大的数组
        List<LinkedList<Integer>> graph = new ArrayList<>(n + 1);
        for (int i = 0; i <= n; i++) {
            graph.add(new LinkedList<>());
        }

        while (m-- > 0) {
            int s = sc.nextInt();
            int t = sc.nextInt();
            // 使用邻接表表示 s -> t 是相连的
            graph.get(s).add(t);
        }

        // 无论什么路径都是从1节点出发的
        path.add(1);
        dfs2(graph, 1, n);

        // 输出结果
        if (result.isEmpty()) {
            System.out.println(-1);
        }
        for (List<Integer> list : result) {
            for (int i = 0; i < list.size() - 1; i++) {
                System.out.print(list.get(i) + " ");
            }
            System.out.println(list.get(list.size() - 1));
        }
    }

    private static void adjacencyList2() {
        Scanner sc = new Scanner(System.in);
        // 表示图中拥有 N 个节点，M 条边
        int n = sc.nextInt();
        int m = sc.nextInt();

        // 使用邻接表表示图
        Map<Integer, List<Integer>> graph = new HashMap<>();
        for (int i = 0; i < m; i++) {
            int s = sc.nextInt();
            int t = sc.nextInt();
            graph.computeIfAbsent(s, k -> new ArrayList<>()).add(t);
        }

        // 无论什么路径都是从1节点出发的
        path.add(1);
        dfs3(graph, 1, n);

        // 输出结果
        if (result.isEmpty()) {
            System.out.println(-1);
        }
        for (List<Integer> list : result) {
            for (int i = 0; i < list.size() - 1; i++) {
                System.out.print(list.get(i) + " ");
            }
            System.out.println(list.get(list.size() - 1));
        }
    }

    private static void dfs(int[][] graph, int x, int n) {
        // 当前遍历的节点x到达节点n，找到符合条件的一条路径
        if (x == n) {
            result.add(new ArrayList<>(path));
            return;
        }

        // 遍历节点x链接的所有节点
        for (int i = 1; i <= n; i++) {
            // 找到x链接的节点
            if (graph[x][i] == 1) {
                // 遍历到的节点加入到路径中来
                path.add(i);
                // 进入下一层递归
                dfs(graph, i, n);
                // 回溯，当前节点的遍历结束，从路径中移除当前节点
                path.remove(path.size() - 1);
            }
        }
    }

    private static void dfs2(List<LinkedList<Integer>> graph, int x, int n) {
        // 当前遍历的节点x到达节点n，找到符合条件的一条路径
        if (x == n) {
            result.add(new ArrayList<>(path));
            return;
        }

        // 找到 x指向的节点
        for (int i : graph.get(x)) {
            // 遍历到的节点加入到路径中来
            path.add(i);
            // 进入下一层递归
            dfs2(graph, i, n);
            // 回溯，撤销本节点
            path.remove(path.size() - 1);
        }
    }

    private static void dfs3(Map<Integer, List<Integer>> graph, int x, int n) {
        // 当前遍历的节点x到达节点n，找到符合条件的一条路径
        if (x == n) {
            result.add(new ArrayList<>(path));
            return;
        }

        // 遍历节点x链接的所有节点
        if (graph.containsKey(x)) {
            for (int neighbor : graph.get(x)) {
                // 遍历到的节点加入到路径中来
                path.add(neighbor);
                // 进入下一层递归
                dfs3(graph, neighbor, n);
                // 回溯，当前节点的遍历结束，从路径中移除当前节点
                path.remove(path.size() - 1);
            }
        }
    }

    public static void main(String[] args) {
        adjacencyMatrix();
        clearData();
        adjacencyList();
        clearData();
        adjacencyList2();
    }

    private static void clearData() {
        path.clear();
        result.clear();
    }
}
