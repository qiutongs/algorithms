class Solution {
    public int[][] generateMatrix(int n) {
        int[][] ret = new int[n][n];
        int maxDepth = (n + 1) / 2;
        
        int num = 1;
        for (int d = 0; d < maxDepth; d++) {
            num = iterate(ret, d, n - 2 * d, num);
        }
        
        return ret;
    }
    
    private int iterate(int[][] matrix, int x1, int n, int num) {
        int y1 = x1;
        int x2 = x1 + n - 1, y2 = x2;
        
        if (n == 1) {
            matrix[x1][y1] = num;
            return num + 1;
        }

        for (int i = y1; i <= y2; i++) {
            matrix[x1][i] = num++;
        }
        for (int i = x1 + 1; i < x2; i++) {
            matrix[i][y2] = num++;
        }
        for (int i = y2; i >= y1; i--) {
            matrix[x2][i] = num++;
        }
        for (int i = x2 - 1; i > x1; i--) {
            matrix[i][y1] = num++;
        }
        return num;
    }
}