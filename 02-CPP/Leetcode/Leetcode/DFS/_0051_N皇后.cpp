//
//  _0051_N皇后.cpp
//  Leetcode
//
//  Created by LiLe on 2021/7/29.
//

#include "_0051_N皇后.hpp"

bool isValid(int row, int col, const vector<string>& board, int n) {
    // 检查列
    for (int i = 0; i < row; i++) {
        if (board[i][col] == 'Q') {
            return false;
        }
    }
    
    // 检查 45 度
    for (int i = row - 1, j = col - 1; i >= 0 && j >= 0; i--, j--) {
        if (board[i][j] == 'Q') {
            return false;
        }
    }
    
    // 检查 135 度
    for (int i = row - 1, j = col + 1; i >= 0 && j < n; i--, j++) {
        if (board[i][j] == 'Q') {
            return false;
        }
    }
    
    return true;
}

void dfs(int row, int n, vector<vector<string>>& result, vector<string>& board) {
    if (row == n) {
        result.push_back(board);
        return;
    }
    
    for (int col = 0; col < n; col++) {
        if (isValid(row, col, board, n)) {
            board[row][col] = 'Q';
            dfs(row + 1, n, result, board);
            board[row][col] = '.';
        }
    }
}

vector<vector<string>> solveNQueens(int n) {
    vector<vector<string>> result;
    vector<string> board(n, string(n, '.'));
    dfs(0, n, result, board);
    return result;
}
