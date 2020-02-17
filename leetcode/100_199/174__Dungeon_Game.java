class Solution {
    public int calculateMinimumHP(int[][] dungeon) {
        Integer[][] memo = new Integer[dungeon.length][dungeon[0].length];
        int ret = dfs(dungeon, 0, 0, memo);
        return ret;
    }
    
    private int dfs(int[][] dungeon, int x, int y, Integer[][] memo) {
        if (memo[x][y] != null) {
            return memo[x][y];
        }
        int m = dungeon.length, n = dungeon[0].length;
        int ret = 0;
        if (x == m - 1 && y == n - 1) {
            ret = -dungeon[x][y] + 1;
        } else {
            int right = y + 1 < n ? dfs(dungeon, x, y + 1, memo) : Integer.MAX_VALUE;
            int down = x + 1 < m ? dfs(dungeon, x + 1, y, memo) : Integer.MAX_VALUE;
            ret =  Math.min(right, down) - dungeon[x][y];
        }
        ret = ret < 1 ? 1 : ret;
        memo[x][y] = ret;
        return ret;
    }
}


// DFS + memo
class Solution {
    public int minimumTotal(List<List<Integer>> triangle) {
        Integer[][] memo = new Integer[triangle.size()][];
        for (int i = 0; i < memo.length; i++) {
            memo[i] = new Integer[i + 1];
        }
        return dfs(triangle, 0, 0, memo);
    }
    
    private int dfs(List<List<Integer>> triangle, int x, int y, Integer[][] memo) {
        if (memo[x][y] != null) {
            return memo[x][y];
        }
        if (x == triangle.size() - 1) {
            return triangle.get(x).get(y);
        }
        int ret = triangle.get(x).get(y) + Math.min(dfs(triangle, x + 1, y, memo), dfs(triangle, x + 1, y + 1, memo));
        memo[x][y] = ret;
        return ret;
    }
}