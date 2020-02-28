class Solution {
    public int expressiveWords(String S, String[] words) {
        List<Pair> pairs = new ArrayList<>();
        int count = 1;
        for (int i = 1; i < S.length(); i++) {
            if (S.charAt(i) != S.charAt(i - 1)) {
                pairs.add(new Pair(S.charAt(i - 1), count));
                count = 1;
            } else {
                count++;
            }
        }
        pairs.add(new Pair(S.charAt(S.length() - 1), count));
        
        int ret = 0;
        for (String word : words) {
            if (stretchy(pairs, word)) {
                ret++;
            }
        }
        return ret;
    }
    
    private boolean stretchy(List<Pair> pairs, String word) {
        int i = 0;
        for (Pair pair : pairs) {
            if (i == word.length() || pair.c != word.charAt(i)) {
                return false;
            }
            int count = 0;
            while(i < word.length() && pair.c == word.charAt(i)) {
                count++;
                i++;
            }
            if ((count < pair.n && pair.n < 3) || count > pair.n) {
                return false;
            }
        }
        return true;
    }
    
    private class Pair {
        char c;
        int n;
        Pair(char c, int n) {
            this.c = c;
            this.n = n;
        }
    }
}