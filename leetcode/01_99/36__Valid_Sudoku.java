class Solution {
    public boolean isValidSudoku(char[][] board) {
        int[] counts = new int[10];
        
        // Validate each row
        for (int i = 0; i < board.length; i++) {
            Arrays.fill(counts, 0);
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] != '.') {
                    if (counts[toIndex(board[i][j])] > 0) {
                        return false;
                    }
                    counts[toIndex(board[i][j])]++;
                }
            }
        }
        
        // Validate each column
        for (int j = 0; j < board[0].length; j++) {
            Arrays.fill(counts, 0);
            for (int i = 0; i < board.length; i++) {
                if (board[i][j] != '.') {
                    if (counts[toIndex(board[i][j])] > 0) {
                        return false;
                    }
                    counts[toIndex(board[i][j])]++;
                }
            }
        }
        
        // Validate each sub-box
        for (int x = 0; x < 3; x++) {
            for (int y = 0; y < 3; y++) {
                Arrays.fill(counts, 0);
                for (int i = x * 3; i < x * 3 + 3; i++) {
                    for (int j = y * 3; j < y * 3 + 3; j++) {
                        if (board[i][j] != '.') {
                            if (counts[toIndex(board[i][j])] > 0) {
                                return false;
                            }
                            counts[toIndex(board[i][j])]++;
                        }
                    }
                }
            }
        }
        return true;
    }
    
    private int toIndex(char c) {
        return (int)(c - '0');
    }
}