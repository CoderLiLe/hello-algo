package 二叉树.构建;

import common.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

public class _108将有序数组转换为二叉搜索树 {

    public TreeNode sortedArrayToBST(int[] nums) {
        return dfs(nums, 0, nums.length);
    }

    /**
     * 把 nums[left] 到 nums[right-1] 转成平衡二叉搜索树
     *
     * @param nums
     * @param left
     * @param right
     * @return
     */
    private TreeNode dfs(int[] nums, int left, int right) {
        if (left == right) {
            return null;
        }
        int m = (left + right) >>> 1;
        return new TreeNode(nums[m], dfs(nums, left, m), dfs(nums, m + 1, right));
    }

    public TreeNode sortedArrayToBST2(int[] nums) {
        if (nums.length == 0) return null;

        // 根节点初始化
        TreeNode root = new TreeNode(-1);
        Queue<TreeNode> nodeQueue = new LinkedList<>();
        Queue<Integer> leftQueue = new LinkedList<>();
        Queue<Integer> rightQueue = new LinkedList<>();

        // 根节点入队列
        nodeQueue.offer(root);
        // 0为左区间下标初始位置
        leftQueue.offer(0);
        // nums.size() - 1为右区间下标初始位置
        rightQueue.offer(nums.length - 1);

        while (!nodeQueue.isEmpty()) {
            TreeNode currNode = nodeQueue.poll();
            int left = leftQueue.poll();
            int right = rightQueue.poll();
            int mid = left + ((right - left) >> 1);

            // 将mid对应的元素给中间节点
            currNode.val = nums[mid];

            // 处理左区间
            if (left <= mid - 1) {
                currNode.left = new TreeNode(-1);
                nodeQueue.offer(currNode.left);
                leftQueue.offer(left);
                rightQueue.offer(mid - 1);
            }

            // 处理右区间
            if (right >= mid + 1) {
                currNode.right = new TreeNode(-1);
                nodeQueue.offer(currNode.right);
                leftQueue.offer(mid + 1);
                rightQueue.offer(right);
            }
        }
        return root;
    }

    public static void main(String[] args) {
        _108将有序数组转换为二叉搜索树 obj = new _108将有序数组转换为二叉搜索树();
        int[] nums = {-10, -3, 0, 5, 9};
        TreeNode treeNode = obj.sortedArrayToBST(nums);
        TreeNode treeNode2 = obj.sortedArrayToBST2(nums);
    }
}
