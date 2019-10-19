class Solution {
    public int rob(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        return Math.max(robHelper(nums, true), robHelper(nums, false));
    }
    
    private int robHelper(int[] nums, boolean robFirst) {
        int[] dp = new int[nums.length];
        dp[0] = robFirst ? nums[0] : 0;
        int ret = dp[0];
        
        int size = robFirst ? nums.length - 1 : nums.length;
        
        for (int i = 1; i < size; i++) {
            dp[i] = nums[i];
            
            if (i - 2 >= 0) {
                dp[i] += dp[i - 2];
            }
            
            if (i - 3 >= 0) {
                dp[i] = Math.max(dp[i], nums[i] + dp[i - 3]);
            }
            
            ret = Math.max(ret, dp[i]);
        }
        
        return ret;
    }
}