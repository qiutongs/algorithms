class Solution {
    public int numIslands(char[][] grid) {
        if (grid == null || grid.length == 0) {
            return 0;
        }
        
        boolean[][] visited = new boolean[grid.length][grid[0].length];
        
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                visited[i][j] = false;
            }
        }
        
        int ret = 0;
        
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] == '1' && visited[i][j] == false) {
                    Integer[] point = {i, j};
                    bfs(grid, visited, point);
                    ret++;
                }
            }
        }
        
        return ret;
    }
    
    private void bfs(char[][] grid, boolean[][] visited, Integer[] point) {
        Queue<Integer[]> queue = new LinkedList<>();
        queue.add(point);
        visited[point[0]][point[1]] = true;
        
        int[] deltaI = {0, 0, 1, -1};
        int[] deltaJ = {1, -1, 0, 0};
        
        while(queue.isEmpty() == false) {
            Integer[] p = queue.remove();
            
            for (int k = 0; k < 4; k++) {
                Integer[] neighbor = {p[0] + deltaI[k], p[1] + deltaJ[k]};
                
                if (inBound(grid, neighbor) && grid[neighbor[0]][neighbor[1]] == '1' && visited[neighbor[0]][neighbor[1]] == false) {
                    queue.add(neighbor);
                    visited[neighbor[0]][neighbor[1]] = true;
                }
            }
        }
    }
    
    private boolean inBound(char[][] grid, Integer[] point) {
        return point[0] >= 0 && point[0] < grid.length && point[1] >= 0 && point[1]< grid[0].length;
    }
}