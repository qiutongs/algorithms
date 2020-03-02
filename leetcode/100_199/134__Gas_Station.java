// Find the max subarray and using the starting index as result
// Time: O(N)
// Space: O(1)
class Solution {
    public int canCompleteCircuit(int[] gas, int[] cost) {
        int sum = 0;
        for (int i = 0; i < gas.length; i++) {
            sum += gas[i] - cost[i];
        }
        if (sum < 0) {
            return -1;
        }
        
        int[] aux = new int[gas.length * 2];
        for (int i = 0; i < gas.length; i++) {
            aux[i] = gas[i] - cost[i];
            aux[i + gas.length] = gas[i] - cost[i];
        }
        return maxSubarray(aux);
    }
    
    private int maxSubarray(int[] aux) {
        int ret = aux.length - 1;
        int max = aux[aux.length - 1];
        int maxStartHere = aux[aux.length - 1];
        
        for (int i = aux.length - 2; i >= 0; i--) {
            maxStartHere = Math.max(aux[i], maxStartHere + aux[i]);
            if (maxStartHere > max) {
                max = maxStartHere;
                ret = i;
            }
        }
        return ret % (aux.length / 2);
    }
}