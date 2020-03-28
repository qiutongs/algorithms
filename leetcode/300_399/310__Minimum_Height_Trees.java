// BFS-like
// Time: O(n)
// Space: O(n)
class Solution {
    public List<Integer> findMinHeightTrees(int n, int[][] edges) {
        if (n <= 0) {
            return Collections.emptyList();
        } else if (n == 1) {
            return Arrays.asList(0);
        }
        List<Integer>[] graph = buildGraph(n, edges);
        int[] degrees = new int[n];
        for (int i = 0; i < n; i++) {
            degrees[i] = graph[i].size();
        }
        
        Queue<Integer> q = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            if (degrees[i] == 1) {
                q.offer(i);
            }
        }
        
        List<Integer> ret = null;
        
        while(q.isEmpty() == false) {
            ret = new ArrayList<>(q);
            
            int size = q.size();
            for (int i = 0; i < size; i++) {
                int cur = q.poll();
                for (int nb : graph[cur]) {
                    degrees[nb]--;
                    if (degrees[nb] == 1) {
                        q.offer(nb);
                    }
                }
            }
        }
        return ret;
    }
    
    private List<Integer>[] buildGraph(int n, int[][] edges) {
        List<Integer>[] ret = new List[n];
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