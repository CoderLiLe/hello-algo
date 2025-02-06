package TwoPoints;

public class _283_移动零 {
    public void moveZeroes(int[] nums) {
        // 去除 nums 中的所有0
        // 返回去除 0 后的数组长度
        int p = removeElement(nums, 0);
        // 将 p 之后的所有元素赋值为0
        for (; p < nums.length; p++) {
            nums[p] = 0;
        }
    }

    private int removeElement(int[] nums, int val) {
        int fast = 0, slow = 0;
        while (fast < nums.length) {
            if (nums[fast] != val) {
                nums[slow] = nums[fast];
                slow++;
            }
            fast++;
        }

        return slow;
    }
}
