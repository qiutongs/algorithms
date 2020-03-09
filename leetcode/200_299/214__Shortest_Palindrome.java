// Brute-force: start from middle
// Time: O(N ^ 2), Time Limit Exceeded
// Space: O(1)
class Solution {
    public String shortestPalindrome(String s) {
        if (s.isEmpty()) {
            return s;
        }
        String ret = null;
        for (int i = (s.length() - 1) / 2; i >= 0; i--) {
            String candidate1 = expandToPalin(s, i, i);
            if (candidate1 != null) {
                ret = ret == null || ret.length() > candidate1.length() ? candidate1 : ret;
            }
            String candidate2 = expandToPalin(s, i, i + 1);
            if (candidate2 != null) {
                ret = ret == null || ret.length() > candidate2.length() ? candidate2 : ret;
            }
            if (ret != null) {
                break;
            }
        }
        return ret;
    }
    
    private String expandToPalin(String s, int l, int r) {
        while(l >= 0 && r < s.length()) {
            if (s.charAt(l) != s.charAt(r)) {
                return null;
            }
            l--;
            r++;
        }
        if (l >= 0) {
            return null;
        }
        String ret = s;
        while(r < s.length()) {
            ret = s.charAt(r) + ret;
            r++;
        }
        return ret;
    }
}