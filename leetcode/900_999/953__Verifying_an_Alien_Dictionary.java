class Solution {
    public boolean isAlienSorted(String[] words, String order) {
        if (words == null || words.length == 0) {
            return true;
        }
        
        int[] cIdxTable = new int[26];
        for (int i = 0; i < order.length(); i++) {
            cIdxTable[toIndex(order.charAt(i))] = i;
        }
        for (int i = 1; i < words.length; i++) {
            if (inorder(words[i - 1], words[i], cIdxTable) == false) {
                return false;
            }
        }
        return true;
    }
    
    private boolean inorder(String s1, String s2, int[] cIdxTable) {
        for (int i = 0; i < Math.min(s1.length(), s2.length()); i++) {
            int i1 = cIdxTable[toIndex(s1.charAt(i))];
            int i2 = cIdxTable[toIndex(s2.charAt(i))];
            if (i1 != i2) {
                return i1 < i2;
            }
        }
        return s1.length() <= s2.length();
    }
    
    private int toIndex(char c) {
        return (int)(c - 'a');
    }
}