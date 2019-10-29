class Solution {
    public int triangleNumber(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        
        int ret = 0;
        
        Arrays.sort(nums);
        
        for (int i = nums.length - 1; i >=0; i--) {
            ret += twoSum(nums, i - 1, nums[i]);
        }
        
        return ret;
    }
    
    private int twoSum(int[] nums, int r, int target) {
        int ret = 0;
        int l = 0;
        while(l < r) {
            int sum = nums[l] + nums[r];
            if (sum > target) {
                ret += r - l;
                r--;
            } else {
                l++;
            }
        }
        return ret;
    }
}