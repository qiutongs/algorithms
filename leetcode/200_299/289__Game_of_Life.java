class Solution {
    public void gameOfLife(int[][] board) {
        if (board.length == 0 || board[0].length == 0) {
            return;
        }
        int m = board.length, n = board[0].length;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                board[i][j] = next(board, i, j) * 10 + board[i][j];
            }
        }
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                board[i][j] /= 10;
            }
        }
    }
    
    private int next(int[][] board, int x, int y) {
        int[] deltaX = {1, 1, 1, 0, 0, -1, -1, -1};
        int[] deltaY = {1, 0, -1, 1, -1, 1, 0, -1};
        int live = 0;
        for (int i = 0; i < 8; i++) {
            int nextX = deltaX[i] + x;
            int nextY = deltaY[i] + y;
            if (nextX >= 0 && nextX < board.length && nextY >= 0 && nextY < board[0].length) {
                live = (board[nextX][nextY] % 10) == 1 ? live + 1 : live;
            }
        }
        
        if ((board[x][y] % 10) == 1) {
            if (live < 2) {
                return 0;
            } else if (live == 2 || live == 3) {
                return 1;
            } else {
                return 0;
            }
        } else {
            if (live == 3) {
                return 1;
            } else {
                return 0;
            }
        }
    }
}