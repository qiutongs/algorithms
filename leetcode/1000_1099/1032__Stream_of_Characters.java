// Time: constructor - O(NL), query - O(L)
// Space: O(NL)
class StreamChecker {
    private TrieNode root = new TrieNode();
    private int maxLen = 0;
    private Deque<Character> fifo = new LinkedList<>();
    
    public StreamChecker(String[] words) {
        for (String word : words) {
            maxLen = Math.max(maxLen, word.length());
            insert(reverse(word));
        }
    }
    
    public boolean query(char letter) {
        fifo.offerFirst(letter);
        if (fifo.size() > maxLen) {
            fifo.pollLast();
        }
        return search();
    }
    
    private boolean search() {
        TrieNode cur = root;
        for (Character c : fifo) {
            TrieNode child = cur.get(c);
            if (child == null) {
                return false;
            } else if (child.isKey) {
                return true;
            }
            cur = cur.get(c);
        }
        return cur.isKey;
    }
    
    private String reverse(String word) {
        return new StringBuilder().append(word).reverse().toString();
    }
    
    private void insert(String word) {
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
        void addIfAbsent(char c) {
            if (childs[c - 'a'] == null) {
                childs[c - 'a'] = new TrieNode();
            }
        }
        TrieNode get(char c) {
            return childs[c - 'a'];
        }
    }
}

/**
 * Your StreamChecker object will be instantiated and called as such:
 * StreamChecker obj = new StreamChecker(words);
 * boolean param_1 = obj.query(letter);
 */