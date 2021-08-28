package 高频题;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;

class ExamRoom {
    // 将端点 p 映射到以 p 为左端点的线段
    private Map<Integer, int[]> startMap;
    // 将端点 p 映射到以 p 为右端点的线段
    private Map<Integer, int[]> endMap;
    // 根据线段长度从小到大存放所有线段
    private TreeSet<int[]> pg;
    private int n;

    public ExamRoom(int n) {
        this.n = n;
        startMap = new HashMap<>();
        endMap = new HashMap<>();
        pg = new TreeSet<>((a, b) -> {
            // 算出两个线段的长度
            int distA = distance(a);
            int distB = distance(b);
            // 如果长度相同，就比较索引
            if (distA == distB) {
                return b[0] - a[0];
            }
            // 长度更大的，排在后面
            return distA - distB;
        });
        // 在有序集合中先放一个虚拟线段
        addInterval(new int[] {-1, n});
    }

    public int seat() {
        // 从集合中拿出最长的线段
        int[] longest = pg.last();
        int x = longest[0];
        int y = longest[1];
        int seat;
        if (-1 == x) {
            seat = 0;
        } else if (y == n) {
            seat = n - 1;
        } else {
            seat = (y - x) / 2 + x;
        }

        // 将最长的线段分成两段
        int[] left = new int[] {x, seat};
        int[] right = new int[] {seat, y};
        removeInterval(longest);
        addInterval(left);
        addInterval(right);
        return seat;
    }

    public void leave(int p) {
        // 将 p 左右的线段找出来
        int[] right = startMap.get(p);
        int[] left = endMap.get(p);

        // 合并两个线段为一个线段
        int[] merged = new int[] {left[0], right[1]};
        removeInterval(left);
        removeInterval(right);
        addInterval(merged);
    }

    /**
     * 去除一个线段
     * @param intv
     */
    private void removeInterval(int[] intv) {
        pg.remove(intv);
        startMap.remove(intv[0]);
        endMap.remove(intv[1]);
    }

    /**
     * 添加一个线段
     * @param intv
     */
    private void addInterval(int[] intv) {
        pg.add(intv);
        startMap.put(intv[0], intv);
        endMap.put(intv[1], intv);
    }

    /**
     * 计算一个线段的长度
     * @param intv
     * @return
     */
    private int distance(int[] intv) {
        //return intv[1] - intv[0];

        int x = intv[0];
        int y = intv[1];
        if (-1 == x) return y;
        if (n == y) return n - 1 - x;
        // 中点和端点之间的长度
        return (y - x) / 2;
    }
}

public class _855_考场就座 {
    public static void main(String[] args) {
        // 0, 9, 4, 2, 5
        ExamRoom obj = new ExamRoom(10);
        System.out.println(obj.seat());
        System.out.println(obj.seat());
        System.out.println(obj.seat());
        System.out.println(obj.seat());
        obj.leave(4);
        System.out.println(obj.seat());
    }
}
