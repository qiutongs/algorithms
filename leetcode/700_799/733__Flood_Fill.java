class Solution {
    public int[][] floodFill(int[][] image, int sr, int sc, int newColor) {
        if (image == null || image.length == 0 || newColor == image[sr][sc]) {
            return image;
        }
        dfs(image, sr, sc, image[sr][sc], newColor);
        return image;
    }
    
    private void dfs(int[][] image, int x, int y, int oldColor, int newColor) {
        image[x][y] = newColor;
        
        int[] deltaX = {0, 0, 1, -1};
        int[] deltaY = {1, -1, 0, 0};
        for (int i = 0; i < 4; i++) {
            int x1 = x + deltaX[i];
            int y1 = y + deltaY[i];
            if (inbound(image, x1, y1) && image[x1][y1] == oldColor) {
                dfs(image, x1, y1, oldColor, newColor);
            }
        }
    }
    
    private boolean inbound(int[][] image, int x, int y) {
        return x >= 0 && x < image.length && y >= 0 && y < image[0].length;
    }
}