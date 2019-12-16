class Solution {
    public String shortestCommonSupersequence(String str1, String str2) {
        int len1 = str1.length(), len2 = str2.length();
        String[][] dp = new String[len1 + 1][len2 + 1];
        for (int i = 0; i <= len1; i++) {
            dp[i][0] = str1.substring(0, i);
        }
        for (int i = 0; i <= len2; i++) {
            dp[0][i] = str2.substring(0, i);
        }
        
        for (int i = 1; i <= len1; i++) {
            for (int j = 1; j <= len2; j++) {
                char c1 = str1.charAt(i - 1), c2 = str2.charAt(j - 1);
                if (c1 == c2) {
                    dp[i][j] = dp[i - 1][j - 1] + c1;
                } else {
                    dp[i][j] = dp[i - 1][j].length() < dp[i][j - 1].length() ? dp[i - 1][j] + c1 : dp[i][j - 1] + c2;
                }
            }
        }
        return dp[len1][len2];
    }
}