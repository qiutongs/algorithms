/*
 * Greedy?  Just compare the sorted one with unsorted
 */
class Solution {
    public int findUnsortedSubarray(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        
        int[] sortedNums = nums.clone();
        
        Arrays.sort(sortedNums);
        
        int l = 0, r = nums.length - 1;
        
        while(l < nums.length) {
            if (nums[l] == sortedNums[l]) {
                l++;
            } else {
                break;
            }
        }
        
        while(r > l) {
            if (nums[r] == sortedNums[r]) {
                r--;
            } else {
                break;
            }
        }
        
        return r - l + 1;
    }
}

class Solution {
    public int findUnsortedSubarray(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        
        Stack<Integer> upStack = new Stack<>();
        int lIdx = nums.length;
        for (int i = 0; i < nums.length; i++) {
            while(upStack.isEmpty() == false && nums[upStack.peek()] > nums[i]) {
                lIdx = Math.min(lIdx, upStack.pop());
            }
            upStack.push(i);
        }
        
        Stack<Integer> downStack = new Stack<>();
        int rIdx = -1;
        for (int i = nums.length - 1; i >= 0; i--) {
            while(downStack.isEmpty() == false && nums[downStack.peek()] < nums[i]) {
                rIdx = Math.max(rIdx, downStack.pop());
            }
            downStack.push(i);
        }
        
        return rIdx == -1 ? 0 : rIdx - lIdx + 1;
    }
}