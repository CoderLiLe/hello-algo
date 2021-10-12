package 哈希.twoSum;

import java.util.HashMap;
import java.util.Map;

public class _170_两数之和III数据结构设计 {
    Map<Integer, Integer> freq = new HashMap<>();

    // T = O(1)
    public void add(int number) {
        // 记录 number 出现的次数
        freq.put(number, freq.getOrDefault(number, 0) + 1);
    }

    // T = O(n)
    public boolean find(int value) {
        for (Integer key : freq.keySet()) {
            int other = value - key;
            // 情况一：add 了 [3,3,2,5] 之后，执行 find(6)，由于 3 出现了两次，3 + 3 = 6，所以返回 true
            if (other == key && freq.get(key) > 1) {
                return true;
            }
            // 情况二：add 了 [3,3,2,5] 之后，执行 find(7)，那么 key 为 2，other 为 5 时算法可以返回 true
            if (other != key && freq.containsKey(other)) {
                return true;
            }
        }

        return false;
    }
}
