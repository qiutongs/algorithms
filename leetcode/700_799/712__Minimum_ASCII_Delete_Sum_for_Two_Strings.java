class Solution {
    public int minimumDeleteSum(String s1, String s2) {
        int len1 = s1.length(), len2 = s2.length();
        int[][] dp = new int[len1 + 1][len2 + 1];
        for (int i = 1; i <= len1; i++) {
            dp[i][0] += dp[i - 1][0] + (int)s1.charAt(i - 1); 
        }
        for (int i = 1; i <= len2; i++) {
            dp[0][i] += dp[0][i - 1] + (int)s2.charAt(i - 1); 
        }
        
        for (int i = 1; i <= len1; i++) {
            for (int j = 1; j <= len2; j++) {
                int c1 = (int)s1.charAt(i - 1), c2 = (int)s2.charAt(j - 1);
                if (c1 == c2) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    dp[i][j] = Math.min(dp[i - 1][j - 1] + c1 + c2,
                               Math.min(dp[i - 1][j] + c1, 
                                        dp[i][j - 1] + c2));
                }
            }
        }
        return dp[len1][len2];
    }
}