// DFS + memo
class Solution {
    public int findTargetSumWays(int[] nums, int S) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        HashMap<Integer, Integer>[] memo = new HashMap[nums.length];
        for (int i = 0; i < memo.length; i++) {
            memo[i] = new HashMap<>();
        }
        return dfs(nums, 0, S, memo);
    }
    
    private int dfs(int[] nums, int offset, int target, HashMap<Integer, Integer>[] memo) {
        if (offset == nums.length) {
            return target == 0 ? 1 : 0;
        }
        if (memo[offset].get(target) != null) {
            return memo[offset].get(target);
        }
        int ret = 0;
        ret += dfs(nums, offset + 1, target + nums[offset], memo);
        ret += dfs(nums, offset + 1, target - nums[offset], memo);
        memo[offset].put(target, ret);
        return ret;
    }
}

// Raw DFS - top down
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