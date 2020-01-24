class Solution {
    public int findPeakElement(int[] nums) {
        int l = 0, r = nums.length - 1;
        
        while(l <= r) {
            int mid = (l + r) / 2;
            
            if (mid > 0 && nums[mid] < nums[mid - 1]) {
                r = mid - 1;
            } else if (mid < nums.length - 1 && nums[mid] < nums[mid + 1]) {
                l = mid + 1;
            } else {
                return mid;
            }
        }
        
        return -1;
    }
}