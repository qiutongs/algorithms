class Solution1 {
    public int[] twoSum(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return nums;
        }
        
        int[] ret = new int[2];
        
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            Integer index = map.get(target - nums[i]);
            if (index == null) {
                map.put(nums[i], i);
            } else {
                ret[0] = index;
                ret[1] = i;
                break;
            }
        }
        return ret;
    }
}

class Solution2 {
    public int[] twoSum(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return nums;
        }
        
        int[] ret = new int[2];
        
        Node[] nodes = new Node[nums.length];
        
        for (int i = 0; i < nums.length; i++) {
            nodes[i] = new Node(nums[i], i);
        }
        
        Arrays.sort(nodes, (n1, n2) -> n1.val - n2.val);
        
        int l = 0, r = nodes.length - 1;
        while(l < r) {
            int sum = nodes[l].val + nodes[r].val;
            if (sum < target) {
                l++;
            } else if (sum > target) {
                r--;
            } else {
                ret[0] = nodes[l].index;
                ret[1] = nodes[r].index;
                break;
            }
        }
        return ret;
    }
    
    private class Node {
        int val;
        int index;
        Node(int val, int index) {
            this.val = val;
            this.index = index;
        }
    }
}