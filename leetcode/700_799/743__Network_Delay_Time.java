class Solution {
    public int networkDelayTime(int[][] times, int N, int K) {
        List<Edge>[] graph = new List[N + 1];
        buildGraph(graph, times);
        
        PriorityQueue<Node> pq = new PriorityQueue<>();
        HashSet<Integer> explored = new HashSet<>();
        pq.offer(new Node(K, 0));
        
        int ret = 0;
        int count = 0;
        while(pq.isEmpty() == false) {
            Node node = pq.poll();
            if (explored.contains(node.id) == false) {
                explored.add(node.id);
                ret = Math.max(ret, node.dis);
                count++;
                
                for (Edge e : graph[node.id]) {
                    if (explored.contains(e.v)) {
                        continue;
                    }
                    pq.offer(new Node(e.v, node.dis + e.w));
                }
            }
        }
        return count == N ? ret : -1;
    }

    private void buildGraph(List<Edge>[] graph, int[][] times) {
        for (int[] time : times) {
            if (graph[time[0]] == null) {
                graph[time[0]] = new ArrayList<>();
            }
            if (graph[time[1]] == null) {
                graph[time[1]] = new ArrayList<>();
            }
            graph[time[0]].add(new Edge(time[1], time[2]));
        }
    }
    
    private class Node implements Comparable<Node> {
        int id;
        int dis;
        Node(int id, int dis) {
            this.id = id;
            this.dis = dis;
        }
        public int compareTo(Node other) {
            return this.dis - other.dis;
        }
    }
    
    private class Edge {
        int v;
        int w;
        Edge(int v, int w) {
            this.v = v;
            this.w = w;
        } 
    }
}