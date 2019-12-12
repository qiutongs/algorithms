class Solution {
    public int minMoves(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int min = nums[0];
        for (int i = 1; i < nums.length; i++) {
            min = Math.min(min, nums[i]);
        }
        
        int ret = 0;
        for (int i = 0; i < nums.length; i++) {
            ret += nums[i] - min;
        }
        return ret;
    }
}