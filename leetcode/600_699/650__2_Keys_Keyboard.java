// DFS + memo
// Time: O(N^2)
// Space: O(N)
class Solution {
    public int minSteps(int n) {
        Integer[] memo = new Integer[n];
        return dfs(1, n, memo);
    }
    
    private int dfs(int cur, int n, Integer[] memo) {
        if (cur == n) {
            return 0;
        }
        if (memo[cur] != null) {
            return memo[cur];
        }
        int ret = Integer.MAX_VALUE;
        for (int i = 2; i * cur <= n; i++) {
            int subret = dfs(cur * i, n, memo);
            if (subret != Integer.MAX_VALUE) {
                ret = Math.min(ret, i + subret);
            }
        }
        memo[cur] = ret;
        return ret;
    }
}