package com.lile;

import com.lile.tools.Asserts;

public class _134加油站 {
    /**
     * 暴力方法
     * 时间复杂度：O(n^2)
     * 空间复杂度：O(1)
     * @param gas
     * @param cost
     * @return
     */
    public int canCompleteCircuit(int[] gas, int[] cost) {
        for (int i = 0; i < cost.length; i++) {
            // 记录剩余油量
            int rest = gas[i] - cost[i];
            int index = (i + 1) % cost.length;
            // 模拟以i为起点行驶一圈（如果有rest==0，那么答案就不唯一了）
            while (rest > 0 && index != i) {
                rest += gas[index] - cost[index];
                index = (index + 1) % cost.length;
            }
            // 如果以i为起点跑一圈，剩余油量>=0，返回该起始位置
            if (rest >= 0 && index == i) {
                return i;
            }
        }
        return -1;
    }

    /**
     * 贪心算法（方法一）
     * 直接从全局进行贪心选择，情况如下：
     * 情况一：如果gas的总和小于cost总和，那么无论从哪里出发，一定是跑不了一圈的
     *
     * 情况二：rest[i] = gas[i]-cost[i]为一天剩下的油，i从0开始计算累加到最后一站，如果累加没有出现负数，说明从0出发，油就没有断过，那么0就是起点。
     *
     * 情况三：如果累加的最小值是负数，汽车就要从非0节点出发，从后向前，看哪个节点能把这个负数填平，能把这个负数填平的节点就是出发节点。
     *
     * @param gas
     * @param cost
     * @return
     */
    public int canCompleteCircuit2(int[] gas, int[] cost) {
        int sum = 0;
        int min = 0;
        for (int i = 0; i < gas.length; i++) {
            sum += (gas[i] - cost[i]);
            min = Math.min(sum, min);
        }

        if (sum < 0) {
            return -1;
        }
        if (min >= 0) {
            return 0;
        }

        for (int i = gas.length - 1; i > 0; i--) {
            min += (gas[i] - cost[i]);
            if (min >= 0) {
                return i;
            }
        }

        return -1;
    }

    /**
     * 贪心算法（方法二）
     * 时间复杂度：O(n)
     * 空间复杂度：O(1)
     *
     * @param gas
     * @param cost
     * @return
     */
    public int canCompleteCircuit3(int[] gas, int[] cost) {
        int curSum = 0;
        int totalSum = 0;
        int start = 0;
        for (int i = 0; i < gas.length; i++) {
            curSum += gas[i] - cost[i];
            totalSum += gas[i] - cost[i];
            if (curSum < 0) {   // 当前累加rest[i]和 curSum一旦小于0
                start = i + 1;  // 起始位置更新为i+1
                curSum = 0;     // curSum从0开始
            }
        }
        if (totalSum < 0) return -1; // 说明怎么走都不可能跑一圈了
        return start;
    }

    public int canCompleteCircuit4(int[] gas, int[] cost) {
        int n = gas.length;
        // 相当于图像中的坐标点和最低点
        int sum = 0, minSum = 0;
        int start = 0;
        for (int i = 0; i < n; i++) {
            sum += gas[i] - cost[i];
            if (sum < minSum) {
                // 经过第 i 个站点后，使 sum 到达新低
                // 所以站点 i + 1 就是最低点（起点）
                start = i + 1;
                minSum = sum;
            }
        }
        if (sum < 0) {
            // 总油量小于总的消耗，无解
            return -1;
        }
        // 环形数组特性
        return start == n ? 0 : start;
    }



public static void main(String[] args) {
        _134加油站 obj = new _134加油站();
        int[] gas = {1, 2, 3, 4, 5};
        int[] cost = {3, 4, 5, 1, 2};
        Asserts.test(obj.canCompleteCircuit(gas, cost) == 3);
        Asserts.test(obj.canCompleteCircuit2(gas, cost) == 3);
        Asserts.test(obj.canCompleteCircuit3(gas, cost) == 3);
        Asserts.test(obj.canCompleteCircuit4(gas, cost) == 3);
    }
}
