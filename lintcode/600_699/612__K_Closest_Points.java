/**
 * Definition for a point.
 * class Point {
 *     int x;
 *     int y;
 *     Point() { x = 0; y = 0; }
 *     Point(int a, int b) { x = a; y = b; }
 * }
 */

public class Solution {
    /**
     * @param points: a list of points
     * @param origin: a point
     * @param k: An integer
     * @return: the k closest points
     */
    public Point[] kClosest(Point[] points, Point origin, int k) {
        Comparator<Point> comparator = new Comparator<Point>() {
          public int compare(Point p1, Point p2) {
            int d1 = dist(p1, origin), d2 = dist(p2, origin);
            if (d1 != d2) {
                return d2 - d1;
            }
            if (p1.x != p2.x) {
                return p2.x - p1.x;
            }
            return p2.y - p1.y;
          }
        };
        PriorityQueue<Point> heap = new PriorityQueue<>(comparator);
        
        for (Point point : points) {
            if (heap.size() < k) {
                heap.add(point);
            } else {
                if (comparator.compare(heap.peek(), point) < 0) {
                    heap.remove();
                    heap.add(point);
                } 
            }
        }
        
        Point[] ret = new Point[heap.size()];
        for (int i = ret.length - 1; i >= 0; i--) {
            ret[i] = heap.remove();
        }
        return ret;
    }
    
    private int dist(Point p1, Point p2) {
        return (p1.x - p2.x) * (p1.x - p2.x) + (p1.y - p2.y) * (p1.y - p2.y);
    }
}