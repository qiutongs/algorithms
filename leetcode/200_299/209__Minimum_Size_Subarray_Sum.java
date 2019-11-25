// Simplified sliding window algorithm
class Solution {
    public int minSubArrayLen(int s, int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int ret = Integer.MAX_VALUE;
        int l = 0, sum = 0;
        for (int r = 0; r < nums.length; r++) {
            sum += nums[r];
            while(l <= r && sum >= s) {
                ret = Math.min(ret, r - l + 1);
                sum -= nums[l];
                l++;
            }
        }
        return ret == Integer.MAX_VALUE ? 0 : ret;
    }
}

// binary search
class Solution {
    public int minSubArrayLen(int s, int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        
        int ret = Integer.MAX_VALUE;
        
        int[] prefixSum = new int[nums.length + 1];
        prefixSum[0] = 0;
        
        for (int i = 1; i < prefixSum.length; i++) {
            prefixSum[i] = prefixSum[i - 1] + nums[i - 1];
        }
        
        for (int i = 1; i < prefixSum.length; i++) {
            int j = binarySearch(prefixSum, 0, i - 1, prefixSum[i] - s);
            // j is -1 if not found
            if (j >= 0) {
                ret = Math.min(ret, i - j);
            }
        }
        
        return ret == Integer.MAX_VALUE ? 0 : ret;
    }
    
    // find last prefixSum <= target
    private int binarySearch(int[] prefixSum, int l, int r, int target) {
        while(l <= r) {
            int mid = (l + r) / 2;
            
            if (target >= prefixSum[mid]) {
                l = mid + 1;
            } else {
                r = mid - 1;
            }
        }
        
        return r;
    }
}