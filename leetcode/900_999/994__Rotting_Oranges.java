// BFS
class Solution {
    private static final int[] deltaX = {1, -1, 0, 0};
    private static final int[] deltaY = {0, 0, 1, -1};
    
    public int orangesRotting(int[][] grid) {
        if (grid.length == 0 || grid[0].length == 0) {
            return 0;
        }
        int m = grid.length, n = grid[0].length;
        Queue<int[]> q = new LinkedList<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 2) {
                    q.offer(new int[]{i, j});
                }
            }
        }
        
        int level = 0;
        while(q.isEmpty() == false) {
            int size = q.size();
            for (int i = 0; i < size; i++) {
                int[] node = q.poll();
                for (int j = 0; j < 4; j++) {
                    int x = node[0] + deltaX[j];
                    int y = node[1] + deltaY[j];
                    if (x >= 0 && x < m && y >= 0 && y < n && grid[x][y] == 1) {
                        grid[x][y] = 2;
                        q.add(new int[]{x, y});
                    }
                }
            }
            if (q.isEmpty() == false) {
                level++;
            }
        }
        
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    return -1;
                }
            }
        }
        return level;
    }
}