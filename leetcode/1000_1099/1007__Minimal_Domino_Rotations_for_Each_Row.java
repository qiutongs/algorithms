class Solution {
    public int minDominoRotations(int[] A, int[] B) {
        int majorA = majorElement(A);
        int majorB = majorElement(B);
        
        int ret = Integer.MAX_VALUE;
        if (majorA != -1) {
            int swap = minSwap(A, B, majorA);
            if (swap != -1) {
                ret = Math.min(ret, swap);
            }
        }
        if (majorB != -1) {
            int swap = minSwap(B, A, majorB);
            if (swap != -1) {
                ret = Math.min(ret, swap);
            }
        }
        return ret == Integer.MAX_VALUE ? -1 : ret;
    }
    
    private int minSwap(int[] A, int[] B, int majorA) {
        int swap = 0;
        for (int i = 0; i < A.length; i++) {
            if (A[i] != majorA) {
                if (B[i] == majorA) {
                    swap++;
                } else {
                    return -1;   
                }
            }
        }
        return swap;
    }
    
    private int majorElement(int[] array) {
        HashMap<Integer, Integer> vfMap = new HashMap<>();
        for (int num : array) {
            vfMap.compute(num, (k, v) -> v == null ? 1 : v + 1);
        }
        for (Map.Entry<Integer, Integer> entry : vfMap.entrySet()) {
            if (entry.getValue().intValue() >= (array.length + 1) / 2) {
                return entry.getKey();
            }
        }
        return -1;
    }
}