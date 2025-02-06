package 二叉树.构建;

import common.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode.cn/problems/construct-binary-tree-from-preorder-and-inorder-traversal
 */
public class _105从前序与中序遍历序列构造二叉树 {
    /**
     * 中序区间：[inorderBegin, inorderEnd)，前序区间[preorderBegin, preorderEnd)
     */
    private TreeNode traversal(List<Integer> inorder, int inorderBegin, int inorderEnd, List<Integer> preorder, int preorderBegin, int preorderEnd) {
        if (preorderBegin == preorderEnd) return null;

        int rootValue = preorder.get(preorderBegin); // 注意用preorderBegin 不要用0
        TreeNode root = new TreeNode(rootValue);

        if (preorderEnd - preorderBegin == 1) return root;

        int delimiterIndex;
        for (delimiterIndex = inorderBegin; delimiterIndex < inorderEnd; delimiterIndex++) {
            if (inorder.get(delimiterIndex) == rootValue) break;
        }

        // 切割中序数组
        // 中序左区间，左闭右开[leftInorderBegin, leftInorderEnd)
        int leftInorderBegin = inorderBegin;
        int leftInorderEnd = delimiterIndex;
        // 中序右区间，左闭右开[rightInorderBegin, rightInorderEnd)
        int rightInorderBegin = delimiterIndex + 1;
        int rightInorderEnd = inorderEnd;

        // 切割前序数组
        // 前序左区间，左闭右开[leftPreorderBegin, leftPreorderEnd)
        int leftPreorderBegin = preorderBegin + 1;
        int leftPreorderEnd = preorderBegin + 1 + delimiterIndex - inorderBegin; // 终止位置是起始位置加上中序左区间的大小size
        // 前序右区间, 左闭右开[rightPreorderBegin, rightPreorderEnd)
        int rightPreorderBegin = preorderBegin + 1 + (delimiterIndex - inorderBegin);
        int rightPreorderEnd = preorderEnd;

        root.left = traversal(inorder, leftInorderBegin, leftInorderEnd, preorder, leftPreorderBegin, leftPreorderEnd);
        root.right = traversal(inorder, rightInorderBegin, rightInorderEnd, preorder, rightPreorderBegin, rightPreorderEnd);

        return root;
    }

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        if (inorder.length == 0 || preorder.length == 0) return null;

        List<Integer> inorderList = new ArrayList<>();
        List<Integer> preorderList = new ArrayList<>();
        for (int value : inorder) {
            inorderList.add(value);
        }
        for (int value : preorder) {
            preorderList.add(value);
        }

        // 参数坚持左闭右开的原则
        return traversal(inorderList, 0, inorderList.size(), preorderList, 0, preorderList.size());
    }

    public static void main(String[] args) {
        _105从前序与中序遍历序列构造二叉树 obj = new _105从前序与中序遍历序列构造二叉树();
        int[] inorder = {9, 3, 15, 20, 7};
        int[] postorder = {9, 15, 7, 20, 3};
        TreeNode root = obj.buildTree(inorder, postorder);
    }
}
