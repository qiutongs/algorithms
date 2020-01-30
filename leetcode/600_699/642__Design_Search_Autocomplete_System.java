// Raw Trie + DFS
class AutocompleteSystem {
    private static final int HOT_COUNT = 3;
    private final TrieNode root = new TrieNode();
    private TrieNode cur = root;
    private StringBuilder sb = new StringBuilder();
    
    public AutocompleteSystem(String[] sentences, int[] times) {
        if (sentences == null || sentences.length == 0) {
            return;
        }
        for (int i = 0; i < sentences.length; i++) {
            insert(root, sentences[i], times[i]);
        } 
    }
    
    public List<String> input(char c) {
        if (c == '#') {
            insert(root, sb.toString(), 1);
            cur = root;
            sb.setLength(0);
            return Collections.emptyList();
        }
        
        sb.append(c);
        
        if (cur == null) {
            return Collections.emptyList();
        } else if (cur.get(c) == null) {
            cur = null;
            return Collections.emptyList();
        } else {
            cur = cur.get(c);
            
            PriorityQueue<TrieNode> pq = new PriorityQueue<>(
                 (n1, n2) -> n1.freq == n2.freq ? n2.key.compareTo(n1.key) : n1.freq - n2.freq );
            
            dfs(cur, pq);

            return output(pq);
        }
    }
    
    private void dfs(TrieNode node, PriorityQueue<TrieNode> pq) {
        if (node == null) {
            return;
        }
        if (node.key != null) {
            pq.offer(node);
            if (pq.size() > HOT_COUNT) {
                pq.poll();
            }
        }
        for (TrieNode child : node.childs) {
            dfs(child, pq);
        }
    }
    
    private List<String> output(PriorityQueue<TrieNode> pq) {
        List<String> ret = new ArrayList<>(HOT_COUNT);
        while(pq.isEmpty() == false) {
            ret.add(pq.poll().key);
        }
        Collections.reverse(ret);
        return ret;
    }
    
    private void insert(TrieNode root, String sentence, int freq) {
        TrieNode cur = root;
        for (int i = 0; i < sentence.length(); i++) {
            char c = sentence.charAt(i);
            cur.addIfAbsent(c);
            cur = cur.get(c);
        }
        cur.key = sentence;
        cur.freq += freq;
    }
    
    private class TrieNode {
        TrieNode[] childs = new TrieNode[27];
        String key = null;
        int freq = 0;
        
        void addIfAbsent(char c) {
            if (c == ' ') {
                if (childs[26] == null) {
                    childs[26] = new TrieNode();
                }
                return;
            }
            if (childs[(int)(c - 'a')] == null) {
                childs[(int)(c - 'a')] = new TrieNode();
            }
        }
        
        TrieNode get(char c) {
            if (c == ' ') {
                return childs[26];
            }
            return childs[(int)(c - 'a')];
        }
    }
}

/**
 * Your AutocompleteSystem object will be instantiated and called as such:
 * AutocompleteSystem obj = new AutocompleteSystem(sentences, times);
 * List<String> param_1 = obj.input(c);
 */