package TwoPoints;

/**
 * 【11_盛最多水的容器】
 * 给你 n 个非负整数 a1，a2，...，an，每个数代表坐标中的一个点 (i, ai) 。在坐标内画 n 条垂直线，垂直线 i 的两个端点分别为 (i, ai) 和 (i, 0) 。
 * 找出其中的两条线，使得它们与 x 轴共同构成的容器可以容纳最多的水。
 *
 * 说明：你不能倾斜容器。
 */
public class _011_盛最多水的容器 {

    public static void main(String[] args) {
        int[] height = {1,8,6,2,5,4,8,3,7};
        System.out.println(maxArea(height));
    }

    /**
     * 双指针法的经典题目
     *
     * T = O(n), S = O(1)
     */
    public static int maxArea(int[] height) {
        int l = 0, r = height.length - 1;
        int maxArea = 0;
        while (l < r) {
            int area = Math.min(height[l], height[r]) * (r - l);
            maxArea = Math.max(maxArea, area);
            if (height[l] <= height[r]) {
                ++l;
            } else {
                --r;
            }
        }
        return maxArea;
    }
}
