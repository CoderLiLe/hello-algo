package 高频题;

public class _042_接雨水 {
    public int trap(int[] height) {
        if (height == null || height.length == 0) return 0;

        int lastIdx = height.length - 2;
        int[] leftMaxes = new int[height.length];
        for (int i = 1; i <= lastIdx; i++) {
            leftMaxes[i] = Math.max(leftMaxes[i - 1], height[i - 1]);
        }

        int[] rightMaxes = new int[height.length];
        for (int i = lastIdx; i >= 1; i--) {
            rightMaxes[i] = Math.max(rightMaxes[i+1], height[i + 1]);
        }

        // 遍历每一根柱子，看看每一根柱子能放多少水
        int water = 0;
        for (int i = 1; i <= lastIdx; i++) {
            // 求出左边最大、右边最大中的较小者
            int min = Math.min(leftMaxes[i], rightMaxes[i]);
            // 说明这根柱子不能放水
            if (min <= height[i]) continue;
            // 说明这根柱子能放水
            water += min - height[i];
        }

        return water;
    }

    public int trap2(int[] height) {
        if (height == null || height.length == 0) return 0;

        int lastIdx = height.length - 2;

        int[] rightMaxes = new int[height.length];
        for (int i = lastIdx; i >= 1; i--) {
            rightMaxes[i] = Math.max(rightMaxes[i+1], height[i + 1]);
        }

        // 遍历每一根柱子，看看每一根柱子能放多少水
        int water = 0, leftMax = 0;
        for (int i = 1; i <= lastIdx; i++) {
            leftMax = Math.max(leftMax, height[i - 1]);
            // 求出左边最大、右边最大中的较小者
            int min = Math.min(leftMax, rightMaxes[i]);
            // 说明这根柱子不能放水
            if (min <= height[i]) continue;
            // 说明这根柱子能放水
            water += min - height[i];
        }

        return water;
    }

    public int trap3(int[] height) {
        if (height == null || height.length == 0) return 0;

        int l = 0, r = height.length - 1, lowerMax = 0, water = 0;
        while (l < r) {
            int lower = height[height[l] <= height[r] ? l++ : r--];
            lowerMax = Math.max(lowerMax, lower);
            water += lowerMax - lower;
        }
        return water;
    }
}
