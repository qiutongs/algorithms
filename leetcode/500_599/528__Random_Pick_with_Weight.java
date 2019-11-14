class Solution {
    private int total = 0;
    private int[] prefixSum = null;
    private Random rand = new Random();
    
    public Solution(int[] w) {
        prefixSum = new int[w.length];
        prefixSum[0] = w[0];
        total = w[0];
        for (int i = 1; i < w.length; i++) {
            total += w[i];
            prefixSum[i] = prefixSum[i - 1] + w[i];
        }
    }
    
    public int pickIndex() {
        int wSum = rand.nextInt(total) + 1;
        int index = Arrays.binarySearch(prefixSum, wSum);
        index = index < 0 ? -index - 1 : index;
        return index;
    }
}

/**
 * Your Solution object will be instantiated and called as such:
 * Solution obj = new Solution(w);
 * int param_1 = obj.pickIndex();
 */