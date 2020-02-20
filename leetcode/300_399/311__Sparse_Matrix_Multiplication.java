/*
Given two sparse matrices(https://en.wikipedia.org/wiki/Sparse_matrix) A and B, return the result of AB.

You may assume that A's column number is equal to B's row number.
*/

// Brute force
// Time: O(M1 * M2 * N), 1ms
class Solution {
    public int[][] multiply(int[][] A, int[][] B) {
        int m1 = A.length, m2 = B[0].length;
        int[][] ret = new int[m1][m2];
        
        for (int rowA = 0; rowA < m1; rowA++) {
            for (int colB = 0; colB < m2; colB++) {
                ret[rowA][colB] = dot(A, rowA, B, colB);
            }
        }
        return ret;
    }
    
    private int dot(int[][] A, int rowA, int[][] B, int colB) {
        int ret = 0;
        for (int i = 0; i < B.length; i++) {
            ret += A[rowA][i] * B[i][colB];
        }
        return ret;
    }
}