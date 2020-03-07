class Solution {
    public int computeArea(int A, int B, int C, int D, int E, int F, int G, int H) {
        Rect r1 = new Rect(A, B, C, D);
        Rect r2 = new Rect(E, F, G, H);
        return r1.area() + r2.area() - r1.intersect(r2);
    }
    
    private class Rect {
        int l, r;
        int d, u;
        
        Rect(int A, int B, int C, int D) {
            l = A;
            r = C;
            d = B;
            u = D;
        }
        
        int area() {
            return (r - l) * (u - d);
        }
        int intersect(Rect other) {
            if (overlap(other)) {
                return (Math.min(this.u, other.u) - Math.max(this.d, other.d)) * (Math.min(this.r, other.r) - Math.max(this.l, other.l));
            } else {
                return 0;
            }
        }
        boolean overlap(Rect other) {
            return (this.l > other.r || this.r < other.l || this.u < other.d || this.d > other.u) == false;
        }
    }
}

// Wrong:
class Solution {
    public int computeArea(int A, int B, int C, int D, int E, int F, int G, int H) {
        Rect r1 = new Rect(A, B, C, D);
        Rect r2 = new Rect(E, F, G, H);
        return r1.area() + r2.area() - r1.intersect(r2);
    }
    
    private class Rect {
        int l, r;
        int d, u;
        
        Rect(int A, int B, int C, int D) {
            l = A;
            r = C;
            d = B;
            u = D;
        }
        
        int area() {
            return (r - l) * (u - d);
        }
        int[][] points() {
            return new int[][]{{l, d}, {r, d}, {r, u}, {l, u}};
        }
        int intersect(Rect other) {
            for (int[] point : other.points()) {
                if (this.inside(point)) {
                    return (Math.min(this.u, other.u) - Math.max(this.d, other.d)) * (Math.min(this.r, other.r) - Math.max(this.l, other.l));
                }
            }
            return 0;
        }
        boolean inside(int[] p) {
            return p[0] >= l && p[0] <= r && p[1] >= d && p[1] <= u;
        }
    }
}