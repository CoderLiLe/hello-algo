package 高频题;

import tools.Asserts;

import java.util.Arrays;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.TreeMap;

public class _253_会议室II {
    /**
     * 最小堆
     *
     * T = O(nlogn), S = O(n)
     */
    public int minMeetingRooms(int[][] intervals) {
        if (intervals == null || intervals.length == 0) return 0;

        // 按照会议的开始时间，从小到大排序 T = O(nlogn)
        Arrays.sort(intervals, (m1, m2) -> m1[0] - m2[0]);

        // 创建一个最小堆（存放每一个会议的结束时间）S = O(n)
        PriorityQueue<Integer> heap = new PriorityQueue<>();
        // 添加0号会议的结束时间
        heap.add(intervals[0][1]);

        // 堆顶的含义：目前占用的会议室中最早结束的时间 T = O(nlogn)
        for (int i = 1; i < intervals.length; i++) { // n
            // i 号会议的开始时间 >= 堆顶
            if (intervals[i][0] >= heap.peek()) { // T = O(1)
                heap.remove(); // T = O(logn)
            }
            // 将 i 号会议的结束时间加入堆中
            heap.add(intervals[i][1]); // T = O(logn)
        }

        return heap.size();
    }

    /**
     * T = O(nlogn), S = O(n)
     */
    public int minMeetingRooms1(int[][] intervals) {
        if (intervals == null || intervals.length == 0) return 0;

        // 存放所有会议的开始时间
        int[] begins = new int[intervals.length];
        // 存放所有会议的结束时间
        int[] ends = new int[intervals.length];
        for (int i = 0; i < intervals.length; i++) {
            begins[i] = intervals[i][0];
            ends[i] = intervals[i][1];
        }
        // 排序
        Arrays.sort(begins);
        Arrays.sort(ends);

        int room = 0, endIdx = 0;
        for (int begin : begins) {
            if (begin >= ends[endIdx]) { // 能重复利用会议室
                endIdx++;
            } else { // 需要新开一个会议室
                room++;
            }
        }

        return room;
    }

    /**
     * 插旗法（有叫上下车法的）
     *
     * 这道题可以看成是一个简单的插旗问题：进入一个区间的时候将该点坐标对应的值+1，代表插上一面进入的🚩，离开时将该点坐标值-1，代表插上一面离开的🚩，
     * 在同一个点可以同时插进入的或离开的，因为这样并不形成区间重叠。上述思想只需要对数组进行一次遍历并利用map存储坐标和旗子数量，即可完成所有旗子的插入
     *
     */
    public int minMeetingRooms2(int[][] intervals) {
        Map<Integer, Integer> map = new TreeMap<>();
        for (int i = 0; i < intervals.length; i++) {
            map.merge(intervals[i][0], 1, Integer::sum);
            map.merge(intervals[i][1], -1, Integer::sum);
        }
        int room = 0, sum = 0;
        for (Integer value : map.values()) {
            sum += value;
            room = Math.max(room, sum);
        }
        return room;
    }

    public static void main(String[] args) {
        _253_会议室II obj = new _253_会议室II();

        int[][] intervals1 = {{0, 30}, {5, 10}, {15, 20}};
        Asserts.test(2 == obj.minMeetingRooms2(intervals1));

        int[][] intervals2 = {{7, 10}, {2, 4}};
        Asserts.test(1 == obj.minMeetingRooms2(intervals2));
    }

}
