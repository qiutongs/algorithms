// DFS + memo
// Time: O(MN)
// Space: O(MN)
class Solution {
    public int minFallingPathSum(int[][] A) {
        Integer[][] memo = new Integer[A.length][A[0].length];
        int ret = Integer.MAX_VALUE;
        for (int i = 0; i < A[0].length; i++) {
            ret = Math.min(ret, dfs(A, 0, i, memo));
        }
        return ret;
    }
    
    private int dfs(int[][] A, int x, int y, Integer[][] memo) {
        if (x == A.length - 1) {
            return A[x][y];
        }
        if (memo[x][y] != null) {
            return memo[x][y];
        }
        int[] deltaY = {-1, 0, 1};
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < 3; i++) {
            if (y + deltaY[i] >= 0 && y + deltaY[i] < A[0].length) {
                min = Math.min(min, dfs(A, x + 1, y + deltaY[i], memo));
            }
        }
        memo[x][y] = A[x][y] + min;
        return A[x][y] + min;
    }
}