// DFS + memo
class Solution {
    public int minimumTotal(List<List<Integer>> triangle) {
        Integer[][] memo = new Integer[triangle.size()][];
        for (int i = 0; i < memo.length; i++) {
            memo[i] = new Integer[i + 1];
        }
        return dfs(triangle, 0, 0, memo);
    }
    
    private int dfs(List<List<Integer>> triangle, int x, int y, Integer[][] memo) {
        if (memo[x][y] != null) {
            return memo[x][y];
        }
        if (x == triangle.size() - 1) {
            return triangle.get(x).get(y);
        }
        int ret = triangle.get(x).get(y) + Math.min(dfs(triangle, x + 1, y, memo), dfs(triangle, x + 1, y + 1, memo));
        memo[x][y] = ret;
        return ret;
    }
}

// raw top-down DFS
class Solution {
    public int minimumTotal(List<List<Integer>> triangle) {
        int[] ret = { Integer.MAX_VALUE };
        dfs(triangle, 0, 0, 0, ret);
        return ret[0];
    }
    
    private void dfs(List<List<Integer>> triangle, int row, int col, int sum, int[] ret) {
        if (row == triangle.size()) {
            ret[0] = Math.min(ret[0], sum);
            return;
        }
        
        int val = triangle.get(row).get(col);
        dfs(triangle, row + 1, col, sum + val, ret);
        dfs(triangle, row + 1, col + 1, sum + val, ret);
    }
}
// raw bottom-up DFS
class Solution {
    public int minimumTotal(List<List<Integer>> triangle) {
        return dfs(triangle, 0, 0);
    }
    
    private int dfs(List<List<Integer>> triangle, int row, int col) {
        if (row == triangle.size()) {
            return 0;
        }
        
        return triangle.get(row).get(col) + Math.min(dfs(triangle, row + 1, col),
                                                     dfs(triangle, row + 1, col + 1));
    }
}

// bottom-up DFS + memorization
class Solution {
    public int minimumTotal(List<List<Integer>> triangle) {
        Integer[][] dp = new Integer[triangle.size()][];
        for (int i = 0; i < dp.length; i++) {
            dp[i] = new Integer[i + 1];
        }
        
        return dfs(triangle, 0, 0, dp);
    }
    
    private int dfs(List<List<Integer>> triangle, int row, int col, Integer[][] dp) {
        if (row == triangle.size()) {
            return 0;
        }
        
        if (dp[row][col] != null) {
            return dp[row][col];
        }
        
        int ret = triangle.get(row).get(col) + Math.min(dfs(triangle, row + 1, col, dp),
                                                     dfs(triangle, row + 1, col + 1, dp));
        
        dp[row][col] = ret;
        return ret;
    }
}

// top-down DP
class Solution {
    public int minimumTotal(List<List<Integer>> triangle) {
        int[][] dp = new int[triangle.size()][];
        for (int i = 0; i < dp.length; i++) {
            dp[i] = new int[i + 1];
        }
        
        dp[0][0] = triangle.get(0).get(0);
        for (int i = 1; i < triangle.size(); i++) {
            dp[i][0] = triangle.get(i).get(0) + dp[i - 1][0];
            dp[i][i] = triangle.get(i).get(i) + dp[i - 1][i - 1];
        }
        
        for (int i = 1; i < dp.length; i++) {
            for (int j = 1; j < dp[i].length - 1; j++) {
                dp[i][j] = Math.min(dp[i - 1][j], dp[i - 1][j - 1]) + triangle.get(i).get(j);
            }
        }
        
        int ret = Integer.MAX_VALUE;
        for (int val : dp[dp.length - 1]) {
            ret = Math.min(ret, val);
        }
        return ret;
    }
}

class Solution {
    public int minimumTotal(List<List<Integer>> triangle) {
        int n = triangle.size();
        int[][] dp = new int[2][n];
        
        dp[0][0] = triangle.get(0).get(0);
        
        for (int i = 1; i < n; i++) {
            dp[i % 2][0] = triangle.get(i).get(0) + dp[(i - 1) % 2][0];
            dp[i % 2][i] = triangle.get(i).get(i) + dp[(i - 1) % 2][i - 1];
            for (int j = 1; j < i; j++) {
                dp[i % 2][j] = Math.min(dp[(i - 1) % 2][j], dp[(i - 1) % 2][j - 1]) + triangle.get(i).get(j);
            }
        }
        
        int ret = Integer.MAX_VALUE;
        for (int val : dp[(n - 1) % 2]) {
            ret = Math.min(ret, val);
        }
        return ret;
    }
}