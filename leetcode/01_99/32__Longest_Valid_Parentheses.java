class Solution {
    public int longestValidParentheses(String s) {
        if (s == null || s.isEmpty()) {
            return 0;
        }

        int n = s.length();
        //dp[i]: longest valid parenthese ending at i
        int[] dp = new int[n];
        int ret = 0; 
        for (int i = 1; i < n; i++) {
            if (s.charAt(i) == ')') {
                int matchIdx = i - dp[i - 1] - 1;
                if (matchIdx >= 0 && s.charAt(matchIdx) == '(') {
                    dp[i] = dp[i - 1] + 2;
                    dp[i] += matchIdx - 1 >= 0 ? dp[matchIdx - 1] : 0;
                    ret = Math.max(ret, dp[i]);
                } 
            }
        }
        return ret;
    }
}