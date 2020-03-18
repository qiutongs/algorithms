/**
 * // This is the robot's control interface.
 * // You should not implement it, or speculate about its implementation
 * interface Robot {
 *     // Returns true if the cell in front is open and robot moves into the cell.
 *     // Returns false if the cell in front is blocked and robot stays in the current cell.
 *     public boolean move();
 *
 *     // Robot will stay in the same cell after calling turnLeft/turnRight.
 *     // Each turn will be 90 degrees.
 *     public void turnLeft();
 *     public void turnRight();
 *
 *     // Clean the current cell.
 *     public void clean();
 * }
 */
class Solution {
 
    private final HashSet<State> visited = new HashSet<>();
    
    public void cleanRoom(Robot robot) {
        dfs(robot, new State(0, 0, Direction.UP));
    }
    
    private void dfs(Robot robot, State state) {
        visited.add(state);
        robot.clean();
        
        for (int i = 0; i < 4; i++) {
            robot.turnRight();
            state.turnRight();
            State next = state.move();
            if (visited.contains(next) == false && robot.move()) {
                dfs(robot, next);
                backtrack(robot);
            }
        }
    }
    
    private void backtrack(Robot robot) {
        robot.turnRight();
        robot.turnRight();
        robot.move();
        robot.turnRight();
        robot.turnRight();
    }
    
    private enum Direction {
        UP(0, 1) {
            @Override
            Direction turnRight() {
                return RIGHT;
            }
        },
        DOWN(0, -1){
            @Override
            Direction turnRight() {
                return LEFT;
            }
        },
        LEFT(-1, 0){
            @Override
            Direction turnRight() {
                return UP;
            }
        },
        RIGHT(1, 0){
            @Override
            Direction turnRight() {
                return DOWN;
            }
        };
        
        int x, y;
        Direction(int x, int y) {
            this.x = x;
            this.y = y;
        }
        abstract Direction turnRight();
    }
    
    private class State {
        private int[] xy = new int[2];
        private Direction d;
        
        State(int x, int y, Direction d) {
            this.xy[0] = x;
            this.xy[1] = y;
            this.d = d;
        }
        
        void turnRight() {
            d = d.turnRight();
        }
        State move() {
            return new State(xy[0] + d.x, xy[1] + d.y, d);
        }
        public boolean equals(Object other) {
            State s = (State)other;
            return Arrays.equals(this.xy, s.xy);
        }
        public int hashCode() {
            return Arrays.hashCode(xy);
        }
    }
}