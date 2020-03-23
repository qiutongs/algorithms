// Raw DFS
// Time Limit Exceeded: 
// "bbbbbbbabbaabbabbbbaaabbabbabaaabbababbbabbbabaaabaab"
// "b*b*ab**ba*b**b***bba"
class Solution {
    public boolean isMatch(String s, String p) {
        return isMatch(s, 0, p, 0);
    }
    
    private boolean isMatch(String s, int sOffset, String p, int pOffset) {
        if (pOffset == p.length()) {
            return sOffset == s.length();
        }
        if (p.charAt(pOffset) == '*') {
            while(sOffset <= s.length()) {
                if (isMatch(s, sOffset, p, pOffset + 1)) {
                    return true;
                }
                sOffset++;
            }
            return false;
        } else {
            if (sOffset < s.length() && (s.charAt(sOffset) == p.charAt(pOffset) || p.charAt(pOffset) == '?')) {
                return isMatch(s, sOffset + 1, p, pOffset + 1);
            } else {
                return false;
            }
        }
    }
}

// DFS + memo
class Solution {
    private Boolean[][] memo;
    
    public boolean isMatch(String s, String p) {
        memo = new Boolean[s.length() + 1][p.length() + 1];
        return isMatch(s, 0, p, 0);
    }
    
    private boolean isMatch(String s, int sOffset, String p, int pOffset) {
        if (pOffset == p.length()) {
            return sOffset == s.length();
        }
        if (memo[sOffset][pOffset] != null) {
            return memo[sOffset][pOffset];
        }
        boolean ret = false;
        if (p.charAt(pOffset) == '*') {
            int i = 0;
            while(sOffset + i<= s.length()) {
                if (isMatch(s, sOffset + i, p, pOffset + 1)) {
                    ret = true;
                    break;
                }
                i++;
            }
        } else {
            if (sOffset < s.length() && (s.charAt(sOffset) == p.charAt(pOffset) || p.charAt(pOffset) == '?')) {
                ret = isMatch(s, sOffset + 1, p, pOffset + 1);
            } else {
                ret = false;
            }
        }
        memo[sOffset][pOffset] = ret;
        return ret;
    }
}