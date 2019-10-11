/**
 * Definition for Directed graph.
 * class DirectedGraphNode {
 *     int label;
 *     ArrayList<DirectedGraphNode> neighbors;
 *     DirectedGraphNode(int x) { label = x; neighbors = new ArrayList<DirectedGraphNode>(); }
 * };
 */

public class Solution {
    /*
     * @param graph: A list of Directed graph node
     * @return: Any topological order for the given graph.
     */
    public ArrayList<DirectedGraphNode> topSort(ArrayList<DirectedGraphNode> graph) {
        ArrayList<DirectedGraphNode> ret = new ArrayList<>();
        
        if (graph == null) {
            return ret;
        }
        
        // Compute indegree for each node
        HashMap<DirectedGraphNode, Integer> indegrees = new HashMap<>();
        computeIndegree(graph, indegrees);
        
        // BFS queue
        Queue<DirectedGraphNode> queue = new LinkedList<>();
        
        // Initially, put all the nodes to queue whose indegree is zero
        indegrees.forEach((k, v) -> {
            if (v == 0) {
                queue.add(k);
            }
        });
        
        while(queue.isEmpty() == false) {
            DirectedGraphNode node = queue.remove();
            ret.add(node);
            
            for (DirectedGraphNode neighbor : node.neighbors) {
                indegrees.compute(neighbor, (v, k) -> k - 1);
                
                if (indegrees.get(neighbor) == 0) {
                    queue.add(neighbor);
                }
            }
        }
        
        return ret;
    }
    
    private void computeIndegree(ArrayList<DirectedGraphNode> graph, HashMap<DirectedGraphNode, Integer> indegrees) {
        for (DirectedGraphNode node : graph) {
            indegrees.put(node, 0);
        }
        
        for (DirectedGraphNode node : graph) {
            for (DirectedGraphNode neighbor : node.neighbors) {
                indegrees.compute(neighbor, (v, k) -> k + 1);
            }
        }
    }
}