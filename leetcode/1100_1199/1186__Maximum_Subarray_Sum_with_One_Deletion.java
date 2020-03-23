// DP
class Solution {
    public int maximumSum(int[] arr) {
        int[][] dp = new int[arr.length][2];
        dp[0][0] = arr[0];
        dp[0][1] = 0;
        
        int ret = dp[0][0];
        for (int i = 1; i < arr.length; i++) {
            dp[i][0] = dp[i - 1][0] < 0 ? arr[i] : dp[i - 1][0] + arr[i];
            dp[i][1] = Math.max(dp[i - 1][0], dp[i - 1][1] + arr[i]);
            ret = Math.max(ret, dp[i][0]);
            ret = Math.max(ret, dp[i][1]);
        }
        return ret;
    }
}