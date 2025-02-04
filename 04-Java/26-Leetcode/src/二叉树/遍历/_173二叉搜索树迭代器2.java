package 二叉树.遍历;

import common.TreeNode;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 这道题本质上考的是「将迭代版的中序遍历代码」做等价拆分。
 * 中序遍历的基本逻辑是：处理左子树 -> 处理当前节点 -> 处理右子树
 *
 * 其中迭代做法是利用「栈」进行处理：
 * （1）先将当前节点的所有左子树压入栈，压到没有为止
 * （2）将最后一个压入的节点弹出（栈顶元素），加入答案
 * （3）将当前弹出的节点作为当前节点，重复步骤一
 */
public class _173二叉搜索树迭代器2 {
    Deque<TreeNode> queue = new ArrayDeque<>();

    public _173二叉搜索树迭代器2(TreeNode root) {
        // 步骤 1
        dfsLeft(root);
    }

    public int next() {
        // 步骤 2
        TreeNode root = queue.pollLast();
        int ans = root.val;
        // 步骤 3
        root = root.right;
        // 步骤 1
        dfsLeft(root);
        return ans;
    }

    public boolean hasNext() {
        return !queue.isEmpty();
    }

    private void dfsLeft(TreeNode root) {
        while (root != null) {
            queue.addLast(root);
            root = root.left;
        }
    }
}
