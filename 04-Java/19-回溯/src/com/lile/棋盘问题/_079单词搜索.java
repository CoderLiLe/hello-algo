package com.lile.棋盘问题;

import com.lile.tools.Asserts;

/**
 * https://leetcode.cn/problems/word-search/description
 */
public class _079单词搜索 {
    /**
     * 检查二维字符数组board中是否存在单词word
     *
     * @param board 二维字符数组，代表了单词搜索的范围
     * @param word 需要搜索的单词
     * @return 如果board中存在单词word，则返回true；否则返回false
     */
    public boolean exist(char[][] board, String word) {
        // 将单词转换为字符数组，便于逐个字符匹配
        char[] words = word.toCharArray();
        // 遍历board的每一个元素
        for(int i = 0; i < board.length; i++) {
            for(int j = 0; j < board[0].length; j++) {
                // 如果当前元素与单词的第一个字符匹配，则尝试从该元素开始进行深度优先搜索
                if (backtrack(board, words, i, j, 0)) {
                    return true;
                }
            }
        }
        // 如果遍历结束后没有找到匹配的单词，则返回false
        return false;
    }

    /**
     * 执行回溯算法以在字母板上寻找单词
     * 时间复杂度 O(3^K*MN) ： 最差情况下，需要遍历矩阵中长度为 K 字符串的所有方案
     * 空间复杂度 O(K)
     *
     * @param board 字母板，表示一个二维字符数组
     * @param word 待搜索的单词，表示一个字符数组
     * @param i 当前检查的单元格的行坐标
     * @param j 当前检查的单元格的列坐标
     * @param k 当前检查的单词字符索引
     * @return 如果在字母板上找到完整的单词，则返回true；否则返回false
     */
    boolean backtrack(char[][] board, char[] word, int i, int j, int k) {
        // 检查当前位置是否超出字母板范围或当前字符不匹配，如果不满足条件则返回false
        if (i >= board.length || i < 0 || j >= board[0].length || j < 0 || board[i][j] != word[k]) {
            return false;
        }
        // 如果已经到达单词的最后一个字符，说明已成功找到单词，返回true
        if (k == word.length - 1) {
            return true;
        }
        // 将当前单元格的字符临时修改为特殊字符，以标记它已被访问
        board[i][j] = '\0';
        // 尝试在四个方向上继续搜索剩余的字符，如果任一方向返回true，则说明找到了单词
        boolean res = backtrack(board, word, i + 1, j, k + 1) || backtrack(board, word, i - 1, j, k + 1) ||
                backtrack(board, word, i, j + 1, k + 1) || backtrack(board, word, i, j - 1, k + 1);
        // 回溯，恢复当前单元格的字符，以便其他路径可以重新访问此单元格
        board[i][j] = word[k];
        // 返回是否找到单词的结果
        return res;
    }

    public static void main(String[] args) {
        _079单词搜索 obj = new _079单词搜索();
        char[][] board = {
                {'A','B','C','E'},
                {'S','F','C','S'},
                {'A','D','E','E'}
        };
        String word = "ABCCED";
        Asserts.test(obj.exist(board, word));
    }
}
