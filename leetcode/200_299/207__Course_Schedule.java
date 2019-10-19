class Solution1 {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        if (numCourses <= 0 || prerequisites == null) {
            return false;
        }
        
        Map<Integer, List<Integer>> graph = buildGraph(numCourses, prerequisites);
        Map<Integer, Integer> indegrees = computeIndegree(numCourses, graph);
        
        Queue<Integer> queue = new LinkedList<>();
        
        indegrees.forEach((k, v) -> {
            if (v == 0) {
                queue.add(k);
            }
        });
        
        int count = 0;
        
        while(queue.isEmpty() == false) {
            Integer node = queue.remove();
            count++;
            
            graph.get(node).forEach(neighbor -> {
                indegrees.compute(neighbor, (k, v) -> v - 1);
                
                if (indegrees.get(neighbor) == 0) {
                    queue.add(neighbor);
                }
            });
        }
        
        return count == numCourses;
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

class Solution2 {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        if (numCourses <= 0 || prerequisites == null) {
            return false;
        }
        
        Map<Integer, List<Integer>> graph = buildGraph(numCourses, prerequisites);
        Set<Integer> visited = new HashSet<>();
        
        for (int i = 0; i < numCourses; i++) {
            if (dfs(i, graph, visited) == false) {
                return false;
            }
        }

        return true;
    }
    
    private boolean dfs(int node, Map<Integer, List<Integer>> graph, Set<Integer> visited) {
        return dfsHelper(node, graph, visited, new HashSet<>());
    }
    
    private boolean dfsHelper(int node, Map<Integer, List<Integer>> graph, Set<Integer> visited, Set<Integer> visitedTmp) {
        if (visited.contains(node)) {
            return true;
        }

        visitedTmp.add(node);
        
        for (Integer neighbor : graph.get(node)) {
            if (visitedTmp.contains(neighbor)) {
                return false;
            } else {
                if (dfsHelper(neighbor, graph, visited, visitedTmp) == false) {
                    return false;
                }
            }
        }
        
        visitedTmp.remove(node);
        visited.add(node);
        
        return true;
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
}