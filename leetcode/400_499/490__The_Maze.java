// DFS
// 
class Solution {
    private static final int[] DELTAX = {0, 0, 1, -1};
    private static final int[] DELTAY = {1, -1, 0, 0};
    
    public boolean hasPath(int[][] maze, int[] start, int[] destination) {
        return dfs(maze, new boolean[maze.length][maze[0].length], start[0], start[1], destination);
    }
    
    private boolean dfs(int[][] maze, boolean[][] visited, int x, int y, int[] des) {
        if (visited[x][y]) {
            return false;
        }
        visited[x][y] = true;
        
        if (des[0] == x && des[1] == y) {
            return true;
        }
        
        for (int i = 0; i < 4; i++) {
            int x1 = x, y1 = y;
            while(inbound(maze, x1 + DELTAX[i], y1 + DELTAY[i]) && maze[x1 + DELTAX[i]][y1 + DELTAY[i]] == 0) {
                x1 += DELTAX[i];
                y1 += DELTAY[i];
            }
            if (dfs(maze, visited, x1, y1, des)) {
                return true;
            }
        }
        return false;
    }
    
    private boolean inbound(int[][] maze, int x, int y) {
        return x >= 0 && x < maze.length && y >= 0 && y < maze[0].length;
    }
}