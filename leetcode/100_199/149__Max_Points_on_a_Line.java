// Math + hashtable: use ax + by + c = 0 to represent a line
// Time: O(N ^ 2)
// Space: O(N ^ 2)
class Solution {
    public int maxPoints(int[][] points) {
        if (points.length == 0) {
            return 0;
        } else if (points.length == 1) {
            return 1;
        }
        HashMap<Line, HashSet<Integer>> linePointsMap = new HashMap<>();
        for (int i = 0; i < points.length; i++) {
            for (int j = i + 1; j < points.length; j++) {
                Line line = new Line(points[i], points[j]);
                linePointsMap.putIfAbsent(line, new HashSet<>());
                linePointsMap.get(line).add(i);
                linePointsMap.get(line).add(j);
            }
        }
        
        int ret = 0;
        for (HashSet<Integer> pointsSet : linePointsMap.values()) {
            ret = Math.max(ret, pointsSet.size());
        }
        return ret;
    }
    
    private class Line {
        // ax + by + c = 0;
        int[] coe = new int[3];
        
        Line(int[] p1, int[] p2) {
            if (p1[0] == p2[0]) {
                coe[0] = 1;
                coe[1] = 0;
                coe[2] = -p1[0];
            } else if (p1[1] == p2[1]) {
                coe[0] = 0;
                coe[1] = 1;
                coe[2] = -p1[1];
            } else {
                coe[0] = p2[1] - p1[1];
                coe[1] = p1[0] - p2[0];
                int cd = gcd(coe[0], coe[1]);
                coe[0] /= cd;
                coe[1] /= cd;
                coe[2] = -(coe[0] * p1[0] + coe[1] * p1[1]);
            }
        }
        
        @Override
        public boolean equals(Object other) {
            return Arrays.equals(this.coe, ((Line)other).coe);
        }
        
        @Override
        public int hashCode() {
            return Arrays.hashCode(coe);
        }
        
        private int gcd(int a, int b) {
            if (b == 0) {
                return a;
            }
            return gcd(b, a % b);
        }
    }
}