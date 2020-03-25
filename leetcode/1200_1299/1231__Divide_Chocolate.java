// DFS + memo
// Time: O(N^2*K) Time limit exceed
class Solution {
    public int maximizeSweetness(int[] sweetness, int K) {
        int[] preSum = new int[sweetness.length + 1];
        for (int i = 0; i < sweetness.length; i++) {
            preSum[i + 1] = preSum[i] + sweetness[i];
        }
        return dfs(preSum, 0, K, new Integer[sweetness.length + 1][K + 1]);
    }
    
    private int dfs(int[] preSum, int idx, int K, Integer[][] memo) {
        if (K == 0) {
            return preSum[preSum.length - 1] - preSum[idx];
        }
        if (memo[idx][K] != null) {
            return memo[idx][K];
        }
        int ret = 0;
        for (int i = idx; i < preSum.length - 1; i++) {
            int curSum = preSum[i + 1] - preSum[idx];
            int subret = dfs(preSum, i + 1, K - 1, memo);
            ret = Math.max(ret, Math.min(curSum, subret));
        }
        memo[idx][K] = ret;
        return ret;
    }
}