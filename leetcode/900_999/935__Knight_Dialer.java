// DFS + memo
// Time: O(N)
// Space: O(N)
class Solution {
    private int[][] NEIGHBORS = {{4, 6}, {6, 8}, {7, 9}, {4, 8}, {0, 3, 9}, {}, {0, 1, 7}, {2, 6}, {1, 3}, {2, 4}};
    
    private int MOD = (int)Math.pow(10, 9) + 7;
    
    public int knightDialer(int N) {
        long ret = 0;
        Long[][] memo = new Long[N + 1][10];
        for (int i = 0; i <= 9; i++) {
            ret += dfs(N, i, memo);
        }
        return (int)(ret % MOD);
    }
    
    private long dfs(int N, int digit, Long[][] memo) {
        if (N == 1) {
            return 1;
        }
        if (memo[N][digit] != null) {
            return memo[N][digit];
        }
        long ret = 0;
        for (int neighbor : NEIGHBORS[digit]) {
            ret += dfs(N - 1, neighbor, memo);
        }
        ret %= MOD;
        memo[N][digit] = ret;
        return ret;
    }
}

// raw DFS
class Solution {
    private int[][] NEIGHBORS = {{4, 6}, {6, 8}, {7, 9}, {4, 8}, {0, 3, 9}, {}, {0, 1, 7}, {2, 6}, {1, 3}, {2, 4}};
    
    private int MOD = (int)Math.pow(10, 9) + 7;
    
    public int knightDialer(int N) {
        long ret = 0;
        for (int i = 0; i <= 9; i++) {
            ret += dfs(N, i);
        }
        return (int)(ret % MOD);
    }
    
    private long dfs(int N, int digit) {
        if (N == 1) {
            return 1;
        }
        long ret = 0;
        for (int neighbor : NEIGHBORS[digit]) {
            ret += dfs(N - 1, neighbor);
        }
        return ret;
    }
}