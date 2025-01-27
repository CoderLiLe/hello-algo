package 排序_数组;

import tools.Asserts;

import java.util.Arrays;

public class _274H指数 {
    /**
     * 计算研究者的 h 指数。
     * h 指数的定义: 一个科研工作者的 h 指数是指他（她）的 N 篇论文中有 h 篇论文分别被引用了至少 h 次。
     * 其余的 N-h 篇论文每篇被引用次数不超过 h 次。
     *
     * 时间复杂度：O(nlogn)，其中 n 为数组 citations 的长度。即为排序的时间复杂度。
     * 空间复杂度：O(logn)，其中 n 为数组 citations 的长度。即为排序的空间复杂度。
     *
     * @param citations 一个整数数组，表示每篇论文的引用次数。
     * @return 返回研究者的 h 指数。
     */
    public int hIndex1(int[] citations) {
        // 对引用次数数组进行排序
        Arrays.sort(citations);
        // 初始化 h 指数为 0
        int h = 0;
        // 从数组末尾开始向前遍历
        int i = citations.length - 1;
        // 说明找到了一篇被引用了至少 h+1 次的论文
        while (i >= 0 && citations[i] > h) {
            // h 指数增加
            h++;
            // 移动到下一篇文章
            i--;
        }
        // 返回计算出的 h 指数
        return h;
    }

    /**
     * 计算研究者的h指数
     * 使用计数排序的思想优化性能
     * 时间复杂度：O(n)
     * 空间复杂度：O(n)
     *
     * @param citations 引用次数数组，表示每个论文的引用次数
     * @return 返回研究者的h指数
     */
    public int hIndex2(int[] citations) {
        // 数组长度
        int n = citations.length;
        // 初始化总数为0
        int tot = 0;
        // 创建计数数组，用于记录每个引用次数出现的论文数量
        int[] counter = new int[n + 1];

        // 遍历引用次数数组
        for (int i = 0; i < n; i++) {
            // 如果引用次数大于等于n，则计入最后一个计数槽
            if (citations[i] >= n) {
                counter[n]++;
            } else {
                // 否则计入相应的计数槽
                counter[citations[i]]++;
            }
        }

        // 从高到低遍历计数数组
        for (int i = n; i >= 0; i--) {
            // 累加总数
            tot += counter[i];
            // 如果总数大于等于当前的引用次数，则返回当前的引用次数作为h指数
            if (tot >= i) {
                return i;
            }
        }

        // 如果没有找到合适的h指数，则返回0
        return 0;
    }

    /**
     * 使用二分查找算法计算研究者的H指数
     * H指数是一种衡量学术成就的指标，表示一个学者的N篇论文中有H篇论文被引用了至少H次
     *
     * @param citations 一个整数数组，包含了每位研究者的论文被引用的次数
     * @return 返回计算出的H指数
     */
    public int hIndex3(int[] citations) {
        int l = 0, r = citations.length;
        while (l < r) {
            // 计算中间值，采用右移一位的方式相当于除以2，但要保证向上的取整
            int mid = (l + r + 1) >> 1;
            int s = 0;
            // 遍历引用次数数组，统计不小于mid的论文数量
            for (int x : citations) {
                if (x >= mid) {
                    ++s;
                }
            }
            // 如果论文数量s不小于mid，说明mid是一个潜在的H指数，尝试向右半边搜索更大的值
            if (s >= mid) {
                l = mid;
            } else {
                // 否则，说明当前的mid太大，不是H指数，需要向左半边搜索
                r = mid - 1;
            }
        }
        // 最终l会停在满足条件的最大值处，即为H指数
        return l;
    }

    public static void main(String[] args) {
        _274H指数 obj = new _274H指数();
        int[] citations = {3, 0, 6, 1, 5};
        Asserts.test(obj.hIndex1(citations) == 3);
        Asserts.test(obj.hIndex2(citations) == 3);
        Asserts.test(obj.hIndex3(citations) == 3);
    }

}
