// DP idea
class Solution {
    public int findMaxConsecutiveOnes(int[] nums) {
        int ret = 0, cur = 0;
        for (int num : nums) {
            cur = num == 1 ? 1 + cur : 0;
            ret = Math.max(ret, cur);
        }
        return ret;
    }
}