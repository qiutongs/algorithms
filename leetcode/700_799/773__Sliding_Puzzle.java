class Solution {
    private static final int[] deltaX = {1, -1, 0, 0};
    private static final int[] deltaY = {0, 0, 1, -1};
    
    public int slidingPuzzle(int[][] board) {
        Board target = new Board(new int[][]{{1,2,3}, {4,5,0}});
        
        Queue<Board> q = new LinkedList<>();
        HashSet<Board> visited = new HashSet<>();
        
        Board start = new Board(board);
        q.offer(start);
        visited.add(start);
        
        int level = -1;
        while(q.isEmpty() == false) {
            level++;
            int size = q.size();
            for (int i = 0; i < size; i++) {
                Board cur = q.poll();
                if (cur.equals(target)) {
                    return level;
                }
                for (Board nb : cur.getNextStates()) {
                    if (visited.contains(nb) == false) {
                        visited.add(nb);
                        q.offer(nb);
                    }
                }
            }
        }
        return -1;
    }
    
    private class Board {
        private int[][] board = new int[2][3];
        private int[] empty = new int[2];
        
        Board(int[][] board) {
            for (int i = 0; i < 2; i++) {
                for (int j = 0; j < 3; j++) {
                    this.board[i][j] = board[i][j];
                    if (board[i][j] == 0) {
                        empty[0] = i;
                        empty[1] = j;
                    }
                }
            }
        }
        List<Board> getNextStates() {
            List<Board> ret = new ArrayList<>();
            for (int i = 0; i < 4; i++) {
                int x = empty[0] + deltaX[i];
                int y = empty[1] + deltaY[i];
                if (x >= 0 && x < 2 && y >= 0 && y < 3) {
                    swapWithEmpty(x, y);
                    ret.add(new Board(board));
                    swapWithEmpty(x, y);
                }
            }
            return ret;
        }
        void swapWithEmpty(int x, int y) {
            int tmp = board[x][y];
            board[x][y] = board[empty[0]][empty[1]];
            board[empty[0]][empty[1]] = tmp;
        }
        @Override
        public boolean equals(Object other) {
            return this.hashCode() == other.hashCode();
        }
        @Override
        public int hashCode() {
            int ret = 0;
            for (int i = 0; i < 2; i++) {
                for (int j = 0; j < 3; j++) {
                    ret = ret * 10 + board[i][j];
                }
            }
            return ret;
        }
    }
}