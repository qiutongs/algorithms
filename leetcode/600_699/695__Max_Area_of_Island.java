class Solution {
    public int maxAreaOfIsland(int[][] grid) {
        if (grid == null || grid.length == 0 || grid[0] == null || grid[0].length == 0) {
            return 0;
        }
        int ret = 0;
        int m = grid.length, n = grid[0].length;
        boolean[][] visited = new boolean[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1 && visited[i][j] == false) {
                    int[] area = { 0 };
                    dfs(grid, i, j, visited, area);
                    ret = Math.max(ret, area[0]);
                }
            }
        }
        return ret;
    }
    
    private void dfs(int[][] grid, int x, int y, boolean[][] visited, int[] ret) {
        ret[0]++;
        
        visited[x][y] = true;
        int[] deltaX = {0, 0, 1, -1};
        int[] deltaY = {1, -1, 0, 0};
        for (int i = 0; i < 4; i++) {
            int x1 = x + deltaX[i];
            int y1 = y + deltaY[i];
            if (inbound(grid, x1, y1) && grid[x1][y1] == 1 && visited[x1][y1] == false) {
                dfs(grid, x1, y1, visited, ret);
            }
        }
    }
    
    private boolean inbound(int[][] grid, int x, int y) {
        return x >= 0 && x < grid.length && y >= 0 && y < grid[0].length;
    }
}