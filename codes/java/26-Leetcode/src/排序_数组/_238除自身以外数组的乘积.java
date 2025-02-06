package 排序_数组;

import tools.Asserts;

import java.util.Arrays;

/**
 * https://leetcode.cn/problems/product-of-array-except-self/description/?envType=study-plan-v2&envId=top-100-liked
 *
 * 给你一个整数数组 nums，返回 数组 answer ，其中 answer[i] 等于 nums 中除 nums[i] 之外其余各元素的乘积 。
 *
 * 题目数据 保证 数组 nums之中任意元素的全部前缀元素和后缀的乘积都在  32 位 整数范围内。
 *
 * 请 不要使用除法，且在 O(n) 时间复杂度内完成此题。
 */
public class _238除自身以外数组的乘积 {
    /**
     * 暴力法
     * 时间复杂度: O(n^2)
     * 空间复杂度: O(n)
     * @param nums
     * @return
     */
    public int[] productExceptSelf(int[] nums) {
        if (nums == null || nums.length == 0) {
            return new int[0];
        }

        int[] res = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            int product = 1;
            for (int j = 0; j < nums.length; j++) {
                if (i != j) {
                    product *= nums[j];
                }
            }
            res[i] = product;
        }
        return res;
    }

    /**
     * 除法:如果有0，则异常
     * 时间复杂度: O(n)
     * 空间复杂度: O(1)
     * @param nums
     * @return
     */
    public int[] productExceptSelf2(int[] nums) {
        if (nums == null || nums.length == 0) {
            return new int[0];
        }

        int allProduct = 1;
        for (int i = 0; i < nums.length; i++) {
            allProduct *= nums[i];
        }

        int[] res = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            res[i] = allProduct / nums[i];
        }
        return res;
    }

    /**
     * answer[i] 等于 nums 中除了 nums[i] 之外其余各元素的乘积。换句话说，如果知道了 i 左边所有数的乘积，以及 i 右边所有数的乘积，就可以算出 answer[i]。
     *
     * 于是：
     *
     * 定义 pre[i] 表示从 nums[0] 到 nums[i−1] 的乘积。
     * 定义 suf[i] 表示从 nums[i+1] 到 nums[n−1] 的乘积。
     *
     * 我们可以先计算出从 nums[0] 到 nums[i−2] 的乘积 pre[i−1]，再乘上 nums[i−1]，就得到了 pre[i]，
     * 即pre[i] = pre[i−1]⋅nums[i−1]
     * 同理有suf[i] = suf[i+1]⋅nums[i+1]
     *
     * 初始值：pre[0]=suf[n−1]=1。
     *
     * 算出 pre 数组和 suf 数组后，有 answer[i] = pre[i]⋅suf[i]
     *
     * 时间复杂度：O(n)，其中 n 是 nums 的长度。
     * 空间复杂度：O(n)。
     *
     * @param nums
     * @return
     */
    public int[] productExceptSelf3(int[] nums) {
        // 检查输入数组是否为空或长度为0，如果是则返回空数组
        if (nums == null || nums.length == 0) {
            return new int[0];
        }

        int len = nums.length;
        int[] pre = new int[len];
        pre[0] = 1;
        for (int i = 1; i < len; i++) {
            pre[i] = pre[i - 1] * nums[i - 1];
        }

        int[] suf = new int[len];
        suf[len - 1] = 1;
        for (int i = len - 2; i >= 0; i--) {
            suf[i] = suf[i + 1] * nums[i + 1];
        }

        int[] res = new int[len];
        for (int i = 0; i < len; i++) {
            res[i] = pre[i] * suf[i];
        }
        return res;
    }

    /**
     * 优化：不使用额外空间
     * 先计算 suf，然后一边计算 pre，一边把 pre 直接乘到 suf[i] 中。最后返回 suf。
     *
     * 题目说「输出数组不被视为额外空间」，所以该做法的空间复杂度为 O(1)。此外，这种做法比上面少遍历了一次。
     *
     * 时间复杂度：O(n)，其中 n 是 nums 的长度。
     * 空间复杂度：O(1)。返回值不计入。
     *
     * @param nums
     * @return
     */
    public int[] productExceptSelf4(int[] nums) {
        // 检查输入数组是否为空或长度为0，如果是则返回空数组
        if (nums == null || nums.length == 0) {
            return new int[0];
        }

        int len = nums.length;

        int[] suf = new int[len];
        suf[len - 1] = 1;
        for (int i = len - 2; i >= 0; i--) {
            suf[i] = suf[i + 1] * nums[i + 1];
        }

        int pre = 1;
        for (int i = 0; i < len; i++) {
            suf[i] *= pre;
            pre *= nums[i];
        }
        return suf;
    }


    /**
     * 算法流程：
     * 初始化：数组 res ，其中 res[0]=1 ；辅助变量 tmp=1 。
     * 计算 res[i] 的 下三角 各元素的乘积，直接乘入 res[i] 。
     * 计算 res[i] 的 上三角 各元素的乘积，记为 tmp ，并乘入 res[i] 。
     * 返回 res 。
     *
     * 时间复杂度 O(N) ： 其中 N 为数组长度，两轮遍历数组 nums ，使用 O(N) 时间。
     * 空间复杂度 O(1) ： 变量 tmp 使用常数大小额外空间（数组 ans 作为返回值，不计入复杂度考虑）。
     *
     * @param nums 输入的整数数组
     * @return 返回一个整数数组，其中每个元素值为除自身以外所有其他元素的乘积
     */
    public int[] productExceptSelf5(int[] nums) {
        // 检查输入数组是否为空或长度为0，如果是则返回空数组
        if (nums == null || nums.length == 0) {
            return new int[0];
        }

        // 初始化结果数组，长度与输入数组相同
        int[] res = new int[nums.length];
        // 设置结果数组的第一个元素为1，因为这是计算乘积的起始值
        res[0] = 1;
        // 计算每个元素左侧所有元素的乘积，并直接保存在结果数组中
        for (int i = 1; i < nums.length; i++) {
            res[i] = res[i - 1] * nums[i - 1];
        }

        // 初始化一个临时变量用于计算右侧元素的乘积
        int tmp = 1;
        // 从右向左遍历数组，计算每个元素右侧所有元素的乘积，并与该元素左侧的乘积相乘
        for (int i = nums.length - 2; i >= 0; i--) {
            tmp *= nums[i + 1];
            res[i] *= tmp;
        }

        // 返回结果数组
        return res;
    }

    public static void main(String[] args) {
        _238除自身以外数组的乘积 obj = new _238除自身以外数组的乘积();
        int[] nums = new int[] {1, 2, 3, 4, 5};
        int[] res = new int[] {120, 60, 40, 30, 24};
        Asserts.test(Arrays.equals(obj.productExceptSelf(nums), res));
        Asserts.test(Arrays.equals(obj.productExceptSelf2(nums), res));
        Asserts.test(Arrays.equals(obj.productExceptSelf3(nums), res));
        Asserts.test(Arrays.equals(obj.productExceptSelf4(nums), res));
        Asserts.test(Arrays.equals(obj.productExceptSelf5(nums), res));
    }
}
