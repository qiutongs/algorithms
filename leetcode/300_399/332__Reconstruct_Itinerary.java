class Solution {
    public List<String> findItinerary(List<List<String>> tickets) {
        if (tickets == null || tickets.isEmpty()) {
            return Collections.emptyList();
        }
        
        Map<String, List<Edge>> graph = buildGraph(tickets);
        
        List<String> ret = new ArrayList<>(graph.keySet().size());
        List<String> curIt = new ArrayList<>();
        
        curIt.add("JFK");
        
        dfs(ret, curIt, graph, tickets.size());
        
        return ret;
    }
    
    private boolean dfs(List<String> ret, List<String> curIt, Map<String, List<Edge>> graph, int tickets) {
        if (curIt.size() == tickets + 1) {
            ret.addAll(curIt);
            return true;
        }
        
        String curNode = curIt.get(curIt.size() - 1);
        
        if (graph.get(curNode) == null) {
            return false;
        }
        
        for (Edge edge : graph.get(curNode)) {
            if (edge.visited == false) {
                edge.visited = true;
                curIt.add(edge.to);
                
                if (dfs(ret, curIt, graph, tickets)) {
                    return true;
                }
                
                edge.visited = false;
                curIt.remove(curIt.size() - 1);
            }
        }
        
        return false;
    }
    
    private Map<String, List<Edge>> buildGraph(List<List<String>> tickets) {
        Map<String, List<Edge>> ret = new HashMap<>();
        
        for (List<String> ticket : tickets) {
            ret.compute(ticket.get(0), (k, v) -> {
                if (v == null) {
                    List<Edge> list = new LinkedList<>();
                    list.add(new Edge(ticket.get(1)));
                    return list;
                } else {
                    v.add(new Edge(ticket.get(1)));
                    return v;
                }
            });
        }
        
        ret.forEach((k, v) -> {
            Collections.sort(v);
        });
        
        return ret;
    }
    
    private class Edge implements Comparable<Edge> {
        String to;
        boolean visited;
        
        Edge(String to) {
            this.to = to;
            this.visited = false;
        }
        
        public int compareTo(Edge other) {
            return this.to.compareTo(other.to);
        }
    } 
}