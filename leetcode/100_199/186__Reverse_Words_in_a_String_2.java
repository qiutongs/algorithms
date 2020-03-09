class Solution {
    public void reverseWords(char[] s) {
        reverse(s, 0, s.length - 1);
        int l = 0;
        while(l < s.length) {
            int r = l;
            while(r < s.length && s[r] != ' ') {
                r++;
            }
            reverse(s, l, r - 1);
            l = r + 1;
        }
    }
    
    private void reverse(char[] s, int l, int r) {
        while(l < r) {
            char tmp = s[l];
            s[l] = s[r];
            s[r] = tmp;
            l++;
            r--;
        }
    }
}