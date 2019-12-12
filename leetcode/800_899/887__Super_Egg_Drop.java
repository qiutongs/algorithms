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