public class Solution {
    /**
     * @param m: An integer m denotes the size of a backpack
     * @param A: Given n items with size A[i]
     * @param V: Given n items with value V[i]
     * @return: The maximum value
     */
    public int backPackII(int m, int[] A, int[] V) {
        int n = A.length;
        int[][] dp = new int[n + 1][m + 1];
        for (int i = 1; i < dp.length; i++) {
            for (int j = 1; j < dp[0].length; j++) {
                dp[i][j] = dp[i - 1][j];
                if (A[i - 1] <= j) {
                    dp[i][j] = Math.max(dp[i][j], V[i - 1] + dp[i - 1][j - A[i - 1]]);
                }
            }
        }
        return dp[n][m];
    }
}