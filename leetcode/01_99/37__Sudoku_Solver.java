class Solution {
    public void solveSudoku(char[][] board) {
        dfs(board, 0, 0);
    }
    
    private boolean dfs(char[][] board, int x, int y) {
        if (x == 9) {
            return true;
        }
        
        int nextX = y + 1 < 9 ? x : x + 1;
        int nextY = y + 1 < 9 ? y + 1 : 0;

        if (board[x][y] == '.') {
            for (int i = 1; i <= 9; i++) {
                board[x][y] = (char)('0' + i);
                
                if (isValid(board, x, y)) {
                    if (dfs(board, nextX, nextY)) {
                        return true;
                    }
                }
                
                board[x][y] = '.';
            }
            
            return false;
        } else {
            return dfs(board, nextX, nextY);
        }
    }
    
    private boolean isValid(char[][] board, int x, int y) {
        int[] count = new int[9];

        // row check
        Arrays.fill(count, 0);
        for (int i = 0; i < 9; i++) {
            if (board[x][i] != '.') {
                count[board[x][i] - '1']++;
            }
        }
        
        if (isValidCount(count) == false) {
            return false;
        }
        
        // column check
        Arrays.fill(count, 0);
        for (int i = 0; i < 9; i++) {
            if (board[i][y] != '.') {
                count[board[i][y] - '1']++;
            }
        }
        
        if (isValidCount(count) == false) {
            return false;
        }
        
        // sub-box check
        Arrays.fill(count, 0);
        int groupX = x / 3;
        int groupY = y / 3;
        
        for (int i = groupX * 3; i < groupX * 3 + 3; i++) {
            for (int j = groupY * 3; j < groupY * 3 + 3; j++) {
                if (board[i][j] != '.') {
                    count[board[i][j] - '1']++;
                }
            }
        }
        
        if (isValidCount(count) == false) {
            return false;
        }
        
        return true;
    }
    
    private boolean isValidCount(int[] count) {
        for (int i = 0; i < 9; i++) {
            if (count[i] > 1) {
                return false;
            }
        }
        
        return true;
    }
}