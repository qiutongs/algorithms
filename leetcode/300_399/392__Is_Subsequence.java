// Naive two pointers (same direction)
// Time: O(N)
// Space: O(1)
class Solution {
    public boolean isSubsequence(String s, String t) {
        if (s.length() > t.length()) {
            return false;
        }
        if (s.isEmpty()) {
            return true;
        }
        int j = 0;
        for (int i = 0; i < t.length(); i++) {
            if (t.charAt(i) == s.charAt(j)) {
                j++;
                if (j == s.length()) {
                    return true;
                }
            }
        }
        return false;
    }
}

// DP Optimal Up To (i, j)
// Time: O(NM)
// Space: O(NM)
class Solution {
    public boolean isSubsequence(String s, String t) {
        if (s.length() > t.length()) {
            return false;
        }
        if (s.isEmpty()) {
            return true;
        }
        boolean[][] dp = new boolean[s.length() + 1][t.length() + 1];
        for (int i = 0; i < t.length() + 1; i++) {
            dp[0][i] = true;
        }
        for (int i = 1; i < s.length() + 1; i++) {
            for (int j = 1; j < t.length() + 1; j++)
            if (t.charAt(j - 1) == s.charAt(i - 1) ) {
                dp[i][j] = dp[i - 1][j - 1];
            } else {
                dp[i][j] = dp[i][j - 1];
            }
        }
        return dp[s.length()][t.length()];
    }
}

/*
Follow up: pre-process t with some data structure fast for query.

- The time is O(|s| + |t|) for each one, with above approach. So it will be O(k(s + t))

1. hashtable: 
- character -> sorted index list in t
- for each one, the time is O(|s| * log(|t|)), total is O(k(s * log(t)))
*/