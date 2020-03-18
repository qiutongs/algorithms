/* Jump window */
class Solution {
    public int maxTurbulenceSize(int[] A) {
        if (A == null || A.length == 0) {
            return 0;
        }
        int ret = 1;
        int l = 0;
        for (int r = 1; r < A.length; r++) {
            if (isTurbulent(A, r)) {
                ret = Math.max(ret, r - l + 1);
            } else {
                l = A[r] == A[r - 1] ? r : r - 1;
            }
        }
        return ret;
    }
    
    private boolean isTurbulent(int[] A, int i) {
        if (i - 2 >= 0) {
            return (A[i] > A[i - 1] && A[i - 1] < A[i - 2]) || (A[i] < A[i - 1] && A[i - 1] > A[i - 2]);
        } else {
            return A[i] != A[i - 1];
        }
    }
}

// DP
class Solution {
    public int maxTurbulenceSize(int[] A) {
        if (A == null || A.length == 0) {
            return 0;
        }
        
        int ret = 1;
        int dp = 1;
        for (int i = 1; i < A.length; i++) {
            if (isTurbulent(A, i)) {
                dp += 1;
            } else {
                dp = A[i] == A[i - 1] ? 1 : 2;
            }
            
            ret = Math.max(ret, dp);
        }
        
        return ret;
    }
    
    private boolean isTurbulent(int[] A, int i) {
        if (i == 1) {
            return A[i] != A[i - 1];
        } else {
            return (A[i] > A[i - 1] && A[i - 1] < A[i - 2])
                || (A[i] < A[i - 1] && A[i - 1] > A[i - 2]);
        }
    }
}