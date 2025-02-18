package 堆;

import tools.Asserts;
import tools.Times;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class _502IPO {
    /**
     * 题目本质是在有约束、多指标的情况下选取指标最大的k个节点的问题
     *
     * 对于这样的问题，我们很容易想到贪心的做法
     * 求动态序列的最大值，用大顶堆
     */
    public int findMaximizedCapital(int k, int w, int[] profits, int[] capital) {
        int len = profits.length;
        List<int[]> list = new ArrayList<>();
        for (int i = 0; i < len; i++) {
            list.add(new int[]{capital[i], profits[i]});
        }
        list.sort((o1, o2) -> o1[0] - o2[0]);
        PriorityQueue<Integer> queue = new PriorityQueue<>((o1, o2) -> o2 - o1);
        int index = 0;
        while (k > 0) {
            while (index < len && list.get(index)[0] <= w) {
                queue.add(list.get(index)[1]);
                index++;
            }
            if (queue.isEmpty()) {
                break;
            } else {
                w += queue.poll();
                k--;
            }
        }
        return w;
    }

    public static void main(String[] args) {
        _502IPO obj = new _502IPO();

        int k = 2;
        int w = 0;
        int[] profits = new int[] {1, 2, 3};
        int[] capital = new int[] {0, 1, 1};

        Times.test("贪心 + 优先队列（堆）", () -> {
            Asserts.test(obj.findMaximizedCapital(k, w, profits, capital) == 4);
        });
    }
}
