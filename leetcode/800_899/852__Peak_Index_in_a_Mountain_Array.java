class Solution {
    public int peakIndexInMountainArray(int[] A) {
        int l = 0, r = A.length - 1;
        
        while(l <= r) {
            int mid = (l + r) / 2;
            
            if (mid == 0 || A[mid] > A[mid - 1]) {
                l = mid + 1;
            } else {
                r = mid - 1;
            }
        }
        
        return r;
    }
}