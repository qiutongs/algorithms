class Solution {
    public List<String> findMissingRanges(int[] nums, int lower, int upper) {
        List<String> ret = new ArrayList<>();
        if (nums.length == 0) {
            output(lower, upper, ret);
            return ret;
        }
        
        if (lower < nums[0]) {
            output(lower, nums[0] - 1, ret);
        }
        
        for (int i = 1; i < nums.length; i++) {
            int cur = nums[i], prev = nums[i - 1];
            if (prev == cur) {
                continue;
            }
            if (lower >= cur) {
                continue;
            } else if (lower < cur && lower > prev) {
                if (upper >= cur) {
                    output(lower, cur - 1, ret);
                } else {
                    output(lower, upper, ret);
                }
            } else {
                if (upper >= cur) {
                    output(prev + 1, cur - 1, ret);
                } else {
                    output(prev + 1, upper, ret);
                }
            }
        }
        
        if (upper > nums[nums.length - 1]) {
            output(nums[nums.length - 1] + 1, upper, ret);
        }
        
        return ret;
    }
    
    private void output(int l, int r, List<String> ret) {
        if (l == r) {
            ret.add(String.valueOf(l));
        } else if (r > l) {
            ret.add(l + "->" + r);
        }
    }
}

class Solution {
    public List<String> findMissingRanges(int[] nums, int lower, int upper) {
        List<String> ret = new ArrayList<>();
        if (nums.length == 0) {
            output(lower, upper, ret);
            return ret;
        }
        
        if (lower < nums[0]) {
            output(lower, nums[0] - 1, ret);
        }
        
        for (int i = 1; i < nums.length; i++) {
            int cur = nums[i], prev = nums[i - 1];
            if (prev == cur || lower >= cur) {
                continue;
            }
            int l = lower > prev ? lower : prev + 1;
            int r = upper < cur ? upper : cur - 1;
            output(l, r, ret);
        }
        
        if (upper > nums[nums.length - 1]) {
            output(nums[nums.length - 1] + 1, upper, ret);
        }
        
        return ret;
    }
    
    private void output(int l, int r, List<String> ret) {
        if (l == r) {
            ret.add(String.valueOf(l));
        } else if (r > l) {
            ret.add(l + "->" + r);
        }
    }
}