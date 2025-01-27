package 字符串;

public class _151_翻转字符串里的单词 {
	/**
	 * 分割 + 倒序
	 * 时间复杂度：O(N)
	 * 空间复杂度：O(N)
	 * @param s
	 * @return
	 */
	public String reverseWords1(String s) {
		if (s == null || s.isEmpty()) {
			return s;
		}

		String[] strings = s.split(" ");
		StringBuilder sb = new StringBuilder();
		for (int i = strings.length - 1; i >= 0; i--) {
			if (!strings[i].isEmpty()) {
				sb.append(strings[i]).append(" ");
			}
		}
		return sb.toString().trim();
	}

	public String reverseWords2(String s) {
		if (s == null) return "";
		char[] chars = s.toCharArray();
		
		// 消除多余的空格
		// 字符串最终的有效长度
		int len = 0;
		// 当前用来存放字符的位置
		int cur = 0;
		// 前一个字符是否为空格字符
		boolean space = true;
		for (int i = 0; i < chars.length; i++) {
			if (chars[i] != ' ') { // chars[i] 是非空格字符
				chars[cur++] = chars[i];
				space = false;
			} else if (space == false) { // chars[i] 是空格字符，chars[i - 1] 是非空格字符
				chars[cur++] = ' ';
				space = true;
			}
		}
		len = space ? (cur - 1) : cur;
		if (len <= 0) return "";
		
		// 对整一个有效字符串进行逆序
		reverse(chars, 0, len);
		
		// 对每个单词进行逆序
		// 前一个空格字符的位置（哨兵）
		int prevSpaceIdx = -1;
		for (int i = 0; i < len; i++) {
			if (chars[i] != ' ') continue;
			// i 是空格字符的位置
			reverse(chars, prevSpaceIdx + 1, i);
			prevSpaceIdx = i;
		}
		
		reverse(chars, prevSpaceIdx + 1, len);
		
		return new String(chars, 0, len);
    }
	
	/**
	 * 将 [li, ri)范围内的字符串进行逆序
	 */
	private void reverse(char[] chars, int li, int ri) {
		ri--;
		while (li < ri) {
			char tmp = chars[li];
			chars[li] = chars[ri];
			chars[ri] = tmp;
			li++;
			ri--;
		}
	}

	/**
	 * 双指针
	 * 算法解析：
	 * 倒序遍历字符串 s ，记录单词左右索引边界 i , j 。
	 * 每确定一个单词的边界，则将其添加至单词列表 res 。
	 * 最终，将单词列表拼接为字符串，并返回即可。
	 *
	 * 时间复杂度 O(N) ： 其中 N 为字符串 s 的长度，线性遍历字符串。
	 * 空间复杂度 O(N) ： 新建的 list(Python) 或 StringBuilder(Java) 中的字符串总长度 ≤N ，占用 O(N) 大小的额外空间。
	 *
	 * @param s
	 * @return
	 */
	public String reverseWords3(String s) {
		s = s.trim();                                    // 删除首尾空格
		int j = s.length() - 1, i = j;
		StringBuilder res = new StringBuilder();
		while (i >= 0) {
			while (i >= 0 && s.charAt(i) != ' ') i--;     // 搜索首个空格
			res.append(s.substring(i + 1, j + 1) + " "); // 添加单词
			while (i >= 0 && s.charAt(i) == ' ') i--;     // 跳过单词间空格
			j = i;                                       // j 指向下个单词的尾字符
		}
		return res.toString().trim();                    // 转化为字符串并返回
	}
}
