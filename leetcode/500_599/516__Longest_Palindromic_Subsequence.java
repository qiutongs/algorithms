class Solution {
    public int longestPalindromeSubseq(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        int n = s.length();
        int[][] dp = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                dp[i][j] = i == j ? 1 : 0;
            }
        }
        
        for (int interval = 2; interval <= n; interval++) {
            for (int i = 0; i + interval <= n; i++) {
                if (s.charAt(i) == s.charAt(i + interval - 1)) {
                    dp[i][i + interval - 1] = dp[i + 1][i + interval - 2] + 2;
                } else {
                    dp[i][i + interval - 1] = Math.max(dp[i + 1][i + interval - 1], dp[i][i + interval - 2]);
                }
            }
        }
        
        return dp[0][n - 1];
    }
}

// DFS + memo
// Time: O(N^2)
// Space: O(N^2)
class Solution {
    public int longestPalindromeSubseq(String s) {
        return dfs(s, 0, s.length() - 1, new Integer[s.length()][s.length()]);
    }
    
    private int dfs(String s, int lIndex, int rIndex, Integer[][] memo) {
        if (lIndex > rIndex) {
            return 0;
        } else if (lIndex == rIndex) {
            return 1;
        }
        if (memo[lIndex][rIndex] != null) {
            return memo[lIndex][rIndex];
        }
        int ret = 0;
        if (s.charAt(lIndex) == s.charAt(rIndex)) {
            ret = 2 + dfs(s, lIndex + 1, rIndex - 1, memo);
        } else {
            ret = Math.max(dfs(s, lIndex + 1, rIndex, memo), dfs(s, lIndex, rIndex - 1, memo));
        }
        memo[lIndex][rIndex] = ret;
        return ret;
    }
}