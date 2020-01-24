// Memory limit exceed
class Solution {
    public int maxProfit(int k, int[] prices) {
        if (prices.length == 0) {
            return 0;
        }
        
        int ret = 0;
        int[][][] dp = new int[prices.length][k + 1][2];
        for (int i = 0; i < prices.length; i++) {
            dp[i][0][0] = 0;
            dp[i][0][1] = 0;
        }
        for (int i = 1; i <= k; i++) {
            dp[0][i][0] = 0;
            dp[0][i][1] = -prices[0];
        }
        
        for (int i = 1; i < prices.length; i++) {
            for (int j = 1; j <= k; j++) {
                dp[i][j][0] = Math.max(dp[i - 1][j][0], dp[i - 1][j][1] + prices[i]);
                dp[i][j][1] = Math.max(dp[i - 1][j][1], dp[i - 1][j - 1][0] - prices[i]);
            }
        }
        
        return dp[prices.length - 1][k][0];
    }
}

// memory optimization: but still Memory limit exceed because k can be too large
class Solution {
    public int maxProfit(int k, int[] prices) {
        if (prices.length == 0) {
            return 0;
        }
        
        int ret = 0;
        int[][][] dp = new int[2][k + 1][2];
        for (int i = 0; i < 2; i++) {
            dp[i][0][0] = 0;
            dp[i][0][1] = 0;
        }
        for (int i = 1; i <= k; i++) {
            dp[0][i][0] = 0;
            dp[0][i][1] = -prices[0];
        }
        
        for (int i = 1; i < prices.length; i++) {
            for (int j = 1; j <= k; j++) {
                dp[i % 2][j][0] = Math.max(dp[(i - 1) % 2][j][0], dp[(i - 1) % 2][j][1] + prices[i]);
                dp[i % 2][j][1] = Math.max(dp[(i - 1) % 2][j][1], dp[(i - 1) % 2][j - 1][0] - prices[i]);
            }
        }
        
        return dp[(prices.length - 1) % 2][k][0];
    }
}