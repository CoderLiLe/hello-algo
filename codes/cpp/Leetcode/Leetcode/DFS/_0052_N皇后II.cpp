//
//  _0052_N皇后II.cpp
//  Leetcode
//
//  Created by LiLe on 2021/7/29.
//

#include "_0052_N皇后II.hpp"
#include "_0051_N皇后.hpp"

void backtracking(int row, int n, int& totalNum, vector<string>& board) {
    if (row == n) {
        totalNum++;
        return;
    }
    
    for (int col = 0; col < n; col++) {
        if (queueuIsValidPlace(row, col, board, n)) {
            board[row][col] = 'Q';
            backtracking(row + 1, n, totalNum, board);
            board[row][col] = '.';
        }
    }
}


int totalNQueens(int n) {
    int totalNum = 0;
    vector<string> board(n, string(n, '.'));
    backtracking(0, n, totalNum, board);
    return totalNum;
}
