class Solution {
    public double[] sampleStats(int[] count) {
        double[] ret = { -1.0, -1.0, 0.0, 0.0, 0.0 };
        long sum = 0;
        int size = 0;
        int mode = 0;
        for (int i = 0; i < count.length; i++) {
            // min
            if (ret[0] == -1.0 && count[i] > 0) {
                ret[0] = (double)i;
            }
            // max
            if (count[i] > 0) {
                ret[1] = (double)i;
            }
            sum += i * count[i];
            size += count[i];
            
            if (count[i] > count[mode]) {
                mode = i;
            }
        }
        // mean
        ret[2] = (double)sum / (double)size;
        // mode
        ret[4] = (double)mode;
        
        // median
        int i = 0;
        int n = 0;
        while(i < count.length) {
            n += count[i];
            if (n > size / 2) {
                break;
            }
            i++;
        }
        if (size % 2 == 1) {
            ret[3] = (double)i;
        } else {
            int j = n - count[i] == size / 2 ? i - 1 : i;
            ret[3] = ((double)i + (double)j) / 2.0;
        }
        
        return ret;
    }
}