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
    
    private int dfs(String s, int nbIdx, int endIdx, Integer[][] memo) {
        if (nbIdx > endIdx) {
            return 0;
        }
        if (nbIdx == endIdx) {
            return 1;
        }
        if (memo[nbIdx][endIdx] != null) {
            return memo[nbIdx][endIdx];
        }
        int ret = 0;
        if (s.charAt(nbIdx) == s.charAt(endIdx)) {
            ret = 2 + dfs(s, nbIdx + 1, endIdx - 1, memo);
        } else {
            ret = Math.max(dfs(s, nbIdx + 1, endIdx, memo), dfs(s, nbIdx, endIdx - 1, memo));
        }
        memo[nbIdx][endIdx] = ret;
        return ret;
    }
}