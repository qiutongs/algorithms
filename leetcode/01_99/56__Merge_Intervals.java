class Solution {
    public int[][] merge(int[][] intervals) {
        if (intervals == null || intervals.length == 0) {
            return intervals;
        }
        List<int[]> ret = new ArrayList<>();
        Arrays.sort(intervals, (i1, i2) -> i1[0] - i2[0]);
        // To not modify original array: intervals[0].clone()
        int[] cur = intervals[0];
        for (int i = 1; i < intervals.length; i++) {
            // If overlap
            if (cur[1] >= intervals[i][0]) {
                // Merge
                cur[1] = Math.max(cur[1], intervals[i][1]);
            } else {
                ret.add(cur);
                cur = intervals[i];
            }
        }
        ret.add(cur);
        return ret.toArray(new int[0][]);
    }
}