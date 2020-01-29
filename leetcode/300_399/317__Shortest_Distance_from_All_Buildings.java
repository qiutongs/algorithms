class Solution {
    public int shortestDistance(int[][] grid) {
        if (grid == null || grid.length == 0 || grid[0] == null || grid[0].length == 0) {
            return -1;
        }
        int m = grid.length, n = grid[0].length;
        int[][] counts = new int[m][n];
        int[][] distances = new int[m][n];
        int buildingCount = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    buildingCount++;
                    bfs(grid, i, j, counts, distances);
                }
            }
        }
        int ret = Integer.MAX_VALUE;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 0 && counts[i][j] == buildingCount) {
                    ret = Math.min(ret, distances[i][j]);
                }
            }
        }
        return ret == Integer.MAX_VALUE ? -1 : ret;
    }
    
    private void bfs(int[][] grid, int x, int y, int[][] counts, int[][] distances) {
        int m = grid.length, n = grid[0].length;
        int[] deltaX = {1, -1, 0, 0};
        int[] deltaY = {0, 0, 1, -1};
        
        Queue<int[]> q = new LinkedList<>();
        Set<Integer> visited = new HashSet<>();
        q.add(new int[]{x, y});
        visited.add(x * n + y);
        
        int dis = 0;
        while(q.isEmpty() == false) {
            int size = q.size();
            for (int i = 0; i < size; i++) {
                int[] node = q.poll();
                counts[node[0]][node[1]]++;
                distances[node[0]][node[1]] += dis;
                
                for (int j = 0; j < 4; j++) {
                    int x1 = node[0] + deltaX[j];
                    int y1 = node[1] + deltaY[j];
                    if (inbound(grid, x1, y1) && grid[x1][y1] == 0 && visited.contains(x1 * n + y1) == false) {
                        visited.add(x1 * n + y1);
                        q.offer(new int[]{x1, y1});
                    }
                }
            }
            dis++;
        }
    }
    
    private boolean inbound(int[][] grid, int x, int y) {
        return x >= 0 && x < grid.length && y >= 0 && y < grid[0].length;
    }
}