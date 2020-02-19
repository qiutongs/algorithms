// DFS + memo + pre-compute next compatible index
// Time: O(NlogN)
// Space: O(N)
class Solution {
    public int jobScheduling(int[] startTime, int[] endTime, int[] profit) {
        int n = startTime.length;
        Interval[] intervals = new Interval[n];
        for (int i = 0; i < n; i++) {
            intervals[i] = new Interval(startTime[i], endTime[i], profit[i]);
        }
        
        Arrays.sort(intervals);
        
        int[] nextIndexes = new int[n];
        for (int i = 0; i < n; i++) {
            nextIndexes[i] = nextCompatible(intervals, i);
        }
        
        Integer[] memo = new Integer[n];
        return dfs(intervals, 0, nextIndexes, memo);
    }
    
    private int dfs(Interval[] intervals, int offset, int[] nextIndexes, Integer[] memo) {
        if (offset == intervals.length) {
            return 0;
        }
        if (memo[offset] != null) {
            return memo[offset];
        }
        int ret = 0;
        for (int i = offset; i < intervals.length; i++) {
            ret = Math.max(ret, intervals[i].profit + dfs(intervals, nextIndexes[i], nextIndexes, memo));
        }
        memo[offset] = ret;
        return ret;
    }
    
    private int nextCompatible(Interval[] intervals, int index) {
        int l = index + 1, r = intervals.length - 1;
        while(l <= r) {
            int mid = (l + r) / 2;
            if (intervals[mid].start >= intervals[index].end) {
                r = mid - 1;
            } else {
                l = mid + 1;
            }
        }
        return l;
    }
    
    private class Interval implements Comparable<Interval> {
        int start;
        int end;
        int profit;
        Interval(int start, int end, int profit) {
            this.start = start;
            this.end = end;
            this.profit = profit;
        }
        public int compareTo(Interval other) {
            return this.start - other.start;
        }
    }
}

// DFS + memo
// Time: O(N^2)
// Time Limit Exceeded
class Solution {
    public int jobScheduling(int[] startTime, int[] endTime, int[] profit) {
        int n = startTime.length;
        Interval[] intervals = new Interval[n];
        for (int i = 0; i < n; i++) {
            intervals[i] = new Interval(startTime[i], endTime[i], profit[i]);
        }
        
        Arrays.sort(intervals, (i1, i2) -> i1.start - i2.start);
        
        Integer[] memo = new Integer[n];
        return dfs(intervals, 0, memo);
    }
    
    private int dfs(Interval[] intervals, int offset, Integer[] memo) {
        if (offset == intervals.length) {
            return 0;
        }
        if (memo[offset] != null) {
            return memo[offset];
        }
        int ret = 0;
        for (int i = offset; i < intervals.length; i++) {
            int nextOffset = nextCompatible(intervals, i);
            ret = Math.max(ret, intervals[i].profit + dfs(intervals, nextOffset, memo));
        }
        memo[offset] = ret;
        return ret;
    }
    
    private int nextCompatible(Interval[] intervals, int index) {
        int curEnd = intervals[index].end;
        while(index < intervals.length && intervals[index].start < curEnd) {
            index++;
        }
        return index;
    }
    
    private class Interval {
        int start;
        int end;
        int profit;
        Interval(int start, int end, int profit) {
            this.start = start;
            this.end = end;
            this.profit = profit;
        }
    }
}