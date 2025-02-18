package 高频题;

public class _011_盛最多水的容器 {
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
