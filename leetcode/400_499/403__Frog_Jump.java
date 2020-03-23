class Solution {
    public boolean canCross(int[] stones) {
        return dfs(stones, 0, 0, new Boolean[stones.length][stones.length]);
    }
    
    private boolean dfs(int[] stones, int curIndex, int k, Boolean[][] memo) {
        if (curIndex == stones.length - 1) {
            return true;
        } else if (curIndex >= stones.length) {
            return false;
        }
        if (memo[curIndex][k] != null) {
            return memo[curIndex][k];
        }
        boolean ret = false;
        if (curIndex == 0) {
            if (stones[1] - stones[0] == 1) {
                ret = dfs(stones, curIndex + 1, 1, memo);
            }
        } else {
            for (int i = curIndex + 1; i < stones.length; i++) {
                int diff = stones[i] - stones[curIndex];
                if (diff >= k - 1 && diff <= k + 1 && dfs(stones, i, diff, memo)) {
                    ret = true;
                    break;
                } else if (diff > k + 1) {
                    break;
                }
            }
        }
        memo[curIndex][k] = ret;
        return ret;
    }
}