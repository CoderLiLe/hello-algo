package 二叉树;

import common.TreeNode;
import tools.Asserts;

import java.util.*;

public class _637二叉树的层平均值 {
    public List<Double> averageOfLevels(TreeNode root) {
        List<Double> res = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            double sum = 0;
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                sum += node.val;
                if (node.left != null) {
                    queue.add(node.left);
                }
                if (node.right != null) {
                    queue.add(node.right);
                }
            }
            res.add(sum / size);
        }
        return res;
    }

    public static void main(String[] args) {
        _637二叉树的层平均值 obj = new _637二叉树的层平均值();
        TreeNode root = new TreeNode(1);
        root.right = new TreeNode(2);
        root.right.left = new TreeNode(3);
        Asserts.test(obj.averageOfLevels(root).equals(Arrays.asList(1.00000, 1.50000, 2.50000)));
    }
}
