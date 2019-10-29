class Solution {
    public int threeSumClosest(int[] nums, int target) {
        int ret = 0;
        int min = Integer.MAX_VALUE;
        
        Arrays.sort(nums);
        for (int i = 0; i < nums.length - 2; i++) {
            int pair = twoSumClosest(nums, i + 1, target - nums[i]);
            int sum = pair + nums[i];
            int diff = Math.abs(sum - target);
            if (diff < min) {
                ret = sum;
                min = diff;
            }
        }
        
        return ret;
    }
    
    private int twoSumClosest(int[] nums, int l, int target) {
        int ret = 0;
        int min = Integer.MAX_VALUE;
        int r = nums.length - 1;
        while(l < r) {
            int sum = nums[l] + nums[r];
            if (sum > target) {
                if (sum - target < min) {
                    ret = sum;
                    min = sum - target;
                }
                r--;
            } else {
                if (target - sum < min) {
                    ret = sum;
                    min = target - sum;
                }
                l++;
            }
        }
        return ret;
    }
}