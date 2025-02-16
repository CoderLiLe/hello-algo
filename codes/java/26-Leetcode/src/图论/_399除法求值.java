package 图论;

import common.Edge;
import tools.Asserts;

import javax.swing.*;
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
        Map<String, List<Edge>> graph = createGraph(equations, values);

        double[] res = new double[queries.size()];
        for (int i = 0; i < queries.size(); i++) {
            List<String> query = queries.get(i);
            String start = query.get(0), end = query.get(1);
            // BFS 遍历图，计算start 到end 的路径乘积
            res[i] = bfs(graph, start, end);
        }
        return res;
    }

    private Map<String, List<Edge>> createGraph(List<List<String>> equations, double[] values) {
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
        return graph;
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

    public double[] calcEquation_dfs(List<List<String>> equations, double[] values, List<List<String>> queries) {
        // 把 equations 抽象成一副图，临接表存储
        Map<String, List<Edge>> graph = createGraph(equations, values);

        Set<String> visited = new HashSet<>();
        double[] res = new double[queries.size()];
        for (int i = 0; i < queries.size(); i++) {
            List<String> query = queries.get(i);
            String start = query.get(0), end = query.get(1);
            visited.clear();
            res[i] = -1.0;
            if (!graph.containsKey(start) || !graph.containsKey(end)) {
                continue;
            }

            // BFS 遍历图，计算start 到end 的路径乘积
            res[i] = dfs(graph, start, 1.0, end, visited);
        }
        return res;
    }

    private double dfs(Map<String, List<Edge>> graph, String start, double mul, String end, Set<String> visited) {
        if (start.equals(end)) {
            return mul;
        }

        visited.add(start);

        // 初始结果为-1.0，表示以当前节点无法到达目标节点qy
        double res = -1.0;
        for (Edge neighbor : graph.get(start)) {
            if (visited.contains(neighbor.getNode())) {
                continue;
            }
            res = dfs(graph, neighbor.getNode(), mul * neighbor.getWeight(), end, visited);
            if (res != -1.0) {
                break;
            }
        }
        return res;
    }

    /**
     * 使用Floyd算法计算方程的值
     *
     * @param equations 一个包含方程的列表，每个方程是一个包含两个变量的列表
     * @param values 方程中每个除法操作的结果值数组
     * @param queries 一个包含查询的列表，每个查询是一个包含两个变量的列表
     * @return 返回每个查询的结果数组，如果查询无法计算，则结果为-1.0
     */
    public double[] calcEquation_floyd(List<List<String>> equations, double[] values, List<List<String>> queries) {
        // 初始化变量计数器
        int nvars = 0;
        // 创建一个映射，将变量字符串映射到整数编号
        Map<String, Integer> variables = new HashMap<>();

        // 遍历方程列表，为每个变量分配一个唯一的整数编号
        int n = equations.size();
        for (int i = 0; i < n; i++) {
            if (!variables.containsKey(equations.get(i).get(0))) {
                variables.put(equations.get(i).get(0), nvars++);
            }
            if (!variables.containsKey(equations.get(i).get(1))) {
                variables.put(equations.get(i).get(1), nvars++);
            }
        }

        // 初始化图的邻接矩阵，表示变量之间的除法关系
        double[][] graph = new double[nvars][nvars];
        for (int i = 0; i < nvars; i++) {
            Arrays.fill(graph[i], -1.0);
        }

        // 根据方程和值填充邻接矩阵
        for (int i = 0; i < n; i++) {
            int va = variables.get(equations.get(i).get(0)), vb = variables.get(equations.get(i).get(1));
            graph[va][vb] = values[i];
            graph[vb][va] = 1.0 / values[i];
        }

        // 使用Floyd算法更新图的邻接矩阵，计算所有变量对之间的除法结果
        for (int k = 0; k < nvars; k++) {
            for (int i = 0; i < nvars; i++) {
                for (int j = 0; j < nvars; j++) {
                    if (graph[i][k] > 0 && graph[k][j] > 0) {
                        graph[i][j] = graph[i][k] * graph[k][j];
                    }
                }
            }
        }

        // 遍历查询列表，计算每个查询的结果
        int queriesCount = queries.size();
        double[] ret = new double[queriesCount];
        for (int i = 0; i < queriesCount; i++) {
            List<String> query = queries.get(i);
            double result = -1.0;
            // 如果查询的两个变量都存在于映射中，并且它们之间存在除法关系，则计算结果
            if (variables.containsKey(query.get(0)) && variables.containsKey(query.get(1))) {
                int ia = variables.get(query.get(0)), ib = variables.get(query.get(1));
                if (graph[ia][ib] > 0) {
                    result = graph[ia][ib];
                }
            }
            ret[i] = result;
        }
        return ret;
    }

    public static void main(String[] args) {
        _399除法求值 obj = new _399除法求值();

        test_bfs(obj);
        test_dfs(obj);
        test_floyd(obj);
    }

    private static void test_bfs(_399除法求值 obj) {
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

    private static void test_dfs(_399除法求值 obj) {
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

        double[] res = obj.calcEquation_dfs(equations, values, queries);
        double[] expect = new double[]{6.00000, 0.50000, -1.00000, 1.00000, -1.00000};

        Asserts.test(Arrays.equals(res, expect));
    }

    private static void test_floyd(_399除法求值 obj) {
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

        double[] res = obj.calcEquation_floyd(equations, values, queries);
        double[] expect = new double[]{6.00000, 0.50000, -1.00000, 1.00000, -1.00000};

        Asserts.test(Arrays.equals(res, expect));
    }
}
