// DP: max sum when M ending at i
// Time: O(n)
// Space: O(1)
class Solution {
    public int maxSumTwoNoOverlap(int[] A, int L, int M) {
        if (A == null || A.length == 0 || L < 0 || M < 0 || L + M > A.length) {
            return 0;
        }
        return Math.max(helper(A, L, M), helper(A, M, L));
    }
    
    private int helper(int[] A, int L, int M) {
        int lSum = 0, mSum = 0;
        for (int i = 0; i < L + M; i++) {
            if (i < L) {
                lSum += A[i];
            } else {
                mSum += A[i];
            }
        }
        
        int ret = lSum + mSum;
        int lMax = lSum;
        for (int i = L + M; i < A.length; i++) {
            mSum += A[i] - A[i - M];
            lSum += A[i - M] - A[i - M - L];
            lMax = Math.max(lMax, lSum);
            ret = Math.max(ret, lMax + mSum);
        }
        return ret;
    }
}

// Prefix Sum
// Time: O(n^2)
// Space: O(n)
class Solution {
    public int maxSumTwoNoOverlap(int[] A, int L, int M) {
        int[] preSum = new int[A.length + 1];
        for (int i = 0; i < A.length; i++) {
            preSum[i + 1] = preSum[i] + A[i];
        }
        int ret = 0;
        for (int i = L; i + M <= A.length; i++) {
            int leftMax = getMax(preSum, 0, i - 1, L);
            int rightMax = getMax(preSum, i, A.length - 1, M);
            ret = Math.max(ret, leftMax + rightMax);
        }
        for (int i = M; i + L <= A.length; i++) {
            int leftMax = getMax(preSum, 0, i - 1, M);
            int rightMax = getMax(preSum, i, A.length - 1, L);
            ret = Math.max(ret, leftMax + rightMax);
        }
        return ret;
    }
    
    private int getMax(int[] preSum, int l, int r, int len) {
        int ret = 0;
        for (int i = l; i + len <= r + 1; i++) {
            ret = Math.max(ret, preSum[i + len] - preSum[i]);
        }
        return ret;
    }
}