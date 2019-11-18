class Solution {
    public int largestRectangleArea(int[] heights) {
        if (heights == null || heights.length == 0) {
            return 0;
        }
        int ret = 0;
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i <= heights.length; i++) {
            while(stack.isEmpty() == false 
                  && (i == heights.length || heights[stack.peek()] > heights[i])) {
                int hIdx = stack.pop();
                int lIdx = stack.isEmpty() ? 0 : stack.peek() + 1;
                ret = Math.max(ret, (i - lIdx) * heights[hIdx]);
            }
            stack.push(i);
        }
        return ret;
    }
}