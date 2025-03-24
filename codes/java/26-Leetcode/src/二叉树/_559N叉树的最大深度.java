package 二叉树;

import common.NNode;

import java.util.LinkedList;
import java.util.Queue;

public class _559N叉树的最大深度 {

    public int maxDepth(NNode root) {
        if (root == null) {
            return 0;
        }

        Queue<NNode> queue = new LinkedList<>();
        queue.offer(root);

        int depth = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();

            for (int i = 0; i < size; i++) {
                if (i == 0) {
                    depth++;
                }
                NNode node = queue.poll();
                if (node.children != null) {
                    for (NNode child : node.children) {
                        queue.offer(child);
                    }
                }
            }
        }

        return depth;
    }

    public int maxDepth2(NNode root) {
        return dfs(root);
    }

    private int dfs(NNode node) {
        if (node == null) {
            return 0;
        }

        int depth = 0;
        if (node.children != null) {
            for (NNode child : node.children) {
                depth = Math.max(depth, dfs(child));
            }
        }
        return depth + 1;
    }
}
