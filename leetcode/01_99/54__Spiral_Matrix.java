class Solution {
    public List<Integer> spiralOrder(int[][] matrix) {
        if (matrix == null || matrix.length == 0|| matrix[0] == null || matrix[0].length == 0) {
            return Collections.emptyList();
        }
        
        List<Integer> ret = new LinkedList<>();
        int m = matrix.length, n = matrix[0].length;
        int maxDepth = (Math.min(m, n) + 1) / 2;
        for (int d = 0; d < maxDepth; d++) {
            iterate(matrix, d, d, m - 2 * d, n - 2 * d, ret);
        }
        return ret;
    }
    
    private void iterate(int[][] matrix, int x1, int y1, int m, int n, List<Integer> ret) {
        int x2 = x1 + m - 1, y2 = y1 + n - 1;
        for (int i = y1; i <= y2; i++) {
            ret.add(matrix[x1][i]);
        }
        for (int i = x1 + 1; i < x2; i++) {
            ret.add(matrix[i][y2]);
        }
        if (x2 > x1) {
            for (int i = y2; i >= y1; i--) {
                ret.add(matrix[x2][i]);
            }
        }
        if (y2 > y1) {
            for (int i = x2 - 1; i > x1; i--) {
                ret.add(matrix[i][y1]);
            }
        }
    }
}