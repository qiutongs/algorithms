
// Segment Tree
// Time: O(logN) - update, O(logN) - query
class NumArray {

    private final SegmentTree segmentTree;
    
    public NumArray(int[] nums) {
        if (nums == null || nums.length == 0) {
            segmentTree = null;
            return;
        }
        
        segmentTree = new SegmentTree(nums);
    }
    
    public void update(int i, int val) {
        segmentTree.update(i, val);
    }
    
    public int sumRange(int i, int j) {
        return segmentTree.query(i, j);
    }
}

class SegmentTree {
    private final int[] sums;
    private final int[] nums;
    
    public SegmentTree(int[] nums) {
        this.nums = nums;
        this.sums = new int[nums.length * 4];
        build(0, nums, 0, nums.length - 1);
    }
    
    public int query(int start, int end) {
        return query(0, 0, nums.length - 1, start, end);
    }
    
    public void update(int index, int val) {
        update(0, 0, nums.length - 1, index, val - nums[index]);
        nums[index] = val;
    }

    private int build(int node, int[] nums, int s, int e) {
        if (s == e) {
            sums[node] = nums[s];
            return sums[node];
        }
        int mid = (s + e) / 2;
        sums[node] = build(leftChild(node), nums, s, mid)
                   + build(rightChild(node), nums, mid + 1, e);
        return sums[node];
    }

    private int query(int node, int nodeStart, int nodeEnd, int qStart, int qEnd) {
        if (qStart <= nodeStart && qEnd >= nodeEnd) {
            return sums[node];
        }
        if (qStart > nodeEnd || qEnd < nodeStart) {
            return 0;
        }
        
        int mid = (nodeStart + nodeEnd) / 2;
        return query(leftChild(node), nodeStart, mid, qStart, qEnd)
             + query(rightChild(node), mid + 1, nodeEnd, qStart, qEnd);
        
    }

    private void update(int node, int nodeStart, int nodeEnd, int index, int diff) {
        sums[node] += diff;
        
        if (nodeStart == nodeEnd) {
            return;
        }
        
        int mid = (nodeStart + nodeEnd) / 2;
        if (index <= mid) {
            update(leftChild(node), nodeStart, mid, index, diff);
        } else {
            update(rightChild(node), mid + 1, nodeEnd, index, diff);
        }
    }
    
    private int leftChild(int index) {
        return 2 * index + 1;
    }
    private int rightChild(int index) {
        return 2 * index + 2;
    }
}

/**
 * Your NumArray object will be instantiated and called as such:
 * NumArray obj = new NumArray(nums);
 * obj.update(i,val);
 * int param_2 = obj.sumRange(i,j);
 */