package 哈希;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * 面试题 16.14. 最佳直线
 * 中等
 *
 * 给定一个二维平面及平面上的 N 个点列表Points，其中第i个点的坐标为Points[i]=[Xi,Yi]。请找出一条直线，其通过的点的数目最多。
 *
 * 设穿过最多点的直线所穿过的全部点编号从小到大排序的列表为S，你仅需返回[S[0],S[1]]作为答案，若有多条直线穿过了相同数量的点，则选择S[0]值较小的直线返回，S[0]相同则选择S[1]值较小的直线返回。
 *
 * 示例：
 *
 * 输入： [[0,0],[1,1],[1,0],[2,0]]
 * 输出： [0,2]
 * 解释： 所求直线穿过的3个点的编号为[0,2,3]
 * 提示：
 *
 * 2 <= len(Points) <= 300
 * len(Points[i]) = 2
 */

public class 面16_14最佳直线 {
    private Map<Double, List<Integer>> map;

    private int[] bestLine(int[][] points) {
        map = new LinkedHashMap<>();
        List<Integer> res = new ArrayList<>();
        for (int i = 0; i < points.length - 1; ++i) {
            for (int j = i + 1; j < points.length; ++j) {
                int diffx = points[j][0] - points[i][0];
                int diffy = points[j][1] - points[i][1];
                double k = compute(diffx, diffy);
                List<Integer> list;
                if (map.get(k) == null) {
                    list = new ArrayList<>();
                    list.add(i);
                    list.add(j);
                    map.put(k, list);
                } else {
                    list = map.get(k);
                    list.add(j);
                    map.put(k, list);
                }
                if (list.size() > res.size()) {
                    res = list;
                }
            }
            map = new LinkedHashMap<>();
        }
        return new int[]{res.get(0), res.get(1)};
    }

    private double compute(int diffx, int diffy) {
        if (diffx == 0) {
            return -12345.0;
        } else if (diffy == 0) {
            return 0.0;
        } else {
            return (double)diffy / (double)diffx;
        }
    }

    public static void main(String[] args) {
        面16_14最佳直线 bestLine = new 面16_14最佳直线();

        int[][] points = {{0,0},{1,1},{1,0},{2,0}};
        int[] res = bestLine.bestLine(points);
        for (int num : res) {
            System.out.print(num + " ");
        }

        System.out.println();
        System.out.println("------------");

        int[][] points2 = {{-3320,51525},{-2214,-4833},{2400,13565}};
        int[] res2 = bestLine.bestLine(points2);
        for (int num : res2) {
            System.out.print(num + " ");
        }
    }
}
