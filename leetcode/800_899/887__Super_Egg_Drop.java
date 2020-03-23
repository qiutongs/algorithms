// Time Limit Exceeded 6, 10000
class Solution {
    public int superEggDrop(int K, int N) {
        return dfs(K, N, new Integer[K + 1][N + 1]);
    }
    
    private int dfs(int K, int N, Integer[][] memo) {
        if (N == 0) {
            return 0;
        } else if (K == 1) {
            return N;
        }
        if (memo[K][N] != null) {
            return memo[K][N];
        }
        int ret = Integer.MAX_VALUE;
        for (int i = 1; i <= N; i++) {
            int dropHere = Math.max(dfs(K - 1, i - 1, memo), dfs(K, N - i, memo));
            ret = Math.min(ret, dropHere);
        }
        memo[K][N] = ret + 1;
        return ret + 1;
    }
}

// O(kn^n) Time Limit Exceeded
class Solution {
    public int superEggDrop(int K, int N) {
        int[][] dp = new int[K + 1][N + 1];
        for (int i = 0; i <= K; i++) {
            dp[i][0] = 0;
        }
        for (int i = 0; i <= N; i++) {
            dp[1][i] = i;
        }
        
        for (int i = 2; i <= K; i++) {
            for (int j = 1; j <= N; j++) {
                dp[i][j] = Integer.MAX_VALUE;
                for (int k = 1; k <= j; k++) {
                    int maxDrop = Math.max(dp[i - 1][k - 1], dp[i][j - k]);
                    dp[i][j] = Math.min(dp[i][j], maxDrop + 1);
                }
            }
        }
        return dp[K][N];
    }
}