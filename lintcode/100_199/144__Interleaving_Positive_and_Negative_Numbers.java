public class Solution {
    /*
     * @param A: An integer array.
     * @return: nothing
     */
    public void rerange(int[] A) {
        int l = 0;
        for (int r = 0; r < A.length; r++) {
            if (A[r] < 0) {
                swap(A, r, l);
                l++;
            }
        }

        int i = l > A.length - l ? 1 : 0;
        int j = A.length - l > l ? A.length - 2 : A.length - 1;
        while(i < j) {
            swap(A, i, j);
            i += 2;
            j -= 2;
        }
    }
    
    private void swap(int[] A, int i, int j) {
        int tmp = A[i];
        A[i] = A[j];
        A[j] = tmp;
    }
}