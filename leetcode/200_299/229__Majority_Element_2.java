// Hashtable
class Solution {
    public List<Integer> majorityElement(int[] nums) {
        HashMap<Integer, Integer> vfMap = new HashMap<>();
        for (int num : nums) {
            vfMap.compute(num, (k, v) -> v == null ? 1 : v + 1);
        }
        List<Integer> ret = new ArrayList<>();
        for (Map.Entry<Integer, Integer> entry : vfMap.entrySet()) {
            if (entry.getValue().intValue() > nums.length / 3) {
                ret.add(entry.getKey());
            }
        }
        return ret;
    }
}

// Variation of Majority Vote
class Solution {
    public List<Integer> majorityElement(int[] nums) {
        int count1 = 0, count2 = 0;
        Integer cur1 = null, cur2 = null;
        for (int num : nums) {
            if (cur1 != null && cur1.intValue() == num) {
                count1++;
            } else if (cur2 != null && cur2.intValue() == num) {
                count2++;
            } else {
                if (count1 == 0) {
                    cur1 = num;
                    count1 = 1;
                } else if (count2 == 0) {
                    cur2 = num;
                    count2 = 1;
                } else {
                    count1--;
                    count2--;
                }
            }
        }

        List<Integer> ret = new ArrayList<>();
        if (isMajority(nums, cur1)) {
            ret.add(cur1);
        }
        if (isMajority(nums, cur2)) {
            ret.add(cur2);
        }
        return ret;
    }
    
    private boolean isMajority(int[] nums, Integer target) {
        if (target == null) {
            return false;
        }
        int f = 0;
        for (int num : nums) {
            if (num == target.intValue()) {
                f++;
            }
        }
        return f > nums.length / 3;
    }
}