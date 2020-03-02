// DFS + memo
// Time: O(MN)
// Space: O(MN)
class Solution {
    public int numDistinct(String s, String t) {
        Integer[][] memo = new Integer[s.length()][t.length()];
        return dfs(s, 0, t, 0, memo);
    }
    
    private int dfs(String s, int sIdx, String t, int tIdx, Integer[][] memo) {
        if (tIdx == t.length()) {
            return 1;
        }
        if (sIdx == s.length()) {
            return 0;
        }
        if (memo[sIdx][tIdx] != null) {
            return memo[sIdx][tIdx];
        }
        int ret = 0;
        if (s.charAt(sIdx) == t.charAt(tIdx)) {
            ret += dfs(s, sIdx + 1, t, tIdx + 1, memo);
        }
        ret += dfs(s, sIdx + 1, t, tIdx, memo);
        memo[sIdx][tIdx] = ret;
        return ret;
    }
}