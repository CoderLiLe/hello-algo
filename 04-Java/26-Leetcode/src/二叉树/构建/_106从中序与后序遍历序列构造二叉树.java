package 二叉树.构建;

import common.TreeNode;

import java.util.ArrayList;
import java.util.List;

public class _106从中序与后序遍历序列构造二叉树 {
    private TreeNode traversal(List<Integer> inorder, List<Integer> postorder) {
        if (postorder.size() == 0) return null;

        // 后序遍历数组最后一个元素，就是当前的中间节点
        int rootValue = postorder.get(postorder.size() - 1);
        TreeNode root = new TreeNode(rootValue);

        // 叶子节点
        if (postorder.size() == 1) return root;

        // 找到中序遍历的切割点
        int delimiterIndex = 0;
        for (; delimiterIndex < inorder.size(); delimiterIndex++) {
            if (inorder.get(delimiterIndex) == rootValue) break;
        }

        // 切割中序数组
        // 左闭右开区间：[0, delimiterIndex)
        List<Integer> leftInorder = inorder.subList(0, delimiterIndex);
        // [delimiterIndex + 1, end)
        List<Integer> rightInorder = inorder.subList(delimiterIndex + 1, inorder.size());

        // postorder 舍弃末尾元素
        postorder.remove(postorder.size() - 1);

        // 切割后序数组
        // 依然左闭右开，注意这里使用了左中序数组大小作为切割点
        // [0, leftInorder.size)
        List<Integer> leftPostorder = postorder.subList(0, leftInorder.size());
        // [leftInorder.size(), end)
        List<Integer> rightPostorder = postorder.subList(leftInorder.size(), postorder.size());

        root.left = traversal(leftInorder, leftPostorder);
        root.right = traversal(rightInorder, rightPostorder);

        return root;
    }

    public TreeNode buildTree(int[] inorder, int[] postorder) {
        if (inorder.length == 0 || postorder.length == 0) return null;

        List<Integer> inorderList = new ArrayList<>();
        List<Integer> postorderList = new ArrayList<>();
        for (int value : inorder) {
            inorderList.add(value);
        }
        for (int value : postorder) {
            postorderList.add(value);
        }

        return traversal(inorderList, postorderList);
    }

    /**
     * 中序区间：[inorderBegin, inorderEnd)，后序区间[postorderBegin, postorderEnd)
     */
    private TreeNode traversal(List<Integer> inorder, int inorderBegin, int inorderEnd, List<Integer> postorder, int postorderBegin, int postorderEnd) {
        if (postorderBegin == postorderEnd) return null;

        int rootValue = postorder.get(postorderEnd - 1);
        TreeNode root = new TreeNode(rootValue);

        if (postorderEnd - postorderBegin == 1) return root;

        int delimiterIndex;
        for (delimiterIndex = inorderBegin; delimiterIndex < inorderEnd; delimiterIndex++) {
            if (inorder.get(delimiterIndex) == rootValue) break;
        }

        // 切割中序数组
        // 左中序区间，左闭右开[leftInorderBegin, leftInorderEnd)
        int leftInorderBegin = inorderBegin;
        int leftInorderEnd = delimiterIndex;
        // 右中序区间，左闭右开[rightInorderBegin, rightInorderEnd)
        int rightInorderBegin = delimiterIndex + 1;
        int rightInorderEnd = inorderEnd;

        // 切割后序数组
        // 左后序区间，左闭右开[leftPostorderBegin, leftPostorderEnd)
        int leftPostorderBegin = postorderBegin;
        int leftPostorderEnd = postorderBegin + delimiterIndex - inorderBegin; // 终止位置是 需要加上 中序区间的大小size
        // 右后序区间，左闭右开[rightPostorderBegin, rightPostorderEnd)
        int rightPostorderBegin = postorderBegin + (delimiterIndex - inorderBegin);
        int rightPostorderEnd = postorderEnd - 1; // 排除最后一个元素，已经作为节点了

        root.left = traversal(inorder, leftInorderBegin, leftInorderEnd, postorder, leftPostorderBegin, leftPostorderEnd);
        root.right = traversal(inorder, rightInorderBegin, rightInorderEnd, postorder, rightPostorderBegin, rightPostorderEnd);

        return root;
    }

    public TreeNode buildTree2(int[] inorder, int[] postorder) {
        if (inorder.length == 0 || postorder.length == 0) return null;

        List<Integer> inorderList = new ArrayList<>();
        List<Integer> postorderList = new ArrayList<>();
        for (int value : inorder) {
            inorderList.add(value);
        }
        for (int value : postorder) {
            postorderList.add(value);
        }

        // 左闭右开的原则
        return traversal(inorderList, 0, inorderList.size(), postorderList, 0, postorderList.size());
    }

    public static void main(String[] args) {
        _106从中序与后序遍历序列构造二叉树 obj = new _106从中序与后序遍历序列构造二叉树();
        int[] inorder = {9, 3, 15, 20, 7};
        int[] postorder = {9, 15, 7, 20, 3};
        TreeNode root = obj.buildTree(inorder, postorder);
        TreeNode root2 = obj.buildTree2(inorder, postorder);
    }
}
