class Solution {
    public void reverseString(char[] s) {
        reverseHelper(s, 0, s.length - 1);
    }
    
    private void reverseHelper(char[] s, int l, int r) {
        if (l >= r) {
            return;
        }
        
        char tmp = s[l];
        s[l] = s[r];
        s[r] = tmp;
        
        reverseHelper(s, l + 1, r - 1);
    }
}