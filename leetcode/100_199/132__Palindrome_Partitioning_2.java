// Preprocess + DP
class Solution {
    public int minCut(String s) {
        if (s == null || s.isEmpty()) {
            return 0;
        }
        int len = s.length();
        boolean[][] palinTable = buildPalindrome(s);
        int[] dp = new int[len];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;
        
        for (int i = 1; i < len; i++) {
            for (int j = 0; j <= i; j++) {
                if (palinTable[j][i]) {
                    dp[i] = Math.min(dp[i], j == 0 ? 0 : dp[j - 1] + 1);
                }
            }
        }
        return dp[len - 1];
    }
    
    private boolean[][] buildPalindrome(String s) {
        int len = s.length();
        boolean[][] ret = new boolean[len][len];
        for (int inter = 1; inter <= len; inter++) {
            for (int i = 0; i + inter <= len; i++) {
                if (inter == 1) {
                   ret[i][i + inter - 1] = true;
                } else if (inter == 2) {
                   ret[i][i + inter - 1] = s.charAt(i) == s.charAt(i + inter - 1) ? true: false;
                } else {
                    ret[i][i + inter - 1] = ret[i + 1][i + inter - 2] && s.charAt(i) == s.charAt(i + inter - 1) ? true : false;
                }
            }
        }
        return ret;
    }
}

// Raw DP: O(n^3)
class Solution {
    public int minCut(String s) {
        if (s == null || s.isEmpty()) {
            return 0;
        }
        int len = s.length();
        int[] dp = new int[len];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;
        
        for (int i = 1; i < len; i++) {
            for (int j = 0; j <= i; j++) {
                if (isPalindrome(s, j, i)) {
                    dp[i] = Math.min(dp[i], j == 0 ? 0 : dp[j - 1] + 1);
                }
            }
        }
        return dp[len - 1];
    }
    
    private boolean isPalindrome(String s, int i, int j) {
        while(i < j) {
            if (s.charAt(i) != s.charAt(j)) {
                return false;
            }
            i++;
            j--;
        }
        return true;
    }
}