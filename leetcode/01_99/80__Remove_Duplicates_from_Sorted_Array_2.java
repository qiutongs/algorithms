class Solution {
    public int removeDuplicates(int[] nums) {
        if (nums == null) {
            return 0;
        } else if (nums.length <= 1) {
            return nums.length;
        }
        int l = 2;
        for (int r = 2; r < nums.length; r++) {
            if (nums[r] != nums[l - 1] || nums[r] != nums[l - 2]) {
                nums[l] = nums[r];
                l++;
            }
        }
        return l;
    }
}