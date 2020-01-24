public class Solution {
    /**
     * @param n: the money of you
     * @param prices: the price of rice[i]
     * @param weight: the weight of rice[i]
     * @param amounts: the amount of rice[i]
     * @return: the maximum weight
     */
    public int backPackVII(int n, int[] prices, int[] weight, int[] amounts) {
        if (n <= 0) {
            return 0;
        }
        int len = prices.length;
        int[][] dp = new int[n + 1][len + 1];
        for (int i = 1; i < dp.length; i++) {
            for (int j = 1; j < dp[0].length; j++) {
                for (int k = 0; k <= amounts[j - 1] && k * prices[j - 1] <= i; k++) {
                    dp[i][j] = Math.max(dp[i][j], dp[i - k * prices[j - 1]][j - 1] + k * weight[j - 1]);
                }
            }
        }
        return dp[n][len];
    }
}