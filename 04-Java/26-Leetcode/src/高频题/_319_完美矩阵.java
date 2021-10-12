package 高频题;

import java.util.HashSet;
import java.util.Set;

public class _319_完美矩阵 {

    /**
     * 想判断最终形成的图形是否是完美矩形，需要从「面积」和「顶点」两个角度来处理
     * @param rectangles
     * @return
     */
    public boolean isRectangleCover(int[][] rectangles) {
        // (X1, Y1)：完美矩形左下角顶点坐标
        int X1 = Integer.MAX_VALUE, Y1 = Integer.MAX_VALUE;
        // (X2, Y2)：完美矩形右上角顶点坐标
        int X2 = Integer.MIN_VALUE, Y2 = Integer.MIN_VALUE;

        // 【面积】记录所有小矩形的面积之和
        int actualArea = 0;
        // 【顶点】记录最终图形的顶点
        Set<String> points = new HashSet<>();
        for (int[] rectangle : rectangles) {
            int x1 = rectangle[0];
            int y1 = rectangle[1];
            int x2 = rectangle[2];
            int y2 = rectangle[3];

            X1 = Math.min(X1, x1);
            Y1 = Math.min(Y1, y1);
            X2 = Math.max(X2, x2);
            Y2 = Math.max(Y2, y2);

            // 累加所有小矩形的面积
            actualArea += (x2 - x1) * (y2 - y1);

            String[] ps = {x1 + "," + y1, x1 + "," + y2, x2 + "," + y1, x2 + "," + y2};
            for (String p : ps) {
                if (points.contains(p)) {
                    // 如果某一个顶点 p 存在于集合 points 中，则将它删除
                    points.remove(p);
                } else {
                    // 如果不存在于集合 points 中，则将它插入
                    points.add(p);
                }
            }
        }

        // 计算完美矩形的理论面积
        int expectedArea = (X2 - X1) * (Y2 - Y1);
        // 面积应该相同
        if (actualArea != expectedArea) return false;

        // 判断最终留下的顶点个数是否为4
        if (points.size() != 4) return false;

        // 判断留下的 4 个顶点是否是完美矩形的顶点
        if (!points.contains(X1 + "," + Y1)
                || !points.contains(X2 + "," + Y1)
                || !points.contains(X1 + "," + Y2)
                || !points.contains(X1 + "," + Y2)) {
            return false;
        }

        // 面积和顶点都对应，说明矩形是完美矩形
        return true;
    }

    public static void main(String[] args) {
        _319_完美矩阵 obj = new _319_完美矩阵();
        int[][] rectangles = {
                {1,1,3,3},
                {3,1,4,2},
                {3,2,4,4},
                {1,3,2,4},
                {2,3,3,4}
        };
        System.out.println(obj.isRectangleCover(rectangles));

        int[][] rectangles2 = {
                {1,1,2,3},
                {1,3,2,4},
                {3,1,4,2},
                {3,2,4,4}
        };
        System.out.println(obj.isRectangleCover(rectangles2));
    }
}
