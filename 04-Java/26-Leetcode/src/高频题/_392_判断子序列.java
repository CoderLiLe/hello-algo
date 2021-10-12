package 高频题;

import java.util.ArrayList;
import java.util.List;

public class _392_判断子序列 {

    public static void main(String[] args) {
        String[] sn = {"abc", "axc"};
        String t = "ahbgdc";
        _392_判断子序列 obj = new _392_判断子序列();
        boolean[] result = obj.isSubsequence(sn, t);
        for (boolean b : result) {
            System.out.println(b);
        }
    }

    /**
     * 双指针法：i, j 分别指向 s, t，一边前进一边匹配子序列
     * @param s
     * @param t
     * @return
     */
    public boolean isSubsequence(String s, String t) {
        int i = 0, j = 0;
        while (i < s.length() && j < t.length()) {
            if (s.charAt(i) == t.charAt(j)) {
                i++;
            }
            j++;
        }

        return s.length() == i;
    }

    /**
     * 贪心算法
     * 局部最优：当前字符是否匹配
     * 全局最优：所有字符匹配
     * 无后效性：当前字符是否匹配不影响后续字符的匹配
     * @param s
     * @param t
     * @return
     */
    public boolean isSubsequence2(String s, String t) {
        if (0 == s.length()) return true;

        char[] sc = s.toCharArray();
        char[] tc = t.toCharArray();

        // s 中字符的索引
        int i = 0;
        for (char c : tc) {
            if (c == sc[i]) {
                i++;
                if (sc.length == i) {
                    return true;
                }
            }
        }

        return false;
    }

    boolean[] isSubsequence(String[] sn, String t) {
        // 对 t 进行预处理
        // 用一个字典 index 将每个字符出现的索引位置按顺序存储下来
        List<Integer>[] index = new ArrayList[256];
        for (int i = 0; i < t.length(); i++) {
            char c = t.charAt(i);
            if (index[c] == null) {
                index[c] = new ArrayList<>();
            }
            index[c].add(i);
        }

        boolean[] result = new boolean[sn.length];
        for (Integer i = 0; i < sn.length; i++) {
            String s = sn[i];
            boolean isSubsequence = isSubsequence3(s, index);
            result[i] = isSubsequence;
        }
        return result;
    }

    boolean isSubsequence3(String s, List<Integer>[] index) {
        // 串 t 上的指针
        int j = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            // 整个 t 压根儿没有字符 c
            if (index[c] == null) {
                return false;
            }

            int pos = leftBounds(index[c], j);
            // 二分搜索区间没有找到字符 c
            if (pos == index[c].size()) {
                return false;
            }

            // 向前移动指针 j
            j = index[c].get(pos) + 1;
        }
        return true;
    }

    int leftBounds(List<Integer> arr, int tar) {
        int lo = 0, hi = arr.size();
        while (lo < hi) {
            int mid = lo + (hi - lo) / 2;
            if (tar > arr.get(mid)) {
                lo = mid + 1;
            } else {
                hi = mid;
            }
        }
        return lo;
    }
}
