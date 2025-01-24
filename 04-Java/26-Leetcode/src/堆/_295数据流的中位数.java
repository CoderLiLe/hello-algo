package 堆;

import java.util.PriorityQueue;
import java.util.Queue;

public class _295数据流的中位数 {
    Queue<Integer> minQueue, maxQueue;

    public _295数据流的中位数() {
        // 小顶堆，保存较大的一半
        minQueue = new PriorityQueue<>();
        // 大顶堆，保存较小的一半
        maxQueue = new PriorityQueue<>((x, y) -> (y - x));
    }

    /**
     * 向数据结构中添加一个数字，同时保持两个优先队列之间的平衡
     * 此方法的核心在于处理两个优先队列（minQueue 和 maxQueue）的平衡问题，以确保
     * 在任何时刻都能在O(1)的时间复杂度内获取到中位数
     *
     * 时间复杂度 O(logN) ，堆的插入和弹出操作使用 O(logN) 时间
     *
     * @param num 要添加到数据结构中的数字
     */
    public void addNum(int num) {
        // 当两个队列的大小不相等时，先将新数字添加到最小堆中，然后将最小堆的最小元素移动到最大堆
        if (minQueue.size() != maxQueue.size()) {
            minQueue.add(num);
            maxQueue.add(minQueue.poll());
        } else {
            // 当两个队列的大小相等时，先将新数字添加到最大堆中，然后将最大堆的最大元素移动到最小堆
            maxQueue.add(num);
            minQueue.add(maxQueue.poll());
        }
    }

    public double findMedian() {
        return minQueue.size() != maxQueue.size() ? minQueue.peek() : (minQueue.peek() + maxQueue.peek()) / 2.0;
    }
}
