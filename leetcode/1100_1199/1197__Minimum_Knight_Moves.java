class Solution {
    private static int[] deltaX = {2, 2, 1, 1, -1, -1, -2, -2};
    private static int[] deltaY = {1, -1, 2, -2, 2, -2, 1, -1};
    
    public int minKnightMoves(int x, int y) {
        if (x == 0 && y == 0) {
            return 0;
        }
        x = Math.abs(x);
        y = Math.abs(y);
        
        Queue<Point> q1 = new LinkedList<>();
        Queue<Point> q2 = new LinkedList<>();
        Set<Point> visited1 = new HashSet<>();
        Set<Point> visited2 = new HashSet<>();
        Point start = new Point(0, 0);
        Point des = new Point(x, y);
        q1.offer(start);
        visited1.add(start);
        q2.offer(new Point(x, y));
        visited2.add(des);
        
        int move1 = 0;
        int move2 = 0;
        while(q1.isEmpty() == false && q2.isEmpty() == false) {
            move1++;
            int size1 = q1.size();
            for (int i = 0; i < size1; i++) {
                Point node = q1.poll();
                for (int j = 0; j < 8; j++) {
                    Point next = new Point(node.x + deltaX[j], node.y + deltaY[j]);
                    if (next.x < -1 || next.y < -1) {
                        continue;
                    }
                    if (visited2.contains(next)) {
                        return move1 + move2;
                    }
                    if (visited1.contains(next) == false) {
                        q1.offer(next);
                        visited1.add(next);
                    }
                }
            }
            
            move2++;
            int size2 = q2.size();
            for (int i = 0; i < size2; i++) {
                Point node = q2.poll();
                for (int j = 0; j < 8; j++) {
                    Point next = new Point(node.x + deltaX[j], node.y + deltaY[j]);
                    if (next.x < -1 || next.y < -1) {
                        continue;
                    }
                    if (visited1.contains(next)) {
                        return move1 + move2;
                    }
                    if (visited2.contains(next) == false) {
                        q2.offer(next);
                        visited2.add(next);
                    }
                }
            }
        }
        return -1;
    }
    
    private class Point {
        int x;
        int y;
        int[] coord;
        Point(int x, int y) {
            this.x = x;
            this.y = y;
            this.coord = new int[]{x, y};
        }
        @Override
        public boolean equals(Object other) {
            return Arrays.equals(this.coord, ((Point)other).coord);
        }
        @Override
        public int hashCode() {
            return Arrays.hashCode(coord);
        }
    }
}

// Bi-BFS
//
class Solution {
    private static int[] deltaX = {2, 2, 1, 1, -1, -1, -2, -2};
    private static int[] deltaY = {1, -1, 2, -2, 2, -2, 1, -1};
    
    public int minKnightMoves(int x, int y) {
        if (x == 0 && y == 0) {
            return 0;
        }
        Queue<Point> q1 = new LinkedList<>();
        Queue<Point> q2 = new LinkedList<>();
        Set<Point> visited1 = new HashSet<>();
        Set<Point> visited2 = new HashSet<>();
        Point start = new Point(0, 0);
        Point des = new Point(x, y);
        q1.offer(start);
        visited1.add(start);
        q2.offer(new Point(x, y));
        visited2.add(des);
        
        int move1 = 0;
        int move2 = 0;
        while(q1.isEmpty() == false && q2.isEmpty() == false) {
            move1++;
            int size1 = q1.size();
            for (int i = 0; i < size1; i++) {
                Point node = q1.poll();
                for (int j = 0; j < 8; j++) {
                    Point next = new Point(node.x + deltaX[j], node.y + deltaY[j]);
                    if (visited2.contains(next)) {
                        return move1 + move2;
                    }
                    if (visited1.contains(next) == false) {
                        q1.offer(next);
                        visited1.add(next);
                    }
                }
            }
            
            move2++;
            int size2 = q2.size();
            for (int i = 0; i < size2; i++) {
                Point node = q2.poll();
                for (int j = 0; j < 8; j++) {
                    Point next = new Point(node.x + deltaX[j], node.y + deltaY[j]);
                    if (visited1.contains(next)) {
                        return move1 + move2;
                    }
                    if (visited2.contains(next) == false) {
                        q2.offer(next);
                        visited2.add(next);
                    }
                }
            }
        }
        return -1;
    }
    
    private class Point {
        int x;
        int y;
        int[] coord;
        Point(int x, int y) {
            this.x = x;
            this.y = y;
            this.coord = new int[]{x, y};
        }
        @Override
        public boolean equals(Object other) {
            return Arrays.equals(this.coord, ((Point)other).coord);
        }
        @Override
        public int hashCode() {
            return Arrays.hashCode(coord);
        }
    }
}

// raw BFS
// Time: O()
// Time Limit Exceeded: 209, -58 (not very bad actually)
class Solution {
    private static int[] deltaX = {2, 2, 1, 1, -1, -1, -2, -2};
    private static int[] deltaY = {1, -1, 2, -2, 2, -2, 1, -1};
    
    public int minKnightMoves(int x, int y) {
        if (x == 0 && y == 0) {
            return 0;
        }
        Queue<Point> q = new LinkedList<>();
        Set<Point> visited = new HashSet<>();
        Point start = new Point(0, 0);
        q.offer(start);
        visited.add(start);
        
        int move = 0;
        while(q.isEmpty() == false) {
            move++;
            int size = q.size();
            for (int i = 0; i < size; i++) {
                Point node = q.poll();
                for (int j = 0; j < 8; j++) {
                    Point next = new Point(node.x + deltaX[j], node.y + deltaY[j]);
                    if (next.x == x && next.y == y) {
                        return move;
                    }
                    if (visited.contains(next) == false) {
                        q.offer(next);
                        visited.add(next);
                    }
                }
            }
        }
        return -1;
    }
    
    private class Point {
        int x;
        int y;
        int[] coord;
        Point(int x, int y) {
            this.x = x;
            this.y = y;
            this.coord = new int[]{x, y};
        }
        @Override
        public boolean equals(Object other) {
            return Arrays.equals(this.coord, ((Point)other).coord);
        }
        @Override
        public int hashCode() {
            return Arrays.hashCode(coord);
        }
    }
}