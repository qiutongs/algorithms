class Solution {
    public List<List<Integer>> palindromePairs(String[] words) {
        TrieNode root = new TrieNode();
        TrieNode reverseRoot = new TrieNode();
        for (int i = 0; i < words.length; i++) {
            insert(root, words[i], i);
            insert(reverseRoot, reverse(words[i]), i);
        }
        
        HashSet<List<Integer>> ret = new HashSet<>();
        for (int i = 0; i < words.length; i++) {
            TrieNode node1 = search(reverseRoot, words[i]);
            if (node1 != null) {
                List<Integer> tmp = new ArrayList<>();
                dfs(node1, new ArrayList<>(), tmp);
                for (Integer idx : tmp) {
                    if (idx != i) {
                        ret.add(Arrays.asList(i, idx));
                    }
                }
            }
            
            TrieNode node2 = search(root, reverse(words[i]));
            if (node2 != null) {
                List<Integer> tmp = new ArrayList<>();
                dfs(node2, new ArrayList<>(), tmp);
                for (Integer idx : tmp) {
                    if (idx != i) {
                        ret.add(Arrays.asList(idx, i));
                    }
                }
            }
        }
        return new ArrayList<>(ret);
    }
    
    private void dfs(TrieNode node, List<Character> curPath, List<Integer> ret) {
        if (node.idx != null && isPalin(curPath)) {
            ret.add(node.idx);
        }
        for (char c = 'a'; c <= 'z'; c++) {
            if (node.get(c) != null) {
                curPath.add(c);
                dfs(node.get(c), curPath, ret);
                curPath.remove(curPath.size() - 1);
            }
        }
    }
    
    private boolean isPalin(List<Character> curPath) {
        int i = 0, j = curPath.size() - 1;
        while(i < j) {
            if (curPath.get(i).equals(curPath.get(j)) == false) {
                return false;
            }
            i++;
            j--;
        }
        return true;
    }
    
    private TrieNode search(TrieNode root, String word) {
        TrieNode cur = root;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            cur = cur.get(c);
            if (cur == null) {
                return null;
            }
        }
        return cur;
    }
    
    private void insert(TrieNode root, String word, int index) {
        TrieNode cur = root;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            cur.addIfAbsent(c);
            cur = cur.get(c);
        }
        cur.idx = index;
    }
    
    private String reverse(String word) {
        return new StringBuilder().append(word).reverse().toString();
    }
    
    private class TrieNode {
        TrieNode[] childs = new TrieNode[26];
        Integer idx = null;
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