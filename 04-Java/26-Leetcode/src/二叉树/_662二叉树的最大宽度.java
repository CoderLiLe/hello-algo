package 二叉树;

import common.TreeNode;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;

public class _662二叉树的最大宽度 {
    /**
     * 深度优先 + 节点编号
     * 根据满二叉树的节点编号规则：若根节点编号为 u，则其左子节点编号为 u << 1，其右节点编号为 u << 1 | 1。
     * 一个朴素的想法是：我们在 DFS过程中使用两个哈希表分别记录每层深度中的最小节点编号和最大节点编号，
     *                两者距离即是当前层的宽度，最终所有层数中的最大宽度即是答案
     * 递归求解：
     * 1. 递归求解左子树最大宽度
     * 2. 递归求解右子树最大宽度
     * 3. 求解当前节点的宽度，即当前节点编号 - 父节点编号 + 1
     * 4. 求解当前节点的宽度与左右子树最大宽度的较大值
     * 5. 返回当前节点的宽度与左右子树最大宽度的较大值
     */

    Map<Integer, Integer> map = new HashMap<>();
    int ans;

    public int widthOfBinaryTree(TreeNode root) {
        dfs(root, 1, 0);
        return ans;
    }

    /**
     * 求解当前节点的宽度
     * 时间复杂度：O(n)
     * 空间复杂度：O(n)
     *
     * @param root 二叉树的根节点
     * @param nodeIndex 当前节点的位置编号
     * @param depth 当前节点的深度
     */
    private void dfs(TreeNode root, int nodeIndex, int depth) {
        if (root == null) {
            return;
        }
        if (!map.containsKey(depth)) {
            map.put(depth, nodeIndex);
        }
        // 更新当前深度的最大宽度
        ans = Math.max(ans, nodeIndex - map.get(depth) + 1);
        // 更新当前节点的位置编号
        nodeIndex = nodeIndex - map.get(depth) + 1;
        // 递归处理左子树
        dfs(root.left, nodeIndex << 1, depth + 1);
        // 递归处理右子树
        dfs(root.right, nodeIndex << 1 | 1, depth + 1);
    }

    /**
     * 计算二叉树的最大宽度
     * 广度优先 + 节点编号
     *
     * @param root 二叉树的根节点
     * @return 返回二叉树的最大宽度
     */
    public int widthOfBinaryTree2(TreeNode root) {
        // 初始化最大宽度为0
        int result = 0;
        // 使用双端队列来辅助层次遍历二叉树
        Deque<TreeNode> deque = new ArrayDeque<>();
        // 将根节点作为第一个节点入队，值为1，用于计算宽度
        deque.addLast(new TreeNode(1, root.left, root.right));
        // 当队列不为空时，继续遍历
        while(!deque.isEmpty()) {
            // 当前层的节点数量
            int count = deque.size();
            // 初始化当前层的起始和结束节点位置
            int startIndex = -1, endIndex = -1;
            // 遍历当前层的所有节点
            for (int i = 0; i < count; i++) {
                // 弹出队首节点
                TreeNode node = deque.pop();
                // 更新当前层的结束节点位置
                endIndex = node.val;
                // 如果当前层的起始节点位置未初始化，则进行初始化
                if (startIndex == -1) {
                    startIndex = node.val;
                }
                // 如果节点有左子节点，则计算其位置并入队
                if (node.left != null) {
                    deque.addLast(new TreeNode(node.val * 2, node.left.left, node.left.right));
                }
                // 如果节点有右子节点，则计算其位置并入队
                if (node.right != null) {
                    deque.addLast(new TreeNode(node.val * 2 + 1, node.right.left, node.right.right));
                }
            }
            // 更新最大宽度
            result = Math.max(result, endIndex - startIndex + 1);
        }
        // 返回最大宽度
        return result;
    }

}
