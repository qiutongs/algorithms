class Solution {
    public int totalHammingDistance(int[] nums) {
        int[] oneCounts = new int[32];
        int[] zeroCounts = new int[32];
        for (int num : nums) {
            for (int i = 0; i < 32; i++) {
                if (num % 2 == 1) {
                    oneCounts[i]++;
                } else {
                    zeroCounts[i]++;
                }
                num >>>= 1;
            }
        }
        
        int ret = 0;
        for (int i = 0; i < 32; i++) {
            ret += oneCounts[i] * zeroCounts[i];
        }
        return ret;
    }
}