class Solution {
    public int closedIsland(int[][] grid) {
        if (grid == null || grid.length == 0 || grid[0] == null || grid[0].length == 0) {
            return 0;
        }
        int ret = 0;
        int m = grid.length, n = grid[0].length;
        boolean[][] visited = new boolean[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 0 && visited[i][j] == false) {
                    if (dfs(grid, i, j, visited)) {
                        ret++;
                    }
                }
            }
        }
        return ret;
    }
    
    private boolean dfs(int[][] grid, int x, int y, boolean[][] visited) {
        visited[x][y] = true;
        int[] deltaX = {0, 0, 1, -1};
        int[] deltaY = {1, -1, 0, 0};
        boolean ret = true;
        for (int i = 0; i < 4; i++) {
            int x1 = x + deltaX[i];
            int y1 = y + deltaY[i];
            if (inbound(grid, x1, y1)) {
                if (grid[x1][y1] == 0 && visited[x1][y1] == false) {
                    if (dfs(grid, x1, y1, visited) == false) {
                        ret = false;
                    }
                }
            } else {
                ret = false;
            }
        }
        return ret;
    }
    
    private boolean inbound(int[][] grid, int x, int y) {
        return x >=0 && x < grid.length && y >= 0 && y < grid[0].length;
    }
}