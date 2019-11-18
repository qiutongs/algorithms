class Trie {
    private final Node root; 
    
    /** Initialize your data structure here. */
    public Trie() {
        this.root = new Node();
        this.root.isKey = true;
    }
    
    /** Inserts a word into the trie. */
    public void insert(String word) {
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
    
    /** Returns if the word is in the trie. */
    public boolean search(String word) {
        Node node = getNode(word);
        return node == null ? false: node.isKey;
    }
    
    /** Returns if there is any word in the trie that starts with the given prefix. */
    public boolean startsWith(String prefix) {
        Node node = getNode(prefix);
        return node != null;
    }
    
    private Node getNode(String str) {
        Node cur = root;
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            Node child = cur.getChild(c);
            if (child == null) {
                return null;
            } else {
                cur = child;
            }
        }
        return cur;
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
 * Your Trie object will be instantiated and called as such:
 * Trie obj = new Trie();
 * obj.insert(word);
 * boolean param_2 = obj.search(word);
 * boolean param_3 = obj.startsWith(prefix);
 */