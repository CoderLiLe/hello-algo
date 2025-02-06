package 二叉树.遍历;

import common.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 103_二叉树的锯齿形层序遍历
 * 中等
 * 给定一个二叉树，返回其节点值的锯齿形层序遍历。（即先从左往右，再从右往左进行下一层遍历，以此类推，层与层之间交替进行）。
 *
 * 例如：
 * 给定二叉树 [3,9,20,null,null,15,7],
 *
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 * 返回锯齿形层序遍历如下：
 *
 * [
 *   [3],
 *   [20,9],
 *   [15,7]
 * ]
 */
public class _103_二叉树的锯齿形层序遍历 {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(3);
        TreeNode node1 = new TreeNode(9);
        TreeNode node2 = new TreeNode(20);
        TreeNode node3 = new TreeNode(15);
        TreeNode node4 = new TreeNode(7);
        root.left = node1;
        root.right = node2;
        node2.left = node3;
        node2.right = node4;

        _103_二叉树的锯齿形层序遍历 zTraversal = new _103_二叉树的锯齿形层序遍历();
        List<List<Integer>> result1 = zTraversal.zigzagLevelOrder1(root);
        for (List<Integer> row : result1) {
            System.out.println(row);
        }

        System.out.println("---------------");

        List<List<Integer>> result2 = zTraversal.zigzagLevelOrder2(root);
        for (List<Integer> row : result2) {
            System.out.println(row);
        }
    }


    private List<List<Integer>> result = new ArrayList<>();

    /**
     * DFS 递归遍历
     */
    private List<List<Integer>> zigzagLevelOrder1(TreeNode root) {
        traverse(root, 0);
        return result;
    }

    private void traverse(TreeNode root, int level) {
        if (root == null) return;

        if (level >= result.size()) {
            result.add(new LinkedList<>());
        }

        List<Integer> tmpList = result.get(level);
        if (level % 2 == 0) {
            tmpList.add(root.val);
        } else {
            tmpList.add(0, root.val);
        }

        traverse(root.left, level + 1);
        traverse(root.right, level + 1);
    }

    /**
     * BFS 迭代遍历
     */
    private List<List<Integer>> zigzagLevelOrder2(TreeNode root) {
        List<List<Integer>> result2 = new ArrayList<>();
        if (root == null) return result2;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        boolean leftToRight = true;
        while (!queue.isEmpty()) {
            List<Integer> list = new LinkedList<>();
            int count = queue.size();
            for (int i = 0; i < count; i++) {
                TreeNode node = queue.poll();
                if (leftToRight) {
                    list.add(node.val);
                } else {
                    list.add(0, node.val);
                }

                if (node.left != null) {
                    queue.add(node.left);
                }
                if (node.right != null) {
                    queue.add(node.right);
                }
            }
            result2.add(list);
            leftToRight = !leftToRight;
        }

        return result2;
    }
}
