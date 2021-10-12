package 哈希.twoSum;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class _170_两数之和III数据结构设计2 {
    // 储存所有加入的数字可能组成的和
    Set<Integer> sum = new HashSet<>();
    List<Integer> nums = new ArrayList<>();

    // T = O(n)
    public void add(int number) {
        // 记录所有可能组成的和
        for (int n : nums) {
            sum.add(n + number);
        }
        nums.add(number);
    }

    // T = O(1)
    public boolean find(int value) {
        return sum.contains(value);
    }
}
