package 技巧;

import tools.Asserts;

import java.util.HashMap;
import java.util.Map;

public class _137只出现一次的数字II {
    public int singleNumber(int[] nums) {
        Map<Integer, Integer> freq = new HashMap<>();
        for (int num : nums) {
            freq.put(num, freq.getOrDefault(num, 0) + 1);
        }
        int ans = 0;
        for (Map.Entry<Integer, Integer> entry : freq.entrySet()) {
            int num = entry.getKey(), occ = entry.getValue();
            if (occ == 1) {
                ans = num;
                break;
            }
        }
        return ans;
    }

    public int singleNumber2(int[] nums) {
        int ones = 0, twos = 0;
        for(int num : nums){
            ones = ones ^ num & ~twos;
            twos = twos ^ num & ~ones;
        }
        return ones;
    }

    public static void main(String[] args) {
        _137只出现一次的数字II obj = new _137只出现一次的数字II();
        int[] nums = {2, 2, 3, 2};
        Asserts.test(obj.singleNumber(nums) == 3);
        Asserts.test(obj.singleNumber2(nums) == 3);
    }
}
