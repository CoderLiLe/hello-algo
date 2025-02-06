package 排序_数组;

public class _1011_在D天内送达包裹的能力 {
    public int shipWithinDays(int[] weights, int days) {
        int left = 0;
        // 注意，right 是开区间，所以额外加1
        int right = 1;
        for (int w : weights) {
            left = Math.max(left, w);
            right += w;
        }

        while (left < right) {
            int mid = left + (right - left) / 2;
            if (f(weights, mid) == days) {
                // 搜索左侧边界，则需要收缩右侧边界
                right = mid;
            } else if (f(weights, mid) < days) {
                // 需要让 f(x) 的返回值大一些
                right = mid;
            } else if (f(weights, mid) > days) {
                // 需要让 f(x) 的返回值小一些
                left = mid + 1;
            }
        }

        return left;
    }

    /**
     * 定义：当运载能力为 x 时，需要 f(x) 天运完所有货物
     * f(x) 随着 x 的增加单调递减
     */
    private int f(int[] weights, int x) {
        int days = 0;
        for (int i = 0; i < weights.length;) {
            // 尽可能多装货物
            int cap = x;
            while (i < weights.length) {
                if (cap < weights[i]) break;
                else cap -= weights[i];
                i++;
            }
            days++;
        }
        return days;
    }
}
