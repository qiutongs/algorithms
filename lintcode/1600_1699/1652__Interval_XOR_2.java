/**
 * Definition of Interval:
 * public classs Interval {
 *     int start, end;
 *     Interval(int start, int end) {
 *         this.start = start;
 *         this.end = end;
 *     }
 * }
 */

/*
Given an integer array A(index from 0 to n-1, where n is the size of this array), 
and an query list. Each query has two integers i and k. For each query, 
calculate the XOR of the numbers Ai, A(i + 1), ..., A(i+k-1).return the result list.
*/
public class Solution {
    /**
     * @param A: 
     * @param query: 
     * @return: nothing
     */
    public List<Integer> intervalXOR(int[] A, List<Interval> query) {
        int[] prefixXOR = new int[A.length + 1];
        for (int i = 1; i < prefixXOR.length; i++) {
            prefixXOR[i] = prefixXOR[i - 1] ^ A[i - 1];
        }
        List<Integer> ret = new ArrayList<>();
        for (Interval inter : query) {
            ret.add(prefixXOR[inter.start + inter.end] ^ prefixXOR[inter.start]);
        }
        return ret;
    }
}