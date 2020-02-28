// Graph: from character to character
// 1. every node outdegree <= 1
// 2. at least 1 node with indegree = 0
class Solution {
    public boolean canConvert(String str1, String str2) {
        if (str1.equals(str2)) {
            return true;
        }
        int[] graph = buildGraph(str1, str2);
        if (graph == null) {
            return false;
        }
        
        int[] indegrees = new int[26];
        int used = 0;
        for (int i = 0; i < 26; i++) {
            if (graph[i] >= 0) {
                if (indegrees[graph[i]] == 0) {
                    used++;
                }
                indegrees[graph[i]]++;
            }
        }
        return used < 26;
    }
    
    private int[] buildGraph(String str1, String str2) {
        int[] graph = new int[26];
        Arrays.fill(graph, -1);
        
        for (int i = 0; i < str1.length(); i++) {
            int from = str1.charAt(i) - 'a';
            int to = str2.charAt(i) - 'a';
            if (graph[from] == -1) {
                graph[from] = to;
            } else {
                if (graph[from] != to) {
                    return null;
                }
            }
        }
        return graph;
    }
}

// Wrong
// Graph + topological sort
/*
"abcdefghijklmnopqrstuvwxyz"
"bcdefghijklmnopqrstuvwxyzq"
*/
class Solution {
    private static int NON_EXIST = -1;
    private static int ISOLATE = 26;
    
    public boolean canConvert(String str1, String str2) {
        int[] graph = buildGraph(str1, str2);
        if (graph == null) {
            return false;
        }
        int size = getSize(graph);
        return str1.length() < 26 || topoSort(graph) == size;
    }

    private int topoSort(int[] graph) {
        int[] indegrees = new int[26];
        for (int i = 0; i < 26; i++) {
            if (graph[i] == NON_EXIST) {
                indegrees[i] = NON_EXIST;
            }
        }
        for (int i = 0; i < 26; i++) {
            if (hasNeighbor(graph, i)) {
                indegrees[graph[i]]++;
            }
        }
        
        Queue<Integer> q = new LinkedList<>();
        for (int i = 0; i < 26; i++) {
            if (indegrees[i] == 0) {
                q.offer(i);
            }
        }
        
        int ret = 0;
        while(q.isEmpty() == false) {
            int cur = q.poll();
            ret++;
            
            if (hasNeighbor(graph, cur)) {
                int nb = graph[cur];
                indegrees[nb]--;
                if (indegrees[nb] == 0) {
                    q.offer(nb);
                }
            }
        }
        return ret;
    }
    
    private int[] buildGraph(String str1, String str2) {
        int[] graph = new int[26];
        Arrays.fill(graph, NON_EXIST);
        for (int i = 0; i < str1.length(); i++) {
            graph[toInt(str1.charAt(i))] = ISOLATE;
            graph[toInt(str2.charAt(i))] = ISOLATE;
        }
        
        for (int i = 0; i < str1.length(); i++) {
            int from = toInt(str1.charAt(i));
            int to = toInt(str2.charAt(i));
            if (graph[from] == ISOLATE) {
                graph[from] = to;
            } else {
                if (graph[from] != to) {
                    return null;
                }
            }
        }
        return graph;
    }
    
    private int getSize(int[] graph) {
        int ret = 0;
        for (int i = 0; i < 26; i++) {
            if (graph[i] != NON_EXIST) {
                ret++;
            }
        }
        return ret;
    }
    
    private boolean hasNeighbor(int[] graph, int index) {
        return graph[index] != NON_EXIST && graph[index] != ISOLATE;
    }
    
    private int toInt(char c) {
        return c - 'a';
    }
}