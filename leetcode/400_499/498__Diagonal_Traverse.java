// Fancy traversal
// Time: O(MN)
// Extra Space: O(1)
class Solution {
    public int[] findDiagonalOrder(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0] == null || matrix[0].length == 0) {
            return new int[]{};
        }
        int m = matrix.length, n = matrix[0].length;
        int[] ret = new int[m * n];
        int[] cur = {0, 0};
        int[] direction = {-1, 1};
        for (int i = 0; i < m * n; i++) {
            ret[i] = matrix[cur[0]][cur[1]];
            next(m, n, cur, direction);
        }
        return ret;
    }
    
    private void next(int m, int n, int[] cur, int[] direction) {
        int[] next = {cur[0] + direction[0], cur[1] + direction[1]}; 
        if (inbound(m, n, next) == false) {
            int[] right = {cur[0], cur[1] + 1}; 
            int[] down = {cur[0] + 1, cur[1]}; 
            if (direction[0] == -1) {
                next = inbound(m, n, right) ? right : down;
            } else {
                next = inbound(m, n, down) ? down : right;
            }
            direction[0] *= -1;
            direction[1] *= -1;
        }
        cur[0] = next[0];
        cur[1] = next[1];
    }
    
    private boolean inbound(int m, int n, int[] cur) {
        return cur[0] >= 0 && cur[0] < m && cur[1] >= 0 && cur[1] < n;
    }
}