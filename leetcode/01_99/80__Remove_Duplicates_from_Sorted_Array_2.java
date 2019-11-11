class Solution {
    public int removeDuplicates(int[] nums) {
        if (nums == null) {
            return 0;
        }
        if (nums.length <= 2) {
            return nums.length;
        }
        int l = 2;
        int prev = nums[1], pprev = nums[0];
        for (int r = 2; r < nums.length; r++) {
            if (nums[r] > prev || nums[r] > pprev) {
                pprev = prev;
                prev = nums[r];
                nums[l] = nums[r];
                l++;
            }
        }
        return l;
    }
}
