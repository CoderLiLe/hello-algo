package 字符串;

import java.util.HashMap;
import java.util.Map;

public class _003_无重复字符的最长子串 {
	public int lengthOfLongestSubstring(String s) {
		if (s == null) return 0;
		char[] chars = s.toCharArray();
		if (chars.length == 0) return 0;
		
		// 用来保存每一个字符上一次出现的位置
		Map<Character, Integer> preIdxes = new HashMap<>();
		preIdxes.put(chars[0], 0);
		// 以 i - 1 位置字符结尾的最长不重复字符串的开始索引（最左索引）
		int li = 0;
		int max = 1;
		for (int i = 1; i < chars.length; i++) {
			// i 位置字符上一次出现的位置
			Integer pi = preIdxes.get(chars[i]);
			if (pi != null && li <= pi) {
				li = pi + 1;
			}
			// 存储这个字符出现的位置
			preIdxes.put(chars[i], i);
			// 求出最长不重复子串的长度
			max = Math.max(max, i - li + 1);
		}
		return max;
    }
	
	public int lengthOfLongestSubstring2(String s) {
		if (s == null) return 0;
		char[] chars = s.toCharArray();
		if (chars.length == 0) return 0;
		
		// 用来保存每一个字符上一次出现的位置
		int[] prevIdxes = new int[128];
		for (int i = 0; i < prevIdxes.length; i++) {
			prevIdxes[i] = -1;
		}
		prevIdxes[chars[0]] = 0;
		// 以 i - 1 位置字符结尾的最长不重复字符串的开始索引（最左索引）
		int li = 0;
		int max = 1;
		for (int i = 1; i < chars.length; i++) {
			// i 位置字符上一次出现的位置
			int pi = prevIdxes[chars[i]];
			if (li <= pi) {
				li = pi + 1;
			}
			// 存储这个字符出现的位置
			prevIdxes[chars[i]] = i;
			// 求出最长不重复子串的长度
			max = Math.max(max, i - li + 1);
		}
		return max;
    }
}
