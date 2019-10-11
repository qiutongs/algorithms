class Solution {
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        if (numCourses <= 0 || prerequisites == null) {
            return new int[0];
        }
        
        Map<Integer, List<Integer>> graph = buildGraph(numCourses, prerequisites);
        Map<Integer, Integer> indegrees = computeIndegree(numCourses, graph);
        
        Queue<Integer> queue = new LinkedList<>();
        
        indegrees.forEach((k, v) -> {
            if (v == 0) {
                queue.add(k);
            }
        });
        
        int[] ret = new int[numCourses];
        int size = 0;
        
        while(queue.isEmpty() == false) {
            Integer node = queue.remove();
            ret[size++] = node;
            
            graph.get(node).forEach(neighbor -> {
                indegrees.compute(neighbor, (k, v) -> v - 1);
                
                if (indegrees.get(neighbor) == 0) {
                    queue.add(neighbor);
                }
            });
        }
        
        return size == numCourses ? ret : new int[0];
    }
    
    private Map<Integer, List<Integer>> buildGraph(int n, int[][] prerequisites) {
        Map<Integer, List<Integer>> ret = new HashMap<>();
        
        for (int i = 0; i < n; i++) {
            ret.put(i, new LinkedList<>());
        }
        
        for (int[] edge : prerequisites) {
            ret.get(edge[1]).add(edge[0]);
        }

        return ret;
    }
    
    private Map<Integer, Integer> computeIndegree(int n, Map<Integer, List<Integer>> graph) {
        Map<Integer, Integer> ret = new HashMap<>();
        
        for (int i = 0; i < n; i++) {
            ret.put(i, 0);
        }
        
        graph.forEach((node, neighbors) -> {
            neighbors.forEach(neighbor -> {
                ret.compute(neighbor, (k, v) -> v + 1);
            });
        });
        
        return ret;
    }
}