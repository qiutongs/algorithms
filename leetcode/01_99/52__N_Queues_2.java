class Solution {
    public int totalNQueens(int n) {
        if (n <= 0) {
            return 0;
        }
        
        int[] ret = { 0 };
        
        int[] board = new int[n];
        Arrays.fill(board, -1);
        
        dfs(ret, board, 0);
        
        return ret[0]; 
    }
    
    private void dfs(int[] ret, int[] board, int index) {
        if (index == board.length) {
            ret[0]++;
            return;
        }
        
        for (int i = 0; i < board.length; i++) {
            board[index] = i;
            
            if (isValid(board, index)) {
                dfs(ret, board, index + 1);
            }
        }
    }
    
    private boolean isValid(int[] board, int index) {
        for (int j = 0; j < index; j++) {
            if (board[j] == board[index]) {
               return false;
            } 

            if (board[j] - board[index] == j - index || board[j] - board[index] == index - j) {
                return false;
            }
        }
        
        return true;
    }
}