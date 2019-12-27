class Solution {
    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int K) {
        List<Edge>[] graph = buildGraph(n, flights);
        int[] dist = new int[n];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[src] = 0;
        
        Queue<Integer> q = new LinkedList<>();
        q.offer(src);
        int level = 0;
        while(q.isEmpty() == false) {
            int size = q.size();
            for (int i = 0; i < size; i++) {
                int v = q.poll();
                for (Edge edge : graph[v]) {
                    if (level == K && edge.des != dst) {
                        continue;
                    }
                    if (dist[v] + edge.w < dist[edge.des]) {
                        dist[edge.des] = dist[v] + edge.w;
                        if (edge.des != dst) {
                            q.add(edge.des);
                        }
                    }
                }
            }
            level++;
        }
        return dist[dst] == Integer.MAX_VALUE ? -1 : dist[dst];
    }
    
    private List<Edge>[] buildGraph(int n, int[][] flights) {
        List<Edge>[] ret = new LinkedList[n];
        for (int i = 0; i < n; i++) {
            ret[i] = new LinkedList<>();
        }
        for (int[] flight : flights) {
            ret[flight[0]].add(new Edge(flight[1], flight[2]));
        }
        return ret;
    }
    
    private class Edge {
        int des;
        int w;
        Edge(int des, int w) {
            this.des = des;
            this.w = w;
        }
    }
}

class Solution {
    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int K) {
        List<Edge>[] graph = buildGraph(n, flights);
        
        Queue<Vertex> q = new PriorityQueue<>((v1, v2) -> v1.dist - v2.dist);
        Set<Integer> visited = new HashSet<>();
        q.offer(new Vertex(src, 0, 0));
        while(q.isEmpty() == false) {
            Vertex v = q.poll();
            visited.add(v.id);
            if (v.id == dst) {
                return v.dist;
            }
            for (Edge edge : graph[v.id]) {
                if (visited.contains(edge.des)) {
                    continue;
                }
                if (v.level < K || edge.des == dst) {
                    q.offer(new Vertex(edge.des, v.dist + edge.w, v.level + 1));
                }
            }
        }
        return -1;
    }
    
    private List<Edge>[] buildGraph(int n, int[][] flights) {
        List<Edge>[] ret = new LinkedList[n];
        for (int i = 0; i < n; i++) {
            ret[i] = new LinkedList<>();
        }
        for (int[] flight : flights) {
            ret[flight[0]].add(new Edge(flight[1], flight[2]));
        }
        return ret;
    }
    
    private class Vertex {
        int id;
        int dist;
        int level;
        Vertex(int id, int dist, int level) {
            this.id = id;
            this.dist = dist;
            this.level = level;
        }
    }
    
    private class Edge {
        int des;
        int w;
        Edge(int des, int w) {
            this.des = des;
            this.w = w;
        }
    }
}