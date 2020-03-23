class Solution {
    public int findCircleNum(int[][] M) {
        int n = M.length;
        boolean[] visited = new boolean[n];
        
        int ret = 0;
        for (int i = 0; i < n; i++) {
            if (visited[i] == false) {
                visited[i] = true;
                dfs(M, i, visited);
                ret++;
            }
        }
        return ret;
    }
    
    private void dfs(int[][] M, int node, boolean[] visited) {
        for (int i = 0; i < M.length; i++) {
            if (i == node) {
                continue;
            }
            if (M[node][i] == 1 && visited[i] == false) {
                visited[i] = true;
                dfs(M, i, visited);
            }
        }
    }
}