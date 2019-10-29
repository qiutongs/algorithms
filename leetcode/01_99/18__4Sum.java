class Solution1 {
    public List<List<Integer>> fourSum(int[] nums, int target) {
        if (nums == null || nums.length < 4) {
            return Collections.emptyList();
        }
        
        Arrays.sort(nums);
        
        List<List<Integer>> ret = new LinkedList<>();

        for (int i = nums.length - 1; i >= 3; i--) {
            if (i + 1 < nums.length && nums[i] == nums[i + 1]) {
                continue;
            }
            List<List<Integer>> threeSumRet = threeSum(nums, i - 1, target -nums[i]);
            for (List<Integer> triple : threeSumRet) {
                triple.add(nums[i]);
                ret.add(triple);
            }
        }
        
        return ret;
    }
    
    private List<List<Integer>> threeSum(int[] nums, int r, int target) {
        List<List<Integer>> ret = new LinkedList<>();

        for (int i = r; i >= 2; i--) {
            if (i + 1 <= r && nums[i] == nums[i + 1]) {
                continue;
            }
            List<List<Integer>> twoSumRet = twoSum(nums, i - 1, target - nums[i]);
            for (List<Integer> pair : twoSumRet) {
                pair.add(nums[i]);
                ret.add(pair);
            }
        }
        
        return ret;
    }
    
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
                List<Integer> pair = new ArrayList<>(4);
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
}

// http://www.lifeincode.net/programming/leetcode-two-sum-3-sum-3-sum-closest-and-4-sum-java/
class Solution2 {
    public List<List<Integer>> fourSum(int[] nums, int target) { 
        Set<List<Integer>> ret = new HashSet<>();
        HashMap<Integer, List<Pair>> valPairs = buildMap(nums);
        
        valPairs.forEach((v1, pairs1) -> {
            List<Pair> pairs2 = valPairs.get(target - v1);
            if (pairs2 != null) {
                for (Pair p1 : pairs1) {
                    for (Pair p2: pairs2) {
                        if (isOverlap(p1, p2) == false) {
                            List<Integer> ans = Arrays.asList(nums[p1.i1], nums[p1.i2], nums[p2.i1], nums[p2.i2]);
                            Collections.sort(ans);
                            ret.add(ans);
                        } 
                    }
                }
            }
        });
        
        return new ArrayList<>(ret);
    }
    
    private HashMap<Integer, List<Pair>> buildMap(int[] nums) {
        HashMap<Integer, List<Pair>> ret = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                int sum = nums[i] + nums[j];
                Pair p = new Pair(i, j);
                if (ret.containsKey(sum)) {
                    ret.get(sum).add(p);
                } else {
                    List<Pair> list = new ArrayList<>();
                    list.add(p);
                    ret.put(sum, list);
                }
            }
        }
        return ret;
    }
    
    private boolean isOverlap(Pair p1, Pair p2) {
        return p1.i1 == p2.i1 || p1.i2 == p2.i2 || p1.i1 == p2.i2 || p1.i2 == p2.i1;
    }
    
    private class Pair {
        int i1;
        int i2;
        Pair(int i1, int i2) {
            this.i1 = i1;
            this.i2 = i2;
        }
    }
}