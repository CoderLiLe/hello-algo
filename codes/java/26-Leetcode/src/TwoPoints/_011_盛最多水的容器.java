package TwoPoints;

import tools.Asserts;

/**
 * 【11_盛最多水的容器】
 * 给你 n 个非负整数 a1，a2，...，an，每个数代表坐标中的一个点 (i, ai) 。在坐标内画 n 条垂直线，垂直线 i 的两个端点分别为 (i, ai) 和 (i, 0) 。
 * 找出其中的两条线，使得它们与 x 轴共同构成的容器可以容纳最多的水。
 * <p>
 * 说明：你不能倾斜容器。
 */
public class _011_盛最多水的容器 {

    public static void main(String[] args) {
        _011_盛最多水的容器 obn = new _011_盛最多水的容器();

        int[] height = {1, 8, 6, 2, 5, 4, 8, 3, 7};
        int res = 49;
        Asserts.test(obn.maxArea(height) == res);
        Asserts.test(obn.maxArea1(height) == res);
        Asserts.test(obn.maxArea2(height) == res);
        Asserts.test(obn.maxArea3(height) == res);
    }

    /**
     * 双指针法的经典题目
     * <p>
     * T = O(n), S = O(1)
     */
    public int maxArea(int[] height) {
        if (height == null || height.length == 0) return 0;

        int l = 0, r = height.length - 1, water = 0;
        while (l < r) {
            if (height[l] <= height[r]) {
                water = Math.max(water, (r - l) * height[l]);
                l++;
            } else {
                water = Math.max(water, (r - l) * height[r]);
                r--;
            }
        }

        return water;
    }

    public int maxArea1(int[] height) {
        if (height == null || height.length == 0) return 0;

        int l = 0, r = height.length - 1, water = 0;
        while (l < r) {
            int minH = (height[l] <= height[r]) ? height[l++] : height[r--];
            water = Math.max(water, (r - l + 1) * minH);
        }

        return water;
    }

    public int maxArea2(int[] height) {
        if (height == null || height.length == 0) return 0;

        int l = 0, r = height.length - 1, water = 0;
        while (l < r) {
            if (height[l] <= height[r]) {
                int minH = height[l];
                water = Math.max(water, (r - l) * minH);
                l++;
                while (l < r && height[l] <= minH) l++;
            } else {
                int minH = height[r];
                water = Math.max(water, (r - l) * minH);
                r--;
                while (l < r && height[r] <= minH) r--;
            }
        }

        return water;
    }

    public int maxArea3(int[] height) {
        if (height == null || height.length == 0) return 0;

        int l = 0, r = height.length - 1, water = 0;
        while (l < r) {
            int minH = (height[l] <= height[r]) ? height[l] : height[r];
            water = Math.max(water, (r - l) * minH);
            while (l < r && height[l] <= minH) l++;
            while (l < r && height[r] <= minH) r--;
        }

        return water;
    }
}
