// DFS
// Ref: https://algs4.cs.princeton.edu/41graph/Bridge.java.html
class Solution {
    private int id = 1;
    private int[] order;
    private int[] lowestOrder;
    
    public List<List<Integer>> criticalConnections(int n, List<List<Integer>> connections) {
        List<Integer>[] graph = buildGraph(n, connections);
        
        order = new int[n];
        lowestOrder = new int[n];
        
        List<List<Integer>> ret = new ArrayList<>();
        dfs(graph, 0, -1, ret);
        return ret;
    }
    
    private void dfs(List<Integer>[] graph, int v, int parent, List<List<Integer>> bridges) {
        order[v] = id;
        lowestOrder[v] = id;
        id++;
        
        for (int nb : graph[v]) {
            if (order[nb] == 0) {
                dfs(graph, nb, v, bridges);
                if (lowestOrder[nb] == order[nb]) {
                    bridges.add(Arrays.asList(v, nb));
                }
            }
            if (nb != parent) {
                lowestOrder[v] = Math.min(lowestOrder[v], lowestOrder[nb]);
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