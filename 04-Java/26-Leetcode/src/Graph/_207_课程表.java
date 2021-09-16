package Graph;

import java.util.LinkedList;
import java.util.List;

public class _207_课程表 {
    // 记录一次 traverse 递归经过的节点
    private boolean[] onPath;
    // 记录遍历过的节点，防止走回头路
    private boolean[] visited;
    // 记录图中是否有环
    private boolean hasCycle = false;

    /**
     * 看到依赖问题，首先想到的就是把问题转化成「有向图」这种数据结构，只要图中存在环，那就说明存在循环依赖。
     */
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        List<Integer>[] graph = buildGraph(numCourses, prerequisites);

        onPath = new boolean[numCourses];
        visited = new boolean[numCourses];

        for (int i = 0; i < numCourses; i++) {
            traverse(graph, i);
        }

        return !hasCycle;
    }

    private void traverse(List<Integer>[] graph, int s) {
        if (onPath[s]) {
            // 出现环
            hasCycle = true;
        }

        if (visited[s] || hasCycle) {
            // 如果已经找到了环，也不用再遍历了
            return;
        }

        // 前序遍历代码位置
        onPath[s] = true;
        visited[s] = true;

        for (int t : graph[s]) {
            traverse(graph, t);
        }

        // 后序遍历代码位置
        onPath[s] = false;
    }

    private List<Integer>[] buildGraph(int numCourses, int[][] prerequisites) {
        // 图中共有 numCourses 个节点
        List<Integer>[] graph = new LinkedList[numCourses];
        for (int i = 0; i < numCourses; i++) {
            graph[i] = new LinkedList<>();
        }

        for (int[] edge : prerequisites) {
            int from = edge[1];
            int to = edge[0];
            // 修完课程 from 才能修课程 to
            // 在图中添加一条从 from 指向 to 的有向边
            graph[from].add(to);
        }

        return graph;
    }
}
