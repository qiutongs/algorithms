
/*
sum up all the "uprise" gain
*/
class Solution {
    public int maxProfit(int[] prices) {
        if (prices.length == 0) {
            return 0;
        }

        int result = 0;
        int curPrice = prices[0];

        for (int i = 1; i < prices.length; i++) {
            if (prices[i] > curPrice) {
                result += prices[i] - curPrice;
            }
            curPrice = prices[i];
        }

        return result;
    }
}

class Solution {
    public int maxProfit(int[] prices) {
        if (prices.length == 0) {
            return 0;
        }
        
        int ret = 0;
        int[][] dp = new int[prices.length][2];
        dp[0][0] = 0;
        dp[0][1] = -prices[0];
        
        for (int i = 1; i < prices.length; i++) {
            // no stock at i day
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] + prices[i]);
            // has stock at i day
            dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][0] - prices[i]);
        }
        
        return dp[prices.length - 1][0];
    }
}