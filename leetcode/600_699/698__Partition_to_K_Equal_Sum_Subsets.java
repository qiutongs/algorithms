class Solution {
    private int SIDE_LEN;
        
    public boolean canPartitionKSubsets(int[] nums, int k) {
        if (nums == null || nums.length == 0) {
            return false;
        }
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        if (sum % k != 0) {
            return false;
        }
        SIDE_LEN = sum / k;
        return dfs(nums, 0, new boolean[nums.length], k, 0);
    }
    
    private boolean dfs(int[] nums, int offset, boolean[] visited, int k, int curL) {
        if (k == 0) {
            return true;
        }
        
        for (int i = offset; i < nums.length; i++) {
            if (visited[i]) {
                continue;
            }
            int l = curL + nums[i];
            if (l < SIDE_LEN) {
                visited[i] = true;
                if (dfs(nums, i + 1, visited, k, l)) {
                    return true;
                }
                visited[i] = false;
            } else if (l == SIDE_LEN) {
                visited[i] = true;
                if (dfs(nums, 0, visited, k - 1, 0)) {
                    return true;
                }
                visited[i] = false;
            }
        }
        
        return false;
    }
}