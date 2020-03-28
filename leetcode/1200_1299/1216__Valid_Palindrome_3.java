// DP
// Time: O(N*N*K)
class Solution {
    public boolean isValidPalindrome(String s, int K) {
        int len = s.length();
        boolean[][][] dp = new boolean[len][len][K + 1];
        for (int intvl = 1; intvl <= len; intvl++) {
            for (int i = 0; i + intvl <= len; i++) {
                int j = i + intvl - 1;
                for (int k = 0; k <= K; k++) {
                    if (intvl == 1) {
                        dp[i][j][k] = true;
                    } else if (intvl == 2) {
                        if (s.charAt(i) == s.charAt(j) || k >= 1) {
                            dp[i][j][k] = true;
                        }
                    } else {
                        if (s.charAt(i) == s.charAt(j)) {
                            dp[i][j][k] = dp[i + 1][j - 1][k];
                        } else {
                            dp[i][j][k] = k >= 1 && (dp[i][j - 1][k - 1] || dp[i + 1][j][k - 1]);
                        }
                    }
                }
            }
        }
        return dp[0][len - 1][K];
    }
}