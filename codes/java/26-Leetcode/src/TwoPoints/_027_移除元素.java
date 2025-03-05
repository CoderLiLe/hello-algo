package TwoPoints;

public class _027_移除元素 {
    /**
     * 快慢指针
     */
    public int removeElement(int[] nums, int val) {
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

    public int removeElement2(int[] nums, int val) {
        int j = nums.length - 1;
        for (int i = 0; i <= j; i++) {
            if (nums[i] == val) {
                swap(nums, i--, j--);
            }
        }
        return j + 1;
    }

    void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }

    public int removeElement3(int[] nums, int val) {
        int idx = 0;
        for (int x : nums) {
            if (x != val) {
                nums[idx++] = x;
            }
        }
        return idx;
    }

    /**
     * 暴力法
     * T = O(n^2)
     * S = O(1)
     */
    public int removeElement4(int[] nums, int val) {
        int len = nums.length;
        for (int i = 0; i < len; i++) {
            if (nums[i] == val) {
                for (int j = i + 1; j < len; j++) {
                    nums[j - 1] = nums[j];
                }
                i--;
                len--;
            }
        }
        return len;
    }

    /**
     * 相向双指针法
     */
    public int removeElement5(int[] nums, int val) {
        int left = 0;
        int right = nums.length - 1;
        // 将right移到从右数第一个值不为val的位置
        while (right >= 0 && nums[right] == val) {
            right--;
        }
        while (left <= right) {
            // left位置的元素需要移除
            if (nums[left] == val) {
                // 将right位置的元素移到left（覆盖），right位置移除
                nums[left] = nums[right];
                right--;
            }
            left++;
            while (right >= 0 && nums[right] == val) {
                right--;
            }
        }
        return left;
    }

    /**
     * 相向双指针法2
     */
    public int removeElement6(int[] nums, int val) {
        int left = 0;
        int right = nums.length - 1;
        while (left <= right) {
            if (nums[left] == val) {
                nums[left] = nums[right];
                right--;
            } else {
                // 这里兼容了right指针指向的值与val相等的情况
                left++;
            }
        }
        return left;
    }
}
