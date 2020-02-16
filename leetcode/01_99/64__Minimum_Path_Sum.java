class Solution {
    public int minPathSum(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        int[][] dp = new int[m][n];
        
        dp[0][0] = grid[0][0];
        for(int i = 1; i < m; i++) {
            dp[i][0] = grid[i][0] + dp[i - 1][0];
        }
        for(int i = 1; i < n; i++) {
            dp[0][i] = grid[0][i] + dp[0][i - 1];
        }
        
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[i][j] = Math.min(dp[i - 1][j], dp[i][j - 1]) + grid[i][j];
            }
        }
        
        return dp[m - 1][n - 1];
    }
}

// DFS + memo
// Note: I think the value can be negative
class Solution {
    public int minPathSum(int[][] grid) {
        Integer[][] memo = new Integer[grid.length][grid[0].length];
        return dfs(grid, 0, 0, memo);
    }
    
    private int dfs(int[][] grid, int x, int y, Integer[][] memo) {
        int m = grid.length, n = grid[0].length;
        if (x == m - 1 && y == n - 1) {
            return grid[x][y];
        }
        if (memo[x][y] != null) {
            return memo[x][y];
        }
        int right = y + 1 < n ? dfs(grid, x, y + 1, memo) : Integer.MAX_VALUE;
        int down = x + 1 < m ? dfs(grid, x + 1, y, memo) : Integer.MAX_VALUE;
        int ret = grid[x][y] + Math.min(right, down);
        memo[x][y] = ret;
        return ret;
    }
}

// Raw DFS
class Solution {
    public int minPathSum(int[][] grid) {
        return dfs(grid, 0, 0);
    }
    
    private int dfs(int[][] grid, int x, int y) {
        int m = grid.length, n = grid[0].length;
        if (x == m - 1 && y == n - 1) {
            return grid[x][y];
        }
        int right = y + 1 < n ? dfs(grid, x, y + 1) : Integer.MAX_VALUE;
        int down = x + 1 < m ? dfs(grid, x + 1, y) : Integer.MAX_VALUE;
        return grid[x][y] + Math.min(right, down);
    }
}