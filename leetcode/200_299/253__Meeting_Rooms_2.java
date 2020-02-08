class Solution {
    public int minMeetingRooms(int[][] intervals) {
        if (intervals == null || intervals.length == 0) {
            return 0;
        }
        Arrays.sort(intervals, (i1, i2) -> (i1[0] - i2[0]));
        int n = intervals.length;
        int ret = 0;
        for (int i = 0; i < n; i++) {
            int depth = 1;
            for (int j = 0; j < i; j++) {
                if (intervals[j][1] > intervals[i][0]) {
                    depth++;
                }
            }
            ret = Math.max(ret, depth);
        }
        return ret;
    }
}

class Solution {
    public int minMeetingRooms(int[][] intervals) {
        if (intervals == null || intervals.length == 0) {
            return 0;
        }
        Arrays.sort(intervals, (i1, i2) -> (i1[0] - i2[0]));
        int n = intervals.length;
        int ret = 1;
        TreeNode root = null;
        for (int i = 0; i < n; i++) {
            int rank = getRank(root, intervals[i][0]);
            System.out.println(rank);
            ret = Math.max(ret, i + 1 - rank);
            root = insert(root, intervals[i][1]);
        }
        return ret;
    }
    
    private TreeNode insert(TreeNode root, int val) {
        if (root == null) {
            return new TreeNode(val);
        }
        if (val <= root.val) {
            root.left = insert(root.left, val);
            root.leftSize++;
        } else {
            root.right = insert(root.right, val);
        }
        return root;
    }
    
    private int getRank(TreeNode root, int val) {
        if (root == null) {
            return 0;
        }
        if (val < root.val) {
            return getRank(root.left, val);
        } else if (val > root.val) {
            return root.leftSize + getRank(root.right, val);
        } else {
            return root.leftSize;
        }
    }
    
    private class TreeNode {
        TreeNode left = null;
        TreeNode right = null;
        int leftSize = 1;
        int val;
        TreeNode(int val) {
            this.val = val;
        }
    }
}


// Heap is to store the end time to know the earliest room available
class Solution {
    public int minMeetingRooms(int[][] intervals) {
        if (intervals == null || intervals.length == 0) {
            return 0;
        }
        Arrays.sort(intervals, (i1, i2) -> (i1[0] - i2[0]));
        int n = intervals.length;
        int ret = 0;
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int i = 0; i < n; i++) {
            if (pq.isEmpty() == false && pq.peek() <= intervals[i][0]) {
                pq.poll();
                pq.offer(intervals[i][1]);
            } else {
                ret++;
                pq.offer(intervals[i][1]);
            }
        }
        return ret;
    }
}

class Solution {
    public int minMeetingRooms(int[][] intervals) {
        if (intervals == null || intervals.length == 0) {
            return 0;
        }
        int n = intervals.length;
        int[] starts = new int[n];
        int[] ends = new int[n];
        for (int i = 0; i < intervals.length; i++) {
            starts[i] = intervals[i][0];
            ends[i] = intervals[i][1];
        }
        Arrays.sort(starts);
        Arrays.sort(ends);
        int ret = 0, depth = 0;
        int i = 0, j = 0;
        while (i < n && j < n) {
            if (starts[i] < ends[j]) {
                depth++;
                i++;
            } else {
                depth--;
                j++;
            }
            ret = Math.max(ret, depth);
        }
        return ret;
    }
}

// Wrong: [[9,10],[4,9],[4,17]]
class Solution {
    public int minMeetingRooms(int[][] intervals) {
        if (intervals == null || intervals.length == 0) {
            return 0;
        }
        Arrays.sort(intervals, (i1, i2) -> i1[0] - i2[0]);
        Stack<int[]> stack = new Stack<>();
        int ret = 0;
        for (int[] interval : intervals) {
            if (stack.isEmpty() || interval[0] < stack.peek()[1]) {
                stack.push(interval);
                ret = Math.max(ret, stack.size());
            } else {
                while(stack.isEmpty() == false && interval[0] >= stack.peek()[1]) {
                    stack.pop();
                }
            }
        }
        return ret;
    }
}

// Wrong: [[1,2],[7,8],[1,6],[5,8],[1,6],[5,8]]
class Solution {
    public int minMeetingRooms(int[][] intervals) {
        if (intervals == null || intervals.length == 0) {
            return 0;
        }
        int n = intervals.length;
        int ret = 0;
        for (int i = 0; i < n; i++) {
            int[] cur = intervals[i].clone();
            int depth = 0;
            for (int j = 0; j < n; j++) {
                if (intersect(cur, intervals[j])) {
                    depth++;
                }
            }
            ret = Math.max(ret, depth);
        }
        return ret;
    }
    
    private boolean intersect(int[] cur, int[] interval) {
        if (cur[0] >= interval[1] || interval[0] >= cur[1]) {
            return false;
        } else {
            cur[0] = Math.max(cur[0], interval[0]);
            cur[1] = Math.min(cur[1], interval[1]);
            return true;
        }
    }
}
