// Sort + count
class Solution {
    public int minIncrementForUnique(int[] A) {
        Arrays.sort(A);
        int ret = 0;
        for (int i = 1; i < A.length; i++) {
            if (A[i] <= A[i - 1]) {
                ret += A[i - 1] + 1 - A[i];
                A[i] = A[i - 1] + 1;
            }
        }
        return ret;
    }
}