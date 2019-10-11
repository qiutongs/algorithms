public class Solution1 {
    /**
     * @param n: An integer
     * @param edges: a list of undirected edges
     * @return: true if it's a valid tree, or false
     */
    public boolean validTree(int n, int[][] edges) {
        if (n <= 0 || edges == null) {
            return false;
        }
        
        // a tree with n nodes must have n - 1 edges
        if (edges.length != n - 1) {
            return false;
        }
        
        int[][] graph = buildGraph(n, edges);
        
        boolean[] visited = new boolean[n];
        Arrays.fill(visited, false);
        
        Queue<Integer> queue = new LinkedList<>();
        queue.add(0);
        visited[0] = true;
        
        int count = 1;
        
        while(queue.isEmpty() == false) {
            int node = queue.remove();
            
            for (int i = 0; i < n; i++) {
                if (visited[i] == false && graph[node][i] == 1) {
                    queue.add(i);
                    visited[neighbor] = true;
                    count++;
                }
            }
        }
        
        return count == n;
    }
    
    // adjacent matrix
    private int[][] buildGraph(int n, int[][] edges) {
        int[][] graph = new int[n][n];
        
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                graph[i][j] = 0;
            }
        }
        
        for (int[] edge : edges) {
            graph[edge[0]][edge[1]] = 1;
            graph[edge[1]][edge[0]] = 1;
        }
        
        return graph;
    }
}

public class Solution2 {
    /**
     * @param n: An integer
     * @param edges: a list of undirected edges
     * @return: true if it's a valid tree, or false
     */
    public boolean validTree(int n, int[][] edges) {
        if (n <= 0 || edges == null) {
            return false;
        }
        
        // a tree with n nodes must have n - 1 edges
        if (edges.length != n - 1) {
            return false;
        }
        
        List<List<Integer>> graph = buildGraph(n, edges);
        
        boolean[] visited = new boolean[n];
        Arrays.fill(visited, false);
        
        Queue<Integer> queue = new LinkedList<>();
        queue.add(0);
        visited[0] = true;
        
        int count = 1;
        
        while(queue.isEmpty() == false) {
            int node = queue.remove();

            List<Integer> neighbors = graph.get(node);
            
            for (Integer neighbor : neighbors) {
                if (visited[neighbor] == false) {
                    queue.add(neighbor);
                    visited[neighbor] = true;
                    count++;
                }
            }
        }
        
        return count == n;
    }
    
    // adjacent list
    private List<List<Integer>> buildGraph(int n, int[][] edges) {
        List<List<Integer>> graph = new ArrayList<>(n);
        
        for (int i = 0; i < n; i++) {
            graph.add(new LinkedList<>());
        }
        
        for (int[] edge : edges) {
            graph.get(edge[0]).add(edge[1]);
            graph.get(edge[1]).add(edge[0]);
        }
        
        return graph;
    }
}