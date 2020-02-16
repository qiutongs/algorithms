// DP - optimal up to index i
// Time: O(N^2)
// Space: O(N)
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

// DFS + memo
// Time: O(N^2)
// Space: O(N)
class Solution {
    private Boolean[] memo;
    
    public boolean wordBreak(String s, List<String> wordDict) {
        if (s == null || s.isEmpty() || wordDict == null || wordDict.isEmpty()) {
            return false;
        }
        List<String> ret = new ArrayList<>();
        memo = new Boolean[s.length()];
        HashSet<String> wordsSet = new HashSet<>(wordDict);
        return dfs(s, 0, wordsSet);
    }
    
    private boolean dfs(String word, int offset, HashSet<String> words) {
        if (offset == word.length()) {
            return true;
        }
        if (memo[offset] != null) {
            return memo[offset];
        }
        for (int i = offset; i < word.length(); i++) {
            String subword = word.substring(offset, i + 1);
            if (words.contains(subword)) {
                if (dfs(word, i + 1, words)) {
                    memo[offset] = true;
                    return true;
                }
            }
        }
        memo[offset] = false;
        return false;
    }
}

// Raw DFS
// Time: O(2^n)
// Space: O(n)
// Time Limit Exceeded
class Solution {
    public boolean wordBreak(String s, List<String> wordDict) {
        if (s == null || s.isEmpty() || wordDict == null || wordDict.isEmpty()) {
            return false;
        }
        List<String> ret = new ArrayList<>();
        HashSet<String> wordsSet = new HashSet<>(wordDict);
        return dfs(s, 0, wordsSet);
    }
    
    private boolean dfs(String word, int offset, HashSet<String> words) {
        if (offset == word.length()) {
            return true;
        }
        for (int i = offset; i < word.length(); i++) {
            String subword = word.substring(offset, i + 1);
            if (words.contains(subword)) {
                if (dfs(word, i + 1, words)) {
                    return true;
                }
            }
        }
        return false;
    }
}