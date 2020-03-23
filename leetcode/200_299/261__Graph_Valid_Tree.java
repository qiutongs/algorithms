/*
Given n nodes labeled from 0 to n-1 and a list of undirected edges (each edge is a pair of nodes), write a function to check whether these edges make up a valid tree.

Note: you can assume that no duplicate edges will appear in edges. Since all edges are undirected, [0,1] is the same as [1,0] and thus will not appear together in edges.
*/
// DFS
class Solution {
    public boolean validTree(int n, int[][] edges) {
        if (edges.length != n - 1) {
            return false;
        }
        List<Integer>[] graph = buildGraph(n, edges);
        return dfs(graph, 0, new boolean[n]) == n;
    }
    
    private int dfs(List<Integer>[] graph, int node, boolean[] visited) {
        visited[node] = true;
        int ret = 1;
        for (int nb : graph[node]) {
            if (visited[nb] == false) {
                ret += dfs(graph, nb, visited);
            }
        }
        return ret;
    }
    
    private List<Integer>[] buildGraph(int n, int[][] edges) {
        List<Integer>[] ret = (List<Integer>[])new List[n];
        for (int i = 0; i < n; i++) {
            ret[i] = new ArrayList<>();
        }
        for (int[] edge : edges) {
            ret[edge[0]].add(edge[1]);
            ret[edge[1]].add(edge[0]);
        }
        return ret;
    }
}