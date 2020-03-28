// DP
// Time: O(N*K)
class Solution {
    public int longestOnes(int[] A, int K) {
        int ret = 0;
        int[] dp = new int[K + 1];
        for (int num : A) {
            if (num == 1) {
                for (int i = 0; i < K + 1; i++) {
                    dp[i] = dp[i] + 1;
                    ret = Math.max(ret, dp[i]);
                }
            } else {
                for (int i = K; i >= 1; i--) {
                    dp[i] = dp[i - 1] + 1;
                    ret = Math.max(ret, dp[i]);
                }
                dp[0] = 0;
            }
        }
        return ret;
    }
}

// Sliding window
// Time: O(N)
class Solution {
    public int longestOnes(int[] nums, int K) {
        int ret = 0;
        int curZeros = 0, maxZeros = K;
        int l = 0;
        for (int r = 0; r < nums.length; r++) {
            curZeros += nums[r] == 1 ? 0 : 1;
            while(l <= r && curZeros > maxZeros) {
                curZeros -= nums[l] == 1 ? 0 : 1;
                l++;
            }
            ret = Math.max(ret, r - l + 1);
        }
        return ret;
    }
}