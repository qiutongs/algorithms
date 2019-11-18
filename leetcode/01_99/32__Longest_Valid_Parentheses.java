class Solution {
    public int longestValidParentheses(String s) {
        if (s == null || s.isEmpty()) {
            return 0;
        }
        
        int ret = 0;
        int l = s.length();
        //dp[i]: left index that produces longest parent
        int[] dp = new int[l];
        for (int i = 0; i < l; i++) {
            dp[i] = i + 1;
        }
        
        for (int i = 1; i < l; i++) {
            char c = s.charAt(i);
            if (c == ')') {
                if (s.charAt(i - 1) == '(') {
                    dp[i] = i - 1;
                    if (i - 2 >= 0) {
                        dp[i] = dp[i - 2];
                    }
                } else {
                    int lIdx = dp[i - 1] - 1;
                    if (lIdx >= 0 && s.charAt(lIdx) == '(') {
                        dp[i] = lIdx;
                        if (lIdx - 1 >= 0) {
                            dp[i] = dp[lIdx - 1];
                        }
                    }
                }
                ret = Math.max(ret, i - dp[i] + 1);
            }
        }
        
        return ret;
    }
}