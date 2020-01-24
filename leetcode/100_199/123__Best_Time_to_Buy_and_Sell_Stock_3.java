class Solution {
    public int maxProfit(int[] prices) {
        if (prices.length == 0) {
            return 0;
        }
        
        int ret = 0;
        int[][][] dp = new int[prices.length][2][3];
        for (int i = 0; i < prices.length; i++) {
            dp[i][0][0] = 0;
            dp[i][1][0] = 0;
        }
        for (int i = 1; i < 3; i++) {
            dp[0][0][i] = 0;
            dp[0][1][i] = -prices[0];
        }
        
        for (int i = 1; i < prices.length; i++) {
            for (int j = 1; j <= 2; j++) {
                dp[i][0][j] = Math.max(dp[i - 1][0][j], dp[i - 1][1][j] + prices[i]);
                dp[i][1][j] = Math.max(dp[i - 1][1][j], dp[i - 1][0][j - 1] - prices[i]);
            }
        }
        
        return dp[prices.length - 1][0][2];
    }
}