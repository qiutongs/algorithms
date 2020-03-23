class Solution {
    public int expressiveWords(String S, String[] words) {
        int ret = 0;
        for (String word : words) {
            if (stretchy(S, word)) {
                ret++;
            }
        }
        return ret;
    }
    
    private boolean stretchy(String S, String word) {
        int i = 0, j = 0;
        while(i < S.length() && j < word.length()) {
            char cS = S.charAt(i);
            char cW = word.charAt(j);
            if (cS != cW) {
                return false;
            }
            int count1 = 0;
            while(i < S.length() && j < word.length() && S.charAt(i) == cS && word.charAt(j) == cW) {
                count1++;
                i++;
                j++;
            }
            int count2 = 0;
            while(i < S.length() && S.charAt(i) == cS) {
                count2++;
                i++;
            }
            if (count1 == 1 && count2 == 1) {
                return false;
            }
        }
        return i == S.length() && j == word.length();
    }
}