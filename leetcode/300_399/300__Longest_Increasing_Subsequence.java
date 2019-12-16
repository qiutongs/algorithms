// DP idea: O(n^2)
class Solution {
    public int lengthOfLIS(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int[] dp = new int[nums.length];
        Arrays.fill(dp, 1);
        int ret = 1;
        for (int i = 1; i < nums.length; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            ret = Math.max(ret, dp[i]);
        }
        return ret;
    }
}

/*
Patience Sort: 
- simulate patience game (first phase of patience sort).
- the number of piles is the result of LIS
*/
class Solution2 {
    public int lengthOfLIS(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        
        int[] piles = new int[nums.length];
        int size = 0;
        
        for (int num : nums) {
            int insertIndex = binarySearch(piles, size - 1, num);
            
            piles[insertIndex] = num;
            
            if (insertIndex == size) {
                size++;
            }
        }

        return size;
    }
    
    private int binarySearch(int[] piles, int high, int target) {
        int low = 0;
        
        while(low <= high) {
            int mid = (low + high) >>> 1;
                
            if (target < piles[mid]) {
                high = mid - 1;
            } else if (target > piles[mid]) {
                low = mid + 1;
            } else {
                return mid;
            }
        }
        
        return low;
    }
}