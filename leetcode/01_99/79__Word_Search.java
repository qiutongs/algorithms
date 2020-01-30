class Solution {
    public boolean exist(char[][] board, String word) {
        int m = board.length, n = board[0].length;
        boolean[][] visited = new boolean[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (dfs(board, i, j, word, 0, visited)) {
                    return true;
                }
            }
        }
        return false;
    }
    
    private boolean dfs(char[][] board, int x, int y, String word, int offset, boolean[][] visited) {
        if (inbound(board, x, y) == false || word.charAt(offset) != board[x][y] || visited[x][y]) {
            return false;
        }
        
        visited[x][y] = true;

        try {
            if (offset == word.length() - 1) {
                return true;
            }
            int[] deltaX = {0, 0, 1, -1};
            int[] deltaY = {1, -1, 0, 0};
            for (int i = 0; i < 4; i++) {
                int x1 = x + deltaX[i];
                int y1 = y + deltaY[i];
                if (dfs(board, x1, y1, word, offset + 1, visited)) {
                    return true;
                }
            }
            return false;
        } finally {
            visited[x][y] = false;
        }
    }
    
    private boolean inbound(char[][] board, int x, int y) {
        return x >= 0 && x < board.length && y >= 0 && y < board[0].length;
    }
}