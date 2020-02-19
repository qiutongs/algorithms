// Binary search
// Time: O(NlogN)
// Space: O(1)
class Solution {
    public int missingElement(int[] nums, int k) {
        int len = nums.length;
        int range = nums[len - 1] - nums[0] + 1;
        int missed = range - len;
        if (k > missed) {
            return nums[len - 1] + k - missed;
        }
        
        int l = 0, r = len - 1;
        while(l + 1 < r) {
            int mid = (l + r) / 2;
            int leftMissed = nums[mid] - nums[l] - (mid - l);
            if (k <= leftMissed) {
                r = mid;
            } else {
                l = mid;
                k -= leftMissed;
            }
        }
        return nums[l] + k;
    }
}