class Solution {
    public int findLength(int[] A, int[] B) {
        if (A == null || B == null || A.length == 0 || B.length == 0) {
            return 0;
        }
        
        int m = A.length, n = B.length;
        int[][] dp = new int[m][n];
        int ret = 0;
        
        for (int i = 0; i < m; i++) {
            if (A[i] == B[0]) {
                dp[i][0] = 1;
                ret = 1;
            }
        }
        for (int i = 0; i < n; i++) {
            if (A[0] == B[i]) {
                dp[0][i] = 1;
                ret = 1;
            }
        }
        
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (A[i] == B[j]) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                    ret = Math.max(ret, dp[i][j]);
                } else {
                    dp[i][j] = 0;
                }
            }
        }
        return ret;
    }
}