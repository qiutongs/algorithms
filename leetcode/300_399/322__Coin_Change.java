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