class Solution1 {
    public boolean isIdealPermutation(int[] A) {
        return getLocal(A) == getGlobal(A);
    }
    
    private int getLocal(int[] A) {
        int ret = 0;
        for (int i = 0; i + 1 < A.length; i++) {
            if (A[i] > A[i + 1]) {
                ret++;
            }
        }
        return ret;
    }
    
    private int getGlobal(int[] A) {
        return mergeAndCount(A, 0, A.length - 1);
    }
    
    private int mergeAndCount(int[] A, int l, int r) {
        if (l == r) {
            return 0;
        }
        
        int mid = (l + r) / 2;
        int left = mergeAndCount(A, l, mid);
        int right = mergeAndCount(A, mid + 1, r);
        return merge(A, l, mid, r) + left + right;
    }
    
    private int merge(int[] A, int l, int mid, int r) {
        int ret = 0;
        List<Integer> tmp = new ArrayList<>();
        int p1 = l, p2 = mid + 1;
        while(p1 <= mid && p2 <= r) {
            if (A[p1] <= A[p2]) {
                tmp.add(A[p1++]);
            } else {
                tmp.add(A[p2++]);
                ret += mid - p1 + 1;
            }
        }
        while(p1 <= mid) {
            tmp.add(A[p1++]);
        }
        while(p2 <= r) {
            tmp.add(A[p2++]);
        }
        for (int i = l; i <= r; i++) {
            A[i] = tmp.get(i - l);
        }
        return ret;
    }
}

class Solution2 {
    public boolean isIdealPermutation(int[] A) {
        int maxPreTwo = Integer.MIN_VALUE;
        
        for (int i = 2; i < A.length; i++) {
            maxPreTwo = Math.max(maxPreTwo, A[i - 2]);
            if (A[i] < maxPreTwo) {
                return false;
            }
        }
        
        return true;
    }
}