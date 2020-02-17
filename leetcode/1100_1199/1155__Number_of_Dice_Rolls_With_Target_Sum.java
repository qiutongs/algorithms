class Solution {
    private int MOD = (int)(Math.pow(10, 9) + 7);
    
    public int numRollsToTarget(int d, int f, int target) {
        Long[][] memo = new Long[d + 1][target + 1];
        return (int)(dfs(d, f, target, memo) % MOD);
    }
    
    private long dfs(int d, int f, int target, Long[][] memo) {
        if (d == 0 && target == 0) {
            return 1;
        } else if (d == 0 || target == 0) {
            return 0;
        }
        if (memo[d][target] != null) {
            return memo[d][target];
        }
        long ret = 0;
        for (int i = 1; i <= f; i++) {
            if (target - i >= 0) {
                ret += dfs(d - 1, f, target - i, memo) % MOD;
            }
        }
        memo[d][target] = ret;
        return ret;
    }
}