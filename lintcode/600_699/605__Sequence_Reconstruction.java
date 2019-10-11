public class Solution {
    /**
     * @param org: a permutation of the integers from 1 to n
     * @param seqs: a list of sequences
     * @return: true if it can be reconstructed only one or false
     */
    public boolean sequenceReconstruction(int[] org, int[][] seqs) {
        if (org == null || seqs == null) {
            return false;
        }
        
        if (hasAllNumber(org.length, seqs) == false) {
            return false;
        }
        
        Map<Integer, List<Integer>> graph = buildGraph(org.length, seqs);
        Map<Integer, Integer> indegrees = computeIndegree(org.length, graph);
        
        Queue<Integer> queue = new LinkedList<>();
        
        indegrees.forEach((k, v) -> {
            if (v == 0) {
                queue.add(k);
            }
        });
        
        int[] uniqueSeq = new int[org.length];
        int size = 0;
        
        while(queue.isEmpty() == false) {
            if (queue.size() > 1) {
                return false;
            }
            
            Integer node = queue.remove();
            uniqueSeq[size++] = node;
            
            graph.get(node).forEach(neighbor -> {
                indegrees.compute(neighbor, (k, v) -> v - 1);
                
                if (indegrees.get(neighbor) == 0) {
                    queue.add(neighbor);
                }
            });
        }
        
        return size == org.length && Arrays.equals(org, uniqueSeq);
    }
    
    private boolean hasAllNumber(int n, int[][] seqs) {
        int[] counts = new int[n];
        Arrays.fill(counts, 0);
        
        for (int i = 0; i < seqs.length; i++) {
            for (int j = 0; j < seqs[i].length; j++) {
                if (seqs[i][j] >= 1 && seqs[i][j] <= n) {
                    counts[seqs[i][j] - 1] = 1;
                } else {
                    return false;
                }
            }
        }
        
        for (int count : counts) {
            if (count == 0) {
                return false;
            }
        }
        
        return true;
    }
    
    private Map<Integer, List<Integer>> buildGraph(int n, int[][] seqs) {
        Map<Integer, List<Integer>> ret = new HashMap<>();
        
        for (int i = 1; i <= n; i++) {
            ret.put(i, new LinkedList<>());
        }
        
        for (int[] seq : seqs) {
            for (int i = 1; i < seq.length; i++) {
                ret.get(seq[i - 1]).add(seq[i]);
            }
        }

        return ret;
    }
    
    private Map<Integer, Integer> computeIndegree(int n, Map<Integer, List<Integer>> graph) {
        Map<Integer, Integer> ret = new HashMap<>();
        
        for (int i = 1; i <= n; i++) {
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