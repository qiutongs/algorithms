// DFS + sort
class Solution {
    public String smallestStringWithSwaps(String s, List<List<Integer>> pairs) {
        List<Integer>[] graph = buildGraph(s.length(), pairs);
        List<List<Integer>> components = getConnectedComponents(graph);
        char[] str = s.toCharArray();
        for (List<Integer> component : components) {
            sort(str, component);
        }
        return new String(str);
    }
    
    private void sort(char[] str, List<Integer> component) {
        Collections.sort(component);
        int[] counts = new int[26];
        for (int idx : component) {
            counts[str[idx] - 'a']++;
        }
        int j = 0;
        for (int i = 0; i < 26; i++) {
            while (counts[i] > 0) {
                str[component.get(j)] = (char)(i + 'a');
                j++;
                counts[i]--;
            }
        }
    }
    
    private List<List<Integer>> getConnectedComponents(List<Integer>[] graph) {
        List<List<Integer>> ret = new ArrayList<>();
        boolean[] visited = new boolean[graph.length];
        for (int i = 0; i < graph.length; i++) {
            if (visited[i] == false) {
                visited[i] = true;
                List<Integer> component = new ArrayList<>();
                dfs(graph, i, visited, component);
                ret.add(component);
            }
        }
        return ret;
    }
    
    private void dfs(List<Integer>[] graph, int node, boolean[] visited, List<Integer> component) {
        component.add(node);
        for (int nb : graph[node]) {
            if (visited[nb] == false) {
                visited[nb] = true;
                dfs(graph, nb, visited, component);
            }
        }
    }
    
    private List<Integer>[] buildGraph(int n, List<List<Integer>> edges) {
        List<Integer>[] ret = (List<Integer>[])new List[n];
        for (int i = 0; i < n; i++) {
            ret[i] = new ArrayList<>();
        }
        for (List<Integer> edge : edges) {
            ret[edge.get(0)].add(edge.get(1));
            ret[edge.get(1)].add(edge.get(0));
        }
        return ret;
    }
}

// Union find + sort
class Solution {
    public String smallestStringWithSwaps(String s, List<List<Integer>> pairs) {
        UF uf = new UF(s.length()); 
        for (List<Integer> pair : pairs) {
            uf.union(pair.get(0), pair.get(1));
        }
        
        Collection<List<Integer>> components = getConnectedComponents(uf);
        
        char[] str = s.toCharArray();
        for (List<Integer> component : components) {
            sort(str, component);
        }
        return new String(str);
    }
    
    private Collection<List<Integer>> getConnectedComponents(UF uf) {
        HashMap<Integer, List<Integer>> ret = new HashMap<>();
        for (int i = 0; i < uf.parents.length; i++) {
            int root = uf.find(i);
            ret.putIfAbsent(root, new ArrayList<>());
            ret.get(root).add(i);
        }
        return ret.values();
    }
    
    private void sort(char[] str, List<Integer> component) {
        Collections.sort(component);
        int[] counts = new int[26];
        for (int idx : component) {
            counts[str[idx] - 'a']++;
        }
        int j = 0;
        for (int i = 0; i < 26; i++) {
            while (counts[i] > 0) {
                str[component.get(j)] = (char)(i + 'a');
                j++;
                counts[i]--;
            }
        }
    }
    
    private class UF {
        private int[] parents;
        UF(int n) {
            parents = new int[n];
            for (int i = 0; i < n; i++) {
                parents[i] = i;
            }
        }
        void union(int p, int q) {
            int rootP = find(p);
            int rootQ = find(q);
            parents[rootP] = rootQ;
        }
        int find(int x) {
            while(parents[x] != x) {
                parents[x] = parents[parents[x]];
                x = parents[x];
            }
            return x;
        }
    }
}