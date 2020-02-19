class Solution {
    private HashMap<Integer, List<Integer>> vilMap = new HashMap<>();
    private Random rand = new Random();
    
    public Solution(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            vilMap.putIfAbsent(nums[i], new ArrayList<>());
            vilMap.get(nums[i]).add(i);
        }
    }
    
    public int pick(int target) {
        List<Integer> idxList = vilMap.get(target);
        return idxList.get(rand.nextInt(idxList.size()));
    }
}

/**
 * Your Solution object will be instantiated and called as such:
 * Solution obj = new Solution(nums);
 * int param_1 = obj.pick(target);
 */