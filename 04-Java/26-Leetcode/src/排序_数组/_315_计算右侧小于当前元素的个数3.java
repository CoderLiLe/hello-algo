package 排序_数组;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class _315_计算右侧小于当前元素的个数3 {

    /**
     * 使用二叉排序树，倒着插入结点，在插入结点的同时记录比其小的数量
     * T(n) = nlogn
     *
     * 二叉排序树（Binary Sort Tree），又称二叉查找树（Binary Search Tree)
     */
    public List<Integer> countSmaller(int[] nums) {
        List<Integer> ret = new ArrayList<>();
        int len = nums.length;
        if (nums == null || len == 0) return ret;

        Integer[] res = new Integer[len];
        for (int i = 0; i < res.length; i++) {
            res[i] = 0;
        }
        TreeNode root = null;
        for (int i = len - 1; i >= 0; i--) {
            root = insert(root, new TreeNode(nums[i]), res, i);
        }

        ret = Arrays.asList(res);
        return ret;
    }

    private TreeNode insert(TreeNode root, TreeNode node, Integer[] res, int i) {
        if (root == null) {
            root = node;
            return root;
        }

        if (root.val >= node.val) {
            root.count++;
            root.left = insert(root.left, node, res, i);
        } else {
            res[i] += root.count + 1;
            root.right = insert(root.right, node, res, i);
        }
        return root;
    }

    private class TreeNode {
        int val;
        int count;
        TreeNode left;
        TreeNode right;

        public TreeNode(int val) {
            this.val = val;
            this.count = 0;
            this.left = null;
            this.right = null;
        }
    }


    public static void main(String[] args) {
        _315_计算右侧小于当前元素的个数3 obj = new _315_计算右侧小于当前元素的个数3();
//        int[] nums = new int[] {5, 2, 6, 1};
        int[] nums = new int[] {1, 0, 2};
        System.out.println(obj.countSmaller(nums));
    }
}
