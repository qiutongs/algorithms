class Solution {
    public String alienOrder(String[] words) {
        if (words == null || words.length == 0) {
            return "";
        }
        List<Integer>[] graph = new List[26];
        int[] indegrees = new int[26];
        int count = buildGraph(words, graph, indegrees);
        List<Integer> ret = topoSort(graph, indegrees);
        return ret.size() == count ? toString(ret) : "";
    }
    
    private List<Integer> topoSort(List<Integer>[] graph, int[] indegrees) {
        Queue<Integer> q = new LinkedList<>();
        for (int i = 0; i < 26; i++) {
            if (indegrees[i] == 0) {
                q.offer(i);
            }
        }
        
        List<Integer> ret = new ArrayList<>();
        while(q.isEmpty() == false) {
            Integer node = q.poll();
            ret.add(node);
            for (Integer nb : graph[node]) {
                indegrees[nb]--;
                if (indegrees[nb] == 0) {
                    q.add(nb);
                }
            }
        }
        return ret;
    }
    
    private int buildGraph(String[] words, List<Integer>[] graph, int[] indegrees) {
        for (int i = 0; i < 26; i++) {
            graph[i] = new LinkedList<>();
        }
        Arrays.fill(indegrees, -1);
        
        for (int i = 0; i < words.length; i++) {
            for (int j = 0; j < words[i].length(); j++) {
                indegrees[toIndex(words[i].charAt(j))] = 0;
            }
        }
        
        for (int i = 1; i < words.length; i++) {
            for (int j = 0; j < Math.min(words[i - 1].length(), words[i].length()); j++) {
                char c1 = words[i - 1].charAt(j), c2 = words[i].charAt(j);
                if (c1 != c2) {
                    graph[toIndex(c1)].add(toIndex(c2));
                    indegrees[toIndex(c2)]++;
                    break;
                }
            }
        }
        int ret = 0;
        for (int i = 0; i < 26; i++) {
            if (indegrees[i] >= 0) {
                ret++;
            }
        }
        return ret;
    }
    
    private String toString(List<Integer> ret) {
        StringBuilder sb = new StringBuilder();
        for (Integer num : ret) {
            sb.append((char)(num + 'a'));
        }
        return sb.toString();
    }
    
    private int toIndex(char c) {
        return (int)(c - 'a');
    }
}