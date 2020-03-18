class Solution {
    public List<String> findAndReplacePattern(String[] words, String pattern) {
        List<String> ret = new ArrayList<>();
        pattern = simplify(pattern);
        for (String word : words) {
            if (simplify(word).equals(pattern)) {
                ret.add(word);
            }
        }
        return ret;
    }
    
    private String simplify(String word) {
        char[] ret = new char[word.length()];
        char[] p = new char[26];
        
        char id = 'a';
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            if (p[c - 'a'] == 0) {
               p[c - 'a'] = id++; 
            }
            ret[i] = p[c - 'a'];
        }
        return new String(ret);
    }
}