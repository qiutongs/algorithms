class Solution {
    static final int NONE = 0;
    static final int PACIFIC = 1;
    static final int ATLANTIC = 2;
    
    public List<List<Integer>> pacificAtlantic(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return Collections.emptyList();
        }
        
        List<List<Integer>> ret = new LinkedList<>();
        int[][] flow = initializeFlow(matrix);
        
        for (int i = 0; i < matrix.length; i++) {
            dfs(matrix, i, 0, flow, PACIFIC);
        }
        
        for (int j = 1; j < matrix[0].length; j++) {
            dfs(matrix, 0, j, flow, PACIFIC);
        }
        
        for (int i = 0; i < matrix.length; i++) {
            dfs(matrix, i, matrix[0].length - 1, flow, ATLANTIC);
        }
        
        for (int j = 0; j < matrix[0].length - 1; j++) {
            dfs(matrix, matrix.length - 1, j, flow, ATLANTIC);
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
    
    private void dfs(int[][] matrix, int x, int y, int[][] flow, int goal) {
        int[] deltaX = {0, 0, 1, -1};
        int[] deltaY = {1, -1, 0, 0};
        
        for (int i = 0; i < 4; i++) {
            int nextX = x + deltaX[i];
            int nextY = y + deltaY[i];
            
            if (inBound(matrix, nextX, nextY) && matrix[nextX][nextY] >= matrix[x][y] && (flow[nextX][nextY] & goal) == 0) {
                flow[nextX][nextY] |= goal;
                dfs(matrix, nextX, nextY, flow, goal);
            }
        }
    }
    
    private boolean inBound(int[][] matrix, int x, int y) {
        return x >= 0 && x < matrix.length && y >= 0 && y < matrix[0].length;
    }
    
    private int[][] initializeFlow(int[][] matrix) {
        int[][] flow = new int[matrix.length][matrix[0].length];

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                flow[i][j] = NONE;
                
                if (i == 0 || j == 0) {
                    flow[i][j] |= PACIFIC;
                }
                
                if (i == matrix.length - 1 || j == matrix[i].length - 1) {
                    flow[i][j] |= ATLANTIC;
                }
            }
        }
        
        return flow;
    }
}