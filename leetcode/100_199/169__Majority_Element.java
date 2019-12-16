class Solution {
    public int majorityElement(int[] nums) {
        int ret = nums[0], count = 1;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] == ret) {
                count++;
            } else if (count == 0) {
                ret = nums[i];
                count = 1;
            } else {
                count--;
            }
        }
        return ret;
    }
}