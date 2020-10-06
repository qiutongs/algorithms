// Boundary Count
// Time: O(N)
class MyCalendarThree {
    private TreeMap<Integer, Integer> boundaryCounts = new TreeMap<>();
    
    public MyCalendarThree() {
        
    }
    
    public int book(int start, int end) {
        boundaryCounts.compute(start, (k, v) -> v == null ? 1 : v + 1);
        boundaryCounts.compute(end, (k, v) -> v == null ? -1 : v - 1);
        
        int ret = 0, active = 0;
        for (int count: boundaryCounts.values()) {
            active += count;
            ret = Math.max(ret, active);
        }
        return ret;
    }
}

/**
 * Your MyCalendarThree object will be instantiated and called as such:
 * MyCalendarThree obj = new MyCalendarThree();
 * int param_1 = obj.book(start,end);
 */