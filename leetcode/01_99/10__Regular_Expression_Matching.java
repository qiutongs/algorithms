// DFS
// Optimize: add 2D memo
// Time
// Space: O(M + N)
class Solution {
    public boolean isMatch(String s, String p) {
        return isMatch(s, 0, p, 0);
    }
    
    private boolean isMatch(String s, int sOffset, String p, int pOffset) {
        if (pOffset == p.length()) {
            return sOffset == s.length();
        }
        if (pOffset + 1 < p.length() && p.charAt(pOffset + 1) == '*') {
            char pre = p.charAt(pOffset);
            // Match zero times
            if (isMatch(s, sOffset, p, pOffset + 2)) {
                return true;
            }
            // Match 1+ times
            while(sOffset < s.length() && (s.charAt(sOffset) == pre || pre == '.')) {
                if (isMatch(s, sOffset + 1, p, pOffset + 2)) {
                    return true;
                }
                sOffset++;
            }
            return false;
        } else {
            if (sOffset < s.length() && (s.charAt(sOffset) == p.charAt(pOffset) || p.charAt(pOffset) == '.')) {
                return isMatch(s, sOffset + 1, p, pOffset + 1);
            } else {
                return false;
            }
        }
    }
}

class Solution {
    public boolean isMatch(String s, String p) {
        return isMatch(s, 0, p, 0, new Boolean[s.length() + 1][p.length()]);
    }
    
    private boolean isMatch(String s, int sOffset, String p, int pOffset, Boolean[][] memo) {
        if (pOffset == p.length()) {
            return sOffset == s.length();
        }
        if (memo[sOffset][pOffset] != null) {
            return memo[sOffset][pOffset];
        }
        if (pOffset + 1 < p.length() && p.charAt(pOffset + 1) == '*') {
            char pre = p.charAt(pOffset);
            // Match zero times
            if (isMatch(s, sOffset, p, pOffset + 2, memo)) {
                memo[sOffset][pOffset] = true;
                return true;
            }
            // Match 1+ times
            while(sOffset < s.length() && (s.charAt(sOffset) == pre || pre == '.')) {
                if (isMatch(s, sOffset + 1, p, pOffset + 2, memo)) {
                    memo[sOffset][pOffset] = true;
                    return true;
                }
                sOffset++;
            }
            memo[sOffset][pOffset] = false;
            return false;
        } else {
            if (sOffset < s.length() && (s.charAt(sOffset) == p.charAt(pOffset) || p.charAt(pOffset) == '.')) {
                memo[sOffset][pOffset] = isMatch(s, sOffset + 1, p, pOffset + 1, memo);
                return memo[sOffset][pOffset];
            } else {
                memo[sOffset][pOffset] = false;
                return false;
            }
        }
    }
}

// DP
class Solution {
    public boolean isMatch(String s, String p) {
        int sLen = s.length(), pLen = p.length();
        boolean[][] dp = new boolean[sLen + 1][pLen + 1];
        dp[0][0] = true;
        
        for (int i = 0; i < sLen + 1; i++) {
            for (int j = 1; j < pLen + 1; j++) {
                if (p.charAt(j - 1) == '*') {
                    int k = i;
                    while(k >= 0) {
                        if (dp[k][j - 1]) {
                            dp[i][j] = true;
                            break;
                        }
                        k--;
                    }
                } else {
                    dp[i][j] = i > 0 && (s.charAt(i - 1) == p.charAt(j - 1) || p.charAt(j - 1) == '?') && dp[i - 1][j - 1];
                }
            }
        }
        
        return dp[sLen][pLen];
    }
}


// DP: same iterative logic as DFS
// Time: O(MN)
// Space: O(MN)
class Solution {
    public boolean isMatch(String s, String p) {
        int sLen = s.length(), pLen = p.length();
        boolean[][] dp = new boolean[sLen + 1][pLen + 1];
        dp[0][0] = true;
        
        for (int i = 0; i < sLen + 1; i++) {
            for (int j = 1; j < pLen + 1; j++) {
                if (p.charAt(j - 1) == '*') {
                    char pre = p.charAt(j - 2);
                    if (dp[i][j - 2]) {
                        dp[i][j] = true;
                        continue;
                    }
                    int k = i;
                    while(k > 0 && (s.charAt(k - 1) == pre || pre == '.')) {
                        if (dp[k - 1][j - 2]) {
                            dp[i][j] = true;
                            break;
                        }
                        k--;
                    }
                } else {
                    dp[i][j] = i > 0 && (s.charAt(i - 1) == p.charAt(j - 1) || p.charAt(j - 1) == '.') && dp[i - 1][j - 1];
                }
            }
        }
        
        return dp[sLen][pLen];
    }
}