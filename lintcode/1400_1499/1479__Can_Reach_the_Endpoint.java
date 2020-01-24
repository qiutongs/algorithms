public class Solution {
    /**
     * @param map: the map
     * @return: can you reach the endpoint
     */
    public boolean reachEndpoint(int[][] map) {
        if (map == null || map.length == 0 || map[0] == null || map[0].length == 0) {
            return false;
        }
        return dfs(map, 0, 0, new boolean[map.length][map[0].length]);
    }
    
    private boolean dfs(int[][] map, int x, int y, boolean[][] visited) {
        if (map[x][y] == 0) {
            return false;
        }
        if (map[x][y] == 9) {
            return true;
        }
        visited[x][y] = true;
        
        int[] deltaX = {1, -1, 0, 0};
        int[] deltaY = {0, 0, 1, -1};
        for (int i = 0; i < 4; i++) {
            int x1 = x + deltaX[i];
            int y1 = y + deltaY[i];
            if (inbound(map, x1, y1) && visited[x1][y1] == false) {
                if (dfs(map, x1, y1, visited)) {
                    return true;
                }
            }
        }
        return false;
    }
    
    private boolean inbound(int[][] map, int x, int y) {
        return x >= 0 && x < map.length && y >= 0 && y < map[0].length;
    }
}