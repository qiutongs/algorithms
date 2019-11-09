class Solution {
    public List<Integer> largestDivisibleSubset(int[] nums) {
        if (nums == null || nums.length == 0) {
            return Collections.emptyList();
        }
        
        Arrays.sort(nums);
        
        int[] dp = new int[nums.length];
        int[] prev = new int[nums.length];
        Arrays.fill(dp, 1);
        for (int i = 0; i < nums.length; i++) {
            prev[i] = i;
        }
        
        for (int i = 1; i < nums.length; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[i] % nums[j] == 0) {
                    if (dp[i] < dp[j] + 1) {
                        dp[i] = dp[j] + 1;
                        prev[i] = j;
                    }
                }
            }
        }
        
        List<Integer> ret = new ArrayList<>();
        int maxIdx = 0;
        for (int i = 1; i < nums.length; i++) {
            maxIdx = dp[i] > dp[maxIdx] ? i : maxIdx;
        }
        ret.add(nums[maxIdx]);
        while(maxIdx != prev[maxIdx]) {
            maxIdx = prev[maxIdx];
            ret.add(nums[maxIdx]);;
        }
        return ret;
    }
}