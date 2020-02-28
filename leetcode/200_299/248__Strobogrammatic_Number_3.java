class Solution {
    private static final String[] SINGLES = {"0", "1", "8"};
    private static final char[][] PAIRS = {{'0', '0'}, {'1', '1'}, {'6', '9'}, {'8', '8'}, {'9', '6'}};
    
    public int strobogrammaticInRange(String low, String high) {
        int[] ret = { 0 };
        dfs("", low, high, ret);
        for (String single : SINGLES) {
            dfs(single, low, high, ret);
        }
        return ret[0];
    }
    
    private void dfs(String cur, String low, String high, int[] ret) {
        if (greater(cur, high)) {
            return;
        } else if (greater(cur, low) || cur.equals(low)) {
            if (cur.charAt(0) != '0' || cur.length() == 1) {
                ret[0]++;
            }
        }
        
        for (char[] pair : PAIRS) {
            dfs(pair[0] + cur + pair[1], low, high, ret);
        }
    }
    
    private boolean greater(String n1, String n2) {
        return n1.length() > n2.length() || (n1.length() == n2.length() && n1.compareTo(n2) > 0);
    }
}