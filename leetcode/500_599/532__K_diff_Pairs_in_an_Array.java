class Solution {
    public int findPairs(int[] nums, int k) {
        if (nums == null || nums.length == 0 || k < 0) {
            return 0;
        }
        HashMap<Integer, Integer> vfMap = new HashMap<>();
        for (int num : nums) {
            vfMap.compute(num, (v, f) -> f == null ? 1 : f + 1);
        }
        int ret = 0;
        if (k == 0) {
            for (int val : vfMap.keySet()) {
                if (vfMap.get(val) > 1) {
                    ret++;
                }
            }
        } else {
            for (int val : vfMap.keySet()) {
                if (vfMap.containsKey(val - k)) {
                    ret++;
                }
            }
        }
        return ret;
    }
}