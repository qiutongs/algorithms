// Find median of X and Y
// Time: O(mn)
// Space: O(1)
class Solution {
    public int minTotalDistance(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        int total = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                total += grid[i][j];
            }
        }
        
        int half = total / 2 + 1;
        int count = 0;
        int xMedian = 0, yMedian = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                count += grid[i][j];
            }
            if (count >= half) {
                xMedian = i;
                break;
            }
        }
        
        count = 0;
        for (int j = 0; j < n; j++) {
            for (int i = 0; i < m; i++) {
                count += grid[i][j];
            }
            if (count >= half) {
                yMedian = j;
                break;
            }
        }

        int ret = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    ret += Math.abs(i - xMedian) + Math.abs(j - yMedian);
                }
            }
        }
        return ret;
    }
}