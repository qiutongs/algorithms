/*
 * DP problem: similar structure as "Go Upstairs Problem"
 */
class Solution {
    public int numDecodings(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        int len = s.length();
        int[] dp = new int[len + 1];
        dp[0] = 1;
        dp[1] = s.charAt(0) == '0' ? 0 : 1;
        for (int i = 1; i < len; i++) {
            char c = s.charAt(i);
            int singleDig = toInt(s.charAt(i));
            int doubleDig = singleDig + 10 * toInt(s.charAt(i - 1));
            if (singleDig > 0) {
                dp[i + 1] += dp[i];
            }
            if (doubleDig >= 10 && doubleDig <= 26) {
                dp[i + 1] += dp[i - 1];
            }
        }
        return dp[s.length()];
    }
    
    private int toInt(char c) {
        return c - '0';
    }
}