// Ref: https://leetcode.com/problems/split-array-into-consecutive-subsequences/discuss/106514/Python-Easy-Understand-Greedy
class Solution {
    public boolean isPossible(int[] nums) {
        HashMap<Integer, Integer> vfMap = new HashMap<>();
        for (int num : nums) {
            vfMap.compute(num, (k,v) -> v == null ? 1 : v + 1);
        }
        
        HashMap<Integer, Integer> tails = new HashMap<>();
        for (int num : nums) {
            if (vfMap.get(num) == 0) {
                continue;
            }
            vfMap.compute(num, (k,v) -> v - 1);
            if (exist(tails, num - 1)) {
                tails.compute(num - 1, (k,v) -> v - 1);
                tails.compute(num, (k,v) -> v == null ? 1 : v + 1);
            } else {
                if (exist(vfMap, num + 1) && exist(vfMap, num + 2)) {
                    vfMap.compute(num + 1, (k,v) -> v - 1);
                    vfMap.compute(num + 2, (k,v) -> v - 1);
                    tails.compute(num + 2, (k,v) -> v == null ? 1 : v + 1);
                } else {
                    return false;
                }
            }
        }
        return true;
    }
    
    private boolean exist(HashMap<Integer, Integer> map, int key) {
        return map.containsKey(key) && map.get(key) > 0;
    }
}