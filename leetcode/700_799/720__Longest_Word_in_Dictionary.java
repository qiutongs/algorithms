class Solution {
    public String longestWord(String[] words) {
        if (words == null || words.length == 0) {
            return "";
        }
        TrieNode root = new TrieNode();
        root.isKey = true;
        String ret = "";
        for (String word : words) {
            insert(root, word);
        }
        for (String word : words) {
            if (valid(root, word)) {
                if (word.length() > ret.length() 
                || (word.length() == ret.length() && word.compareTo(ret) < 0)) {
                    ret = word;
                }
            }
        }
        return ret;
    }

    private boolean valid(TrieNode root, String word) {
        TrieNode cur = root;
        for (int i = 0; i < word.length(); i++) {
            if (cur.isKey == false) {
                return false;
            }
            cur = cur.get(word.charAt(i));
        }
        return true;
    }
    
    private void insert(TrieNode root, String word) {
        TrieNode cur = root;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            cur.addIfAbsent(c);
            cur = cur.get(c);
        }
        cur.isKey = true;
    }
    
    private class TrieNode {
        TrieNode[] childs = new TrieNode[26];
        boolean isKey = false;
        TrieNode get(char c) {
            return childs[(int)(c - 'a')];
        }
        void addIfAbsent(char c) {
            if (childs[(int)(c - 'a')] == null) {
                childs[(int)(c - 'a')] = new TrieNode();
            }
        }
    }
}