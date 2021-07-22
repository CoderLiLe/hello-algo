package 栈_队列;

import java.util.Deque;
import java.util.LinkedList;

public class _239_滑动窗口最大值 {
	public int[] maxSlidingWindow_deque(int[] nums, int k) {
		if (nums == null || nums.length == 0 || k < 1) return new int[0];
		if (k == 1) return nums;
		
		int[] maxes = new int[nums.length - k + 1];
		
		// peek：取值（偷偷瞥一眼）
		// poll：删除（削）
		// offer：添加（入队）
		
		Deque<Integer> deque = new LinkedList<>();
		for (int i = 0; i < nums.length; i++) {
			// 1、只要 nums[队尾] <= nums[i] ，就删除队尾
			while (!deque.isEmpty() && nums[i] >= nums[deque.peekLast()]) {
				deque.pollLast();
			}
			
			// 2、将 i 加到队尾
			deque.offerLast(i);
			
			// 3、检查窗口的索引是否合法
			int w = i - k + 1;
			if (w < 0) continue;
			
			// 4、检查队头的合法性
			if (deque.peekFirst() < w) {
				// 对头不合法（失效，不在滑动窗口索引范围内）
				deque.pollFirst();
			}
			
			// 5、设置窗口的最大值
			maxes[w] = nums[deque.peekFirst()];
		}
		return maxes;
    }
	
	public int[] maxSlidingWindow(int[] nums, int k) {
		if (nums == null || nums.length == 0 || k < 1) return new int[0];
		if (k == 1) return nums;
		
		int[] maxes = new int[nums.length - k + 1];
		
		// 当前滑动窗口的最大值索引
		int maxIdx = 0;
		// 求出前 k 个元素的最大值索引
		for (int i = 1; i < k; i++) {
			if (nums[i] > nums[maxIdx]) maxIdx = i;
		}
		
		// li 是滑动窗口的最左边索引
		for (int li = 0; li < maxes.length; li++) {
			// ri 是滑动窗口的最右边索引
			int ri = li + k - 1;
			if (maxIdx < li) { // 最大值的索引不在滑动窗口的合理范围内
				// 求 [li, ri] 范围内最大值的索引
				maxIdx = li;
				for (int i = li + 1; i <= ri; i++) {
					if (nums[i] > nums[maxIdx]) maxIdx = i;
				}
			} else if (nums[ri] >= nums[maxIdx]) {
				maxIdx = ri;
			}
			maxes[li] = nums[maxIdx];
		}
		
		return maxes;
	}
}
