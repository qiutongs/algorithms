public class Solution {
    private static final int[] deltaX = {1, -1, 0, 0};
    private static final int[] deltaY = {0, 0, 1, -1};
    /**
     * @param grid: a 2D integer grid
     * @return: an integer
     */
    public int zombie(int[][] grid) {
        if (grid.length == 0 || grid[0].length == 0) {
            return 0;
        }
        int m = grid.length, n = grid[0].length;
        Queue<int[]> q = new LinkedList<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    q.offer(new int[]{i, j});
                }
            }
        }
        
        int level = -1;
        while(q.isEmpty() == false) {
            level++;
            int size = q.size();
            for (int i = 0; i < size; i++) {
                int[] node = q.poll();
                for (int j = 0; j < 4; j++) {
                    int x = node[0] + deltaX[j];
                    int y = node[1] + deltaY[j];
                    if (x >= 0 && x < m && y >= 0 && y < n && grid[x][y] == 0) {
                        grid[x][y] = 1;
                        q.add(new int[]{x, y});
                    }
                }
            }
        }
        
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 0) {
                    return -1;
                }
            }
        }
        return level == -1 ? 0 : level;
    }
}