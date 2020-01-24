public class Solution {
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
        
        List<Integer>[] graph = buildGraph(n, edges);
        int[] ret = {0};
        dfs(graph, 0, new boolean[n], ret);
        return ret[0] == n;
    }
    
    private void dfs(List<Integer>[] graph, int node, boolean[] visited, int[] ret) {
        visited[node] = true;
        ret[0]++;
        for (Integer nb : graph[node]) {
            if (visited[nb] == false) {
                dfs(graph, nb, visited, ret);
            }
        }
    }
    
    private List<Integer>[] buildGraph(int n, int[][] edges) {
        List<Integer>[] graph = new LinkedList[n];
        for (int i = 0; i < n; i++) {
            graph[i] = new LinkedList<>();
        }
        for (int[] edge : edges) {
            graph[edge[0]].add(edge[1]);
            graph[edge[1]].add(edge[0]);
        }
        return graph;
    }
}

public class Solution {
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

public class Solution3 {
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
        
        int[] ret = {0};
        dfs(0, graph, visited, ret);
       
        return ret[0] == n;
    }
    
    private void dfs(int node, List<List<Integer>> graph, boolean[] visited, int[] ret) {
        visited[node] = true;
        ret[0]++;
        
        for (Integer nb : graph.get(node)) {
            if (visited[nb] == false) {
                dfs(nb, graph, visited, ret);
            }
        }
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