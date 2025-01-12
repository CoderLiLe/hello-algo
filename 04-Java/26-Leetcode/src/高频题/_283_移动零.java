package 高频题;

public class _283_移动零 {
    /**
     * 两次遍历
     * 我们创建两个指针 i 和 j，第一次遍历的时候指针 j 用来记录当前有多少 非0 元素。即遍历的时候每遇到一个
     * 非0 元素就将其往数组左边挪，第一次遍历完后，j 指针的下标就指向了最后一个 非0 元素下标。
     * 第二次遍历的时候，起始位置就从 j 开始到结束，将剩下的这段区域内的元素全部置为 0。
     * <p>
     * 时间复杂度：O(n)
     * 空间复杂度：O(1)
     */
    public void moveZeroes1(int[] nums) {
        if (nums == null || nums.length == 0) return;

        // 第一次遍历的时候，j指针记录非0的个数，只要是非0的统统都赋给nums[j]
        int j = 0;
        for (int i = 0; i < nums.length; ++i) {
            if (nums[i] != 0) {
                nums[j++] = nums[i];
            }
        }

        // 非0元素统计完了，剩下的都是0了
        // 所以第二次遍历把末尾的元素都赋为0即可
        for (int i = j; i < nums.length; ++i) {
            nums[i] = 0;
        }
    }

    /**
     * 一次遍历
     * 这里参考了快速排序的思想，快速排序首先要确定一个待分割的元素做中间点 x，然后把所有小于等于 x 的元素
     * 放到 x 的左边，大于 x 的元素放到其右边。
     * 这里我们可以用 0 当做这个中间点，把不等于 0(注意题目没说不能有负数)的放到中间点的左边，等于 0 的放到其右边。
     * 这的中间点就是 0 本身，所以实现起来比快速排序简单很多，我们使用两个指针 i 和 j，只要 nums[i]!=0，
     * 我们就交换 nums[i] 和 nums[j]
     *
     * 时间复杂度：O(n)
     * 空间复杂度：O(1)
     */
    public void moveZeroes2(int[] nums) {
        if (nums == null || nums.length == 0) return;

        // 两个指针i和j
        int j = 0;
        for(int i=0;i<nums.length;i++) {
            // 当前元素!=0，就把其交换到左边，等于0的交换到右边
            if(nums[i]!=0) {
                int tmp = nums[i];
                nums[i] = nums[j];
                nums[j++] = tmp;
            }
        }
    }

    public void moveZeroes3(int[] nums) {
        if (nums == null || nums.length == 0) return;

        int cur = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
                if (cur != i) {
                    nums[cur] = nums[i];
                    nums[i] = 0;
                }
                cur++;
            }
        }
    }

    public void moveZeroes4(int[] nums) {
        if (nums == null || nums.length == 0) return;

        for (int i = 0, cur = 0; i < nums.length; i++) {
            if (nums[i] == 0) continue;;

            if (cur != i) {
                nums[cur] = nums[i];
                nums[i] = 0;
            }
            cur++;
        }
    }
}
