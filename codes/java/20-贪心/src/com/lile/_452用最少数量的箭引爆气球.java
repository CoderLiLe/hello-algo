package com.lile;

import com.lile.tools.Asserts;

import java.util.Arrays;

public class _452用最少数量的箭引爆气球 {

    /**
     * 局部最优：当气球出现重叠，一起射，所用弓箭最少。全局最优：把所有气球射爆所用弓箭最少
     * 为了让气球尽可能的重叠，需要对数组进行排序
     * 时间复杂度 : O(NlogN)，排序需要 O(NlogN) 的复杂度
     * 空间复杂度 : O(logN)，java所使用的内置函数用的是快速排序需要 logN 的空间
     */
    public int findMinArrowShots(int[][] points) {
        // 根据气球直径的开始坐标从小到大排序
        // 使用Integer内置比较方法，不会溢出
        Arrays.sort(points, (a, b) -> Integer.compare(a[0], b[0]));

        // points 不为空至少需要一支箭
        int count = 1;
        for (int i = 1; i < points.length; i++) {
            // 气球i和气球i-1不挨着，注意这里不是>=
            if (points[i][0] > points[i - 1][1]) {
                // 需要一支箭
                count++;
            } else {
                // 气球i和气球i-1挨着
                // 更新重叠气球最小右边界
                points[i][1] = Math.min(points[i][1], points[i - 1][1]);
            }
        }
        return count;
    }

    public static void main(String[] args) {
        _452用最少数量的箭引爆气球 obj = new _452用最少数量的箭引爆气球();
        int[][] points = {
                {10, 16},
                {2, 8},
                {1, 6},
                {7, 12}
        };
        Asserts.test(obj.findMinArrowShots(points) == 2);
    }
}
