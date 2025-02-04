package 二叉树.层序遍历;

import common.TreeNode;
import tools.Asserts;

import java.util.*;

public class _515在每个树行中找最大值 {
    public List<Integer> largestValues(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            int max = Integer.MIN_VALUE;
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                max = Math.max(max, node.val);
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }
            res.add(max);
        }
        return res;
    }

    public static void main(String[] args) {
        _515在每个树行中找最大值 obj = new _515在每个树行中找最大值();
        TreeNode root = new TreeNode(1);
        TreeNode node2 = new TreeNode(2);
        TreeNode node3 = new TreeNode(3);
        TreeNode node33 = new TreeNode(3);
        TreeNode node5 = new TreeNode(5);
        TreeNode node9 = new TreeNode(9);

        root.left = node3;
        root.right = node2;
        node3.left = node5;
        node3.right = node33;

        node2.right = node9;

        Asserts.test(obj.largestValues(root).equals(Arrays.asList(1, 3, 9)));
    }

}
