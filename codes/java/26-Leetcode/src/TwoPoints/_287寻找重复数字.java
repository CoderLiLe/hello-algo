package TwoPoints;

import tools.Asserts;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class _287寻找重复数字 {
    public int findDuplicate(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for (int num : nums) {
            if (!set.add(num)) {
                return num;
            }
        }
        return 0;
    }

    public int findDuplicate2(int[] nums) {
        Arrays.sort(nums);
        for (int i = 0; i < nums.length - 1; i++) {
            if (nums[i] == nums[i + 1]) {
                return nums[i];
            }
        }
        return 0;
    }

    /**
     * 寻找数组中的重复数字
     * 采用快慢指针的方法，将问题转化为寻找链表的环入口
     *
     * @param nums 包含重复数字的整数数组
     * @return 返回数组中的重复数字
     */
    public int findDuplicate3(int[] nums) {
        // 初始化快慢指针
        int slow = 0, fast = 0;

        // 先找到慢指针追上快指针的点
        do {
            // 慢指针每次移动一步
            slow = nums[slow];
            // 快指针每次移动两步
            fast = nums[nums[fast]];
        } while (slow != fast);

        // 然后 slow从头开始，fast从相遇点开始，再次相遇就是入环的点（即重复数字）
        slow = 0;
        while (slow != fast) {
            // 慢指针每次移动一步
            slow = nums[slow];
            // 快指针每次移动一步
            fast = nums[fast];
        }
        // 返回重复数字
        return slow;
    }

    /**
     * 寻找数组中的重复数字
     * 采用二分查找的变体，不直接比较元素值，而是比较元素值的范围
     * 这种方法避免了直接元素比较，绕过了打表或排序的限制
     * 时间复杂度：O(nlogn)，其中 n 为 nums 数组的长度。二分查找最多需要二分 O(logn) 次，每次判断的时候需要O(n) 遍历 nums 数组求解小于等于 mid 的数的个数，因此总时间复杂度为 O(nlogn)。
     * 空间复杂度：O(1)。我们只需要常数空间存放若干变量
     *
     * @param nums 输入的整数数组，其中包含一个重复的数字
     * @return 返回数组中的重复数字
     */
    public int findDuplicate4(int[] nums) {
        // 数组长度
        int n = nums.length;
        // 初始化左边界l为1，右边界r为n-1，结果res为-1
        int l = 1, r = n - 1, res = -1;
        // 当左边界不大于右边界时，执行二分查找
        while (l <= r) {
            // 计算中间值，避免整数溢出
            int mid = l + ((r - l) >> 1);
            // cnt用于统计小于等于mid的数字个数
            int cnt = 0;
            // 遍历数组，统计小于等于mid的元素个数
            for (int i = 0; i < n; ++i) {
                if (nums[i] <= mid) {
                    cnt++;
                }
            }
            // 如果cnt不超过mid，说明重复数字在mid右侧，更新左边界
            if (cnt <= mid) {
                l = mid + 1;
            } else {
                // 否则，说明重复数字在mid左侧或为mid，更新右边界和结果
                r = mid - 1;
                res = mid;
            }
        }
        // 返回找到的重复数字
        return res;
    }

    /**
     * 使用位操作查找数组中的重复数字
     * 该方法通过比较数组中每个数字的每一位与自然数序列（1, 2, ..., n-1）中对应位的出现次数，
     * 来确定重复数字的每一位，最终合成整个重复数字
     *
     * @param nums 输入的整数数组，其中包含一个重复的数字
     * @return 返回数组中的重复数字
     */
    public int findDuplicate5(int[] nums) {
        // 初始化数组长度和答案变量
        int n = nums.length, ans = 0;
        // 计算最大位数，用于后续的位操作
        int bit_max = 31;
        // 找到最高位，即n-1的二进制表示的最高位
        while (((n - 1) >> bit_max) == 0) {
            bit_max -= 1;
        }
        // 遍历每一位，从0到最高位
        for (int bit = 0; bit <= bit_max; ++bit) {
            // 初始化计数器x和y，分别用于统计数组中的数字和自然数序列中对应位的1的数量
            int x = 0, y = 0;
            // 遍历数组中的每个数字
            for (int i = 0; i < n; ++i) {
                // 如果当前数字的第bit位是1，则x计数器加1
                if ((nums[i] & (1 << bit)) != 0) {
                    x += 1;
                }
                // 如果当前索引对应的自然数的第bit位是1，则y计数器加1
                if (i >= 1 && ((i & (1 << bit)) != 0)) {
                    y += 1;
                }
            }
            // 如果x大于y，说明重复数字的第bit位是1，否则是0
            if (x > y) {
                // 将当前位的1加入到答案中
                ans |= 1 << bit;
            }
        }
        // 返回计算出的重复数字
        return ans;
    }

    public static void main(String[] args) {
        _287寻找重复数字 obj = new _287寻找重复数字();
        int[] nums = {1, 3, 4, 2, 2};
        Asserts.test(obj.findDuplicate(nums) == 2);
        Asserts.test(obj.findDuplicate2(nums) == 2);
        Asserts.test(obj.findDuplicate3(nums) == 2);
        Asserts.test(obj.findDuplicate4(nums) == 2);
        Asserts.test(obj.findDuplicate5(nums) == 2);
    }
}
