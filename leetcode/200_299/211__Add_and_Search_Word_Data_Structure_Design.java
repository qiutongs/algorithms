class WordDictionary {

    private final TrieNode root = new TrieNode(); 
    
    /** Initialize your data structure here. */
    public WordDictionary() {
    }
    
    /** Adds a word into the data structure. */
    public void addWord(String word) {
        TrieNode cur = root;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            cur.addIfAbsent(c);
            cur = cur.get(c);
        }
        cur.isKey = true;
    }
    
    /** Returns if the word is in the data structure. A word could contain the dot character '.' to represent any one letter. */
    public boolean search(String word) {
        return dfs(root, word, 0);
    }
    
    private boolean dfs(TrieNode node, String word, int offset) {
        if (node == null) {
            return false;
        }
        if (offset == word.length()) {
            return node.isKey;
        }
        char c = word.charAt(offset);
        if (c == '.') {
            for (char i = 'a'; i <= 'z'; i++) {
                if (dfs(node.get(i), word, offset + 1)) {
                    return true;
                }
            }
            return false;
        } else {
            return dfs(node.get(c), word, offset + 1);
        }
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

/**
 * Your WordDictionary object will be instantiated and called as such:
 * WordDictionary obj = new WordDictionary();
 * obj.addWord(word);
 * boolean param_2 = obj.search(word);
 */