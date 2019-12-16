class Solution {
    private static final int[] DELTA_X = {1, -1, 0, 0};
    private static final int[] DELTA_Y = {0, 0, 1, -1};
    
    public void solve(char[][] board) {
        if (board == null || board.length == 0 || board[0] == null || board[0].length == 0) {
            return;
        }
        int m = board.length, n = board[0].length;
        boolean[][] visited = new boolean[m][n];
        boolean[][] flipVisited = new boolean[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] == 'O' && visited[i][j] == false) {
                    if (isSurrounded(board, i, j, visited)) {
                        flip(board, i, j, flipVisited);
                    }
                }
            }
        }
    }
    
    private boolean isSurrounded(char[][] board, int x, int y, boolean[][] visited) {
        return dfs(board, x, y, visited, false);
    }
    
    private void flip(char[][] board, int x, int y, boolean[][] visited) {
        dfs(board, x, y, visited, true);
    }
    
    private boolean dfs(char[][] board, int x, int y, boolean[][] visited, boolean flip) {
        boolean ret = true;
        
        visited[x][y] = true;
        if (flip) {
            board[x][y] = 'X';
        }
        
        for (int i = 0; i < 4; i++) {
            int x1 = x + DELTA_X[i];
            int y1 = y + DELTA_Y[i];
            if (inbound(board, x1, y1)) {
                if (visited[x1][y1] == false && board[x1][y1] == 'O') {
                    if (dfs(board, x1, y1, visited, flip) == false) {
                        ret = false;
                    }
                }
            } else {
                ret = false;
            }
        }
        return ret;
    }
    
    private boolean inbound(char[][] board, int x, int y) {
        return x >= 0 && x < board.length && y >= 0 && y < board[0].length;
    }
}

class Solution {
    public void solve(char[][] board) {
        if (board == null || board.length == 0 || board[0].length == 0) {
            return;
        }
        
        boolean[][] visited = new boolean[board.length][board[0].length];
        
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                visited[i][j] = false;
            }
        }
        
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] == 'O' && visited[i][j] == false) {
                    bfs(board, i, j, visited);
                }
            }
        }
    }
    
    private void bfs(char[][] board, int x, int y, boolean[][] visited) {
        Integer[] startPoint = {x, y};
        int[] deltaX = {0, 0, 1, -1};
        int[] deltaY = {1, -1, 0, 0};
        
        boolean surrounded = true;
        Queue<Integer[]> queue = new LinkedList<>();
        List<Integer[]> component = new LinkedList<>();
        queue.add(startPoint);
        
        while(queue.isEmpty() == false) {
            Integer[] curP = queue.remove();
            component.add(curP);
            
            for (int i = 0; i < 4; i++) {
                int nextX = curP[0] + deltaX[i];
                int nextY = curP[1] + deltaY[i];
                Integer[] nextP = {nextX, nextY};
                
                if (inBound(board, nextP)) {
                    if (board[nextX][nextY] == 'O' && visited[nextX][nextY] == false) {
                        visited[nextX][nextY] = true;
                        queue.add(nextP);
                    }
                } else {
                    surrounded = false;
                }
            }
        }
        
        // Flip
        if (surrounded) {
            for (Integer[] p : component) {
                board[p[0]][p[1]] = 'X';
            }
        }
    }
    
    private boolean inBound(char[][] board, Integer[] point) {
        return point[0] >= 0 && point[0] < board.length && point[1] >= 0 && point[1] < board[0].length;
    }
}