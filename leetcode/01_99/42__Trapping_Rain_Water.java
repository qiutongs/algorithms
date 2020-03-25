// BF: look at how much water each height can hold on its top
// View the problem vertically
class Solution {
    public int trap(int[] height) {
        if (height == null || height.length == 0) {
            return 0;
        }
        int ret = 0;
        for (int i = 0; i < height.length; i++) {
            int leftMax = 0, rightMax = 0;
            for (int j = 0; j < i; j++) {
                leftMax = Math.max(leftMax, height[j]);
            }
            for (int j = i + 1; j < height.length; j++) {
                rightMax = Math.max(rightMax, height[j]);
            }
            int min = Math.min(leftMax, rightMax);
            if (min > height[i]) {
                ret += min - height[i];
            }
        }
        return ret;
    }
}

// Two pointer
class Solution {
    public int trap(int[] height) {
        int ret = 0;
        int l = 0, r = height.length - 1;
        while(l < r) {
            if (height[l] < height[r]) {
                int lH = height[l];
                while(l < r && height[l] <= lH) {
                    ret += lH - height[l];
                    l++;
                }
            } else {
                int rH = height[r];
                while(l < r && height[r] <= rH) {
                    ret += rH - height[r];
                    r--;
                }
            }
        }
        return ret;
    }
}

// Decreasing monotonic stack
class Solution {
    public int trap(int[] height) {
        if (height == null || height.length == 0) {
            return 0;
        }
        int ret = 0;
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < height.length; i++) {
            while(stack.isEmpty() == false && height[stack.peek()] < height[i]) {
                int hIdx = stack.pop();
                int lIdx = stack.isEmpty() ? hIdx : stack.peek();
                int h = Math.min(height[i], height[lIdx]) - height[hIdx];
                int w = i - lIdx - 1;
                ret += w * h;
            }
            stack.push(i);
        }
        return ret;
    }
}