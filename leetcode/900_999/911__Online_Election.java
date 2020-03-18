class TopVotedCandidate {
    private final int[] leadings;
    private final int[] times;
    
    public TopVotedCandidate(int[] persons, int[] times) {
        this.times = times;
        this.leadings = new int[times.length];
        
        int curMax = 0;
        int curLead = -1;
        
        int[] votes = new int[persons.length];
        for (int i = 0; i < times.length; i++) {
            votes[persons[i]]++;
            if (votes[persons[i]] >= curMax) {
                curMax = votes[persons[i]];
                curLead = persons[i];
            }
            leadings[i] = curLead;
        }
    }
    
    public int q(int t) {
        int l = 0, r = times.length - 1;
        while(l <= r) {
            int mid = (l + r) / 2;
            if (times[mid] <= t) {
                l = mid + 1;
            } else {
                r = mid - 1;
            }
        }
        return leadings[r];
    }
}

/**
 * Your TopVotedCandidate object will be instantiated and called as such:
 * TopVotedCandidate obj = new TopVotedCandidate(persons, times);
 * int param_1 = obj.q(t);
 */