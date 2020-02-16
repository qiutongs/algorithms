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

// DFS + memo
class Solution {
    public int coinChange(int[] coins, int amount) {
        Integer[] memo = new Integer[amount + 1];
        int ret = dfs(coins, amount, memo);
        return ret == Integer.MAX_VALUE ? -1 : ret;
    }
    
    private int dfs(int[] coins, int amount, Integer[] memo) {
        if (amount == 0) {
            return 0;
        }
        if (memo[amount] != null) {
            return memo[amount];
        }
        int ret = Integer.MAX_VALUE;
        for (int coin : coins) {
            if (amount >= coin) {
                ret = Math.min(ret, dfs(coins, amount - coin, memo));
            }
        }
        ret = ret == Integer.MAX_VALUE ? Integer.MAX_VALUE : ret + 1;
        memo[amount] = ret;
        return ret;
    }
}

// Raw DFS
// Time Limit Exceeded: [1,2,5] 100
class Solution {
    public int coinChange(int[] coins, int amount) {
        int ret = dfs(coins, amount);
        return ret == Integer.MAX_VALUE ? -1 : ret;
    }
    
    private int dfs(int[] coins, int amount) {
        if (amount == 0) {
            return 0;
        }
        int ret = Integer.MAX_VALUE;
        for (int coin : coins) {
            if (amount >= coin) {
                ret = Math.min(ret, dfs(coins, amount - coin));
            }
        }
        return ret == Integer.MAX_VALUE ? Integer.MAX_VALUE : ret + 1;
    }
}