// forward searching the max index reachable
class Solution {
    public boolean canJump(int[] nums) {
        int farIdx = 0;
        for(int i = 0; i < nums.length; i++) {
            if (i <= farIdx) {
               farIdx = Math.max(farIdx, nums[i] + i); 
            } else {
               break;
            }
        }
        return farIdx >= nums.length - 1;
    }
}

// DP: O(n^2)
class Solution {
    public boolean canJump(int[] nums) {
        boolean[] dp = new boolean[nums.length];
        dp[0] = true;
        for (int i = 1; i < nums.length; i++) {
            for (int j = 0; j < i; j++) {
                if (dp[j] && nums[j] + j >= i) {
                    dp[i] = true;
                }
            }
        }
        return dp[nums.length - 1];
    }
}

//Wrong: [2,5,0,0]
class Solution {
    public boolean canJump(int[] nums) {
        HashSet<Integer> canJumpSet = new HashSet<>();
        for (int i = nums.length - 1; i >= 0; i--) {
            int max = i + nums[i];
            if (max >= nums.length - 1 || canJumpSet.contains(max)) {
                canJumpSet.add(i);
            }
        }
        return canJumpSet.contains(0);
    }
}