class Solution1 {
    public int uniquePaths(int m, int n) {
        int[][] dp = new int[m][n];
        for (int i = 0; i < m; i++) {
            dp[i][0] = 1;
        }
        for (int i = 0; i < n; i++) {
            dp[0][i] = 1;
        }
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
            }
        }
        return dp[m - 1][n - 1];
    }
}

class Solution2 {
    public int uniquePaths(int m, int n) {
        m--;
        n--;
        return (int)combination(m + n, m);
    }
    
    private long combination(int n, int k) {
        if (k == 0 || k == n) {
            return 1;
        }
        if (k == 1 || k == n - 1) {
            return n;
        }
        
        if (k > n / 2) {
            k = n - k;
        }
        
        long ret = 1;
        for (int i = 1; i <= k; i++) {
            ret = ret * (n - k + i) / i;
        }
        return ret;
    }
}

class Solution3 {
    public int uniquePaths(int m, int n) {
        m--;
        n--;
        return (int)combination(m + n, m);
    }
    
    private long combination(int n, int k) {
        if (k == 0 || k == n) {
            return 1;
        }
        if (k == 1 || k == n - 1) {
            return n;
        }
        
        if (k > n / 2) {
            k = n - k;
        }
        return combination(n - 1, k - 1) * n / k;
    }
}