class Solution {
    public int largestRectangleArea(int[] heights) {
        int ret = 0;
        Stack<Integer> upStack = new Stack<>();
        for (int i = 0; i <= heights.length; i++) {
            while (upStack.isEmpty() == false && (i == heights.length || heights[upStack.peek()] > heights[i])) {
                int hIdx = upStack.pop();
                int lSmallIdx = upStack.isEmpty() ? -1 : upStack.peek();
                int rSmallIdx = i;
                ret = Math.max(ret, heights[hIdx] * (rSmallIdx - lSmallIdx - 1));
            }
            upStack.push(i);
        }
        return ret;
    }
}