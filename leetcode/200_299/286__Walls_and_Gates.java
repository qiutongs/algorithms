class Solution {
    public void wallsAndGates(int[][] rooms) {
        if (rooms == null || rooms.length == 0 || rooms[0] == null || rooms[0].length == 0) {
            return;
        }

        for (int i = 0; i < rooms.length; i++) {
            for (int j = 0; j < rooms[i].length; j++) {
                if (rooms[i][j] == 0) {
                    bfs(rooms, i, j);
                }
            }
        }
    }

    private void bfs(int[][] rooms, int x, int y) {
        int[] deltaX = {0, 0, 1, -1};
        int[] deltaY = {1, -1, 0, 0};
        boolean[][] visited = new boolean[rooms.length][rooms[0].length];

        Queue<Integer[]> queue = new LinkedList<>();
        Integer[] start = {x, y};
        queue.add(start);
        visited[x][y] = true;

        int d = 0;
        while(queue.isEmpty() == false) {
            int size = queue.size();
            d++;
            for (int j = 0; j < size; j++) {
                Integer[] curP = queue.remove();
                for (int i = 0; i < 4; i++) {
                    int nextX = curP[0] + deltaX[i];
                    int nextY = curP[1] + deltaY[i];
                    Integer[] next = {nextX, nextY};
                    if (inBound(rooms, next) && visited[nextX][nextY] == false && rooms[nextX][nextY] > 0) {
                        rooms[nextX][nextY] = Math.min(rooms[nextX][nextY], d);
                        visited[nextX][nextY] = true;
                        queue.add(next);
                    }
                }
            }
        }
    }

    private boolean inBound(int[][] rooms, Integer[] p) {
        return p[0] >= 0 && p[0] < rooms.length && p[1] >= 0 && p[1] < rooms[0].length;
    }
}