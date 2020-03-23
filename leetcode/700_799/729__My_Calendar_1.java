class MyCalendar {
    private final TreeSet<int[]> intervals = new TreeSet<>((i1, i2) -> i1[0] - i2[0]);

    public MyCalendar() {
        
    }
    
    public boolean book(int start, int end) {
        int[] newIntvl = {start, end};
        int[] lIntvl = intervals.floor(newIntvl);
        if (lIntvl != null &&  newIntvl[0] < lIntvl[1]) {
            return false;
        }
        int[] rIntvl = intervals.ceiling(newIntvl);
        if (rIntvl != null &&  rIntvl[0] < newIntvl[1]) {
            return false;
        }
        intervals.add(newIntvl);
        return true;
    }
}