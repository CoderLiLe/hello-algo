package 细节实现;

import java.util.ArrayList;
import java.util.List;

/**
 * 118_杨辉三角 [Pascal’s Triangle]
 * 【简单】
 * 给定一个非负整数 numRows，生成杨辉三角的前 numRows 行。
 * 在杨辉三角中，每个数是它左上方和右上方的数的和。
 *
 * 示例:
 *
 * 输入: 5
 * 输出:
 * [
 *      [1],
 *     [1,1],
 *    [1,2,1],
 *   [1,3,3,1],
 *  [1,4,6,4,1]
 * ]
 */
public class _118_杨辉三角 {

    public static void main(String[] args) {
        List<List<Integer>> result = generate(5);
        for (List<Integer> rowList : result) {
            System.out.println(rowList);
        }
    }

    /**
     * 从左边到右 T = O(n ^ 2), S = O(n)
     */
    public static List<List<Integer>> generate(int numRows) {
        List<List<Integer>> result = new ArrayList<>();
        if (numRows == 0) return result;

        // handle first row
        List<Integer> firstList = new ArrayList<>();
        firstList.add(1);
        result.add(firstList);

        for (int i = 2; i <= numRows; i++) {
            // 上一行
            List<Integer> preList = result.get(i - 2);
            // 存储当前行结果，前后填充1
            List<Integer> curList = new ArrayList<>();
            curList.add(1);
            for (int j = 1; j < i - 1; j++) {
                // 左上角和右上角之和
                Integer num = preList.get(j - 1) + preList.get(j);
                curList.add(num);
            }
            curList.add(1);
            result.add(curList);
        }

        return result;
    }
}
