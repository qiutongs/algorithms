class WordDictionary {

    private final Node root; 
    
    /** Initialize your data structure here. */
    public WordDictionary() {
        this.root = new Node();
        this.root.isKey = true;
    }
    
    /** Adds a word into the data structure. */
    public void addWord(String word) {
        Node cur = root;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            Node child = cur.getChild(c);
            if (child == null) {
                cur.addChild(c);
            }
            cur = cur.getChild(c);
        }
        cur.isKey = true;
    }
    
    /** Returns if the word is in the data structure. A word could contain the dot character '.' to represent any one letter. */
    public boolean search(String word) {
        return dfs(word, 0, root);
    }
    
    private boolean dfs(String word, int offset, Node node) {
        if (node == null) {
            return false;
        }
        if (offset == word.length()) {
            return node.isKey;
        }
        char c = word.charAt(offset);
        if (c == '.') {
            for (char i = 'a'; i <= 'z'; i++) {
                if (dfs(word, offset + 1, node.getChild(i))) {
                    return true;
                }
            }
            return false;
        } else {
            return dfs(word, offset + 1, node.getChild(c));
        }
    }
    
    private class Node {
        Node[] children = new Node[26];
        boolean isKey = false;
        
        Node getChild(char c) {
            return children[(int)(c - 'a')];
        }
        
        void addChild(char c) {
            children[(int)(c - 'a')] = new Node();
        }
    }
}

/**
 * Your WordDictionary object will be instantiated and called as such:
 * WordDictionary obj = new WordDictionary();
 * obj.addWord(word);
 * boolean param_2 = obj.search(word);
 */