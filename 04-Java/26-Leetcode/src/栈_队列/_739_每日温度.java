package 栈_队列;

import java.util.Stack;

public class _739_每日温度 {
	public int[] dailyTemperatures(int[] T) {
		if (T== null || T.length == 0) return null;
		
		int[] result = new int[T.length];
		Stack<Integer> stack = new Stack<>();
		for (int i = 0; i < T.length; i++) {
			while (!stack.isEmpty() && T[i] > T[stack.peek()]) {
				Integer topIdx = stack.peek();
				result[stack.pop()] = i - topIdx;
			}
			stack.push(i);
		}
		
		return result;
    }
	
	/**
	 * 倒推法
	 * 
	 * i 用来扫描所有的元素，从右往左扫描（i逐渐递减），一开始 i 指向倒数第 2 个元素
	 * 对于每一个 i，一开始令 j = i + 1
	 * 1> 如果 T[i] < T[j]，那么 values[i] = j - i，然后 i--
	 * 2> 如果 values[j] == 0，那么 values[i] = 0，然后 i--
	 * 3> 否则，设置 j = j + values[j]，回到步骤 1
	 */
	public int[] dailyTemperatures2(int[] T) {
		if (T== null || T.length == 0) return null;
		
		int[] result = new int[T.length];
		int j;
		for (int i = T.length - 2; i >= 0; i--) {
			j = i + 1;
			while (true) {
				if (T[i] < T[j]) {
					result[i] = j - i;
					break;
				} else if (result[j] == 0) {
					result[i] = 0;
					break;
				} else {
					j = j + result[j];
				}
			}
		}
		
		return result;
    }
}
