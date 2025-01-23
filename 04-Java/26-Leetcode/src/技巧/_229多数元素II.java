package 技巧;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * 给定一个大小为 n 的整数数组，找出其中所有出现超过 ⌊ n/3 ⌋ 次的元素。
 */
public class _229多数元素II {
    /**
     * 摩尔投票法：摩尔投票法的核心思想为对拼消耗
     *
     * 时间复杂度：O(n)，其中 n 为数组的长度。
     * 空间复杂度：O(1)，只需要常数个元素用来存储关键元素和统计次数即可。
     *
     * @param nums
     * @return
     */
    public List<Integer> majorityElement(int[] nums) {
        int element1 = 0;
        int element2 = 0;
        int vote1 = 0;
        int vote2 = 0;

        for (int num : nums) {
            // 如果该元素为第一个元素，则计数加1
            if (vote1 > 0 && num == element1) {
                vote1++;
            } // 如果该元素为第二个元素，则计数加1
            else if (vote2 > 0 && num == element2) {
                vote2++;
            } // 选择第一个元素
            else if (vote1 == 0) {
                element1 = num;
                vote1++;
            } // 选择第二个元素
            else if (vote2 == 0) {
                element2 = num;
                vote2++;
            } // 如果三个元素均不相同，则相互抵消1次
            else {
                vote1--;
                vote2--;
            }
        }

        int cnt1 = 0;
        int cnt2 = 0;
        for (int num : nums) {
            if (vote1 > 0 && num == element1) {
                cnt1++;
            }
            if (vote2 > 0 && num == element2) {
                cnt2++;
            }
        }
        // 检测元素出现的次数是否满足要求
        List<Integer> ans = new ArrayList<>();
        if (vote1 > 0 && cnt1 > nums.length / 3) {
            ans.add(element1);
        }
        if (vote2 > 0 && cnt2 > nums.length / 3) {
            ans.add(element2);
        }

        return ans;
    }

    public List<Integer> majorityElement2(int[] nums) {
        List<Integer> res = new ArrayList<>();
        Map<Integer, Long> map = Arrays.stream(nums).boxed().collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        Integer limit = nums.length / 3;
        for (Map.Entry<Integer, Long> entry : map.entrySet()) {
            if (entry.getValue() > limit) {
                res.add(entry.getKey());
            }
        }

        return res;
    }

    public List<Integer> majorityElement3(int[] nums) {
        List<Integer> res = new ArrayList<>();
        int limit = nums.length / 3;
        HashMap<Integer, Integer> map = new HashMap<>(limit);
        for (int num : nums) {
            map.merge(num, 1, Integer::sum);
        }

        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if (entry.getValue() > limit) {
                res.add(entry.getKey());
            }
        }

        return res;
    }
}
