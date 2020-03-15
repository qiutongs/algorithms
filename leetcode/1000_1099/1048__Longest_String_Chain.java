// Graph (DAG): longest path
// Time: O(N*N*L) 69ms
class Solution {
    public int longestStrChain(String[] words) {
        HashMap<String, List<String>> graph = buildGraph(words);
        HashMap<String, Integer> memo = new HashMap<>();
        int ret = 0;
        for (String node : graph.keySet()) {
            ret = Math.max(ret, dfs(graph, node, memo));
        }
        return ret;
    }
    
    private int dfs(HashMap<String, List<String>> graph, String node, HashMap<String, Integer> memo) {
        if (memo.containsKey(node)) {
            return memo.get(node);
        }
        int ret = 0;
        for (String nb : graph.get(node)) {
            ret = Math.max(ret, dfs(graph, nb, memo));
        }
        memo.put(node, ret + 1);
        return ret + 1;
    }
    
    private HashMap<String, List<String>> buildGraph(String[] words) {
        HashMap<String, List<String>> ret = new HashMap<>();
        for (String word : words) {
            ret.put(word, new ArrayList<>());
        }
        
        for (int i = 0; i < words.length; i++) {
            for (int j = 0; j < words.length; j++) {
                if (isPre(words[i], words[j])) {
                    ret.get(words[i]).add(words[j]);
                }
            }
        }
        return ret;
    }
    
    private boolean isPre(String pre, String str) {
        if (str.length() != pre.length() + 1) {
            return false;
        }
        boolean mismatch = false;
        int i = 0, j = 0;
        while(i < pre.length() && j < str.length()) {
            if (pre.charAt(i) != str.charAt(j)) {
                if (mismatch) {
                    return false;
                } else {
                    mismatch = true;
                    j++;
                }
            } else {
                i++;
                j++;
            }
        }
        return true;
    }
}

// Graph (DAG): longest path
// Time: O(N*L*L) 51ms
class Solution {
    public int longestStrChain(String[] words) {
        HashMap<String, List<String>> graph = buildGraph(words);
        HashMap<String, Integer> memo = new HashMap<>();
        int ret = 0;
        for (String node : graph.keySet()) {
            ret = Math.max(ret, dfs(graph, node, memo));
        }
        return ret;
    }
    
    private int dfs(HashMap<String, List<String>> graph, String node, HashMap<String, Integer> memo) {
        if (memo.containsKey(node)) {
            return memo.get(node);
        }
        int ret = 0;
        for (String nb : graph.get(node)) {
            ret = Math.max(ret, dfs(graph, nb, memo));
        }
        memo.put(node, ret + 1);
        return ret + 1;
    }
    
    private HashMap<String, List<String>> buildGraph(String[] words) {
        HashSet<String> set = new HashSet<>();
        for (String word : words) {
            set.add(word);
        }
        HashMap<String, List<String>> ret = new HashMap<>();
        for (String word : words) {
            ret.put(word, new ArrayList<>());
        }
        StringBuilder sb = new StringBuilder();
        for (String word : words) {
            for (int i = 0; i < word.length(); i++) {
                sb.setLength(0);
                for (int j = 0; j < word.length(); j++) {
                    if (i != j) {
                        sb.append(word.charAt(j));
                    }
                }
                String candidate = sb.toString();
                if (set.contains(candidate)) {
                    ret.get(candidate).add(word);
                }
            }
        }
        return ret;
    }
}