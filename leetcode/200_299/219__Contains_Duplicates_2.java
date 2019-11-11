class Solution {
    public boolean containsNearbyDuplicate(int[] nums, int k) {
        HashMap<Integer, Integer> viMap = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            Integer index = viMap.get(nums[i]); 
            if (index != null && i - index <= k) {
                return true;
            } else {
                viMap.put(nums[i], i);
            }
        }
        return false;
    }
}