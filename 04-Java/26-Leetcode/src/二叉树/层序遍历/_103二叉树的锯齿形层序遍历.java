package 二叉树.层序遍历;

import common.TreeNode;
import tools.Asserts;

import java.util.*;

public class _103二叉树的锯齿形层序遍历 {
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        boolean flag = false;
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
            if (flag) {
                Collections.reverse(level);
            }
            flag = !flag;
            res.add(level);
        }

        return res;
    }

    public List<List<Integer>> zigzagLevelOrder2(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) {
            return res;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            LinkedList<Integer> level = new LinkedList<>();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                if (res.size() % 2 == 0) {
                    level.addLast(node.val);
                } else {
                    level.addFirst(node.val);
                }
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
        _103二叉树的锯齿形层序遍历 obj = new _103二叉树的锯齿形层序遍历();
        TreeNode root = new TreeNode(3);
        TreeNode node9 = new TreeNode(9);
        TreeNode node20 = new TreeNode(20);
        TreeNode node15 = new TreeNode(15);
        TreeNode node7 = new TreeNode(7);
        root.left = node9;
        root.right = node20;
        node20.left = node15;
        node20.right = node7;

        List<List<Integer>> res = Arrays.asList(Arrays.asList(3), Arrays.asList(20, 9), Arrays.asList(15, 7));
        Asserts.test(obj.zigzagLevelOrder(root).equals(res));
        Asserts.test(obj.zigzagLevelOrder2(root).equals(res));
    }
}
