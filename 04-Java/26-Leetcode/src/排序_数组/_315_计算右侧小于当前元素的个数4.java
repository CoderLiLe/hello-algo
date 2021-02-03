package 排序_数组;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// TODO: 此解法错误❌❌
public class _315_计算右侧小于当前元素的个数4 {
    private int[] array;
    private Integer[] leftArray;
    private Integer[] res;

    /**
     * 归并排序
     * T(n) = O(nlogn)
     *
     * 因为归并排序，在合并的时候，可以判断出右边比它小的个数
     */
    public List<Integer> countSmaller(int[] nums) {
        List<Integer> ret = new ArrayList<>();
        int len = nums.length;
        if (nums == null || len == 0) return ret;

        this.array = nums;
        res = new Integer[len];
        for (int i = 0; i < res.length; i++) {
            res[i] = 0;
        }

        leftArray = new Integer[len >> 1];
        sort(0, len);

        ret = Arrays.asList(res);
        return ret;
    }

    /**
     * 对 [begin, end) 范围的数据进行归并排序
     */
    private void sort(int begin, int end) {
        if (end - begin < 2)  return;

        int mid = (begin + end) >> 1;
        sort(begin, mid);
        sort(mid, end);
        merge(begin, mid, end);
    }

    /**
     * 将 [begin, mid) 和 [mid, end) 范围的序列合并成一个有序序列
     */
    private void merge(int begin, int mid, int end) {
        int li = 0, le = mid - begin;
        int ri = mid, re = end;
        int ai = begin;

        // 备份左边数组
        for (int i = li; i < le; i++) {
            leftArray[i] = array[begin + i];
        }

        int smallCount = 0;

        System.out.println("begin = " + begin + ", mid = " + mid + ", end = " + end);
        System.out.println("===============");
        for (Integer num: leftArray) {
            System.out.println(num);
        }
        System.out.println("===============");

        // 如果左边还没有结束
        while (li < le) {
            if (ri < re && cmp(array[ri], leftArray[li]) < 0) {
                array[ai++] = array[ri++];
                smallCount++;
            } else {
                // 在合并的时候，可以判断出右边比它小的个数
                res[li+begin] += smallCount;
                array[ai++] = leftArray[li++];
            }
        }
    }

    private int cmp(Integer v1, Integer v2) {
        return v1.compareTo(v2);
    }

    public static void main(String[] args) {
        _315_计算右侧小于当前元素的个数4 obj = new _315_计算右侧小于当前元素的个数4();
//        int[] nums = new int[] {5, 2, 6, 1};
//        int[] nums = new int[] {1, 0, 2};
        // 预期 [10,27,10,35,12,22,28,8,19,2,12,2,9,6,12,5,17,9,19,12,14,6,12,5,12,3,0,10,0,7,8,4,0,0,4,3,2,0,1,0]
        int[] nums = new int[] {26,78,27,100,33,67,90,23,66,5,38,7,35,23,52,22,83,51,98,69,81,32,78,28,94,13,2,97,3,76,99,51,9,21,84,66,65,36,100,41};
        System.out.println(obj.countSmaller(nums));
    }
}
