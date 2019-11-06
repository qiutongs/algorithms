// Ref http://bangbingsyb.blogspot.com/2014/11/leetcode-next-permutation.html?m=1
class Solution {
    public void nextPermutation(int[] nums) {
        if (nums == null || nums.length == 0) {
            return;
        }
        
        int i = nums.length - 2;
        for (; i >= 0; i--) {
            if (nums[i] < nums[i + 1]) {
                break;
            }
        }
        
        if (i >= 0) {
            int j = nums.length - 1;
            for (; j > i; j--) {
                if (nums[j] > nums[i]) {
                    break;
                }
            }

            swap(nums, i, j);
        }

        reverse(nums, i + 1, nums.length - 1);
    }
    
    private void reverse(int[] nums, int i, int j) {
        while(i < j) {
            swap(nums, i, j);
            i++;
            j--;
        }
    }
    
    private void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }
}