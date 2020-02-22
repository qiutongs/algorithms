// Find the max frequency of prefix sum values
// Time: O(N) N = all bricks
class Solution {
    public int leastBricks(List<List<Integer>> wall) {
        int h = wall.size();
        HashMap<Integer, Integer> preSumFreqMap = new HashMap<>();
        
        for (int i = 0; i < h; i++) {
            int sum = 0;
            for (int j = 0; j < wall.get(i).size() - 1; j++) {
                sum += wall.get(i).get(j);
                preSumFreqMap.compute(sum, (k, v) -> v == null ? 1 : v + 1);
            }
        }
     
        int maxFreq = 0;
        for (Integer freq : preSumFreqMap.values()) {
            maxFreq = Math.max(maxFreq, freq);
        }
        
        return h - maxFreq;
    }
}