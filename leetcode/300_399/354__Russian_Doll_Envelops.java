class Solution {
    public int maxEnvelopes(int[][] envelopes) {
        if (envelopes == null || envelopes.length == 0) {
            return 0;
        }
        // sort by w
        Arrays.sort(envelopes, (e1, e2) -> e1[0] - e2[0]);
        // compute LIS of h
        int[] dp = new int[envelopes.length];
        Arrays.fill(dp, 1);
        
        for (int i = 1; i < envelopes.length; i++) {
            for (int j = 0; j < i; j++) {
                if (envelopes[j][0] < envelopes[i][0] && envelopes[j][1] < envelopes[i][1]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
        }
        
        int ret = 0;
        for (int val : dp) {
            ret = Math.max(ret, val);
        }
        return ret;
    }
}