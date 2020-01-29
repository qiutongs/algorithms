class Solution {
    public int[] productExceptSelf(int[] nums) {
        if (nums == null || nums.length == 0) {
            return nums;
        }
        int[] ret = new int[nums.length];
        int product = 1;
        for (int i = 0; i < ret.length; i++) {
            ret[i] = product;
            product *= nums[i];
        }
        product = 1;
        for (int i = ret.length - 1; i >= 0; i--) {
            ret[i] *= product;
            product *= nums[i];
        }
        return ret;
    }
}