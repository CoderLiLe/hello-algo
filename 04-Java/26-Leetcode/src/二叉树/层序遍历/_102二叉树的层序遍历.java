package 二叉树.层序遍历;

import common.TreeNode;
import tools.Asserts;

import java.util.*;

public class _102二叉树的层序遍历 {
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            List<Integer> level = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                level.add(node.val);
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }
            res.add(level);
        }
        return res;
    }

    public static void main(String[] args) {
        _102二叉树的层序遍历 obj = new _102二叉树的层序遍历();
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        Asserts.test(obj.levelOrder(root).equals(Arrays.asList(Arrays.asList(1), Arrays.asList(2, 3))));
    }
}
