// Graph DAG: DFS + memo
class Solution {
    private static int[] deltaX = {0, 0, 1, -1};
    private static int[] deltaY = {1, -1, 0, 0};;
    
    public int longestIncreasingPath(int[][] matrix) {
        if (matrix == null || matrix.length == 0) {
            return 0;
        }
        int ret = 0;
        int m = matrix.length, n = matrix[0].length;
        int[][] memo = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                ret = Math.max(ret, dfs(matrix, i, j, memo));
            }
        }
        return ret;
    }
    
    private int dfs(int[][] matrix, int x, int y, int[][] memo) {
        if (memo[x][y] > 0) {
            return memo[x][y];
        }
        int ret = 0;
        for (int i = 0; i < 4; i++) {
            int x1 = x + deltaX[i];
            int y1 = y + deltaY[i];
            if (inbound(matrix, x1, y1) && matrix[x1][y1] > matrix[x][y]) {
                ret = Math.max(ret, dfs(matrix, x1, y1, memo));
            }
        }
        memo[x][y] = ret + 1;
        return ret + 1;
    }
    
    private boolean inbound(int[][] matrix, int x, int y) {
        return x >=0 && x < matrix.length && y >= 0 && y < matrix[0].length;
    }
}

// Raw DFS, Time Limit Exceeded
class Solution {
    public int longestIncreasingPath(int[][] matrix) {
        if (matrix == null || matrix.length == 0) {
            return 0;
        }
        int ret = 0;
        int m = matrix.length, n = matrix[0].length;
        boolean[][] visited = new boolean[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int[] len = { 0 };
                dfs(matrix, i, j, visited, 0, len);
                ret = Math.max(ret, len[0]);
            }
        }
        return ret;
    }
    
    private void dfs(int[][] matrix, int x, int y, boolean[][] visited, int curLen, int[] ret) {
        int[] deltaX = {0, 0, 1, -1};
        int[] deltaY = {1, -1, 0, 0};
        
        visited[x][y] = true;
        
        try {
            ret[0] = Math.max(ret[0], curLen + 1);
            for (int i = 0; i < 4; i++) {
                int x1 = x + deltaX[i];
                int y1 = y + deltaY[i];
                if (inbound(matrix, x1, y1) && matrix[x1][y1] > matrix[x][y] && visited[x1][y1] == false) {
                    dfs(matrix, x1, y1, visited, curLen + 1, ret);
                }
            }
        } finally {
            visited[x][y] = false;
        }
    }
    
    private boolean inbound(int[][] matrix, int x, int y) {
        return x >=0 && x < matrix.length && y >= 0 && y < matrix[0].length;
    }
}