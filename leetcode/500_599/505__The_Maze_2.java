// Uniform Cost Search
class Solution {
    private static final int[] DELTAX = {0, 0, 1, -1};
    private static final int[] DELTAY = {1, -1, 0, 0};
    
    public int shortestDistance(int[][] maze, int[] start, int[] destination) {
        boolean[][] explored = new boolean[maze.length][maze[0].length];
        
        Queue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(start[0], start[1], 0));
        
        while(pq.isEmpty() == false) {
            Node cur = pq.poll();
            explored[cur.x][cur.y] = true;
            
            if (cur.x == destination[0] && cur.y == destination[1]) {
                return cur.dis;
            }
            
            for (int i = 0; i < 4; i++) {
                int x1 = cur.x, y1 = cur.y;
                while(inbound(maze, x1 + DELTAX[i], y1 + DELTAY[i]) && maze[x1 + DELTAX[i]][y1 + DELTAY[i]] == 0) {
                    x1 += DELTAX[i];
                    y1 += DELTAY[i];
                }
                if (explored[x1][y1]) {
                    continue;
                }
                pq.offer(new Node(x1, y1, cur.dis + Math.abs(x1 - cur.x + y1 - cur.y)));
            }
        }
        return -1;
    }

    private boolean inbound(int[][] maze, int x, int y) {
        return x >= 0 && x < maze.length && y >= 0 && y < maze[0].length;
    }
    
    private class Node implements Comparable<Node> {
        int x;
        int y;
        int dis;
        Node(int x, int y, int dis) {
            this.x = x;
            this.y = y;
            this.dis = dis;
        }
        public int compareTo(Node other) {
            return this.dis - other.dis;
        }
    }
}