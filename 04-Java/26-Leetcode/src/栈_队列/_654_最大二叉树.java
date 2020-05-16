package 栈_队列;

import java.util.Arrays;
import java.util.Stack;

import common.TreeNode;

public class _654_最大二叉树 {
	public TreeNode constructMaximumBinaryTree(int[] nums) {
		if (nums == null || nums.length == 0) return null;
		
		return findRoot(nums, 0, nums.length);
    }
	
	/**
	 * 找出 [l, r) 范围内的根节点
	 */
	private TreeNode findRoot(int[] nums, int l, int r) {
		if (l == r) return null;
		
		int maxIdx = l;
		for (int i = l + 1; i < r; i++) {
			if (nums[maxIdx] < nums[i]) {
				maxIdx = i;
			}
		}
		
		TreeNode root = new TreeNode(nums[maxIdx]);
		root.left = findRoot(nums, l, maxIdx);
		root.right = findRoot(nums, maxIdx+1, r);
		return root;
	}
	
	public int[] parentIndexes(int[] nums) {
		/**
		 * 1、扫描一遍所有元素
		 * 2、保持栈从栈底到栈顶是单调递减的
		 */
		
		int[] lis = new int[nums.length];
		int[] ris = new int[nums.length];
		Stack<Integer> stack = new Stack<>();
		for (int i = 0; i < nums.length; i++) {
			ris[i] = -1;
			while (!stack.isEmpty() && nums[i] > nums[stack.peek()]) {
				ris[stack.pop()] = i;
			}
			lis[i] = stack.isEmpty() ? -1 : stack.peek();
			stack.push(i);
		}
		
		System.out.println(Arrays.toString(lis));
		System.out.println(Arrays.toString(ris));
		
		int[] pis = new int[nums.length];
		for (int i = 0; i < pis.length; i++) {
			if (lis[i] == -1 && ris[i] == -1) {
				// i 位置是根节点
				pis[i] = -1;
				continue;
			}
			
			if (lis[i] == -1) {
				pis[i] = ris[i];
			} else if (ris[i] == -1) {
				pis[i] = lis[i];
			} else if (nums[lis[i]] < nums[ris[i]]) {
				pis[i] = lis[i];
			} else {
				pis[i] = ris[i];
			}
		}
		
		return pis;
	}
	
	public static void main(String[] args) {
		_654_最大二叉树 obj = new _654_最大二叉树();
		int[] nums = {3, 2, 1, 6, 0, 5};
		System.out.println(Arrays.toString(obj.parentIndexes(nums)));
	}
}
