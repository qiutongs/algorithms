class Solution {
    public int findDuplicate(int[] nums) {
        int l = 1, r = nums.length - 1;
        
        while(l <= r) {
            int mid = (l + r) / 2;
            int[] counts = new int[2];
            getCount(nums, mid, counts);

            if (counts[1] > 1) {
                return mid;
            } else if (counts[0] >= mid) {
                r = mid - 1;
            } else {
                l = mid + 1;
            }
        }
        throw new RuntimeException();
    }
    
    private void getCount(int[] nums, int target, int[] counts) {
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] < target) {
                counts[0]++;
            } else if (nums[i] == target) {
                counts[1]++;
            }
        }
    }
}

// https://leetcode.com/problems/find-the-duplicate-number/discuss/433464/O(N)-time-O(1)-memory-Java-solution-like-a-boss
class Solution {
    public int findDuplicate(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            int tmp = Math.abs(nums[i]);
            if (nums[tmp-1] > 0) {
                nums[tmp-1] = -nums[tmp-1];
            } else {
                return tmp;
            }
        }
        return 0;
    }
}