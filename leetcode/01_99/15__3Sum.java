class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        if (nums == null || nums.length < 3) {
            return Collections.emptyList();
        }
        
        List<List<Integer>> ret = new LinkedList<>();
        
        Arrays.sort(nums);
        
        for (int i = nums.length - 1; i >= 2; i--) {
            if (i + 1 < nums.length && nums[i] == nums[i + 1]) {
                continue;
            }
            List<List<Integer>> twoSumRet = twoSum(nums, i - 1, -nums[i]);
            for (List<Integer> pair : twoSumRet) {
                pair.add(nums[i]);
                ret.add(pair);
            }
        }
        
        return ret;
    }
    // two sum with all unique pairs
    private List<List<Integer>> twoSum(int[] nums, int r, int target) {
        List<List<Integer>> ret = new LinkedList<>();
        
        int l = 0;
        while(l < r) {
            int sum = nums[l] + nums[r];
            if (sum < target) {
                l++;
            } else if (sum > target) {
                r--;
            } else {
                List<Integer> pair = new ArrayList<>();
                pair.add(nums[l]);
                pair.add(nums[r]);
                ret.add(pair);
                l++;
                r--;
                while(l < r && nums[l] == nums[l - 1]) {
                    l++;
                }
                while(l < r && nums[r] == nums[r + 1]) {
                    r--;
                }
            }
        }
        return ret;
    }

    // alternative
    private List<List<Integer>> twoSum(int[] nums, int r, int target) {
        List<List<Integer>> ret = new LinkedList<>();
        
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i <= r; i++) {
            map.compute(nums[i], (k,v) -> v == null ? 1 : v + 1);
        }
        
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            int k1 = entry.getKey();
            int k2 = target - k1;
            
            if (k1 <= k2) {
                Integer v2 = map.get(k2);
                if (v2 != null) {
                    if (k1 == k2 && v2.intValue() == 1) {
                        continue;
                    }
                    List<Integer> pair = new ArrayList<>();
                    pair.add(k1);
                    pair.add(k2);
                    ret.add(pair);
                }
            }
        }
        
        return ret;
    }
}