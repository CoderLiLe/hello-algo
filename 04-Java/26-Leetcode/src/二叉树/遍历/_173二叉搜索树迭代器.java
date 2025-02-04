package 二叉树.遍历;

import common.TreeNode;

import java.util.ArrayList;
import java.util.List;

public class _173二叉搜索树迭代器 {
    private int idx;
    private List<Integer> arr;

    public _173二叉搜索树迭代器(TreeNode root) {
        idx = 0;
        arr = new ArrayList<>();
        inorderTraversal(root, arr);
    }

    public int next() {
        return arr.get(idx++);
    }

    public boolean hasNext() {
        return idx < arr.size();
    }

    private void inorderTraversal(TreeNode root, List<Integer> arr) {
        if (root == null) {
            return;
        }
        inorderTraversal(root.left, arr);
        arr.add(root.val);
        inorderTraversal(root.right, arr);
    }
}
