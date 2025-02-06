package 设计;

import java.util.*;

public class _380常数时间插入删除获取 {
    // 存储元素的值
    private List<Integer> nums;
    // 记录每个元素对应在 nums 中的索引
    private Map<Integer, Integer> valToIndex;
    private Random rand;

    public _380常数时间插入删除获取() {
        nums = new ArrayList<>();
        valToIndex = new HashMap<>();
        rand = new Random();
    }

    public boolean insert(int val) {
        // 若 val 已存在，不用再插入
        if (valToIndex.containsKey(val)) {
            return false;
        }
        // 若 val 不存在，插入到 nums 尾部，
        // 并记录 val 对应的索引值
        valToIndex.put(val, nums.size());
        nums.add(val);
        return true;
    }

    public boolean remove(int val) {
        // 若 val 不存在，不用再删除
        if (!valToIndex.containsKey(val)) {
            return false;
        }
        // 先拿到 val 的索引
        int index = valToIndex.get(val);
        int lastElement = nums.get(nums.size() - 1);
        // 将最后一个元素对应的索引修改为 index
        valToIndex.put(lastElement, index);
        // 交换 val 和最后一个元素
        nums.set(index, lastElement);
        // 在数组中删除元素 val
        nums.remove(nums.size() - 1);
        // 删除元素 val 对应的索引
        valToIndex.remove(val);
        return true;
    }

    public int getRandom() {
        // 随机获取 nums 中的一个元素
        return nums.get(rand.nextInt(nums.size()));
    }
}