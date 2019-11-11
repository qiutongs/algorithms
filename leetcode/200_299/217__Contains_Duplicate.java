class Solution {
    public boolean containsDuplicate(int[] nums) {
        HashSet<Integer> vSet = new HashSet<>();
        for (int num : nums) {
            if (vSet.contains(num)) {
                return true;
            } else {
                vSet.add(num);
            }
        }
        return false;
    }
}