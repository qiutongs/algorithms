// Greedy
// Time: O(NlogN)
class Solution {
    public int findMinArrowShots(int[][] points) {
        if (points.length == 0) {
            return 0;
        }
        Arrays.sort(points, (p1, p2) -> p1[0] - p2[0]);
        
        PriorityQueue<int[]> pq = new PriorityQueue<>((p1, p2) -> p1[1] - p2[1]);
        int ret = 0;
        for (int[] point : points) {
            if (pq.isEmpty() == false && pq.peek()[1] < point[0]) {
                ret++;
                pq.clear();
            }
            pq.offer(point);
        }
        ret++;
        return ret;
    }
}