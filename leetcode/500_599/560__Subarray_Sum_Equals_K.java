
/*
 * Ref https://www.youtube.com/watch?v=aYfwus5T3Bs
 */

class Solution {
    public int subarraySum(int[] nums, int k) {
        int ret = 0;
        
        int[] preSum = new int[nums.length + 1];
        for(int i = 0; i < nums.length; i++) {
            preSum[i + 1] = preSum[i] + nums[i];
        }
        
        HashMap<Integer, Integer> hashMap = new HashMap<>();
        hashMap.put(0, 1);
        
        for (int i = 1; i < preSum.length; i++) {
            if (hashMap.containsKey(preSum[i] - k)) {
                ret += hashMap.get(preSum[i] - k);
            }
            hashMap.compute(preSum[i], (key, v) -> v == null ? 1 : v + 1);
        }
        return ret;
    }
}