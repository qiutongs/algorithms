class Solution {
    public int shipWithinDays(int[] weights, int D) {
        if (weights == null || weights.length == 0 || D <= 0) {
            return 0;
        }
        int sum = 0;
        for (int weight : weights) {
            sum += weight;
        }
        int l = 0, r = sum;
        while(l <= r) {
            int mid = (l + r) / 2;
            if (shipable(weights, D, mid)) {
                r = mid - 1;
            } else {
                l = mid + 1;
            }
        }
        return l;
    }
    
    private boolean shipable(int[] weights, int D, int capacity) {
        int j = 0;
        for (int i = 1; i <= D; i++) {
            int curCap = capacity;
            while(curCap > 0) {
                if (j < weights.length && weights[j] <= curCap) {
                    curCap -= weights[j];
                    j++;
                } else {
                    break;
                }
            }
        }
        return j == weights.length;
    }
}