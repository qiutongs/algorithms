// DFS + memo
class Solution {
    public int combinationSum4(int[] nums, int target) {
        Integer[] memo = new Integer[target + 1];
        return dfs(nums, target, memo);
    }
    
    private int dfs(int[] nums, int target, Integer[] memo) {
        if (target == 0) {
            return 1;
        }
        if (memo[target] != null) {
            return memo[target];
        }
        int ret = 0;
        for (int num : nums) {
            if (num <= target) {
                ret += dfs(nums, target - num, memo);
            }
        }
        memo[target] = ret;
        return ret;
    } 
}