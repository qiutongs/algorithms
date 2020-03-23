class Solution {
    private int MOD = (int)Math.pow(10, 9) + 7;
    
    public int numDecodings(String s) {
        return (int)dfs(s, 0, new Long[s.length()]);
    }
    
    private long dfs(String s, int index, Long[] memo) {
        if (index >= s.length()) {
            return 1;
        }
        if (memo[index] != null) {
            return memo[index];
        }
        long ret = 0;
        ret += singleWays(s, index) * dfs(s, index + 1, memo);
        ret += doubleWays(s, index) * dfs(s, index + 2, memo);
        ret %= MOD;
        memo[index] = ret;
        return ret;
    }
    
    private int singleWays(String s, int index) {
        if (s.charAt(index) == '*') {
            return 9;
        } else {
            return s.charAt(index) == '0' ? 0 : 1;
        }
    }
    
    private int doubleWays(String s, int index) {
        if (index == s.length() - 1 || s.charAt(index) == '0') {
            return 0;
        }
        char c1 = s.charAt(index), c2 = s.charAt(index + 1);
        if (c1 == '*' && c2 == '*') {
            return 15;
        } else if (c1 == '*' && c2 != '*') {
            return c2 <= '6' ? 2 : 1;
        } else if (c1 != '*' && c2 == '*') {
            return c1 == '1' ? 9 
                 : c1 == '2' ? 6 
                 : 0;
        } else {
            int num = 10 * (c1 - '0') + (c2 - '0');
            return num >= 10 && num <= 26 ? 1 : 0;
        }
    }
}