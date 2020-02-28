// Hashtable
// Time: O(N)
// Space: O(N)
class Solution {
    public int majorityElement(int[] nums) {
        HashMap<Integer, Integer> vfMap = new HashMap<>();
        for (int num : nums) {
            vfMap.compute(num, (k, v) -> v == null ? 1 : v + 1);
        }
        for (Map.Entry<Integer, Integer> entry : vfMap.entrySet()) {
            if (entry.getValue().intValue() > nums.length / 2) {
                return entry.getKey();
            }
        }
        return -1;
    }
}

// Majority Vote algorithm
// Time: O(N)
// Space: O(1)
class Solution {
    public int majorityElement(int[] nums) {
        int count = 0;
        int cur = 0;
        for (int num : nums) {
            if (count == 0) {
                cur = num;
                count = 1;
            } else {
                if (num == cur) {
                    count++;
                } else {
                    count--;
                }
            }
        }
        return cur;
    }
}