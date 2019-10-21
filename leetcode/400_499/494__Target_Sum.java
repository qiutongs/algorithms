class Solution {
    public int findTargetSumWays(int[] nums, int S) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        
        int[] ret = { 0 };
        dfs(nums, 0, S, ret);
        return ret[0];
    }
    
    private void dfs(int[] nums, int index, int target, int[] ret) {
        if (index == nums.length) {
            if (target == 0) {
                ret[0]++;
            }
            return;
        }
        
        dfs(nums, index + 1, target + nums[index], ret);
        dfs(nums, index + 1, target - nums[index], ret);
    }
}