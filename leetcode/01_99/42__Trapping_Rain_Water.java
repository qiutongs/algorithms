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