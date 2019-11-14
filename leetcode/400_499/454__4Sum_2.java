// Time Limit Exceed
class Solution {
    public int fourSumCount(int[] A, int[] B, int[] C, int[] D) {
        Arrays.sort(A);
        Arrays.sort(B);
        Arrays.sort(C);
        Arrays.sort(D);
        int ret = 0, prevSum = 0;
        for (int l = 0; l < D.length; l++) {
            if (l > 0 && D[l] == D[l - 1]) {
                ret += prevSum;
            } else {
                prevSum = threeSumCount(A, B, C, -D[l]);
                ret += prevSum;
            }
        }
        return ret;
    }

    private int threeSumCount(int[] A, int[] B, int[] C, int target) {
        int ret = 0, prevSum = 0;
        for (int k = 0; k < C.length; k++) {
            if (k > 0 && C[k] == C[k - 1]) {
                ret += prevSum;
            } else {
                prevSum = twoSumCount(A, B, target - C[k]);
                ret += prevSum;
            }
        }
        return ret;
    }
    
    private int twoSumCount(int[] A, int[] B, int target) {
        HashMap<Integer, Integer> vfMap = new HashMap<>();
        for (int aNum : A) {
            vfMap.compute(aNum, (k,v) -> v == null ? 1 : v + 1);
        }
        int ret = 0;
        for (int bNum : B) {
            ret += vfMap.getOrDefault(target - bNum, 0);
        }
        return ret;
    }
}