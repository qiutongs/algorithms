// Time: O(N^2)
class Solution {
    public int[] maxSumOfThreeSubarrays(int[] nums, int k) {
        int[] preSum = new int[nums.length + 1];
        for (int i = 0; i < nums.length; i++) {
            preSum[i + 1] = preSum[i] + nums[i];
        }
        
        Result result = dfs(nums, preSum, 0, 0, k, new Result[nums.length][3]);
        int[] ret = new int[result.indexes.size()];
        for (int i = 0; i < ret.length; i++) {
            ret[i] = result.indexes.get(i);
        }
        return ret;
    }
    
    private Result dfs(int[] nums, int[] preSum, int idx, int count, int k, Result[][] memo) {
        if (count == 3) {
            return new Result(0);
        }
        if (memo[idx][count] != null) {
            return memo[idx][count];
        }
        Result ret = new Result(0);
        for (int i = idx; i + (3 - count) * k <= nums.length; i++) {
            // i to i + k - 1
            int curSum = preSum[i + k] - preSum[i];
            Result subret = dfs(nums, preSum, i + k, count + 1, k, memo);
            int tmp = curSum + subret.sum;
            if (curSum + subret.sum > ret.sum) {
                ret.sum = curSum + subret.sum;
                ret.indexes.clear();
                ret.indexes.add(i);
                ret.indexes.addAll(subret.indexes);
            }
        }
        memo[idx][count] = ret;
        return ret;
    }
    
    private class Result {
        int sum;
        List<Integer> indexes;
        Result(int sum) {
            this(sum, new ArrayList<>());
        }
        Result(int sum, List<Integer> indexes) {
            this.sum = sum;
            this.indexes = indexes;
        }
    }
}