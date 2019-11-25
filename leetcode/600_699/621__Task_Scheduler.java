class Solution {
    public int leastInterval(char[] tasks, int n) {
        if (tasks == null || tasks.length == 0) {
            return 0;
        }
        int[] cfArr = new int[26];
        for (char task : tasks) {
            cfArr[toInt(task)]++;
        }
        
        int maxTaskFre = 0;
        for (int i = 0; i < cfArr.length; i++) {
            maxTaskFre = Math.max(maxTaskFre, cfArr[i]);
        }
        
        int maxTaskCount = 0;
        for (int i = 0; i < cfArr.length; i++) {
            if (cfArr[i] == maxTaskFre) {
                maxTaskCount++;
            }
        }
        
        int ret = (maxTaskFre - 1) * (n + 1) + maxTaskCount;
        return ret > tasks.length ? ret : tasks.length;
    }
    
    private int toInt(char c) {
        return (int)(c - 'A');
    }
}