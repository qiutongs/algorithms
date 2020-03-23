class Solution {
    public int findMaxLength(int[] nums) {
        int[] preSum = new int[nums.length + 1];
        for (int i = 0; i < nums.length; i++) {
            preSum[i + 1] = preSum[i] + (nums[i] == 0 ? -1 : 1);
        }
        
        int ret = 0;
        HashMap<Integer, Integer> viMap = new HashMap<>();
        for (int i = 0; i < preSum.length; i++) {
            if (viMap.containsKey(preSum[i])) {
                ret = Math.max(ret, i - viMap.get(preSum[i]));
            } else {
                viMap.put(preSum[i], i);
            }
        }
        return ret;
    }
}