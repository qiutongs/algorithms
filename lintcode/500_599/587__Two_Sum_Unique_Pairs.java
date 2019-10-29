public class Solution {
    /**
     * @param nums an array of integer
     * @param target an integer
     * @return an integer
     */
    public int twoSum6(int[] nums, int target) {
        if (nums == null || nums.length < 2) {
            return 0;
        }

        int ret = 0;

        Arrays.sort(nums);

        int l = 0, r = nums.length - 1;
        while(l < r) {
            int sum = nums[l] + nums[r];
            if (sum < target) {
                l++;
            } else if (sum > target) {
                r--;
            } else {
                ret++;
                l++;
                r--;
                while(l < r && nums[l] == nums[l - 1]) {
                    l++;
                }
                while(l < r && nums[r] == nums[l + 1]) {
                    r--
                }
            }
        }
        
        return ret;
    }
}