// Raw DFS
// Time Limit Exceeded
class Solution {
    public List<List<String>> wordSquares(String[] words) {
        if (words == null || words.length == 0) {
            return Collections.emptyList();
        }
        List<List<String>> ret = new ArrayList<>();
        dfs(words, new ArrayList<>(), ret);
        return ret;
    }
    
    private void dfs(String[] words, List<String> curList, List<List<String>> ret) {
        if (curList.size() == words[0].length()) {
            ret.add(new ArrayList<>(curList));
            return;
        }
        for (int i = 0; i < words.length; i++) {
            if (valid(curList, words[i])) {
                curList.add(words[i]);
                dfs(words, curList, ret);
                curList.remove(curList.size() - 1);
            }
        }
    }
    
    private boolean valid(List<String> curList, String word) {
        int sIdx = curList.size();
        for (int i = 0; i < curList.size(); i++) {
            if (word.charAt(i) != curList.get(i).charAt(sIdx)) {
                return false;
            }
        }
        return true;
    }
}

// Trie: better prefix match instead of scanning the entire input array
class Solution {
    public List<List<String>> wordSquares(String[] words) {
        TrieNode root = new TrieNode();
        for (String word : words) {
            insert(root, word);
        }
        
        List<List<String>> ret = new ArrayList<>();
        dfs(words, root, new ArrayList<>(), ret);
        return ret;
    }
    
    private void dfs(String[] words, TrieNode root, List<String> curList, List<List<String>> ret) {
        if (curList.size() == words[0].length()) {
            ret.add(new ArrayList<>(curList));
            return;
        }
        String prefix = toPrefix(curList);
        TrieNode node = search(root, prefix);
        List<String> matchedWords = new ArrayList<>();
        dfs(node, matchedWords);
        
        for (String word : matchedWords) {
            curList.add(word);
            dfs(words, root, curList, ret);
            curList.remove(curList.size() - 1);
        }
    }
    
    private String toPrefix(List<String> curList) {
        int idx = curList.size();
        StringBuilder sb = new StringBuilder();
        for (String word : curList) {
            sb.append(word.charAt(idx));
        }
        return sb.toString();
    }
    
    private TrieNode search(TrieNode root, String prefix) {
        TrieNode cur = root;
        for (int i = 0; i < prefix.length(); i++) {
            char c = prefix.charAt(i);
            cur = cur.get(c);
            if (cur == null) {
                return null;
            }
        }
        return cur;
    }
    
    private void dfs(TrieNode node, List<String> ret) {
        if (node == null) {
            return;
        }
        if (node.word != null) {
            ret.add(node.word);
        }
        for (TrieNode child : node.childs) {
            if (child != null) {
                dfs(child, ret);
            }
        }
    }
    
    private void insert(TrieNode root, String word) {
        TrieNode cur = root;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            cur.addIfAbsent(c);
            cur = cur.get(c);
        }
        cur.word = word;
    }
    
    private class TrieNode {
        TrieNode[] childs = new TrieNode[26];
        String word;
        
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