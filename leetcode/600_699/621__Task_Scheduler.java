class Solution {
    public int leastInterval(char[] tasks, int n) {
        if (tasks == null || tasks.length == 0) {
            return 0;
        }
        int[] cfArr = new int[26];
        for (char task : tasks) {
            cfArr[task - 'A']++;
        }
        
        int maxFreq = 0;
        for (int i = 0; i < cfArr.length; i++) {
            maxFreq = Math.max(maxFreq, cfArr[i]);
        }
        
        int maxTaskCount = 0;
        for (int i = 0; i < cfArr.length; i++) {
            if (cfArr[i] == maxFreq) {
                maxTaskCount++;
            }
        }
        
        int ret = (maxFreq - 1) * (n + 1) + maxTaskCount;
        return ret > tasks.length ? ret : tasks.length;
    }
}