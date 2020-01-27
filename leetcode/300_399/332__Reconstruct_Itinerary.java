class Solution {
    public List<String> findItinerary(List<List<String>> tickets) {
        if (tickets == null || tickets.isEmpty()) {
            return Collections.emptyList();
        }
        Map<String, List<Edge>> graph = buildGraph(tickets);
        
        List<String> ret = new ArrayList<>(graph.keySet().size());
        ret.add("JFK");
        dfs(graph, new HashSet<>(), tickets.size(), ret);
        return ret;
    }
    
    private boolean dfs(Map<String, List<Edge>> graph, Set<Edge> visited, int edgesCnt, List<String> ret) {
        if (ret.size() == edgesCnt + 1) {
            return true;
        }
        String curNode = ret.get(ret.size() - 1);
        for (Edge edge : graph.get(curNode)) {
            if (visited.contains(edge) == false) {
                visited.add(edge);
                ret.add(edge.des);
                if (dfs(graph, visited, edgesCnt, ret)) {
                    return true;
                }
                ret.remove(ret.size() - 1);
                visited.remove(edge);
            }
        }
        return false;
    }
    
    private Map<String, List<Edge>> buildGraph(List<List<String>> tickets) {
        Map<String, List<Edge>> ret = new HashMap<>();
        for (List<String> ticket : tickets) {
            ret.putIfAbsent(ticket.get(0), new LinkedList<>());
            ret.putIfAbsent(ticket.get(1), new LinkedList<>());
            ret.get(ticket.get(0)).add(new Edge(ticket.get(1)));
        }
        ret.forEach((node, edgeList) -> {
            Collections.sort(edgeList);
        });
        return ret;
    }
    
    private class Edge implements Comparable<Edge> {
        String des;
        
        Edge(String des) {
            this.des = des;
        }
        public int compareTo(Edge other) {
            return this.des.compareTo(other.des);
        }
    } 
}