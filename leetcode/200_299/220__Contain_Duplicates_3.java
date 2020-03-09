// Buckets
class Solution {
    public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
        if (nums == null || nums.length == 0 || k <= 0 || t < 0) {
            return false;
        }
        long bucketSize = t + 1;
        HashMap<Long, Element> buckets = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            long bucketId = nums[i] / bucketSize;
            for (long j = bucketId - 1; j <= bucketId + 1; j++) {
                Element e = buckets.get(j);
                if (e != null && Math.abs(nums[i] - e.val) <= t && Math.abs(i - e.index) <= k) {
                    return true;
                }
            }
            buckets.put(bucketId, new Element(i, nums[i]));
        }
        return false;
    }
    
    private class Element {
        long val;
        long index;
        Element(int index, int val) {
            this.index = index;
            this.val = val;
        }
    }
}