class Solution {
    public int[][] insert(int[][] intervals, int[] newInterval) {
        List<int[]> ret = new ArrayList<>();
        boolean addedNew = false;
        for (int[] interval : intervals) {
            if (newInterval[0] > interval[1]) {
                ret.add(interval);
            } else if (newInterval[1] < interval[0]) {
                if (addedNew == false) {
                    ret.add(newInterval);
                    addedNew = true;
                }
                ret.add(interval);
            } else {
                newInterval[0] = Math.min(newInterval[0], interval[0]);
                newInterval[1] = Math.max(newInterval[1], interval[1]);
            }
        }
        if (addedNew == false) {
            ret.add(newInterval);
        }
        return ret.toArray(new int[0][]);
    }
}

class Solution {
    public int[][] insert(int[][] intervals, int[] newInterval) {
        if (intervals == null || newInterval == null) {
            return null;
        }
        
        int i = 0;
        while(i < intervals.length && intervals[i][1] < newInterval[0]) {
            i++;
        }
        
        int j = intervals.length - 1;
        while(j >= 0 && intervals[j][0] > newInterval[1]) {
            j--;
        }
        
        if (i < intervals.length) {
            newInterval[0] = Math.min(newInterval[0], intervals[i][0]);
        }
        if (j >= 0) {
            newInterval[1] = Math.max(newInterval[1], intervals[j][1]);
        }
        
        int[][] ret = new int[i + intervals.length - j][2];
        for (int k = 0; k < ret.length; k++) {
            if (k < i) {
                ret[k] = intervals[k];
            } else if (k == i) {
                ret[k] = newInterval;
            } else {
                ret[k] = intervals[k + j - i];
            }
        }
        return ret;
    }
}