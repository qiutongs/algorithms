// DFS + memo
// Time: O(N * N)
class Solution {
    public boolean canCross(int[] stones) {
        return dfs(stones, 0, 0, new Boolean[stones.length][stones.length]);
    }
    
    private boolean dfs(int[] stones, int curIndex, int k, Boolean[][] memo) {
        if (curIndex == stones.length - 1) {
            return true;
        }
        if (memo[curIndex][k] != null) {
            return memo[curIndex][k];
        }
        boolean ret = false;
        if (curIndex == 0) {
            if (curIndex + 1 < stones.length && stones[curIndex + 1] - stones[curIndex] == 1) {
                ret = dfs(stones, curIndex + 1, 1, memo);
            }
        } else {
            for (int i = curIndex + 1; i < stones.length; i++) {
                int diff = stones[i] - stones[curIndex];
                if (diff == k - 1) {
                    if (dfs(stones, i, k - 1, memo)) {
                        ret = true;
                    }
                } else if (diff == k) {
                    if (dfs(stones, i, k, memo)) {
                        ret = true;
                    }
                } else if (diff == k + 1) {
                    if (dfs(stones, i, k + 1, memo)) {
                        ret = true;
                    }
                } else if (diff > k + 1) {
                    break;
                }
                if (ret) {
                    break;
                }
            }
        }
        memo[curIndex][k] = ret;
        return ret;
    }
}