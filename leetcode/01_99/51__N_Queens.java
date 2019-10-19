class Solution {
    public List<List<String>> solveNQueens(int n) {
        if (n <= 0) {
            return Collections.emptyList();
        }
        
        List<List<String>> ret = new LinkedList<>();
        
        int[] board = new int[n];
        Arrays.fill(board, -1);
        
        dfs(ret, board, 0);
        
        return ret;   
    }
    
    private void dfs(List<List<String>> ret, int[] board, int index) {
        if (index == board.length) {
            ret.add(serialize(board));
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
    
    private List<String> serialize(int[] board) {
        List<String> ret = new ArrayList<>(board.length);
        char[] row = new char[board.length];
        Arrays.fill(row, '.');
        
        for (int pos : board) {
            row[pos] = 'Q';
            ret.add(new String(row));
            row[pos] = '.';
        }
        
        return ret;
    }
}