package 二叉树.构建;

import common.FourNode;

public class _427建立四叉树 {
    private int[][] grid;
    public FourNode construct(int[][] grid) {
        this.grid = grid;
        return dfs(0, grid.length - 1, 0, grid.length - 1);
    }

    private FourNode dfs(int left, int right, int top, int bottom) {
        boolean isLeaf = true;
        int val = grid[top][left];
        for (int i = top; i <= bottom && isLeaf; i++) {
            for (int j = left; j <= right && isLeaf; j++) {
                if (grid[i][j] != val) {
                    isLeaf = false;
                }
            }
        }
        if (isLeaf) {
            return new FourNode(1 == val, true);
        } else {
            FourNode root = new FourNode(1 == val, false);

            // 由题知道矩阵的行和列相等，且均为偶数
            // 此为偏左的中间点
            int midX = left + ((right - left) >> 1);
            // 此为偏上的中间点
            int midY = top + ((bottom - top) >> 1);
            root.topLeft = dfs(left, midX, top, midY);
            root.topRight = dfs(midX + 1, right, top, midY);
            root.bottomLeft = dfs(left, midX, midY + 1, bottom);
            root.bottomRight = dfs(midX + 1, right, midY + 1, bottom);
            return root;
        }
    }

    public static void main(String[] args) {
        _427建立四叉树 obj = new _427建立四叉树();

        int[][] grid = {{0,1},{1,0}};
        obj.construct(grid);
    }
}
