package 图论;

import common.Edge;
import tools.Asserts;

import java.util.*;

/**
 * 这道题说白了就是考你 有向加权图 的遍历。
 * <p>
 * a/b=2 就相当于往图中添加了一条 a->b 权值为 2 的边，同时添加一条 b->a 权值为 1/2 的边。
 * <p>
 * queries 问你 x/y 的值，相当于就是图中是否存在一条从 x 到 y 的路径，如果有，那么路径上所有边的权值相乘就是 x/y 的值。
 * <p>
 * 所以思路就很简单了，用邻接表建图，然后用 DFS 或者 BFS 遍历即可
 */
public class _399除法求值 {
    public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
        // 把 equations 抽象成一副图，临接表存储
        Map<String, List<Edge>> graph = new HashMap<>();
        for (int i = 0; i < equations.size(); i++) {
            List<String> equation = equations.get(i);
            String a = equation.get(0), b = equation.get(1);
            double w = values[i];
            // 构建双向图
            if (!graph.containsKey(a)) {
                graph.put(a, new ArrayList<>());
            }
            graph.get(a).add(new Edge(b, w));
            if (!graph.containsKey(b)) {
                graph.put(b, new ArrayList<>());
            }
            graph.get(b).add(new Edge(a, 1.0 / w));
        }

        double[] res = new double[queries.size()];
        for (int i = 0; i < queries.size(); i++) {
            List<String> query = queries.get(i);
            String start = query.get(0), end = query.get(1);
            // BFS 遍历图，计算start 到end 的路径乘积
            res[i] = bfs(graph, start, end);
        }
        return res;
    }

    private double bfs(Map<String, List<Edge>> graph, String start, String end) {
        if (!graph.containsKey(start) || !graph.containsKey(end)) {
            return -1.0;
        }
        if (start.equals(end)) {
            return 1.0;
        }

        // BFS 标准框架
        Queue<String> queue = new LinkedList<>();
        Set<String> visited = new HashSet<>();
        queue.offer(start);
        visited.add(start);

        // key 为节点ID，value 记录从 start 到该节点的路径乘积
        Map<String, Double> weight = new HashMap<>();
        weight.put(start, 1.0);

        while (!queue.isEmpty()) {
            String cur = queue.poll();
            for (Edge neighbor : graph.get(cur)) {
                if (visited.contains(neighbor.getNode())) {
                    continue;
                }
                // 更新路径乘积
                weight.put(neighbor.getNode(), weight.get(cur) * neighbor.getWeight());
                if (neighbor.getNode().equals(end)) {
                    return weight.get(end);
                }
                // 记录 visited
                visited.add(neighbor.getNode());
                // 新节点加入队列继续遍历
                queue.offer(neighbor.getNode());
            }
        }
        return -1.0;
    }

    public static void main(String[] args) {
        _399除法求值 obj = new _399除法求值();

        List<List<String>> equations = new ArrayList<>();
        equations.add(Arrays.asList("a", "b"));
        equations.add(Arrays.asList("b", "c"));

        double[] values = new double[]{2.0, 3.0};

        List<List<String>> queries = new ArrayList<>();
        queries.add(Arrays.asList("a", "c"));
        queries.add(Arrays.asList("b", "a"));
        queries.add(Arrays.asList("a", "e"));
        queries.add(Arrays.asList("a", "a"));
        queries.add(Arrays.asList("x", "x"));

        double[] res = obj.calcEquation(equations, values, queries);
        double[] expect = new double[]{6.00000, 0.50000, -1.00000, 1.00000, -1.00000};

        Asserts.test(Arrays.equals(res, expect));
    }
}
