package 细节实现;

import java.util.ArrayList;
import java.util.List;

/**
 * 119. 杨辉三角 II
 *
 * 给定一个非负索引 k，其中 k ≤ 33，返回杨辉三角的第 k 行。
 *
 * 在杨辉三角中，每个数是它左上方和右上方的数的和。
 *
 * 示例:
 *
 * 输入: 3
 * 输出: [1,3,3,1]
 * 进阶：
 *
 * 你可以优化你的算法到 O(k) 空间复杂度吗？
 */
public class _119_杨辉三角II {

    public static void main(String[] args) {
        System.out.println(getRow(3));
    }

    /**
     * 滚动数组
     */
    public static List<Integer> getRow(int rowIndex) {
        List<Integer> resultList = new ArrayList<>();
        for (int i = 0; i <= rowIndex; i++) {
            for (int j = i - 1; j > 0; j--) {
                Integer num = resultList.get(j - 1) + resultList.get(j);
                resultList.set(j, num);
            }
            resultList.add(1);
        }
        return resultList;
    }
}
