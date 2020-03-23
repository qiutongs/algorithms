class Solution {
    public boolean isInterleave(String s1, String s2, String s3) {
        if (s1.length() + s2.length() != s3.length()) {
            return false;
        }
        return dfs(s1, 0, s2, 0, s3, new Boolean[s1.length() + 1][s2.length() + 2]);
    }
    
    private boolean dfs(String s1, int idx1, String s2, int idx2, String s3, Boolean[][] memo) {
        int idx3 = idx1 + idx2;
        if (idx3 == s3.length()) {
            return true;
        }
        if (memo[idx1][idx2] != null) {
            return memo[idx1][idx2];
        }
        if (idx1 < s1.length() && s1.charAt(idx1) == s3.charAt(idx3) && dfs(s1, idx1 + 1, s2, idx2, s3, memo)) {
            memo[idx1][idx2] = true;
            return true;
        }
        if (idx2 < s2.length() && s2.charAt(idx2) == s3.charAt(idx3) && dfs(s1, idx1, s2, idx2 + 1, s3, memo)) {
            memo[idx1][idx2] = true;
            return true;
        }
        memo[idx1][idx2] = false;
        return false;
    }
}