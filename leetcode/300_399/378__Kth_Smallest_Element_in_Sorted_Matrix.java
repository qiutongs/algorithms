import javafx.util.Pair;

/*
 * Maintain a min heap. Time klogk

 * Note: this reminds me of Dijkstra algorithm for shortest path
 */
class Solution {
    public int kthSmallest(int[][] matrix, int k) {
        int n = matrix.length;
        boolean[][] visited = new boolean[n][n];
        
        PriorityQueue<Point> heap = new PriorityQueue<>((p1, p2) -> p1.val - p2.val);
        heap.add(new Point(0, 0, matrix[0][0]));
        visited[0][0] = true;
        
        for (int i = 1; i < k; i++) {
            Point min = heap.remove();
            if (min.i + 1 < n && visited[min.i + 1][min.j] == false) {
                visited[min.i + 1][min.j] = true;
                heap.add(new Point(min.i + 1, min.j, matrix[min.i + 1][min.j]));
            }
            if (min.j + 1 < n && visited[min.i][min.j + 1] == false) {
                visited[min.i][min.j + 1] = true;
                heap.add(new Point(min.i, min.j + 1, matrix[min.i][min.j + 1]));
            }
        }
        return heap.peek().val;
    }

    private class Point {
        int i;
        int j;
        int val;
        Point(int i, int j, int val) {
            this.i = i;
            this.j = j;
            this.val = val;
        }
    }
}