// Prefix + counts
// Time: O(N + K)
// Space: O(N + K)
class Solution {
    public int subarraysDivByK(int[] A, int K) {
        int[] preSum = new int[A.length + 1];
        for (int i = 0; i < A.length; i++) {
            preSum[i + 1] = preSum[i] + A[i];
        }
        
        int[] counts = new int[K];
        for (int sum : preSum) {
            counts[(sum % K + K) % K]++;
        }
        
        int ret = 0;
        for (int count : counts) {
            if (count > 0) {
                ret += count * (count - 1) / 2;
            }
        }
        return ret;
    }
}