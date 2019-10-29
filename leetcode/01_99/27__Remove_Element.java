class Solution {
    public int removeElement(int[] nums, int val) {
        int l = 0; // next position to put non-val
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != val) {
                int tmp = nums[l];
                nums[l++] = nums[i];
                nums[i] = tmp;
            }
        }
        return l;
    }
}