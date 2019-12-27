// O(nm)
class Solution {
    public int coinChange(int[] coins, int amount) {
        // dp[i] is the min amount of coins that add up to i
        int[] dp = new int[amount + 1];
        for (int i = 1; i < dp.length; i++) {
            dp[i] = Integer.MAX_VALUE;
            for (int coin : coins) {
                if (i >= coin && dp[i - coin] >= 0) {
                    dp[i] = Math.min(dp[i], dp[i - coin] + 1);
                }
            }
            dp[i] = dp[i] == Integer.MAX_VALUE ? -1 : dp[i];
        }
        return dp[amount];
    }
}

class Solution {
    public int coinChange(int[] coins, int amount) {
        // dp[i] is the min amount of coins that add up to i
        int[][] dp = new int[coins.length + 1][amount + 1];
        for (int i = 0; i <= amount; i++) {
            dp[0][i] = Integer.MAX_VALUE;
        }
        for (int i = 0; i <= coins.length; i++) {
            dp[i][0] = 0;
        }
        
        for (int i = 1; i <= coins.length; i++) {
            for (int j = 1; j <= amount; j++) {
                dp[i][j] = dp[i - 1][j];
                for (int k = 1; k * coins[i - 1] <= j; k++) {
                    if (dp[i][j - k * coins[i - 1]] != Integer.MAX_VALUE) {
                        dp[i][j] = Math.min(dp[i][j], dp[i][j - k * coins[i - 1]] + k);
                    }
                }
            }
        }
        return dp[coins.length][amount] == Integer.MAX_VALUE ? -1 : dp[coins.length][amount];
    }
}