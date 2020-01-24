class Solution {
    public void solveSudoku(char[][] board) {
        dfs(board, 0);
    }
    
    private boolean dfs(char[][] board, int pos) {
        int n = board.length;
        if (pos > (n - 1) * n + (n - 1)) {
            return true;
        }
        
        int x = pos / n, y = pos % n;
        
        if (board[x][y] != '.') {
            return dfs(board, pos + 1);
        }
        
        for (int i = 1; i <= 9; i++) {
            board[x][y] = (char)(i + '0');
            if (isValid(board, x, y)) {
                if (dfs(board, pos + 1)) {
                    return true;
                }
            }
        }
        board[x][y] = '.';
        return false;
    }
    
    private boolean isValid(char[][] board, int x, int y) {
        for (int i = 0; i < board.length; i++) {
            if (i != x && board[i][y] == board[x][y]) {
                return false;
            }
        }
        for (int i = 0; i < board[0].length; i++) {
            if (i != y && board[x][i] == board[x][y]) {
                return false;
            }
        }
        for (int i = x - x % 3; i < x - x % 3 + 3; i++) {
            for (int j = y - y % 3; j < y - y % 3 + 3; j++) {
                if (i != x && j != y && board[i][j] == board[x][y]) {
                    return false;
                }
            }
        }
        return true;
    }
}