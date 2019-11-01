class Solution {
    public int maxArea(int[] height) {
        if (height == null || height.length == 0) {
            return 0;
        }
        int ret = 0;
        int l = 0, r = height.length - 1;
        while(l < r) {
            ret = Math.max(ret, Math.min(height[l], height[r]) * (r - l));
            if (height[l] < height[r]) {
                l++;
            } else {
                r--;
            }
        }
        return ret;
    }
}