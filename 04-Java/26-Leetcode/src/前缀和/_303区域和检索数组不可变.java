package 前缀和;

import tools.Asserts;

/**
 * https://leetcode.cn/problems/range-sum-query-immutable/
 */
public class _303区域和检索数组不可变 {
    // 前缀和数组
    private int[] preSum;

    public _303区域和检索数组不可变(int[] nums) {
        // preSum[0] = 0，便于计算累加和
        preSum = new int[nums.length + 1];
        // 计算 nums 的累加和
        for (int i = 1; i < preSum.length; i++) {
            preSum[i] = preSum[i - 1] + nums[i - 1];
        }
    }

    // 查询闭区间 [left, right] 的累加和
    public int sumRange(int left, int right) {
        return preSum[right + 1] - preSum[left];
    }


    public static void main(String[] args) {
        int[] nums = {-2, 0, 3, -5, 2, -1};
        _303区域和检索数组不可变 obj = new _303区域和检索数组不可变(nums);
        Asserts.test(obj.sumRange(0, 2) == 1);
        Asserts.test(obj.sumRange(2, 5) == -1);
        Asserts.test(obj.sumRange(0, 5) == -3);
    }

    /**
     * 这个技巧在生活中运用也挺广泛的，比方说，你们班上有若干同学，每个同学有一个期末考试的成绩（满分 100 分），那么请你实现一个 API，输入任意一个分数段，返回有多少同学的成绩在这个分数段内。
     * 那么，你可以先通过计数排序的方式计算每个分数具体有多少个同学，然后利用前缀和技巧来实现分数段查询的 API
     */
    private void test() {
        // 存储着所有同学的分数
        int[] scores = {80, 90, 70, 60, 100, 80, 90, 80, 90, 100};
        // 试卷满分 100 分
        int[] count = new int[100 + 1];
        // 记录每个分数有几个同学
        for (int score : scores) {
            count[score]++;
        }

        // 构造前缀和
        for (int i = 1; i < count.length; i++) {
            count[i] = count[i] + count[i - 1];
        }
        // 利用 count 这个前缀和数组进行分数段查询
    }
}
