class Solution {
    public int[] nextGreaterElements(int[] nums) {
        int[] ret = new int[nums.length];
        Arrays.fill(ret, -1);
        Stack<Integer> deStack = new Stack<>();
        
        for (int i = 0; i < nums.length; i++) {
            while(deStack.isEmpty() == false && nums[deStack.peek()] < nums[i]) {
                ret[deStack.pop()] = nums[i];
            }
            deStack.push(i);
        }
        
        for (int i = 0; i < nums.length; i++) {
            while(deStack.isEmpty() == false && nums[deStack.peek()] < nums[i]) {
                ret[deStack.pop()] = nums[i];
            }
        }
        return ret;
    }
}