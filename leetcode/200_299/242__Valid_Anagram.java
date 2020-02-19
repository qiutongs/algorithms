// Counting sort
// Time: O(M + N)
// Space: O(M + N)
class Solution {
    public boolean isAnagram(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }
        return sort(s).equals(sort(t));
    }
    
    private String sort(String str) {
        int[] counts = new int[26];
        for (int i = 0; i < str.length(); i++) {
            counts[str.charAt(i) - 'a']++;
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < counts.length; i++) {
            while(counts[i] > 0) {
                sb.append((char)(i + 'a'));
                counts[i]--;
            }
        }
        return sb.toString();
    }
}