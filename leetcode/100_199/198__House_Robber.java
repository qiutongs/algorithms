class Solution {
    public int rob(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        
        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        int ret = dp[0];
        
        for (int i = 1; i < nums.length; i++) {
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