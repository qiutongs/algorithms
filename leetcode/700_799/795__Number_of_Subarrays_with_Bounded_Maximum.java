/* tweaked sliding window */
class Solution {
    public int numSubarrayBoundedMax(int[] A, int L, int R) {
        if (A == null || A.length == 0) {
            return 0;
        }
        int ret = 0;
        int l = 0, inBdIdx = -1;
        for (int r = 0; r < A.length; r++) {
            if (A[r] > R) {
                l = r + 1;
                inBdIdx = l - 1;
            } else if (A[r] < L) {
                ret += inBdIdx - l + 1;
            } else {
                ret += r - l + 1;
                inBdIdx = r;
            }
        }
        return ret;
    }
}

/*
 * This is actually number of subarray SUM with bounded max
 */
class Solution2 {
    public int numSubarrayBoundedMax(int[] A, int L, int R) {
        if (A == null || A.length == 0) {
            return 0;
        }
        
        int ret = 0;
        int l = 0, sum = 0;
        
        for (int r = 0; r < A.length; r++) {
            sum += A[r];
            
            while(l <= r && sum > R) {
                sum -= A[l];
                l++;
            } 
            
            // first <= R
            int tmpL = l;
            int tmpSum = sum;
            
            while(tmpL <= r && tmpSum >= L) {
                tmpSum -= A[tmpL];
                tmpL++;
            }
            
            // now, tmpL is first < L
            
            ret += tmpL - l;
        }
        
        return ret;
    }
}