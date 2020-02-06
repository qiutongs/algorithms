class Solution {
    public boolean isBipartite(int[][] graph) {
        if (graph == null || graph.length == 0) {
            return true;
        }
        int n = graph.length;
        int[] color = new int[n];
        Arrays.fill(color, -1);
        for (int i = 0; i < n; i++) {
            if (color[i] == -1) {
                color[i] = 0;
                if (dfs(graph, i, color) == false) {
                    return false;
                }
            }
        }
        return true;
    }
    
    private boolean dfs(int[][] graph, int v, int[] color) {
        for (int nb : graph[v]) {
            if (color[nb] == -1) {
                color[nb] = color[v] == 0 ? 1 : 0;
                if (dfs(graph, nb, color) == false) {
                    return false;
                }
            } else if (color[nb] == color[v]) {
                return false;
            }
        }
        return true;
    }
}