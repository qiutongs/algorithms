class Solution {
    public int numIslands(char[][] grid) {
        if (grid == null || grid.length == 0 || grid[0] == null || grid[0].length == 0) {
            return 0;
        }
        int ret = 0;
        int m = grid.length;
        int n = grid[0].length;
        boolean[][] visited = new boolean[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == '1' && visited[i][j] == false) {
                    dfs(grid, i, j, visited);
                    ret++;
                }
            }
        }
        
        return ret;
    }
    
    private void dfs(char[][] grid, int x, int y, boolean[][] visited) {
        visited[x][y] = true;
        
        int[] deltaX = {0, 1, 0, -1};
        int[] deltaY = {1, 0, -1, 0};
        for (int i = 0; i < 4; i++) {
            int x1 = x + deltaX[i];
            int y1 = y + deltaY[i];
            if (inBound(grid, x1, y1) && visited[x1][y1] == false && grid[x1][y1] == '1') {
                dfs(grid, x1, y1, visited);
            }
        }
    }
    
    private boolean inBound(char[][] grid, int x, int y) {
        return x >= 0 && x < grid.length && y >= 0 && y < grid[0].length;
    }
}

class Solution {
    public int numIslands(char[][] grid) {
        if (grid == null || grid.length == 0) {
            return 0;
        }

        int ret = 0;
        int m = grid.length;
        int n = grid[0].length;
        boolean[][] visited = new boolean[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == '1' && visited[i][j] == false) {
                    bfs(grid, new Integer[]{i, j}, visited);
                    ret++;
                }
            }
        }
        
        return ret;
    }
    
    private void bfs(char[][] grid, Integer[] s, boolean[][] visited) {
        Queue<Integer[]> queue = new LinkedList<>();
        queue.add(s);
        visited[s[0]][s[1]] = true;
        
        int[] deltaX = {0, 0, 1, -1};
        int[] deltaY = {1, -1, 0, 0};
        
        while(queue.isEmpty() == false) {
            Integer[] p = queue.remove();
            
            for (int k = 0; k < 4; k++) {
                Integer[] p1 = {p[0] + deltaX[k], p[1] + deltaY[k]};
                if (inBound(grid, p1) && grid[p1[0]][p1[1]] == '1' && visited[p1[0]][p1[1]] == false) {
                    visited[p1[0]][p1[1]] = true;
                    queue.add(p1);
                }
            }
        }
    }
    
    private boolean inBound(char[][] grid, Integer[] point) {
        return point[0] >= 0 && point[0] < grid.length && point[1] >= 0 && point[1]< grid[0].length;
    }
}

class Solution {
    private int[] parent;
    private int count = 0;
    
    public int numIslands(char[][] grid) {
        if (grid == null || grid.length == 0 || grid[0] == null || grid[0].length == 0) {
            return 0;
        }
        int m = grid.length, n = grid[0].length;
        int[] deltaX = {0, 0, 1, -1};
        int[] deltaY = {1, -1, 0, 0};
        parent = new int[m * n];
        for (int i = 0; i < parent.length; i++) {
            parent[i] = i;
        }
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == '1') {
                    count++;
                }
            }
        }
        
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == '1') {
                    for (int k = 0; k < 4; k++) {
                        int x = i + deltaX[k];
                        int y = j + deltaY[k];
                        if (inbound(grid, x, y) && grid[x][y] == '1') {
                            union(i * n + j, x * n + y);
                        }
                    }
                }
            }
        }
        return count;
    }
    
    private void union(int p, int q) {
        int rootP = find(p);
        int rootQ = find(q);
        if (rootP != rootQ) {
            parent[rootP] = rootQ;
            count--;
        }
    }

    private int find(int x) {
        while(x != parent[x]) {
            parent[x] = parent[parent[x]];
            x = parent[x];
        }
        return x;
    }
    
    private boolean inbound(char[][] grid, int x, int y) {
        return x >= 0 && x < grid.length && y >= 0 && y < grid[0].length;
    }
}