class Solution {
    public boolean validSquare(int[] p1, int[] p2, int[] p3, int[] p4) {
        if (equal(p1, p2) || equal(p1, p3) || equal(p1, p4) || equal(p2, p3) || equal(p2, p4) || equal(p3, p4)) {
            return false;
        }
        return squareSegment(p1, p2, p3, p4) 
            || squareSegment(p1, p3, p2, p4)
            || squareSegment(p2, p1, p3, p4)
            || squareSegment(p3, p1, p2, p4);
    }
    
    private boolean squareSegment(int[] s1, int[] e1, int[] s2, int[] e2) {
        if ((s1[1] - e1[1]) * (s2[0] - e2[0]) != (s2[1] - e2[1]) * (s1[0] - e1[0])) {
            return false;
        }
        if ((s2[1] - s1[1]) * (e2[0] - e1[0]) != (e2[1] - e1[1]) * (s2[0] - s1[0])) {
            return false;
        }
        if ((s1[0] - e1[0]) * (s1[0] - s2[0]) + (s1[1] - e1[1]) * (s1[1] - s2[1]) != 0) {
            return false;
        }
        int manLen1 = Math.abs(s1[0] - e1[0]) + Math.abs(s1[1] - e1[1]);
        int manLen2 = Math.abs(s2[0] - e2[0]) + Math.abs(s2[1] - e2[1]);
        int manLen3 = Math.abs(s1[0] - s2[0]) + Math.abs(s1[1] - s2[1]);
        int manLen4 = Math.abs(e1[0] - e2[0]) + Math.abs(e1[1] - e2[1]);
        return manLen1 == manLen2 && manLen2 == manLen3 && manLen3 == manLen4;
    }
    
    private boolean equal(int[] p1, int[] p2) {
        return p1[0] == p2[0] && p1[1] == p2[1];
    }
}