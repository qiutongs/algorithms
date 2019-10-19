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