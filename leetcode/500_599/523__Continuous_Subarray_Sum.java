class Solution {
    public boolean checkSubarraySum(int[] nums, int k) {
        if (nums == null || nums.length < 2) {
            return false;
        }
        k = k < 0 ? -k : k;
        
        int n = nums.length;
        int[] preSum = new int[n + 1];
        for (int i = 0; i < n; i++) {
            preSum[i + 1] = preSum[i] + nums[i];
        }
        
        HashMap<Integer, Integer> miMap = new HashMap<>();
        for (int i = 0; i < preSum.length; i++) {
            int mod = k == 0 ? preSum[i] : preSum[i] % k;
            
            Integer index = miMap.get(mod);
            if (index == null) {
                miMap.put(mod, i);
            } else {
                if (i - index >= 2) {
                    return true;
                }
            }
        }
        return false;
    }
}