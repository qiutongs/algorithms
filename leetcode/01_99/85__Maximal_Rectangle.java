// 2D -> 1D Largetst rectagnle, treat each row as bottom
// Time: O(M * N)
class Solution {
    public int maximalRectangle(char[][] matrix) {
        if (matrix.length == 0 || matrix[0].length == 0) {
            return 0;
        }
        int ret = 0;
        int m = matrix.length, n = matrix[0].length;
        int[] heights = new int[n];
        for (int i = 0; i < m; i++) {
            updateHeights(matrix, i, heights);
            ret = Math.max(ret, largestRectangleArea(heights));
        }
        return ret;
    }
    
    private int largestRectangleArea(int[] heights) {
        int ret = 0;
        Stack<Integer> upStack = new Stack<>();
        for (int i = 0; i <= heights.length; i++) {
            while (upStack.isEmpty() == false && (i == heights.length || heights[upStack.peek()] > heights[i])) {
                int hIdx = upStack.pop();
                int lSmallIdx = upStack.isEmpty() ? -1 : upStack.peek();
                int rSmallIdx = i;
                ret = Math.max(ret, heights[hIdx] * (rSmallIdx - lSmallIdx - 1));
            }
            upStack.push(i);
        }
        return ret;
    }
    
    private void updateHeights(char[][] matrix, int row, int[] heights) {
        for (int i = 0; i < matrix[row].length; i++) {
            heights[i] = matrix[row][i] == '0' ? 0 : 1 + heights[i];
        }
    }
}

// Wrong: at least not handle the case where there are multiple same max area but different w and h
// DP: try to be similar as problem Max Square
class Solution {
    private Area EMPTY = new Area(0, 0);
    
    public int maximalRectangle(char[][] matrix) {
        if (matrix.length == 0 || matrix[0].length == 0) {
            return 0;
        }
        
        int ret = 0;
        int m = matrix.length, n = matrix[0].length;
        Area[][] dp = new Area[m][n];
        dp[0][0] = matrix[0][0] == '1' ? new Area(1, 1) : EMPTY;
        ret = Math.max(ret, dp[0][0].val());
            
        for (int i = 1; i < m; i++) {
            dp[i][0] = matrix[i][0] == '1' ? new Area(1, dp[i - 1][0].h + 1) : EMPTY;
            ret = Math.max(ret, dp[i][0].val());
        }
        for (int i = 1; i < n; i++) {
            dp[0][i] = matrix[0][i] == '1' ? new Area(dp[0][i - 1].w + 1, 1) : EMPTY;
            ret = Math.max(ret, dp[0][i].val());
        }
        
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (matrix[i][j] == '1') {
                    dp[i][j] = new Area(Math.min(dp[i - 1][j - 1].w, dp[i][j - 1].w) + 1, Math.min(dp[i - 1][j - 1].h, dp[i - 1][j].h) + 1);
                    Area a1 = new Area(1, dp[i - 1][j].h + 1);
                    if (dp[i][j].val() < a1.val()) {
                        dp[i][j] = a1;
                    }
                    Area a2 = new Area(dp[i][j - 1].w + 1, 1);
                    if (dp[i][j].val() < a2.val()) {
                        dp[i][j] = a2;
                    }
                    ret = Math.max(ret, dp[i][j].val());
                } else {
                    dp[i][j] = EMPTY;
                }
            }
        }
        
        /*for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                System.out.println(dp[i][j]);
            }
        }*/
        System.out.println(dp[4][3]);
        return ret;
    }
    
    private class Area {
        int w;
        int h;
        Area(int w, int h) {
            this.w = w;
            this.h = h;
        }
        int val() {
            return w * h;
        }
        public String toString() {
            return "[" + w +","+h+"]";
        }
    }
}