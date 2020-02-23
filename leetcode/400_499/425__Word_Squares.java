class Solution {
    public List<List<String>> wordSquares(String[] words) {
        TrieNode root = new TrieNode();
        for (int i = 0; i < words.length; i++) {
            insert(root, words[i], i);
        }
        
        List<List<String>> ret = new ArrayList<>();
        dfs(words, root, new ArrayList<>(), ret);
        return ret;
    }
    
    private void dfs(String[] words, TrieNode root, List<Integer> curList, List<List<String>> ret) {
        if (curList.size() == words[0].length()) {
            List<String> ans = new ArrayList<>(curList.size());
            for (Integer index : curList) {
                ans.add(words[index]);
            }
            ret.add(ans);
            return;
        }
        for (Integer index : search(words, root, curList)) {
            curList.add(index);
            dfs(words, root, curList, ret);
            curList.remove(curList.size() - 1);
        }
    }
    
    private List<Integer> search(String[] words, TrieNode root, List<Integer> curList) {
        List<Integer> ret = new ArrayList<>();
        TrieNode cur = root;
        int offset = curList.size();
        for (Integer index : curList) {
            cur = cur.get(words[index].charAt(offset));
            if (cur == null) {
                break;
            }
        }
        dfs(cur, ret);
        return ret;
    }
    
    private void dfs(TrieNode node, List<Integer> ret) {
        if (node == null) {
            return;
        }
        if (node.isKey) {
            ret.add(node.index);
        }
        for (TrieNode child : node.childs) {
            if (child != null) {
                dfs(child, ret);
            }
        }
    }
    
    private void insert(TrieNode root, String word, int index) {
        TrieNode cur = root;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            cur.addIfAbsent(c);
            cur = cur.get(c);
        }
        cur.isKey = true;
        cur.index = index;
    }
    
    private class TrieNode {
        TrieNode[] childs = new TrieNode[26];
        boolean isKey = false;
        int index = -1;
        
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