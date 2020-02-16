// DP
public class Solution {
    /**
     * @param m: An integer m denotes the size of a backpack
     * @param A: Given n items with size A[i]
     * @return: The maximum size
     */
    public int backPack(int m, int[] A) {
        int[][] dp = new int[A.length + 1][m + 1];
        for (int i = 0; i <= m; i++) {
            dp[0][i] = 0;
        }
        for (int i = 0; i <= A.length; i++) {
            dp[i][0] = 0;
        }
        
        for (int i = 1; i <= A.length; i++) {
            for (int j = 1; j <= m; j++) {
                dp[i][j] = dp[i - 1][j];
                int curSize = A[i - 1];
                if (curSize <= j) {
                    dp[i][j] = Math.max(dp[i][j], dp[i - 1][j - curSize] + curSize);
                }
            }
        }
        return dp[A.length][m];
    }
}

// DFS + memo
// Time Limit Exceeded (not understand why)
public class Solution {
    /**
     * @param m: An integer m denotes the size of a backpack
     * @param A: Given n items with size A[i]
     * @return: The maximum size
     */
    public int backPack(int m, int[] A) {
        Integer[][] memo = new Integer[A.length][m + 1];
        return dfs(A, 0, m, memo);
    }
    
    private int dfs(int[] A, int offset, int size, Integer[][] memo) {
        if (offset == A.length) {
            return 0;
        }
        if (memo[offset][size] != null) {
            return memo[offset][size];
        }
        int ret = 0;
        for (int i = offset; i < A.length; i++) {
            if (A[i] <= size) {
                ret = Math.max(ret, A[i] + dfs(A, i + 1, size - A[i], memo));
            }
        }
        memo[offset][size] = ret;
        return ret;
    }
}

// Raw DFS - top down
// Time Limit Exceeded
public class Solution {
    /**
     * @param m: An integer m denotes the size of a backpack
     * @param A: Given n items with size A[i]
     * @return: The maximum size
     */
    public int backPack(int m, int[] A) {
        return dfs(A, 0, m);
    }
    
    private int dfs(int[] A, int offset, int size) {
        int ret = 0;
        for (int i = offset; i < A.length; i++) {
            if (A[i] <= size) {
                ret = Math.max(ret, A[i] + dfs(A, i + 1, size - A[i]));
            }
        }
        return ret;
    }
}

// Raw DFS - bottom up
public class Solution {
    /**
     * @param m: An integer m denotes the size of a backpack
     * @param A: Given n items with size A[i]
     * @return: The maximum size
     */
    public int backPack(int m, int[] A) {
        int[] ret = {0};
        dfs(A, 0, m, 0, ret);
        return ret[0];
    }
    
    private void dfs(int[] A, int offset, int size, int curSize, int[] ret) {
        ret[0] = Math.max(ret[0], curSize); 
        for (int i = offset; i < A.length; i++) {
            if (A[i] <= size) {
                dfs(A, i + 1, size - A[i], curSize + A[i], ret);
            }
        }
    }
}