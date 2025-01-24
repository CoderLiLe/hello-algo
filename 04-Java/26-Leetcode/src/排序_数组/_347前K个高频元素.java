package 排序_数组;

import tools.Asserts;

import java.util.*;

public class _347前K个高频元素 {
    /**
     * 解法一：粗暴排序法
     * 使用排序算法对元素按照频率由高到低进行排序，然后再取前 k 个元素。
     */
    public int[] topKFrequent(int[] nums, int k) {
        int[] result = new int[k];
        // key : 数字; value ：出现到频次
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int curNum = nums[i];
            map.put(curNum, map.getOrDefault(curNum, 0) + 1);
        }

        // 根据 value 进行排序
        ArrayList<Map.Entry<Integer, Integer>> list = new ArrayList(map.entrySet());
        Collections.sort(list, (a, b) -> {
            return b.getValue() - a.getValue();
        });

        for (int i = 0; i < k; i++) {
            result[i] = list.get(i).getKey();
        }

        return result;
    }

    /**
     * 解法二：最小堆
     * 题目最终需要返回的是前 k 个频率最大的元素，可以想到借助堆这种数据结构，
     * 对于 k 频率之后的元素不用再去处理，进一步优化时间复杂度。
     * <p>
     * 时间复杂度：O(nlogk)
     * 空间复杂度：O(n)
     */
    public int[] topKFrequent2(int[] nums, int k) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int n : nums) {
            map.put(n, map.getOrDefault(n, 0) + 1);
        }
        // 从大到小排序，这样后面取前k个元素就可以了
        PriorityQueue<Integer> pq = new PriorityQueue((e1, e2) -> map.get(e2) - map.get(e1));
        for (int key : map.keySet()) {
            pq.add(key);
        }

        int[] res = new int[k];
        for (int i = 0; i < k; i++) {
            res[i] = pq.poll();
        }
        return res;
    }

    /**
     * 解法三：桶排序法
     * 首先依旧使用哈希表统计频率，统计完成后，创建一个数组，将频率作为数组下标，
     * 对于出现频率不同的数字集合，存入对应的数组下标即可
     * <p>
     * 时间复杂度：O(n)
     * 空间复杂度：O(n)
     */
    public int[] topKFrequent3(int[] nums, int k) {
        // 初始化结果数组
        int[] res = new int[k];
        // 获取输入数组的长度
        int n = nums.length;
        // 使用HashMap统计每个数字出现的频率
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            // 如果数字已经在map中存在，则频率加1，否则默认为0后加1
            map.put(nums[i], map.getOrDefault(nums[i], 0) + 1);
        }
        // 创建一个数组列表的数组，索引代表频率，值代表具有该频率的所有数字
        List<Integer>[] bucket = new List[n + 1];
        for (int key : map.keySet()) {
            // 获取当前数字的频率
            int freq = map.get(key);
            // 如果当前频率对应的桶还未初始化，则初始化
            if (bucket[freq] == null) {
                bucket[freq] = new ArrayList<>();
            }
            // 将数字添加到对应频率的桶中
            bucket[freq].add(key);
        }

        // 用于结果数组的索引
        int index = 0;
        // 从高频到低频遍历桶，收集结果数组
        for (int i = bucket.length - 1; i >= 0; i--) {
            // 如果已经收集到前k个高频数字，则结束循环
            if (index == k) {
                break;
            }
            // 如果当前频率的桶为空，则跳过
            if (bucket[i] == null) {
                continue;
            }
            // 获取当前频率的桶
            List<Integer> list = bucket[i];
            for (int j = 0; j < list.size(); j++) {
                // 如果还没有收集到前k个高频数字，则将当前数字添加到结果数组
                if (index < k) {
                    res[index++] = list.get(j);
                }
            }
        }
        // 返回结果数组
        return res;
    }

    public static void main(String[] args) {
        _347前K个高频元素 obj = new _347前K个高频元素();
        int[] nums = new int[] {1, 1, 1, 2, 2, 3};
        int k = 2;
        int[] res = {1, 2};
        Asserts.test(Arrays.equals(obj.topKFrequent(nums, k), res));
        Asserts.test(Arrays.equals(obj.topKFrequent2(nums, k), res));
        Asserts.test(Arrays.equals(obj.topKFrequent3(nums, k), res));
    }
}
