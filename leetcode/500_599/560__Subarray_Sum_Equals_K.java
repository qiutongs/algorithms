
/*
 * Ref https://www.youtube.com/watch?v=aYfwus5T3Bs
 */

class Solution {
    public int subarraySum(int[] nums, int k) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int n = nums.length;
        int[] preSum = new int[n + 1];
        for (int i = 0; i < n; i++) {
            preSum[i + 1] = preSum[i] + nums[i];
        }
        
        int ret = 0;
        HashMap<Integer, Integer> vfMap = new HashMap<>();
        for (int sum : preSum) {
            Integer freq = vfMap.get(sum - k);
            if (freq != null) {
                ret += freq;
            }
            vfMap.compute(sum, (s, cnt) -> cnt == null ? 1 : cnt + 1);
        }
        return ret;
    }
}