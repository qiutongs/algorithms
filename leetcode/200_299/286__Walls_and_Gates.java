/*
You are given a m x n 2D grid initialized with these three possible values.

1. -1 - A wall or an obstacle.
2. 0 - A gate.
3. INF - Infinity means an empty room. 
We use the value 231 - 1 = 2147483647 to represent INF as you may assume that the distance to a gate is less than 2147483647.

Fill each empty room with the distance to its nearest gate. If it is impossible to reach a gate, it should be filled with INF
*/
class Solution {
    public void wallsAndGates(int[][] rooms) {
        if (rooms == null || rooms.length == 0 || rooms[0] == null || rooms[0].length == 0) {
            return;
        }

        int[] deltaX = {0, 0, 1, -1};
        int[] deltaY = {1, -1, 0, 0};
        boolean[][] visited = new boolean[rooms.length][rooms[0].length];

        Queue<int[]> queue = new LinkedList<>();
        for (int i = 0; i < rooms.length; i++) {
            for (int j = 0; j < rooms[i].length; j++) {
                if (rooms[i][j] == 0) {
                    queue.add(new int[]{i, j});
                    visited[i][j] = true;
                }
            }
        }

        int d = 0;
        while(queue.isEmpty() == false) {
            int size = queue.size();
            d++;
            for (int j = 0; j < size; j++) {
                int[] curP = queue.poll();
                for (int i = 0; i < 4; i++) {
                    int x1 = curP[0] + deltaX[i];
                    int y1 = curP[1] + deltaY[i];
                    if (inBound(rooms, x1, y1) && visited[x1][y1] == false && rooms[x1][y1] > 0) {
                        rooms[x1][y1] = Math.min(rooms[x1][y1], d);
                        visited[x1][y1] = true;
                        queue.add(new int[]{x1, y1});
                    }
                }
            }
        }

    }

    private boolean inBound(int[][] rooms, int x, int y) {
        return x >= 0 && x < rooms.length && y >= 0 && y < rooms[0].length;
    }
}