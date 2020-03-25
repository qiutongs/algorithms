// Wrong: looking at row and column, get min of the two; but water can leak from side
class Solution {
    public int trapRainWater(int[][] heightMap) {
        int m = heightMap.length, n = heightMap[0].length;
        int[][] maxRowH = new int[m][n];
        for (int i = 0; i < m; i++) {
            trap(heightMap[i], maxRowH[i]);
        }
        
        int[][] flipHeightMap = flip(heightMap);
        int[][] maxColH = new int[n][m];
        for (int i = 0; i < n; i++) {
            trap(flipHeightMap[i], maxColH[i]);
        }
        int[][] flipMaxColH = flip(maxColH);
        
        int ret = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                ret += Math.min(maxRowH[i][j], flipMaxColH[i][j]) - heightMap[i][j];
            }
        }
        return ret;
    }
    
    private void trap(int[] height, int[] maxH) {
        int l = 0, r = height.length - 1;
        while(l <= r) {
            if (height[l] < height[r]) {
                int lH = height[l];
                while(l <= r && height[l] <= lH) {
                    maxH[l] = lH;
                    l++;
                }
            } else {
                int rH = height[r];
                while(l <= r && height[r] <= rH) {
                    maxH[r] = rH;
                    r--;
                }
            }
        }
    }
    
    private int[][] flip(int[][] matrix) {
        int m = matrix.length, n = matrix[0].length;
        int[][] ret = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                ret[i][j] = matrix[j][i];
            }
        }
        return ret;
    }
}