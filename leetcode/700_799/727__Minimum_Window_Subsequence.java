class Solution {
    public String minWindow(String S, String T) {
        int min = Integer.MAX_VALUE;
        int minEndIdx = -1;
        int sLen = S.length(), tLen = T.length();
        int[][] dp = new int[sLen + 1][tLen + 1];
        for (int i = 0; i < sLen + 1; i++) {
            for (int j = 0; j < tLen + 1; j++) {
                dp[i][j] = j == 0 ? 0 : -1;
            }
        }
        for (int i = 0; i < sLen; i++) {
            for (int j = 0; j < tLen; j++) {
                if (S.charAt(i) == T.charAt(j)) {
                    dp[i + 1][j + 1] = dp[i][j] == -1 ? -1 : dp[i][j] + 1;
                } else {
                    dp[i + 1][j + 1] = dp[i][j + 1] == -1 ? -1 : dp[i][j + 1] + 1;
                }
            }
            if (dp[i + 1][tLen] >= 0 && dp[i + 1][tLen] < min) {
                min = dp[i + 1][tLen];
                minEndIdx = i;
            }
        }
        return min == Integer.MAX_VALUE ? "" 
            : S.substring(minEndIdx - dp[minEndIdx + 1][tLen] + 1, minEndIdx + 1);
    }
}