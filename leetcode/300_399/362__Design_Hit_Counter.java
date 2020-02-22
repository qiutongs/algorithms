/*
Design a hit counter which counts the number of hits received in the past 5 minutes.

Each function accepts a timestamp parameter (in seconds granularity) and you may assume that calls are being made to the system in chronological order (ie, the timestamp is monotonically increasing). You may assume that the earliest timestamp starts at 1.

It is possible that several hits arrive roughly at the same time.

Follow up: What if the number of hits per second could be very large? Does your design scale?
*/

// Straightfoward: record every hits, remove those past 5 minutes
// Time: O(N), amortized O(1)
// Space: O(N) - all hits within 5 minutes
class HitCounter {
    private static int MAX_DURATION = 300;
    private Deque<Integer> hitWindow = new LinkedList<>();
    
    /** Initialize your data structure here. */
    public HitCounter() {
        
    }
    
    /** Record a hit.
        @param timestamp - The current timestamp (in seconds granularity). */
    public void hit(int timestamp) {
        // This can be removed at the cost of high latency of getHits
        removeOldHits(timestamp);
        hitWindow.addLast(timestamp);
    }
    
    /** Return the number of hits in the past 5 minutes.
        @param timestamp - The current timestamp (in seconds granularity). */
    public int getHits(int timestamp) {
        removeOldHits(timestamp);
        return hitWindow.size();
    }
    
    private void removeOldHits(int timestamp) {
        while(hitWindow.isEmpty() == false && hitWindow.getFirst() <= timestamp - MAX_DURATION) {
            hitWindow.removeFirst();
        }
    }
}

// Fancy counting array: map timestamp to one of the 300 buckets
// Time: O(1)
// Space: O(1)
class HitCounter {
    private static int MAX_DURATION = 300;
    private int[] timestamps = new int[MAX_DURATION];
    private int[] counts = new int[MAX_DURATION];
    
    /** Initialize your data structure here. */
    public HitCounter() {
        
    }
    
    /** Record a hit.
        @param timestamp - The current timestamp (in seconds granularity). */
    public void hit(int timestamp) {
        if (timestamp == timestamps[timestamp % MAX_DURATION]) {
            counts[timestamp % MAX_DURATION]++;
        } else {
            timestamps[timestamp % MAX_DURATION] = timestamp;
            counts[timestamp % MAX_DURATION] = 1;
        }
    }
    
    /** Return the number of hits in the past 5 minutes.
        @param timestamp - The current timestamp (in seconds granularity). */
    public int getHits(int timestamp) {
        int ret = 0;
        for (int i = 0; i < MAX_DURATION; i++) {
            if (timestamp - timestamps[i] < MAX_DURATION) {
                ret += counts[i];
            }
        }
        return ret;
    }
}

/**
 * Your HitCounter object will be instantiated and called as such:
 * HitCounter obj = new HitCounter();
 * obj.hit(timestamp);
 * int param_2 = obj.getHits(timestamp);
 */
