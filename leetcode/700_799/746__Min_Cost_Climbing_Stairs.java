class Solution {
    public int minCostClimbingStairs(int[] cost) {
        if (cost == null || cost.length == 0) {
            return 0;
        }
        if (cost.length == 1) {
            return cost[0];
        } 
        int[] dp = new int[cost.length + 1];
        dp[0] = cost[0];
        dp[1] = cost[1];
        
        for (int i = 2; i < dp.length; i++) {
            int curCost = i < cost.length ? cost[i] : 0;
            dp[i] = Math.min(dp[i - 1], dp[i - 2]) + curCost;
        }
        return dp[cost.length];
    }
}

// DFS + memo
class Solution {
    public int minCostClimbingStairs(int[] cost) {
        if (cost == null || cost.length == 0) {
            return 0;
        }
        Integer[] memo = new Integer[cost.length];
        return Math.min(dfs(cost, 0, memo), dfs(cost, 1, memo));
    }
    
    private int dfs(int[] cost, int offset, Integer[] memo) {
        if (offset >= cost.length) {
            return 0;
        }
        if (memo[offset] != null) {
            return memo[offset];
        }
        int ret = cost[offset] + Math.min(dfs(cost, offset + 1, memo),
                                          dfs(cost, offset + 2, memo));
    
        memo[offset] = ret;
        return ret;
    }
}

// Raw DFS
// Time Limit Exceeded
class Solution {
    public int minCostClimbingStairs(int[] cost) {
        if (cost == null || cost.length == 0) {
            return 0;
        }
        return dfs(cost, -1);
    }
    
    private int dfs(int[] cost, int offset) {
        if (offset >= cost.length) {
            return 0;
        }
        int c = offset >= 0 ? cost[offset] : 0;
        return c + Math.min(dfs(cost, offset + 1),
                            dfs(cost, offset + 2));
    }
}