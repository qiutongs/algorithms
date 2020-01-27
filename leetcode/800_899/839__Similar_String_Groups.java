// O(n*n*l)
class Solution {
    public int numSimilarGroups(String[] A) {
        if (A == null || A.length == 0) {
            return 0;
        }
        UF uf = new UF(A);
        for (int i = 0; i < A.length; i++) {
            for (int j = i + 1; j < A.length; j++) {
                if (isSimilar(A[i], A[j])) {
                    uf.union(A[i], A[j]);
                }
            }
        }
        return uf.count;
    }
    
    private boolean isSimilar(String a, String b) {
        int diff = 0;
        for (int i = 0; i < a.length(); i++) {
            if (a.charAt(i) != b.charAt(i)) {
                diff++;
                if (diff > 2) {
                    return false;
                }
            }
        }
        return true;
    }
    
    private class UF {
        HashMap<String, String> parents = new HashMap<>();
        int count;
        
        UF(String[] nodes) {
            for (String node : nodes) {
                parents.put(node, node);
            }
            count = parents.size();
        }
        
        void union(String p, String q) {
            String rootP = find(p);
            String rootQ = find(q);
            if (rootP.equals(rootQ) == false) {
                parents.put(rootP, rootQ);
                count--;
            }
        }
        
        String find(String x) {
            while(parents.get(x).equals(x) == false) {
                parents.put(x, parents.get(parents.get(x)));
                x = parents.get(x);
            }
            return x;
        }
    }
}

// Time Limit Exceeded
// O(n*l*l + n*n)
class Solution {
    public int numSimilarGroups(String[] A) {
        if (A == null || A.length == 0) {
            return 0;
        }
        Map<String, List<String>> graph = buildGraph(A);
        int ret = 0;
        Set<String> visited = new HashSet<>();
        for (String vertex : graph.keySet()) {
            if (visited.contains(vertex) == false) {
                dfs(graph, vertex, visited);
                ret++;
            }
        }
        return ret;
    }
    
    private void dfs(Map<String, List<String>> graph, String vertex, Set<String> visited) {
        visited.add(vertex);
        for (String neighbor : graph.get(vertex)) {
            if (visited.contains(neighbor) == false) {
                dfs(graph, neighbor, visited);
            }
        }
    }
    
    private Map<String, List<String>> buildGraph(String[] A) {
        Map<String, List<String>> ret = new HashMap<>();
        for (String vertex : A) {
            ret.put(vertex, new ArrayList<>());
        }
        for (String vertex : A) {
            char[] chars = vertex.toCharArray();
            for (int i = 0; i < chars.length; i++) {
                for (int j = i + 1; j < chars.length; j++) {
                    swap(chars, i, j);
                    String candi = new String(chars);
                    if (ret.containsKey(candi)) {
                        ret.get(vertex).add(candi);
                    }
                    swap(chars, i, j);
                }
            }
        }
        return ret;
    }
    
    private void swap(char[] chars, int i, int j) {
        char tmp = chars[i];
        chars[i] = chars[j];
        chars[j] = tmp;
    }
}