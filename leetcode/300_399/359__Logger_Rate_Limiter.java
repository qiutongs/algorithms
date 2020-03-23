// Time: shouldPrintMessage - O(1)
class Logger {
    private HashSet<String>[] win = new HashSet[10];
    private int[] timestamps = new int[10];
    
    /** Initialize your data structure here. */
    public Logger() {
        for (int i = 0; i < 10; i++) {
            win[i] = new HashSet<String>();
        }
    }
    
    /** Returns true if the message should be printed in the given timestamp, otherwise returns false.
        If this method returns false, the message will not be printed.
        The timestamp is in seconds granularity. */
    public boolean shouldPrintMessage(int timestamp, String message) {
        boolean ret = true;
        for (int i = 0; i < 10; i++) {
            if (timestamp - timestamps[i] < 10) {
                if (win[i].contains(message)) {
                    ret = false;
                }
            } else {
                win[i].clear();
            }
        }
        if (ret) {
            timestamps[timestamp % 10] = timestamp;
            win[timestamp % 10].add(message);
        }
        return ret;
    }
}

/**
 * Your Logger object will be instantiated and called as such:
 * Logger obj = new Logger();
 * boolean param_1 = obj.shouldPrintMessage(timestamp,message);
 */