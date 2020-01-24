public class Solution {
    /**
     * @param L: Given n pieces of wood with length L[i]
     * @param k: An integer
     * @return: The maximum length of the small pieces
     */
    public int woodCut(int[] L, int k) {
        if (L == null || L.length == 0 || k < 0) {
            return 0;
        }
        int maxL = getMax(L);
        int l = 1, r = maxL;
        while(l <= r) {
            int mid = l + (r - l) / 2;
            if (isValid(L, k, mid)) {
                l = mid + 1;
            } else {
                r = mid - 1;
            }
        }
        return r;
    }
    
    private boolean isValid(int[] L, int k, int target) {
        int sum = 0;
        for (int l : L) {
            sum += l / target;
        }
        return sum >= k;
    }
    
    private int getMax(int[] L) {
        int ret = L[0];
        for (int l : L) {
            ret = Math.max(ret, l);
        }
        return ret;
    }
}