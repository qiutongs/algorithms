class Solution {
    public boolean wordBreak(String s, List<String> wordDict) {
        int len = s.length();
        
        // dp[i]: if first i characters of s can do wordBreak
        boolean[] dp = new boolean[len + 1];
        dp[0] = true;
        HashSet<String> wordSets = new HashSet<>(wordDict);

        for (int i = 0; i < len; i++) {
            for (int j = 0; j <= i; j++) {
                if (dp[j] && wordSets.contains(s.substring(j, i + 1))) {
                    dp[i + 1] = true;
                }
            }
        }
        
        return dp[len];
    }
}