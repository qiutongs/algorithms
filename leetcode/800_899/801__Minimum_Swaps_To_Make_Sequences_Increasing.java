class Solution {
    public int minSwap(int[] A, int[] B) {
        int n = A.length;
        int[][] dp = new int[n][2];
        
        dp[0][0] = 0;
        dp[0][1] = 1;
        
        for (int i = 1; i < n; i++) {
            dp[i][0] = Integer.MAX_VALUE;
            dp[i][1] = Integer.MAX_VALUE;
            if (A[i] > A[i - 1] && B[i] > B[i - 1]) {
                if (dp[i - 1][0] >= 0) {
                    dp[i][0] = Math.min(dp[i][0], dp[i - 1][0]);
                }
                if (dp[i - 1][1] >= 0) {
                    dp[i][1] = Math.min(dp[i][1], dp[i - 1][1] + 1);
                }
            }
            if (A[i] > B[i - 1] && B[i] > A[i - 1]) {
                if (dp[i - 1][0] >= 0) {
                    dp[i][1] = Math.min(dp[i][1], dp[i - 1][0] + 1);
                }
                if (dp[i - 1][1] >= 0) {
                    dp[i][0] = Math.min(dp[i][0], dp[i - 1][1]);
                }
            }
            dp[i][0] = dp[i][0] == Integer.MAX_VALUE ? -1 : dp[i][0];
            dp[i][1] = dp[i][1] == Integer.MAX_VALUE ? -1 : dp[i][1];
        }
        return Math.min(dp[n - 1][0], dp[n - 1][1]);
    }
}