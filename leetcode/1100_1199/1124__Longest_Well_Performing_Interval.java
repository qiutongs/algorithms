class Solution {
    public int longestWPI(int[] hours) {
        if (hours == null || hours.length == 0) {
            return 0;
        }
        
        int[] preSum = new int[hours.length + 1];
        for (int i = 0; i < hours.length; i++) {
            preSum[i + 1] = preSum[i] + (hours[i] > 8 ? 1 : -1);
        }
        
        if (preSum[hours.length] > 0) {
            return hours.length;
        }

        int ret = 0;
        HashMap<Integer, Integer> viMap = new HashMap<>();
        for (int i = 0; i < preSum.length; i++) {
            if (viMap.containsKey(preSum[i] - 1)) {
                ret = Math.max(ret, i - viMap.get(preSum[i] - 1));
            } else if (viMap.containsKey(preSum[i]) == false) {
                viMap.put(preSum[i], i);
            }
        }
        return ret;
    }
}