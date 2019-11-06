class Solution {
    public boolean isAlienSorted(String[] words, String order) {
        if (words == null || words.length == 0) {
            return true;
        }
        
        int[] ord = new int[26];
        for (int i = 0; i < order.length(); i++) {
            ord[toIndex(order.charAt(i))] = i;
        }
        
        for (int i = 1; i < words.length; i++) {
            if (inOrder(words[i - 1], words[i], ord) == false) {
                return false;
            }
        }
        return true;
    }
    
    private boolean inOrder(String s1, String s2, int[] ord) {
        for (int i = 0; i < Math.min(s1.length(), s2.length()); i++) {
            int i1 = ord[toIndex(s1.charAt(i))];
            int i2 = ord[toIndex(s2.charAt(i))];
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