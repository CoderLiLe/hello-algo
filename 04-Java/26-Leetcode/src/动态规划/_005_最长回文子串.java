package 动态规划;

public class _005_最长回文子串 {
	/**
	 * 动态规划法
	 */
	public String longestPalindrome(String s) {
		if (s == null) return null;
		char[] cs = s.toCharArray();
		if (cs.length == 0) return s;
		
		// 最长回文子串的长度（至少是1）
		int maxLen = 1;
		// 最长回文子串的开始索引
		int begin = 0;
		boolean[][] dp = new boolean[cs.length][cs.length];
		// 从下到上（i由大到小）
		for (int i = cs.length - 1; i >= 0; i--) {
			// 从左到右（j由小到大）
			for (int j = i; j < cs.length; j++) {
				// cs[i, j] 的长度
				int len = j - i + 1;
				dp[i][j] = (cs[i] == cs[j]) && (len <= 2 || dp[i + 1][j - 1]);
				if (dp[i][j] && len > maxLen) { // 说明 cs[i, j] 是回文子串
					maxLen = len;
					begin = i;
				}
			}
		}
		return new String(cs, begin, maxLen);
    }
	
	/**
	 * 扩展中心法
	 */
	public String longestPalindrome2(String s) {
		if (s == null) return null;
		char[] cs = s.toCharArray();
		if (cs.length <= 1) return s;
		// 最长回文子串的长度（至少是1）
		int maxLen = 1;
		// 最长回文子串的开始索引
		int begin = 0;
		
		for (int i = cs.length - 2; i >= 1; i--) {
			// 以字符为中心向左右扩展
			int len1 = palindromeLength(cs, i - 1, i + 1);
			// 以字符右边的间隙为中心向左右扩展
			int len2 = palindromeLength(cs, i, i + 1);
			len1 = Math.max(len1, len2);
			if (len1 > maxLen) {
				maxLen = len1;
				begin = i - ((maxLen - 1) >> 1);
			}
		}
		// 以 0 号字符右边的间隙为中心的最长回文子串长度是2
		if (cs[0] == cs[1] && maxLen < 2) {
			// cs[0, 1] 就是最长的回文子串
			begin = 0;
			maxLen = 2;
		}
		return new String(cs, begin, maxLen);
    }
	
	private int palindromeLength(char[] cs, int l, int r) {
		while (l >= 0 && r < cs.length && cs[l] == cs[r]) {
			l--;
			r++;
		}
		return r - l - 1;
	}
	
	/**
	 * 扩展中心法2
	 */
	public String longestPalindrome3(String s) {
		if (s == null) return null;
		char[] cs = s.toCharArray();
		if (cs.length <= 1) return s;
		// 最长回文子串的长度（至少是1）
		int maxLen = 1;
		// 最长回文子串的开始索引
		int begin = 0;
		int i = 0;
		while (i < cs.length) {
			int l = i - 1;
			// 找到右边第一个不等于 cs[i] 的位置
			int r = i;
			while (++r < cs.length && cs[r] == cs[i]);
			// r会成为新的 i
			i = r;
			
			// 从 l 向左，从 r 向右扩展
			while (l >= 0 && r < cs.length && cs[l] == cs[r]) {
				l--;
				r++;
			}
			
			// 扩展结束后，cs[l + 1, r)就是刚才找到的最大回文子串
			// ++l 后，l就是刚才找到的最大回文子串的开始索引
			int len = r - ++l;
			if (len > maxLen) {
				maxLen = len;
				begin = l;
			}
		}
		return new String(cs, begin, maxLen);
    }

	/**
	 * 马拉车算法
	 */
	public String longestPalindrome4(String s) {
		if (s == null) return null;
		char[] oldCs = s.toCharArray();
		if (oldCs.length <= 1) return s;

		// 预处理
		char[] cs = preprocess(oldCs);
		// 构建 m 数组
		int[] m = new int[cs.length];

		return new String(cs);
	}

	private char[] preprocess(char[] oldCs) {
		char[] cs = new char[(oldCs.length << 1) + 3];
		cs[0] = '^';
		cs[1] = '#';
		cs[cs.length - 1] = '$';
		for (int i = 0; i < oldCs.length; i++) {
			int idx = (i + 1) << 1;
			cs[idx] = oldCs[i];
			cs[idx + 1] = '#';
		}
		return cs;
	}

	public static void main(String[] args) {
		_005_最长回文子串 obj = new _005_最长回文子串();
		System.out.println(obj.longestPalindrome4("abba"));
	}
}
