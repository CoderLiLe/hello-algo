package 二叉树.层序遍历;

import common.TreeNode;
import tools.Asserts;

import java.util.*;

public class _107二叉树的层序遍历II {
    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        List<List<Integer>> lists = new ArrayList<>();
        if (root == null) {
            return lists;
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
            lists.add(level);
        }

        List<List<Integer>> res = new ArrayList<>();
        for (int i = lists.size() - 1; i >= 0; i--) {
            res.add(lists.get(i));
        }

        return res;
    }

    public static void main(String[] args) {
        _107二叉树的层序遍历II obj = new _107二叉树的层序遍历II();
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        Asserts.test(obj.levelOrderBottom(root).equals(Arrays.asList(Arrays.asList(2, 3), Arrays.asList(1))));
    }
}
