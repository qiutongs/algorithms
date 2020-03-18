class Solution {
    public int splitArray(int[] nums, int m) {
        int n = nums.length;
        long[] preSum = new long[n + 1];
        for (int i = 0; i < n; i++) {
            preSum[i + 1] = preSum[i] + nums[i];
        }
        
        long[][] dp = new long[n][m + 1]; 
        dp[0][1] = nums[0];
        for (int i = 2; i < m + 1; i++) {
            dp[0][i] = -1;
        }
        for (int i = 1; i < n; i++) {
            dp[i][1] = dp[i - 1][1] + nums[i];
        }

        for (int i = 1; i < n; i++) {
            for (int j  = 2; j < m + 1; j++) {
                dp[i][j] = Long.MAX_VALUE;
                for (int k = 0; k < i; k++) {
                    if (dp[k][j - 1] != -1) {
                        long candidate = Math.max(dp[k][j - 1], preSum[i + 1] - preSum[k + 1]);
                        dp[i][j] = Math.min(dp[i][j], candidate);
                    }
                }
                dp[i][j] = dp[i][j] == Long.MAX_VALUE ? -1 : dp[i][j];
            }
        }
        return (int)dp[n - 1][m];
    }
}