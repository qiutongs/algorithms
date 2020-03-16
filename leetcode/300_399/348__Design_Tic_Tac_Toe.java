class TicTacToe {
    private static final int X = 1, O = 2;
    private final int[][] board;
    
    /** Initialize your data structure here. */
    public TicTacToe(int n) {
        this.board = new int[n][n];
    }
    
    /** Player {player} makes a move at ({row}, {col}).
        @param row The row of the board.
        @param col The column of the board.
        @param player The player, can be either 1 or 2.
        @return The current winning condition, can be either:
                0: No one wins.
                1: Player 1 wins.
                2: Player 2 wins. */
    public int move(int row, int col, int player) {
        board[row][col] = player;
        
        int ret = rowWin();
        if (ret != 0) {
            return ret;
        }
        ret = colWin();
        if (ret != 0) {
            return ret;
        }
        return diagWin();
    }
    
    private int rowWin() {
        int n = board.length;
        for (int i = 0; i < n; i++) {
            int t = board[i][0];
            if (t != 0) {
                int j = 1;
                for (; j < n; j++) {
                    if (board[i][j] != board[i][j - 1]) {
                        break;
                    }
                }
                if (j == n) {
                    return t;
                }
            }
        }
        return 0;
    }
    
    private int colWin() {
        int n = board.length;
        for (int i = 0; i < n; i++) {
            int t = board[0][i];
            if (t != 0) {
                int j = 1;
                for (; j < n; j++) {
                    if (board[j][i] != board[j - 1][i]) {
                        break;
                    }
                }
                if (j == n) {
                    return t;
                }
            }
        }
        return 0;
    }
    
    private int diagWin() {
        int n = board.length;
        int t = board[0][0];
        if (t != 0) {
            int i = 1;
            for (; i < n; i++) {
                if (board[i][i] != board[i - 1][i - 1]) {
                    break;
                }
            }
            if (i == n) {
                return t;
            }
        }
        
        t = board[0][n - 1];
        if (t != 0) {
            int i = 1;
            for (; i < n; i++) {
                if (board[i][n - 1 - i] != board[i - 1][n - i]) {
                    break;
                }
            }
            if (i == n) {
                return t;
            }
        }
        return 0;
    }
}

/**
 * Your TicTacToe object will be instantiated and called as such:
 * TicTacToe obj = new TicTacToe(n);
 * int param_1 = obj.move(row,col,player);
 */