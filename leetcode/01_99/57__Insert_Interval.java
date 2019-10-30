class Solution {
    public int[][] insert(int[][] intervals, int[] newInterval) {
        if (intervals == null || newInterval == null) {
            return null;
        }

        Integer[][] intervalArr = toBoxedArray(intervals);
        List<Integer[]> list = new ArrayList<>();
        
        // 1. left part
        int i = 0;
        for (; i < intervalArr.length; i++) {
            if (intervalArr[i][1] >= newInterval[0]) {
                break;
            }
            list.add(intervalArr[i]);
        }
        
        // 2. merged part
        for (; i < intervalArr.length; i++) {
            if (intervalArr[i][0] > newInterval[1]) {
                break;
            }
            newInterval[0] = Math.min(newInterval[0], intervalArr[i][0]);
            newInterval[1] = Math.max(newInterval[1], intervalArr[i][1]);
        }
        
        Integer[] boxedNew = {newInterval[0], newInterval[1]};
        list.add(boxedNew);
        
        // 3. right part
        for (; i < intervals.length; i++) {
            list.add(intervalArr[i]);
        }

        return toArray(list);
    }
    
    private Integer[][] toBoxedArray(int[][] intervals) {
        Integer[][] ret = new Integer[intervals.length][2];
        for (int i = 0; i < intervals.length; i++) {
            ret[i][0] = intervals[i][0];
            ret[i][1] = intervals[i][1];
        }
        return ret;
    }
    
    private int[][] toArray(List<Integer[]> list) {
        int[][] ret = new int[list.size()][2];
        for (int j = 0; j < list.size(); j++) {
            ret[j][0] = list.get(j)[0];
            ret[j][1] = list.get(j)[1];
        }
        return ret;
    }
}