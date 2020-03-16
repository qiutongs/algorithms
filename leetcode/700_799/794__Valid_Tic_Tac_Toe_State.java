class Solution {
    public boolean validTicTacToe(String[] board) {
        int xCount = 0, oCount = 0;
        for (int i = 0; i < 9; i++) {
            char c = board[i / 3].charAt(i % 3);
            if (c == 'X') {
                xCount++;
            } else if (c == 'O') {
                oCount++;
            }
        }
        if (oCount - xCount > 0 || xCount - oCount > 1) {
            return false;
        }
        
        int xWinCount = 0, oWinCount = 0;
        for (int i = 0; i < 3; i++) {
            if (board[i].equals("XXX")) {
                xWinCount++;
            } else if (board[i].equals("OOO")) {
                oWinCount++;
            }
        }
        for (int i = 0; i < 3; i++) {
            String col = "" + board[0].charAt(i) + board[1].charAt(i) + board[2].charAt(i);
            if (col.equals("XXX")) {
                xWinCount++;
            } else if (col.equals("OOO")) {
                oWinCount++;
            }
        }
        for (char c : new char[]{'X', 'O'}) {
            if (board[1].charAt(1) == c) {
                if (board[0].charAt(0) == board[1].charAt(1) && board[1].charAt(1) == board[2].charAt(2)) {
                    if (c == 'X') {
                        xWinCount++;
                    } else {
                        oWinCount++;
                    }
                }
                if (board[0].charAt(2) == board[1].charAt(1) && board[1].charAt(1) == board[2].charAt(0)) {
                    if (c == 'X') {
                        xWinCount++;
                    } else {
                        oWinCount++;
                    }
                }
            }
        }
        return (xWinCount == 0 && oWinCount == 0) 
            || (xWinCount > 0 && oWinCount == 0 && xCount == oCount + 1) 
            || (oWinCount > 0 && xWinCount == 0 && xCount == oCount);
    }
}

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
        
        int ret = rowWin(row);
        if (ret != 0) {
            return ret;
        }
        ret = colWin(col);
        if (ret != 0) {
            return ret;
        }
        return diagWin(row, col);
    }
    
    private int rowWin(int row) {
        int n = board.length;
        int t = board[row][0];
        if (t != 0) {
            int j = 1;
            for (; j < n; j++) {
                if (board[row][j] != board[row][j - 1]) {
                    break;
                }
            }
            if (j == n) {
                return t;
            }
        }
        return 0;
    }
    
    private int colWin(int col) {
        int n = board.length;
        int t = board[0][col];
        if (t != 0) {
            int j = 1;
            for (; j < n; j++) {
                if (board[j][col] != board[j - 1][col]) {
                       break;
                }
            }
            if (j == n) {
                return t;
            }
        }
        return 0;
    }
    
    private int diagWin(int row, int col) {
        int n = board.length;
        if (row == col) {
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
        }
        if (row + col == n - 1) {
            int t = board[0][n - 1];
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
        }
        return 0;
    }
}

/**
 * Your TicTacToe object will be instantiated and called as such:
 * TicTacToe obj = new TicTacToe(n);
 * int param_1 = obj.move(row,col,player);
 */