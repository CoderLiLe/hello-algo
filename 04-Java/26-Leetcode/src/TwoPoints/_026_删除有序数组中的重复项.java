package TwoPoints;

public class _026_删除有序数组中的重复项 {
    public int removeDuplicates(int[] nums) {
        if (0 == nums.length) {
            return 0;
        }

        int slow = 0, fast = 0;
        while (fast < nums.length) {
            if (nums[fast] != nums[slow]) {
                slow++;
                // 维护 nums[0..slow] 无重复
                nums[slow] = nums[fast];
            }
            fast++;
        }

        // 数组长度为索引 + 1
        return slow + 1;
    }
}
