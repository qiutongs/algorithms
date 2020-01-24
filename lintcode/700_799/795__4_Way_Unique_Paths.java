/*
A robot is located at the top-left corner of a m x n grid.

The robot can move any direction at any point in time, but every grid can only be up to once. The robot is trying to reach the bottom-right corner of the grid.

How many possible unique paths are there?
*/

public class Solution {
    /**
     * @param m: the row
     * @param n: the column
     * @return: the possible unique paths
     */
    public int uniquePaths(int m, int n) {
        int[] ret = { 0 };
        HashSet<Integer> visited = new HashSet<>();
        dfs(m, n, 1, 1, visited, ret);
        return ret[0];
    }
    
    private void dfs(int m, int n, int x, int y, HashSet<Integer> visited, int[] ret) {
        if (x == m && y == n) {
            ret[0]++;
            return;
        }
        if (x < 1 || x > m || y < 1 || y > n) {
            return;
        }
        visited.add(toPos(n, x, y));
        int[] deltaX = {0, 0, 1, -1};
        int[] deltaY = {1, -1, 0, 0};
        for (int i = 0; i < 4; i++) {
            int pos = toPos(n, x + deltaX[i], y + deltaY[i]);
            if (visited.contains(pos) == false) {
                dfs(m, n, x + deltaX[i], y + deltaY[i], visited, ret);
            }
        }
        visited.remove(toPos(n, x, y));
    }
    
    private int toPos(int n, int x, int y) {
        return (x - 1) * n + y - 1;
    }
}