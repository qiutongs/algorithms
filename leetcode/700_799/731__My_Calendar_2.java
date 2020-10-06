// Boundary Count
// Time: O(N)
class MyCalendarTwo {
    private TreeMap<Integer, Integer> boundaryCounts = new TreeMap<>();
    
    public MyCalendarTwo() {
        
    }
    
    public boolean book(int start, int end) {
        boundaryCounts.compute(start, (k, v) -> v == null ? 1 : v + 1);
        boundaryCounts.compute(end, (k, v) -> v == null ? -1 : v - 1);
        
        int active = 0;
        for (int count: boundaryCounts.values()) {
            active += count;
            if (active >= 3) {
                boundaryCounts.compute(start, (k, v) -> v - 1);
                boundaryCounts.compute(end, (k, v) -> v + 1);
                return false;
            }
        }
        return true;
    }
}

/**
 * Your MyCalendarTwo object will be instantiated and called as such:
 * MyCalendarTwo obj = new MyCalendarTwo();
 * boolean param_1 = obj.book(start,end);
 */