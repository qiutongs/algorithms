public class Solution {
    /**
     * @param nums: The integer array you should partition
     * @param k: An integer
     * @return: The index after partition
     */
    public int partitionArray(int[] nums, int k) {
        int l = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] < k) {
                int tmp = nums[l];
                nums[l++] = nums[i];
                nums[i] = tmp;
            }
        }
        return l;
    }
}