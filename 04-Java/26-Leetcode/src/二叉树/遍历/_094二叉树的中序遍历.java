package 二叉树.遍历;

import common.TreeNode;
import tools.Asserts;

import java.util.*;

public class _094二叉树的中序遍历 {
    List<Integer> res = new ArrayList<>();
    Deque<TreeNode> queue = new ArrayDeque<>();

    public List<Integer> inorderTraversal(TreeNode root) {
        while (root != null || !queue.isEmpty()) {
            // 步骤 1
            while (root != null) {
                queue.addLast(root);
                root = root.left;
            }

            // 步骤 2
            root = queue.pollLast();
            res.add(root.val);

            // 步骤 3
            root = root.right;
        }
        return res;
    }

    // 回溯算法思路
    LinkedList<Integer> ressult = new LinkedList<>();

    public List<Integer> inorderTraversal2(TreeNode root) {
        traverse(root);
        return ressult;
    }

    private void traverse(TreeNode root) {
        if (root == null) {
            return;
        }
        traverse(root.left);
        // 中序遍历位置
        ressult.add(root.val);
        traverse(root.right);
    }

    public static void main(String[] args) {
        _094二叉树的中序遍历 obj = new _094二叉树的中序遍历();

        TreeNode root = new TreeNode(1);
        root.right = new TreeNode(2);
        root.right.left = new TreeNode(3);
        Asserts.test(obj.inorderTraversal(root).equals(Arrays.asList(1, 3, 2)));
        Asserts.test(obj.inorderTraversal2(root).equals(Arrays.asList(1, 3, 2)));
    }
}
