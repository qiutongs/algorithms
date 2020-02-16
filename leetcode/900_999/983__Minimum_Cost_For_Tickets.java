// DFS + memo
// Time: O(N)
// Space: O(N)
class Solution {
    public int mincostTickets(int[] days, int[] costs) {
        Integer[] memo = new Integer[days.length];
        return dfs(days, 0, costs, memo);
    }
    
    private int dfs(int[] days, int offset, int[] costs, Integer[] memo) {
        if (offset == days.length) {
            return 0;
        }
        if (memo[offset] != null) {
            return memo[offset];
        }
        int ret = costs[0] + dfs(days, offset + 1, costs, memo);
        
        int curDay = days[offset];
        int i = offset;
        while(i < days.length && days[i] < curDay + 7) {
            i++;
        }
        ret = Math.min(ret, costs[1] + dfs(days, i, costs, memo));
        
        while(i < days.length && days[i] < curDay + 30) {
            i++;
        }
        ret = Math.min(ret, costs[2] + dfs(days, i, costs, memo));
        memo[offset] = ret;
        return ret;
    }
}