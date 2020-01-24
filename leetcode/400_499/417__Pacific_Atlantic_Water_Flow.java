class Solution {
    static final int PACIFIC = 1;
    static final int ATLANTIC = 2;
    
    public List<List<Integer>> pacificAtlantic(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return Collections.emptyList();
        }
        
        List<List<Integer>> ret = new LinkedList<>();
        int m = matrix.length, n = matrix[0].length;
        int[][] flow = new int[m][n];
        
        for (int i = 0; i < m; i++) {
            dfs(matrix, i, 0, flow, PACIFIC);
            dfs(matrix, i, n - 1, flow, ATLANTIC);
        }
        
        for (int j = 0; j < matrix[0].length; j++) {
            dfs(matrix, 0, j, flow, PACIFIC);
            dfs(matrix, m - 1, j, flow, ATLANTIC);
        }

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (flow[i][j] == (PACIFIC | ATLANTIC)) {
                    ret.add(Arrays.asList(i, j));
                }
            }
        }
        return ret;
    }
    
    private void dfs(int[][] matrix, int x, int y, int[][] flow, int color) {
        flow[x][y] |= color;
        
        int[] deltaX = {0, 0, 1, -1};
        int[] deltaY = {1, -1, 0, 0};
        
        for (int i = 0; i < 4; i++) {
            int x1 = x + deltaX[i];
            int y1 = y + deltaY[i];
            
            if (inBound(matrix, x1, y1) && matrix[x1][y1] >= matrix[x][y] && (flow[x1][y1] & color) == 0) {
                dfs(matrix, x1, y1, flow, color);
            }
        }
    }
    
    private boolean inBound(int[][] matrix, int x, int y) {
        return x >= 0 && x < matrix.length && y >= 0 && y < matrix[0].length;
    }
}