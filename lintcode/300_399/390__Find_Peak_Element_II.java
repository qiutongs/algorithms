public class Solution {
    /*
     * @param A: An integer matrix
     * @return: The index of the peak
     */
    public List<Integer> findPeakII(int[][] A) {
        int m = A.length, n = A[0].length;
        int l = 0, r = m - 1;
        while(l <= r) {
            int mid = (l + r) / 2;
            int maxIdx = getMaxIdx(A[mid]);
            if (isPeak(A, mid, maxIdx)) {
                return Arrays.asList(mid, maxIdx);
            }
            if (mid > 0 && A[mid][maxIdx] < A[mid - 1][maxIdx]) {
                r = mid - 1;
            } else {
                l = mid + 1;
            }
        }
        return null;
    }
    
    private boolean isPeak(int[][] A, int x, int y) {
        if (x == 0 || x == A.length - 1 || y == 0 || y == A[0].length - 1) {
            return false;
        }
        return A[x][y] > A[x - 1][y] && A[x][y] > A[x + 1][y];
    }
    
    private int getMaxIdx(int[] A) {
        int ret = 0;
        for (int i = 1; i < A.length; i++) {
            if (A[i] > A[ret]) {
                ret = i;
            }
        }
        return ret;
    }
}