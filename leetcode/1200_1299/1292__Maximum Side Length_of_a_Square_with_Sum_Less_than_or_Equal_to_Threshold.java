// Prefix sum + Binary search
// Time: O(N ^ 2 * logN)
// Space: O(N ^ 2)
class Solution {
    public int maxSideLength(int[][] mat, int threshold) {
        if (mat.length == 0 || mat[0].length == 0) {
            return 0;
        }
        int m = mat.length, n = mat[0].length;
        int[][] preSum = new int[m + 1][n + 1];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                preSum[i + 1][j + 1] = mat[i][j] + preSum[i + 1][j] + preSum[i][j + 1] - preSum[i][j];  
            }
        }
        
        int l = 1, r = Math.min(m, n);
        while(l <= r) {
            int mid = (l + r) / 2;
            if (isSquareExist(preSum, threshold, mid)) {
                l = mid + 1;
            } else {
                r = mid - 1;
            }
        }
        return r;
    }
    
    private boolean isSquareExist(int[][] preSum, int threshold, int len) {
        for (int i = len; i < preSum.length; i++) {
            for (int j = len; j < preSum[i].length; j++) {
                if (preSum[i][j] - preSum[i - len][j] - preSum[i][j - len] + preSum[i - len][j - len] <= threshold) {
                    return true;
                }
            }
        }
        return false;
    }
}