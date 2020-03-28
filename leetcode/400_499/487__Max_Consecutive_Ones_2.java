// DP
class Solution {
    public int findMaxConsecutiveOnes(int[] nums) {
        int ret = 0;
        int curNoFlip = 0, curFlip = 0;
        for (int num : nums) {
            if (num == 1) {
                curNoFlip = curNoFlip + 1;
                curFlip = curFlip + 1;
            } else {
                curFlip = curNoFlip + 1;
                curNoFlip = 0;
            }
            ret = Math.max(ret, curNoFlip);
            ret = Math.max(ret, curFlip);
        }
        return ret;
    }
}

// Sliding window: max zero is 1
class Solution {
    public int findMaxConsecutiveOnes(int[] nums) {
        int ret = 0;
        int curZeros = 0, maxZeros = 1;
        int l = 0;
        for (int r = 0; r < nums.length; r++) {
            curZeros += nums[r] == 1 ? 0 : 1;
            while(l <= r && curZeros > maxZeros) {
                curZeros -= nums[l] == 1 ? 0 : 1;
                l++;
            }
            ret = Math.max(ret, r - l + 1);
        }
        return ret;
    }
}