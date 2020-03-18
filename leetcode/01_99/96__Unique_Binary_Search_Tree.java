class Solution {
    public int numTrees(int n) {
        return dfs(n, new Integer[n + 1]);
    }
    
    private int dfs(int n, Integer[] memo) {
        if (n == 0) {
            return 1;
        } else if (n == 1) {
            return 1;
        }
        if (memo[n] != null) {
            return memo[n];
        }
        int ret = 0;
        for (int root = 1; root <= n; root++) {
            ret += dfs(root - 1, memo) * dfs(n - root, memo);
        }
        memo[n] = ret;
        return ret;
    }
}

class Solution {
    public int numTrees(int n) {
        if (n <= 0) {
            return 0;
        }
        
        int[] dp = new int[n + 1];
        
        dp[0] = 1;
        dp[1] = 1;
        
        for (int i = 2; i <= n; i++) {
            dp[i] = 0;
            for (int j = 0; j <= i - 1; j++) {
                dp[i] += dp[j] * dp[i - 1 - j];
            }
        }
        
        return dp[n];
    }
}