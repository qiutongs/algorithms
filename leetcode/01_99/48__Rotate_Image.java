class Solution {
    public void rotate(int[][] matrix) {
        int n = matrix.length;
        int maxDepth = n / 2;
        
        for (int d = 0; d < maxDepth; d++) {
            iterate(matrix, d, n - 2 * d);
        }
    }
    
    private void iterate(int[][] matrix, int x1, int n) {
        int x2 = x1 + n - 1;
        for (int i = 0; i < n - 1; i++) {
            int tmp = matrix[x1][x1 + i];
            matrix[x1][x1 + i] = matrix[x2 - i][x1];
            matrix[x2 - i][x1] = matrix[x2][x2 - i];
            matrix[x2][x2 - i] = matrix[x1 + i][x2];
            matrix[x1 + i][x2] = tmp;
        }
    }
}
