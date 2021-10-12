package 排序_数组;

public class _875_爱吃香蕉的珂珂 {
    /**
     * 时间复杂度：O(Nlogmax(piles))，这里 N 表示数组 piles 的长度。我们在 [1,maxpiles] 里使用二分查找定位最小速度，而每一次执行判别函数的时间复杂度是 O(N)
     * 空间复杂度：O(1)，算法只使用了常数个临时变量
     */
    public int minEatingSpeed(int[] piles, int h) {
        int left = 1;
        int right = 1000000000 + 1;

        while (left < right) {
            int mid = left + (right - left) / 2;
            if (f(piles, mid) == h) {
                // 搜索左侧边界，则需要收缩右侧边界
                right = mid;
            } else if (f(piles, mid) < h) {
                // 需要让 f(x) 的返回值大一些
                right = mid;
            } else if (f(piles, mid) > h) {
                // 需要让 f(x) 的返回值小一些
                left = mid + 1;
            }
        }

        return left;
    }

    private int f(int[] piles, int x) {
        int hours = 0;
        for (int i = 0; i < piles.length; i++) {
            hours += piles[i] / x;
            if (piles[i] % x > 0) {
                hours++;
            }
        }
        return hours;
    }
}
