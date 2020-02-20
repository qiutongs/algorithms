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

// Quick select
// Time: expected O(N)
class Solution {
    private final Random rand = new Random();
    
    public int[][] kClosest(int[][] points, int K) {
        if (points == null || points.length == 0) {
            return points;
        }
        quickSelect(points, 0, points.length - 1, K);
        return Arrays.copyOfRange(points, 0, K);
    }
    
    private void quickSelect(int[][] points, int l, int r, int K) {
        if (l == r) {
            return;
        }
        int pivot = partition(points, l, r);
        if (pivot < K) {
            quickSelect(points, pivot + 1, r, K);
        } else if (pivot > K) {
            quickSelect(points, l, pivot - 1, K);
        }
    }
    
    private int partition(int[][] points, int l, int r) {
        int p = rand.nextInt(r - l + 1) + l;
        swap(points, p, r);
        
        int i = l;
        for (int j = l; j <= r; j++) {
            if (eucDis(points[j]) < eucDis(points[r])) {
                swap(points, i, j);
                i++;
            }
        }
        swap(points, i, r);
        return i;
    }
    
    private void swap(int[][] points, int i, int j) {
        int[] tmp = points[i];
        points[i] = points[j];
        points[j] = tmp;
    }
    
    private int eucDis(int[] point) {
        return point[0] * point[0] + point[1] * point[1];
    }
}