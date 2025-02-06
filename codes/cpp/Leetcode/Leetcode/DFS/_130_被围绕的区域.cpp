//
//  _130_被围绕的区域.cpp
//  Leetcode
//
//  Created by LiLe on 2021/7/29.
//

#include "_130_被围绕的区域.hpp"
#include <stack>
#include <queue>

/**
 存在错误
 */
void solve(vector<vector<char>>& board) {
    size_t rows = board.size();
    size_t cols = board[0].size();
    if (rows < 3 || cols < 3) return;
    
    for (size_t i = 1; i < rows - 1; i++) {
        for (size_t j = 1; j < cols - 1; j++) {
            /*
             * 被围绕的区间不会存在于边界上，换句话说，任何边界上的 'O' 都不会被填充为 'X'。
             * 任何不在边界上，或不与边界上的 'O' 相连的 'O' 最终都会被填充为 'X'。如果两
             * 个元素在水平或垂直方向相邻，则称它们是“相连”的。
             * */
            
            // left up
            if (1 == i && 1 == j) {
                if (board[0][1] != 'O' && board[1][0] != 'O') board[1][1] = 'X';
            } else if (1 == i && cols - 2 == j) { // right up
                if (board[1][cols - 1] != 'O' && board[0][cols - 2] != 'O') board[1][cols - 2] = 'X';
            } else if (rows - 2 == i && 1 == j) { // left bottom
                if (board[rows - 2][0] != 'O' && board[rows - 1][1] != 'O') board[rows - 2][1] = 'X';
            } else if (rows - 2 == i && cols - 2 == j) { // right bottom
                if (board[rows - 2][cols - 1] != 'O' && board[rows - 1][cols - 2] != 'O') board[rows - 2][cols - 2] = 'X';
            } else {
                if (board[i][j] == 'O') {
                    board[i][j] = 'X';
                }
            }
        }
    }
}

#pragma mark - dfs递归

void dfs(vector<vector<char>>& board, int i, int j) {
    if (i < 0 || j < 0 || i >= board.size() || j >= board[0].size() || board[i][j] == 'X' || board[i][j] == '#') {
        return;
    }
    
    board[i][j] = '#';
    dfs(board, i - 1, j); // 上
    dfs(board, i + 1, j); // 下
    dfs(board, i, j - 1); // 左
    dfs(board, i, j + 1); // 右
}

#pragma mark - dfs非递归
void dfs2(vector<vector<char>>& board, int i, int j) {
    stack<pair<int, int>> st;
    pair<int, int> pos(i, j);
    st.push(pos);
    board[i][j] = '#';
    while (!st.empty()) {
        pair<int, int> current = st.top();
        // 上
        if (current.first - 1 >= 0 && 'O' == board[current.first - 1][current.second]) {
            pair<int, int> upPos(current.first - 1, current.second);
            st.push(upPos);
            board[current.first - 1][current.second] = '#';
            continue;
        }
        
        // 下
        if (current.first + 1 <= board.size() - 1 && 'O' == board[current.first + 1][current.second]) {
            pair<int, int> downPos(current.first + 1, current.second);
            st.push(downPos);
            board[current.first + 1][current.second] = '#';
            continue;
        }
        
        // 左
        if (current.second - 1 >= 0 && 'O' == board[current.first][current.second - 1]) {
            pair<int, int> leftPos(current.first, current.second - 1);
            st.push(leftPos);
            board[current.first][current.second - 1] = '#';
            continue;
        }
        
        // 右
        if (current.second + 1 <= board[0].size() - 1 && 'O' == board[current.first][current.second + 1]) {
            pair<int, int> rightPos(current.first, current.second + 1);
            st.push(rightPos);
            board[current.first][current.second + 1] = '#';
            continue;
        }
        
        // 如果上下左右都搜索不到,本次搜索结束，弹出stack
        st.pop();
    }
}

#pragma mark - bfs非递归
// TODO: 存在BUG
void bfs(vector<vector<char>>& board, size_t i, size_t j) {
    queue<pair<size_t, size_t>> q;
    q.push({i, j});
    board[i][j] = '#';
    while (!q.empty()) {
        auto [row, col] = q.front();
        q.pop();
        
        // 上
        if (row - 1 >= 0 && 'O' == board[row - 1][col]) {
            q.push({row - 1, col});
            board[row - 1][col] = '#';
        }
        
        // 下
        if (row + 1 <= board.size() - 1 && 'O' == board[row + 1][col]) {
            q.push({row + 1, col});
            board[row + 1][col] = '#';
        }
        
        // 左
        if (col - 1 >= 0 && 'O' == board[row][col - 1]) {
            q.push({row, col - 1});
            board[row][col - 1] = '#';
        }
        
        // 右
        if (col + 1 <= board[0].size() - 1 && 'O' == board[row][col + 1]) {
            q.push({row, col + 1});
            board[row][col + 1] = '#';
        }
    }
}

void solve2(vector<vector<char>>& board) {
    size_t rows = board.size();
    size_t cols = board[0].size();
    if (rows < 3 || cols < 3) return;
    
    for (size_t i = 0; i < rows; i++) {
        for (size_t j = 0; j < cols; j++) {
            bool isEdge = (0 == i || 0 == j || rows - 1 == i || cols - 1 == j);
            if (isEdge && 'O' == board[i][j]) {
//                dfs(board, i, j);
//                dfs2(board, i, j);
                bfs(board, i, j);
            }
        }
    }
    
    for (int i = 0; i < rows; i++) {
        for (int j = 0; j < cols; j++) {
            if ('O' == board[i][j]) {
                board[i][j] = 'X';
            }
            
            if ('#' == board[i][j]) {
                board[i][j] = 'O';
            }
        }
    }
}





