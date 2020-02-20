/*
Given two strings s and t, determine if they are both one edit distance apart.

Note: 

There are 3 possiblities to satisify one edit distance apart:

Insert a character into s to get t
Delete a character from s to get t
Replace a character of s to get t
*/
// Search
// Time: O(M + N)
// Space: O(1)
class Solution {
    public boolean isOneEditDistance(String s, String t) {
        int index = 0;
        while(index < s.length() && index < t.length() && s.charAt(index) == t.charAt(index)) {
            index++;
        }
        if (index == s.length() || index == t.length()) {
            return Math.abs(s.length() - t.length()) == 1;
        }
        return isSame(s, index + 1, t, index) 
            || isSame(s, index, t, index + 1) 
            || isSame(s, index + 1, t, index + 1);
    }
    
    private boolean isSame(String s, int sIdx, String t, int tIdx) {
        if (s.length() - sIdx != t.length() - tIdx) {
            return false;
        }
        for (; sIdx < s.length() && tIdx < t.length(); sIdx++, tIdx++) {
            if (s.charAt(sIdx) != t.charAt(tIdx)) {
                return false;
            }
        }
        return true;
    }
}