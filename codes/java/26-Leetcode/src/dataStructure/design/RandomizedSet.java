package dataStructure.design;

/**
 * 380. O(1) 时间插入、删除和获取随机元素
 *
 * https://leetcode-cn.com/problems/insert-delete-getrandom-o1/
 */

import java.util.*;

/**
 * 满足每个函数的 平均 时间复杂度为 O(1)
 */
public class RandomizedSet {
    // 存储元素的值
    private List<Integer> nums;
    // 记录每个元素对应在 nums 中的索引
    private Map<Integer, Integer> valToIndex;
    private Random random = new Random();

    public RandomizedSet() {
        nums = new ArrayList<Integer>();
        valToIndex = new HashMap<Integer, Integer>();
    }

    /**
     * 当元素 val 不存在时，向集合中插入该项，并返回 true ；否则，返回 false 。
     * @param val
     * @return
     */
    public boolean insert(int val) {
        // 若 val 已存在，不用再插入
        if (valToIndex.containsKey(val)) {
            return false;
        }

        // 若 val 不存在，插入到 nums 尾部
        // 并记录 val 对应的索引值
        valToIndex.put(val, nums.size());
        nums.add(val);
        return true;
    }

    /**
     * 当元素 val 存在时，从集合中移除该项，并返回 true ；否则，返回 false
     * @param val
     * @return
     */
    public boolean remove(int val) {
        // 若 val 已存在，不用再删除
        if (!valToIndex.containsKey(val)) {
            return false;
        }

        // 先拿到 val 的索引
        Integer index = valToIndex.get(val);
        // 最后一个元素的索引
        Integer lastIndex = nums.size() - 1;
        Integer lastElement = nums.get(lastIndex);
        // 将最后一个元素对应的索引修改为 index
        valToIndex.put(lastElement, index);
        // 交换 val 和最后一个元素
        int tmp = nums.get(index);
        nums.set(index, lastElement);
        nums.set(lastIndex, tmp);
        // 在数组中删除元素 val
        nums.remove(lastIndex);
        valToIndex.remove(val);
        return true;
    }

    /**
     * 随机返回现有集合中的一项（测试用例保证调用此方法时集合中至少存在一个元素）。每个元素应该有 相同的概率 被返回。
     * @return
     */
    public int getRandom() {
        // 随机获取 nums 中的一个元素
        return nums.get(random.nextInt(nums.size()));
    }
}
