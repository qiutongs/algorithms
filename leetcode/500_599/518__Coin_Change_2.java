class Solution {
    public int change(int amount, int[] coins) {
        // dp[i] is the total combinations of coins that add up to i
        int[][] dp = new int[amount + 1][coins.length + 1];
        for (int i = 0; i < dp[0].length; i++) {
            dp[0][i] = 1;
        }
        for (int i = 1; i < dp.length; i++) {
            for (int j = 1; j < dp[0].length; j++) {
                for (int k = 0; coins[j - 1] * k <= i; k++) {
                    dp[i][j] += dp[i - coins[j - 1] * k][j - 1];
                }
            }
        }
        return dp[amount][coins.length];
    }
}

// DFS + memo
class Solution {
    public int change(int amount, int[] coins) {
        Integer[][] memo = new Integer[amount + 1][coins.length];
        return dfs(amount, coins, 0, memo);
    }
    
    private int dfs(int amount, int[] coins, int offset, Integer[][] memo) {
        if (amount == 0) {
            return 1;
        }
        if (offset == coins.length) {
            return 0;
        }
        if (memo[amount][offset] != null) {
            return memo[amount][offset];
        }
        int ret = 0;
        for (int i = offset; i < coins.length; i++) {
            if (amount >= coins[i]) {
                ret += dfs(amount - coins[i], coins, i, memo);
            }
        }
        memo[amount][offset] = ret;
        return ret;
    }
}
