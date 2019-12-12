class Solution {
    public int longestWPI(int[] hours) {
        if (hours == null || hours.length == 0) {
            return 0;
        }
        
        int ret = 0;
        int[] preSum = new int[hours.length + 1];
        for (int i = 1; i < preSum.length; i++) {
            preSum[i] = preSum[i - 1] + (hours[i - 1] > 8 ? 1 : -1);
        }

        if (preSum[preSum.length - 1] > 0) {
            return hours.length;
        }
        
        HashMap<Integer, Integer> viMap = new HashMap<>();
        for (int i = 0; i < preSum.length; i++) {
            if (viMap.containsKey(preSum[i]) == false) {
                viMap.put(preSum[i], i);
            }
        }
        
        for (int i = 1; i < preSum.length; i++) {
            Integer idx = viMap.get(preSum[i] - 1);
            if (idx != null && idx < i) {
                ret = Math.max(ret, i - idx);
            }
        }
        
        return ret;
    }
}