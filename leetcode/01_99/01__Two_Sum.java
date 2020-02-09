// Hashtable: value to index and one pass
// Time O(n) 
// Space O(n)
class Solution {
    public int[] twoSum(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return nums;
        }
        int[] ret = new int[2];
        HashMap<Integer, Integer> viMap = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            Integer index = viMap.get(target - nums[i]);
            if (index == null) {
                viMap.put(nums[i], i);
            } else {
                ret[0] = index;
                ret[1] = i;
                break;
            }
        }
        return ret;
    }
}

// Hashtable: value to index list
// Time O(n) 
// Space O(n)
class Solution {
    public int[] twoSum(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return nums;
        }
        int[] ret = new int[2];
        HashMap<Integer, List<Integer>> vilMap = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            vilMap.putIfAbsent(nums[i], new ArrayList<>());
            vilMap.get(nums[i]).add(i);
        }
        for (int i = 0; i < nums.length; i++) {
            List<Integer> list = vilMap.get(target - nums[i]);
            if (list != null) {
                if (nums[i] != target - nums[i]) {
                    ret[0] = i;
                    ret[1] = list.get(0);
                    break;
                } else if (list.size() > 1) {
                    ret[0] = i;
                    ret[1] = list.get(1);
                    break;
                }
            }
        }
        return ret;
    }
}

// Two pointers
// Time O(nlogn)
// Space O(n)
class Solution {
    public int[] twoSum(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return nums;
        }
        
        int[] ret = new int[2];
        Data[] datas = toDatas(nums);
        Arrays.sort(datas, (d1, d2) -> d1.val - d2.val);
        
        int l = 0, r = datas.length - 1;
        while(l < r) {
            int sum = datas[l].val + datas[r].val;
            if (sum < target) {
                l++;
            } else if (sum > target) {
                r--;
            } else {
                ret[0] = datas[l].index;
                ret[1] = datas[r].index;
                break;
            }
        }
        return ret;
    }
    
    private Data[] toDatas(int[] nums) {
        Data[] ret = new Data[nums.length];
        for (int i = 0; i < nums.length; i++) {
            ret[i] = new Data(nums[i], i);
        }
        return ret;
    }
    
    private class Data {
        int val;
        int index;
        Data(int val, int index) {
            this.val = val;
            this.index = index;
        }
    }
}