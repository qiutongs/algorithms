class Solution {
    public int[][] merge(int[][] intervals) {
        if (intervals == null || intervals.length == 0) {
            return intervals;
        }
        List<int[]> ret = new ArrayList<>();
        Arrays.sort(intervals, (i1, i2) -> i1[0] - i2[0]);
        
        int[] cur = intervals[0].clone();
        for (int i = 1; i < intervals.length; i++) {
            if (isOverlap(cur, intervals[i])) {
                merge(cur, intervals[i]);
            } else {
                ret.add(cur);
                cur = intervals[i].clone();
            }
        }
        ret.add(cur);
        return ret.toArray(new int[0][]);
    }
    
    private boolean isOverlap(int[] cur, int[] i) {
        return i[0] <= cur[1];
    }
    
    private void merge(int[] cur, int[] i) {
        cur[1] = Math.max(cur[1], i[1]);
    }
}