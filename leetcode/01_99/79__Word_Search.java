class Solution {
    public boolean exist(char[][] board, String word) {
        boolean[][] visited = new boolean[board.length][board[0].length];
        
        for (int i = 0; i < board.length; i++) {
            Arrays.fill(visited[i], false);
        }
        
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (dfs(board, i, j, word, 0, visited)) {
                    return true;
                }
            }
        }
        
        return false;
    }
    
    private boolean dfs(char[][] board, int x, int y, String word, int wordIndex, boolean[][] visited) {
        if (word.charAt(wordIndex) != board[x][y]) {
            return false;
        }
        
        if (wordIndex == word.length() - 1) {
            return true;
        }

        visited[x][y] = true;
        
        int[] deltaX = {0, 0, 1, -1};
        int[] deltaY = {1, -1, 0, 0};
        
        for (int i = 0; i < 4; i++) {
            int nextX = x + deltaX[i];
            int nextY = y + deltaY[i];
            
            if (inBound(board, nextX, nextY) && visited[nextX][nextY] == false) {
                if (dfs(board, nextX, nextY, word, wordIndex + 1, visited)) {
                    return true;
                }
            }
        }
        
        visited[x][y] = false;
        
        return false;
    }
    
    private boolean inBound(char[][] board, int x, int y) {
        return x >= 0 && x < board.length && y >= 0 && y < board[0].length;
    }
}