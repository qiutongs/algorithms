class Solution {
    public int countSubstrings(String s) {
        if (s == null || s.isEmpty()) {
            return 0;
        }
        int ret = 0;
        int len = s.length();
        boolean[][] dp = new boolean[len][len];
        for (int inter = 1; inter <= len; inter++) {
            for (int i = 0; i + inter <= len; i++) {
                int j = i + inter - 1;
                if (inter == 1) {
                    dp[i][j] = true;
                } else if (inter == 2) {
                    dp[i][j] = s.charAt(i) == s.charAt(j);
                } else {
                    dp[i][j] = dp[i + 1][j - 1] && s.charAt(i) == s.charAt(j);
                }
                if (dp[i][j]) {
                    ret++;
                }
            }
        }
        return ret;
    }
}