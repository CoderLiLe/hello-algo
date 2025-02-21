package 数学;

import tools.Asserts;

import java.util.HashMap;
import java.util.Map;

public class _149直线上最多的点数 {
    /**
     *  枚举直线 + 枚举统计
     */
    public int maxPoints(int[][] points) {
        int len = points.length;
        int res = 1;
        for (int i = 0; i < len; i++) {
            int[] x = points[i];
            for (int j = i + 1; j < len; j++) {
                int[] y = points[j];
                // 枚举 (i, j) 并统计有多少点在该直线上，起始 count = 2 代表只有 i 和 j 两个点在此线上
                int count = 2;
                for (int k = j + 1; k < len; k++) {
                    int[] z = points[k];
                    // 斜率相同的两条直线要么平行，要么重合，这里直接判定两条直线是否重合即可
                    // (y[1] - x[1]) / (y[0] - x[0]) == (z[1] - y[1]) / (z[0] - y[0])
                    // 为避免计算机除法的精度问题，将「除法判定」巧妙转为「乘法判定」
                    // 等价于 (y[1] - x[1]) * (z[0] - y[0]) == (z[1] - y[1]) * (y[0] - x[0])
                    // 判断是否在同一条直线上
                    int s1 = (y[1] - x[1]) * (z[0] - y[0]);
                    int s2 = (z[1] - y[1]) * (y[0] - x[0]);
                    if (s1 == s2) {
                        count++;
                    }
                }
                res = Math.max(res, count);
            }
        }
        return res;
    }

    /**
     *  枚举直线 + 哈希表统计
     *  先枚举所有可能出现的直线斜率（根据两点确定一条直线，即枚举所有的「点对」），使用「哈希表」统计所有斜率对应的点的数量，在所有值中取个max即是答案
     */
    public int maxPoints2(int[][] points) {
        int len = points.length;
        int res = 1;
        for (int i = 0; i < len; i++) {
            Map<String, Integer> map = new HashMap<>();
            // 由当前点 i 出发的直线所经过的最多点数量
            int count = 0;
            for (int j = i + 1; j < len; j++) {
                int x1 = points[i][0];
                int y1 = points[i][1];
                int x2 = points[j][0];
                int y2 = points[j][1];
                int deltaX = x2 - x1;
                int deltaY = y2 - y1;
                int k = gcd(deltaX, deltaY);
                String key = (deltaX / k) + "_" + (deltaY / k);
                map.put(key, map.getOrDefault(key, 0) + 1);
                count = Math.max(count, map.get(key));
            }
            res = Math.max(res, count + 1);
        }
        return res;
    }

    private int gcd(int deltaX, int deltaY) {
        return deltaY == 0 ? deltaX : gcd(deltaY, deltaX % deltaY);
    }

    public static void main(String[] args) {
        _149直线上最多的点数 obj = new _149直线上最多的点数();

        int[][] points = new int[][]{{1, 1}, {2, 2}, {3, 3}};
        int res = 3;
        Asserts.test(obj.maxPoints(points) == res);
        Asserts.test(obj.maxPoints2(points) == res);
    }
}
