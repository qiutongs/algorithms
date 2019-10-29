class Solution {
    public boolean isPalindrome(String s) {
        if (s == null || s.length() == 0) {
            return true;
        }
        
        s = s.toLowerCase();
        
        int l = 0, r = s.length() - 1;
        while(l < r) {
            char lc = s.charAt(l);
            char rc = s.charAt(r);
            
            if (isAlphanumeric(lc) == false) {
                l++;
            } else if (isAlphanumeric(rc) == false) {
                r--;
            } else {
                if (lc != rc) {
                    return false;
                }
                l++;
                r--;
            }
        }
        return true;
    }
    
    private boolean isAlphanumeric(char c) {
        return Character.isDigit(c) || Character.isAlphabetic(c);
    }
}