package 图论;

import common.CloneNode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class _133克隆图 {
    private Map<CloneNode, CloneNode> visited = new HashMap<>();

    public CloneNode cloneGraph(CloneNode node) {
        if (null == node) {
            return node;
        }

        if (visited.containsKey(node)) {
            return visited.get(node);
        }

        CloneNode cloneNode = new CloneNode(node.val, new ArrayList<>());
        visited.put(node, cloneNode);

        for (CloneNode neighbor : node.neighbors) {
            cloneNode.neighbors.add(cloneGraph(neighbor));
        }

        return cloneNode;
    }

    public CloneNode cloneGraph2(CloneNode node) {
        if (null == node) {
            return node;
        }

        Map<CloneNode, CloneNode> visited = new HashMap<>();

        LinkedList<CloneNode> queue = new LinkedList<>();
        queue.offer(node);
        // 克隆第一个节点并存储到哈希表中
        visited.put(node, new CloneNode(node.val, new ArrayList<>()));

        // 广度优先搜索
        while (!queue.isEmpty()) {
            // 取出队列的头节点
            CloneNode cur = queue.poll();
            // 遍历该节点的邻居
            for (CloneNode neighbor : cur.neighbors) {
                if (!visited.containsKey(neighbor)) {
                    // 如果没有被访问过，就克隆并存储在哈希表中
                    visited.put(neighbor, new CloneNode(neighbor.val, new ArrayList<>()));
                    // 将邻居节点加入队列中
                    queue.add(neighbor);
                }
                // 更新当前节点的邻居列表
                visited.get(cur).neighbors.add(visited.get(neighbor));
            }
        }
        return visited.get(node);
    }
}
