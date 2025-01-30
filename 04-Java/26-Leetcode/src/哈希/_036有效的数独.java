package 哈希;

import tools.Asserts;

public class _036有效的数独 {
    /**
     * 检查一个数独是否有效
     * 通过检查数独板上的每个数字，确保它们在所在的行、列和3x3子网格中没有重复
     *
     * @param board 数独板，表示一个9x9的字符数组，其中'.'表示空格
     * @return 如果数独板有效返回true，否则返回false
     */
    public boolean isValidSudoku(char[][] board) {
        // 用于记录每个数字在每行出现的次数
        int[][] rows = new int[9][9];
        // 用于记录每个数字在每列出现的次数
        int[][] columns = new int[9][9];
        // 用于记录每个数字在每个3x3子网格中出现的次数
        int[][][] subboxes = new int[3][3][9];

        // 遍历数独板
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                char c = board[i][j];
                // 如果当前位置不是空格
                if (c != '.') {
                    // 计算当前数字的索引（0-8）
                    int index = c - '0' - 1;
                    // 在对应的行、列和3x3子网格中增加该数字的出现次数
                    rows[i][index]++;
                    columns[j][index]++;
                    subboxes[i / 3][j / 3][index]++;
                    // 如果任何行、列或3x3子网格中某个数字出现次数超过1，则数独无效
                    if (rows[i][index] > 1 || columns[j][index] > 1 || subboxes[i / 3][j / 3][index] > 1) {
                        return false;
                    }
                }
            }
        }

        // 如果所有数字都符合规则，则数独有效
        return true;
    }

    public static void main(String[] args) {
        _036有效的数独 obj = new _036有效的数独();
        char[][] board = {
                {'5','3','.','.','7','.','.','.','.'}
                ,{'6','.','.','1','9','5','.','.','.'}
                ,{'.','9','8','.','.','.','.','6','.'}
                ,{'8','.','.','.','6','.','.','.','3'}
                ,{'4','.','.','8','.','3','.','.','1'}
                ,{'7','.','.','.','2','.','.','.','6'}
                ,{'.','6','.','.','.','.','2','8','.'}
                ,{'.','.','.','4','1','9','.','.','5'}
                ,{'.','.','.','.','8','.','.','7','9'}
        };
        Asserts.test(obj.isValidSudoku(board));
    }
}
