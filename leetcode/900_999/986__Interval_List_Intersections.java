class Solution {
    public int[][] intervalIntersection(int[][] A, int[][] B) {
        List<int[]> ret = new ArrayList<>();
        int i = 0, j = 0;
        while(i < A.length && j < B.length) {
            int[] inter = intersect(A[i], B[j]);
            if (inter != null) {
                ret.add(inter);
            }
            if (A[i][1] < B[j][1]) {
                i++;
            } else {
                j++;
            }
        }
        return ret.toArray(new int[0][]);
    }
    
    private int[] intersect(int[] i1, int[] i2) {
        if (i1[0] > i2[1] || i2[0] > i1[1]) {
            return null;
        } else {
            return new int[]{Math.max(i1[0], i2[0]), Math.min(i1[1], i2[1])};
        }
    }
}