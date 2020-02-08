// BST to maintain the max in window: O(nlogk)
class Solution {
    public int[] maxSlidingWindow(int[] nums, int k) {
        if (nums == null || nums.length == 0) {
            int[] ret = new int[0];
            return ret;
        }
        int[] ret = new int[nums.length - k + 1];
        TreeSet<Data> bst = new TreeSet<>();

        for (int r = 0; r < nums.length; r++) {
            int l = r - k + 1;
            bst.add(new Data(r, nums[r]));
            if (l >= 0) {
                if (l > 0) {
                    bst.remove(new Data(l - 1, nums[l - 1]));
                }
                ret[r - k + 1] = bst.first().val;
            }
        }
        return ret;
    }
    
    private class Data implements Comparable<Data> {
        int val;
        int index;
        Data(int index, int val) {
            this.val = val;
            this.index = index;
        }
        public int compareTo(Data other) {
            int valDiff = other.val - this.val;
            return valDiff == 0 ? this.index - other.index : valDiff;
        }
        public boolean equals(Data other) {
            return this.index == other.index;
        }
    }
}

// Monotonic decreasing queue: O(n)
// https://www.youtube.com/watch?v=2SXqBsTR6a8
class Solution {
    public int[] maxSlidingWindow(int[] nums, int k) {
        if (nums == null || nums.length == 0) {
            int[] ret = new int[0];
            return ret;
        }
        int[] ret = new int[nums.length - k + 1];
        Deque<Integer> dq = new LinkedList<>();

        for (int r = 0; r < nums.length; r++) {
            while(dq.isEmpty() == false && dq.getLast() < nums[r]) {
                dq.removeLast();
            }
            dq.addLast(nums[r]);
            int l = r - k + 1;
            if (l >= 0) {
                if (l > 0 && nums[l - 1] == dq.getFirst()) {
                    dq.removeFirst();
                }
                ret[l] = dq.getFirst();
            }
        }
        return ret;
    }
}