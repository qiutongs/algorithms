public class Solution {
    /**
     * @param nums: an integer array and all positive numbers, no duplicates
     * @param target: An integer
     * @return: An integer
     */
    public int backPackIV(int[] nums, int target) {
        if (nums == null || nums.length == 0 || target <= 0) {
            return 0;
        }
        int[][] dp = new int[nums.length + 1][target + 1];
        for (int i = 0; i < dp.length; i++) {
            dp[i][0] = 1;
        }
        for (int i = 1; i < dp.length; i++) {
            for (int j = 1; j < dp[0].length; j++) {
                for (int k = 0; k * nums[i - 1] <= j; k++) {
                    dp[i][j] += dp[i - 1][j - k * nums[i - 1]];
                }
            }
        }
        return dp[nums.length][target];
    }
}