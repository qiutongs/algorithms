public class Solution {
    /**
     * @param image: a binary matrix with '0' and '1'
     * @param x: the location of one of the black pixels
     * @param y: the location of one of the black pixels
     * @return: an integer
     */
    public int minArea(char[][] image, int x, int y) {
        if (image == null || image.length == 0 || image[0].length == 0 ) {
            return 0;
        }

        int m = image.length, n = image[0].length;
        boolean[][] visited = new boolean[m][n];
        int[] ret = {x, y, x, y};
        
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (image[i][j] == '1') {
                    dfs(image, i, j, visited, ret);
                }
            }
        }
        
        return (ret[2] - ret[0] + 1) * (ret[3] - ret[1] + 1);
    }
    
    private void dfs(char[][] image, int x, int y, boolean[][] visited, int[] ret) {
        visited[x][y] = true;
        
        ret[0] = Math.min(ret[0], x);
        ret[1] = Math.min(ret[1], y);
        ret[2] = Math.max(ret[2], x);
        ret[3] = Math.max(ret[3], y);
        
        int[] deltaX = {0, 0, -1, 1};
        int[] deltaY = {1, -1, 0, 0};
        for (int i = 0; i < 4; i++) {
            int x1 = x + deltaX[i], y1 = y + deltaY[i];
            if (inBound(image, x1, y1) && visited[x1][y1] == false && image[x1][y1] == '1') {
                dfs(image, x1, y1, visited, ret);
            }
        }
    }
    
    private boolean inBound(char[][] image, int x, int y) {
        return x >= 0 && x < image.length && y >= 0 && y < image[0].length;
    }
}

public class Solution {
    /**
     * @param image: a binary matrix with '0' and '1'
     * @param x: the location of one of the black pixels
     * @param y: the location of one of the black pixels
     * @return: an integer
     */
    public int minArea(char[][] image, int x, int y) {
        if (image == null || image.length == 0 || image[0].length == 0 ) {
            return 0;
        }

        int m = image.length, n = image[0].length;
        boolean[][] visited = new boolean[m][n];

        int maxX = x, minX = x;
        int maxY = y, minY = y;
        
        int[] deltaX = {0, 0, -1, 1};
        int[] deltaY = {1, -1, 0, 0};
        
        Queue<Integer[]> queue = new LinkedList<>();
        queue.add(new Integer[]{x, y});
        visited[x][y] = true;
        
        while(queue.isEmpty() == false) {
            Integer[] v = queue.remove();
            
            maxX = Math.max(maxX, v[0]);
            minX = Math.min(minX, v[0]);
            maxY = Math.max(maxY, v[1]);
            minY = Math.min(minY, v[1]);
            
            for (int i = 0; i < 4; i++) {
                Integer[] u ={ v[0] + deltaX[i], v[1] + deltaY[i] };
                
                if (inBound(image, u) && visited[u[0]][u[1]] == false && image[u[0]][u[1]] == '1') {
                    visited[u[0]][u[1]] = true;
                    queue.add(u);
                }
            }
            
        }
        
        return (maxX - minX + 1) * (maxY - minY + 1);
    }
    
    private boolean inBound(char[][] image, Integer[] point) {
        return point[0] >= 0 && point[0] < image.length && point[1] >= 0 && point[1] < image[0].length;
    }
}