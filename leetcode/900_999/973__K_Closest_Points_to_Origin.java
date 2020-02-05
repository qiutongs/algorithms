class Solution {
    public int[][] kClosest(int[][] points, int K) {
        if (points == null || points.length == 0) {
            return points;
        }
        PriorityQueue<int[]> pq = new PriorityQueue<>((p1, p2) -> eucDis(p2) - eucDis(p1));
        for (int[] point : points) {
            pq.offer(point);
            if (pq.size() > K) {
                pq.poll();
            }
        }
        return new ArrayList<>(pq).toArray(new int[0][]);
    }
    
    private int eucDis(int[] point) {
        return point[0] * point[0] + point[1] * point[1];
    }
}