class Solution {
    public int findNumberOfLIS(int[] nums) {
        int ret = 0;
        int maxLen = 0;
        DP[] memo = new DP[nums.length];
        for (int i = 0; i < nums.length; i++) {
            DP dp = dfs(nums, i, memo);
            if (dp.len > maxLen) {
                maxLen = dp.len;
                ret = dp.count;
            } else if (dp.len == maxLen) {
                ret += dp.count;
            }
        }
        return ret;
    }
    
    private DP dfs(int[] nums, int curIdx, DP[] memo) {
        if (curIdx == nums.length) {
            return new DP(0, 1);
        }
        if (memo[curIdx] != null) {
            return memo[curIdx];
        }
        DP ret = new DP(0, 1);
        for (int i = curIdx + 1; i < nums.length; i++) {
            if (nums[i] > nums[curIdx]) {
                DP subret = dfs(nums, i, memo);
                if (subret.len > ret.len) {
                    ret.len = subret.len;
                    ret.count = subret.count;
                } else if (subret.len == ret.len) {
                    ret.count += subret.count;
                }
            }
        }
        ret.len++;
        memo[curIdx] = ret;
        return ret;
    }
    
    private class DP {
        int len;
        int count;
        DP(int len, int count) {
            this.len = len;
            this.count = count;
        }
        public String toString() {
            return len + " " + count + " ";
        }
    }
}

/*
 * DP: variation of Longest Increasing Subsequence.
 * - add a count array
 * - sum up count array if it is equal to max LIS
 */
class Solution {
    public int findNumberOfLIS(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        
        int[] dp = new int[nums.length];
        int[] counts = new int[nums.length];
        int maxLIS = 1;
        
        Arrays.fill(dp, 1);
        Arrays.fill(counts, 1);
        
        for (int i = 1; i < nums.length; i++) {
            for (int j = i - 1; j >= 0; j--) {
                if (nums[i] > nums[j]) {
                    if (dp[j] + 1 > dp[i]) {
                        dp[i] = dp[j] + 1;
                        maxLIS = Math.max(maxLIS, dp[i]);
                        
                        counts[i] = counts[j];
                    } else if (dp[j] + 1 == dp[i]) {
                        counts[i] += counts[j];
                    }
                }
            }
        }
        
        int ret = 0;
        
        for (int i = 0; i < nums.length; i++) {
            if (dp[i] == maxLIS) {
                ret += counts[i];
            }
        }
    
        return ret;
    }
}