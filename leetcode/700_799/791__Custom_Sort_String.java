class Solution {
    public String customSortString(String S, String T) {
        int[] cFreqTable = new int[26];
        for (int i = 0; i < T.length(); i++) {
            cFreqTable[T.charAt(i) - 'a']++;
        }
        
        char[] ret = new char[T.length()];
        int index = 0;
        for (int i = 0; i < S.length(); i++) {
            while(cFreqTable[S.charAt(i) - 'a'] > 0) {
                ret[index++] = S.charAt(i);
                cFreqTable[S.charAt(i) - 'a']--;
            }
        }
        
        for (int i = 0; i < 26; i++) {
            while(cFreqTable[i] > 0) {
                ret[index++] = (char)(i + 'a');
                cFreqTable[i]--;
            }
        }
        return new String(ret);
    }
}