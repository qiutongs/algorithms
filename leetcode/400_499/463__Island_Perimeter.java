// Traverse
// Time: O(MN)
// Space: O(1)
class Solution {
    public int islandPerimeter(int[][] grid) {
        int ret = 0;
        int m = grid.length, n = grid[0].length;
        int[] deltaX = {1, -1, 0, 0};
        int[] deltaY = {0, 0, 1, -1};
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    for (int k = 0; k < 4; k++) {
                        int x = i + deltaX[k];
                        int y = j + deltaY[k];
                        ret += inbound(grid, x, y) == false || grid[x][y] == 0 ? 1 : 0;
                    }
                }
            }
        }
        return ret;
    }
    
    private boolean inbound(int[][] grid, int x, int y) {
        return x >= 0 && x < grid.length && y >= 0 && y < grid[0].length;
    }
}